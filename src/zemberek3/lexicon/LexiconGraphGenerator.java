package zemberek3.lexicon;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import zemberek3.structure.TurkicLetter;
import zemberek3.structure.TurkicSeq;
import zemberek3.structure.TurkishAlphabet;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static zemberek3.lexicon.RootAttr.*;
import static zemberek3.lexicon.RootAttr.Voicing;
import static zemberek3.lexicon.PrimaryPos.*;
import static zemberek3.lexicon.TurkishSuffixes.*;

/**
 * This class gets a list of Lexicon Items and builds the lexicon part of the main graph.
 */
public class LexiconGraphGenerator {
    List<LexiconItem> lexicon;
    Map<BoundaryNode, BoundaryNode> boundaryStateMap = Maps.newHashMap();
    List<RootNode> rootNodes = new ArrayList<RootNode>();
    TurkishAlphabet alphabet = new TurkishAlphabet();

    public LexiconGraphGenerator(List<LexiconItem> lexicon) {
        this.lexicon = lexicon;
    }

    public void generate() {
        for (LexiconItem lexiconItem : lexicon) {
            if (hasModifierAttribute(lexiconItem)) {
                rootNodes.addAll(Arrays.asList(generateModifiedRootStates(lexiconItem)));
            } else {
                rootNodes.add(generateRootState(lexiconItem));
            }
        }
    }

    public List<RootNode> getRootNodes() {
        return rootNodes;
    }

    private RootNode generateRootState(LexiconItem lexiconItem) {
        BoundaryNode boundaryNode = generateBoundaryState(lexiconItem);
        return new RootNode(lexiconItem.root, lexiconItem, boundaryNode, true);
    }

    private BoundaryNode generateBoundaryState(LexiconItem lexiconItem) {
        BoundaryNode boundaryNode = new BoundaryNode(lexiconItem.primaryPos, calculateRootAttributes(lexiconItem));
        return createIfNotExist(boundaryNode);
    }

    private BoundaryNode createIfNotExist(BoundaryNode boundaryNode) {
        // weird. but i needed to do this.
        if (!boundaryStateMap.containsKey(boundaryNode)) {
            boundaryStateMap.put(boundaryNode, boundaryNode);
        } else boundaryNode = boundaryStateMap.get(boundaryNode);
        return boundaryNode;
    }

    public boolean hasModifierAttribute(LexiconItem item) {
        for (RootAttr attribute : item.attrs.getAsList(RootAttr.class))
            if (modifiers.contains(attribute))
                return true;
        return false;
    }

    Set<RootAttr> modifiers = Sets.newHashSet(
            Doubling,
            LastVowelDrop,
            ProgressiveVowelDrop,
            Voicing,
            StemChange,
            CompoundP3sg
    );

    private List<PhonAttr> calculateRootAttributes(LexiconItem lexiconItem) {
        List<PhonAttr> attributesList = new ArrayList<PhonAttr>();
        TurkicSeq sequence = new TurkicSeq(lexiconItem.root, alphabet);
        // general phonetic attributes.
        if (sequence.lastVowel().isRoundedVowel())
            attributesList.add(PhonAttr.LastVowelRounded);
        else
            attributesList.add(PhonAttr.LastVowelUnrounded);
        if (sequence.lastVowel().isFrontalVowel()) {
            attributesList.add(PhonAttr.LastVowelFrontal);
        } else if (lexiconItem.hasAttribute(RootAttr.InverseHarmony)) {
            // saat, takat
            attributesList.add(PhonAttr.LastVowelFrontal);
        } else
            attributesList.add(PhonAttr.LastVowelBack);
        if (sequence.lastLetter().isVowel()) {
            // elma
            attributesList.add(PhonAttr.LastLetterVowel);
        } else
            attributesList.add(PhonAttr.LastLetterConsonant);
        if (sequence.lastLetter().isVoiceless() && sequence.lastLetter().isStopConsonant()) {
            // kitap
            attributesList.add(PhonAttr.LastLetterVoicelessStop);
        } else
            attributesList.add(PhonAttr.LastLetterNotVoicelessStop);
        return attributesList;
    }


