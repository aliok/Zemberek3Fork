package zemberek3.lexicon;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import zemberek3.structure.TurkicLetter;
import zemberek3.structure.TurkicLetterSequence;
import zemberek3.structure.TurkishAlphabet;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static zemberek3.lexicon.MorphemicAttribute.*;
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
        BoundaryNode boundaryNode = new BoundaryNode(lexiconItem.primaryPos);
        for (MorphemicAttribute attribute : lexiconItem.attributes) {
            if (boundaryStateAttributes.contains(attribute))
                boundaryNode.add(attribute);
        }
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
        for (MorphemicAttribute attribute : item.attributes)
            if (modifiers.contains(attribute))
                return true;
        return false;
    }

    Set<MorphemicAttribute> modifiers = Sets.newHashSet(
            Doubling,
            LastVowelDrop,
            ProgressiveVowelDrop,
            Voicing,
            StemChange
    );

    Set<MorphemicAttribute> boundaryStateAttributes = Sets.newHashSet(
            LastLetterVowel,
            LastVowelFrontal,
            LastVowelRounded,
            LastLetterVoicelessStop,
            ExpectsVowel,
            ExpectsConsonant,
            Aorist_A,
            Aorist_I
    );

    static Map<TurkicLetter, TurkicLetter> voicingMap = Maps.newHashMap();

    static {
        voicingMap.put(TurkishAlphabet.L_p, TurkishAlphabet.L_b);
        voicingMap.put(TurkishAlphabet.L_k, TurkishAlphabet.L_gg);
        voicingMap.put(TurkishAlphabet.L_cc, TurkishAlphabet.L_c);
        voicingMap.put(TurkishAlphabet.L_t, TurkishAlphabet.L_d);
        voicingMap.put(TurkishAlphabet.L_g, TurkishAlphabet.L_gg);
    }

    private RootNode[] generateModifiedRootStates(LexiconItem lexiconItem) {

        BoundaryNode modifiedNode = new BoundaryNode(lexiconItem.primaryPos);
        BoundaryNode originalNode = new BoundaryNode(lexiconItem.primaryPos);

        TurkicLetterSequence modifiedSequence = new TurkicLetterSequence(lexiconItem.root, alphabet);

        for (MorphemicAttribute attribute : lexiconItem.attributes) {

            // transfer necessary attributes to boundary state.
            if (boundaryStateAttributes.contains(attribute)) {
                modifiedNode.add(attribute);
                originalNode.add(attribute);
            }

            // generate other boundary attributes and modified root state.
            switch (attribute) {

                case Voicing:
                    TurkicLetter last = modifiedSequence.lastLetter();
                    TurkicLetter modifiedLetter = voicingMap.get(last);
                    if (!voicingMap.containsKey(last)) {
                        throw new LexiconGenerationException("Voicing letter is not proper in:" + lexiconItem);
                    }
                    if (lexiconItem.root.endsWith("nk"))
                        modifiedLetter = TurkishAlphabet.L_g;
                    modifiedSequence.changeLetter(modifiedSequence.length() - 1, modifiedLetter);
                    modifiedNode.add(ExpectsVowel);
                    modifiedNode.remove(LastLetterVoicelessStop);
                    originalNode.add(ExpectsConsonant);
                    break;
                case Doubling:
                    modifiedNode.add(ExpectsVowel);
                    originalNode.add(ExpectsConsonant);
                    modifiedSequence.append(modifiedSequence.lastLetter());
                    break;
                case LastVowelDrop:
                    modifiedNode.add(ExpectsVowel);
                    originalNode.add(ExpectsConsonant);
                    if (lexiconItem.primaryPos == Verb) {
                        modifiedNode.addExclusiveSuffix(TurkishSuffixes.Pass_In);
                        originalNode.addRestrictedsuffix(TurkishSuffixes.Prog_Iyor);
                    }
                    modifiedSequence.delete(modifiedSequence.length() - 2);
                    break;
                case ProgressiveVowelDrop:
                    modifiedNode.addExclusiveSuffix(TurkishSuffixes.Prog_Iyor);
                    originalNode.addRestrictedsuffix(TurkishSuffixes.Prog_Iyor);
                    modifiedSequence.delete(modifiedSequence.length() - 1);
                    break;
                case StemChange:
                    return handleSpecialStems(lexiconItem);
                default:
                    break;
            }

        }
        originalNode = createIfNotExist(originalNode);
        modifiedNode = createIfNotExist(modifiedNode);

        RootNode[] nodes = new RootNode[2];
        nodes[0] = new RootNode(lexiconItem.root, lexiconItem, originalNode, true);
        nodes[1] = new RootNode(modifiedSequence.toString(), lexiconItem, modifiedNode, false);
        return nodes;

    }

    // handle stem changes demek-diyecek , beni-bana
    private RootNode[] handleSpecialStems(LexiconItem lexiconItem) {
        if ((lexiconItem.root.equals("ye") || lexiconItem.root.equals("de")) && lexiconItem.primaryPos == Verb) {
            RootNode[] nodes = new RootNode[3];
            BoundaryNode originalNode =
                    new BoundaryNode(Verb, LastVowelFrontal, LastLetterVowel).addRestrictedsuffix(Prog_Iyor, Fut_yAcAk, Opt_yA);
            nodes[0] = new RootNode(lexiconItem.root, lexiconItem, originalNode, true);

            BoundaryNode progressiveNode =
                    new BoundaryNode(Verb, LastVowelFrontal).addExclusiveSuffix(Prog_Iyor);
            nodes[1] = new RootNode(lexiconItem.root.substring(0, 1), lexiconItem, progressiveNode, false);

            BoundaryNode modifiedNode = originalNode.clone().addExclusiveSuffix(Fut_yAcAk, Opt_yA);
            // modification rule does not apply for some suffixes for "demek". like deyip, not deyip
            if (lexiconItem.root.equals("ye")) {
                modifiedNode.addExclusiveSuffix(AfterDoing_yIp);
            }
            nodes[2] = new RootNode(lexiconItem.root.substring(0, 1) + "i", lexiconItem, modifiedNode, false);
            return nodes;
        } else if ((lexiconItem.root.equals("ben") || lexiconItem.root.equals("sen")) && lexiconItem.primaryPos == Pronoun) {
            RootNode[] nodes = new RootNode[2];
            BoundaryNode originalNode =
                    new BoundaryNode(Pronoun, LastVowelFrontal).addRestrictedsuffix(Dat_yA);
            nodes[0] = new RootNode(lexiconItem.root, lexiconItem, originalNode, true);
            BoundaryNode modifiedNode = originalNode.clone().addExclusiveSuffix(Dat_yA);
            if (lexiconItem.root.equals("ben"))
                nodes[1] = new RootNode("ban", lexiconItem, modifiedNode, false);
            else
                nodes[1] = new RootNode("san", lexiconItem, modifiedNode, false);
            return nodes;
        }
        throw new IllegalArgumentException("Lexicon Item with special stem change cannot be handled:" + lexiconItem);
    }

/*    private RootNode[] handleCompounds(LexiconItem lexiconItem) {
        RootNode[] nodes = new RootNode[2];
        BoundaryNode originalState =
                new BoundaryNode(Verb, LastVowelFrontal, LastLetterVowel).addRestrictedsuffix(Prog_Iyor, Fut_yAcAk, Opt_yA);
    }*/


    public static void main(String[] args) throws IOException {
        List<LexiconItem> items = TurkishLexiconLoader.load(new File("test/data/dev-lexicon.txt"));
        LexiconGraphGenerator generator = new LexiconGraphGenerator(items);
        generator.generate();
        List<RootNode> rootNodes = generator.getRootNodes();
        for (RootNode rootNode : rootNodes) {
            System.out.println(rootNode);
        }
    }


}