    private RootNode[] generateModifiedRootStates(LexiconItem lexiconItem) {

        BoundaryNode modifiedNode = new BoundaryNode(lexiconItem.primaryPos, calculateRootAttributes(lexiconItem));
        BoundaryNode originalNode = new BoundaryNode(lexiconItem.primaryPos, calculateRootAttributes(lexiconItem));

        TurkicSeq modifiedSeq = new TurkicSeq(lexiconItem.root, alphabet);

        for (RootAttr attribute : lexiconItem.attrs.getAsList(RootAttr.class)) {

            // generate other boundary attributes and modified root state.
            switch (attribute) {

                case Voicing:
                    TurkicLetter last = modifiedSeq.lastLetter();
                    TurkicLetter modifiedLetter = alphabet.voice(last);
                    if (modifiedLetter == null) {
                        throw new LexiconGenerationException("Voicing letter is not proper in:" + lexiconItem);
                    }
                    if (lexiconItem.root.endsWith("nk"))
                        modifiedLetter = TurkishAlphabet.L_g;
                    modifiedSeq.changeLetter(modifiedSeq.length() - 1, modifiedLetter);
                    modifiedNode.getForwardExpectations().set(PhonAttr.FirstLetterVowel);
                    modifiedNode.getForwardAttributes().reset(PhonAttr.LastLetterVoicelessStop);
                    originalNode.getForwardExpectations().set(PhonAttr.FirstLetterConsonant);

                    break;
                case Doubling:
                    modifiedNode.getForwardExpectations().set(PhonAttr.FirstLetterVowel);
                    originalNode.getForwardExpectations().set(PhonAttr.FirstLetterConsonant);
                    modifiedSeq.append(modifiedSeq.lastLetter());
                    break;
                case LastVowelDrop:
                    modifiedNode.getForwardExpectations().set(PhonAttr.FirstLetterVowel);
                    originalNode.getForwardExpectations().set(PhonAttr.FirstLetterConsonant);
                    if (lexiconItem.primaryPos == Verb) {
                        modifiedNode.addExclusiveSuffix(TurkishSuffixes.Pass_In);
                        originalNode.addRestrictedsuffixes(TurkishSuffixes.Prog_Iyor);
                    }
                    modifiedSeq.delete(modifiedSeq.length() - 2);
                    break;
                case ProgressiveVowelDrop:
                    modifiedNode.addExclusiveSuffix(TurkishSuffixes.Prog_Iyor);
                    originalNode.addRestrictedsuffixes(TurkishSuffixes.Prog_Iyor);
                    modifiedSeq.delete(modifiedSeq.length() - 1);
                    break;
                case StemChange:
                    return handleSpecialStems(lexiconItem);
                case CompoundP3sg:
                    return handleP3sgCompounds(lexiconItem);
                default:
                    break;
            }

        }
        originalNode = createIfNotExist(originalNode);
        modifiedNode = createIfNotExist(modifiedNode);

        RootNode[] nodes = new RootNode[2];
        nodes[0] = new RootNode(lexiconItem.root, lexiconItem, originalNode, true);
        nodes[1] = new RootNode(modifiedSeq.toString(), lexiconItem, modifiedNode, false);
        return nodes;

    }

    // handle stem changes demek-diyecek , beni-bana
    private RootNode[] handleSpecialStems(LexiconItem lexiconItem) {
        if ((lexiconItem.root.equals("ye") || lexiconItem.root.equals("de")) && lexiconItem.primaryPos == Verb) {
            RootNode[] nodes = new RootNode[3];
            BoundaryNode originalNode =
                    new BoundaryNode(Verb, PhonAttr.LastVowelFrontal, PhonAttr.LastLetterVowel)
                            .addRestrictedsuffixes(Prog_Iyor, Fut_yAcAk, Opt_yA);
            nodes[0] = new RootNode(lexiconItem.root, lexiconItem, originalNode, true);

            BoundaryNode progressiveNode = new BoundaryNode(Verb, PhonAttr.LastVowelFrontal).addExclusiveSuffix(Prog_Iyor);
            nodes[1] = new RootNode(lexiconItem.root.substring(0, 1), lexiconItem, progressiveNode, false);

            BoundaryNode modifiedNode = new BoundaryNode(
                    originalNode.getPrimaryPos(),
                    originalNode.getForwardAttributes(),
                    originalNode.getForwardExpectations())
                    .addExclusiveSuffix(Fut_yAcAk, Opt_yA);
            // modification rule does not apply for some suffixes for "demek". like deyip, not deyip
            if (lexiconItem.root.equals("ye")) {
                modifiedNode.addExclusiveSuffix(AfterDoing_yIp);
            }
            nodes[2] = new RootNode(lexiconItem.root.substring(0, 1) + "i", lexiconItem, modifiedNode, false);
            return nodes;
        } else if ((lexiconItem.root.equals("ben") || lexiconItem.root.equals("sen")) && lexiconItem.primaryPos == Pronoun) {
            RootNode[] nodes = new RootNode[2];

            BoundaryNode originalNode = new BoundaryNode(Pronoun, PhonAttr.LastVowelFrontal).addRestrictedsuffixes(Dat_yA);
            nodes[0] = new RootNode(lexiconItem.root, lexiconItem, originalNode, true);

            BoundaryNode modifiedNode = new BoundaryNode(
                    originalNode.getPrimaryPos(),
                    originalNode.getForwardAttributes(),
                    originalNode.getForwardExpectations())
                    .addExclusiveSuffix(Dat_yA);
            if (lexiconItem.root.equals("ben"))
                nodes[1] = new RootNode("ban", lexiconItem, modifiedNode, false);
            else
                nodes[1] = new RootNode("san", lexiconItem, modifiedNode, false);
            return nodes;
        }
        throw new IllegalArgumentException("Lexicon Item with special stem change cannot be handled:" + lexiconItem);
    }

    private RootNode[] handleP3sgCompounds(LexiconItem lexiconItem) {

        RootNode[] nodes = new RootNode[2];
        // atkuyruGu -
        BoundaryNode originalState = new BoundaryNode(lexiconItem.primaryPos, calculateRootAttributes(lexiconItem))
                .addExclusiveSuffix(TurkishSuffixes.NOUN_CASE)
                .addExclusiveSuffix(TurkishSuffixes.COPULAR)
                .addExclusiveSuffix(TurkishSuffixes.NOUN_POSS);
        nodes[0] = new RootNode(lexiconItem.root, lexiconItem, originalState, true);

        TurkicSeq modifiedSeq = new TurkicSeq(lexiconItem.root, alphabet);
        // TODO: phonetic properties needs to be found again nicely.
        BoundaryNode modifiedNode = new BoundaryNode(lexiconItem.primaryPos, calculateRootAttributes(lexiconItem)).
                addExclusiveSuffix(Pl_lAr, With_lI, P3pl_lArI);
        modifiedNode.getForwardAttributes().reset(PhonAttr.LastLetterVowel);

        String modified = modifiedSeq.eraseLast().toString();
        // for cases compund's last word has voicing. atkuruGu
        if (lexiconItem.hasAttribute(Voicing)) {
            // hack for cases denizbiyoloGu - denizbiyologlari
            if (modified.endsWith("o" + String.valueOf(TurkishAlphabet.C_gg)))
                modifiedSeq.eraseLast().append(TurkishAlphabet.L_g);
            else {
                TurkicLetter l = alphabet.devoice(modifiedSeq.lastLetter());
                modifiedSeq.changeLetter(modifiedSeq.length() - 1, l);
            }
            nodes[1] = new RootNode(modifiedSeq.toString(), lexiconItem, modifiedNode, false);
        } else {
            nodes[1] = new RootNode(modified, lexiconItem, modifiedNode, false);
        }
        return nodes;
    }


    public static void main(String[] args) throws IOException {
        List<LexiconItem> items = new TurkishLexiconLoader().load(new File("test/data/dev-lexicon.txt"));
        LexiconGraphGenerator generator = new LexiconGraphGenerator(items);
        generator.generate();
        List<RootNode> rootNodes = generator.getRootNodes();
        for (RootNode rootNode : rootNodes) {
            System.out.println(rootNode);
        }
    }


}
