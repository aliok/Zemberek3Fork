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
    Map<BoundaryState, BoundaryState> boundaryStateMap = Maps.newHashMap();
    List<RootState> rootStates = new ArrayList<RootState>();
    TurkishAlphabet alphabet = new TurkishAlphabet();

    public LexiconGraphGenerator(List<LexiconItem> lexicon) {
        this.lexicon = lexicon;
    }

    public void generate() {
        for (LexiconItem lexiconItem : lexicon) {
            if (hasModifierAttribute(lexiconItem)) {
                rootStates.addAll(Arrays.asList(generateModifiedRootStates(lexiconItem)));
            } else {
                rootStates.add(generateRootState(lexiconItem));
            }
        }
    }

    private RootState generateRootState(LexiconItem lexiconItem) {
        BoundaryState boundaryState = generateBoundaryState(lexiconItem);
        return new RootState(lexiconItem.root, lexiconItem, boundaryState, true);
    }

    private BoundaryState generateBoundaryState(LexiconItem lexiconItem) {
        BoundaryState boundaryState = new BoundaryState(lexiconItem.primaryPos);
        for (MorphemicAttribute attribute : lexiconItem.attributes) {
            if (boundaryStateAttributes.contains(attribute))
                boundaryState.add(attribute);
        }
        return createIfNotExist(boundaryState);
    }

    private BoundaryState createIfNotExist(BoundaryState boundaryState) {
        // weird. but i needed to do this.
        if (!boundaryStateMap.containsKey(boundaryState)) {
            boundaryStateMap.put(boundaryState, boundaryState);
        } else boundaryState = boundaryStateMap.get(boundaryState);
        return boundaryState;
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

    private RootState[] generateModifiedRootStates(LexiconItem lexiconItem) {

        BoundaryState modifiedState = new BoundaryState(lexiconItem.primaryPos);
        BoundaryState originalState = new BoundaryState(lexiconItem.primaryPos);

        TurkicLetterSequence modifiedSequence = new TurkicLetterSequence(lexiconItem.root, alphabet);

        for (MorphemicAttribute attribute : lexiconItem.attributes) {

            // transfer necessary attributes to boundary state.
            if (boundaryStateAttributes.contains(attribute)) {
                modifiedState.add(attribute);
                originalState.add(attribute);
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
                    modifiedState.add(ExpectsVowel);
                    modifiedState.remove(LastLetterVoicelessStop);
                    originalState.add(ExpectsConsonant);
                    break;
                case Doubling:
                    modifiedState.add(ExpectsVowel);
                    originalState.add(ExpectsConsonant);
                    modifiedSequence.append(modifiedSequence.lastLetter());
                    break;
                case LastVowelDrop:
                    modifiedState.add(ExpectsVowel);
                    originalState.add(ExpectsConsonant);
                    modifiedSequence.delete(modifiedSequence.length() - 2);
                    break;
                case ProgressiveVowelDrop:
                    modifiedState.addExclusiveSuffix(TurkishSuffixes.Prog_Iyor);
                    originalState.addRestrictedsuffix(TurkishSuffixes.Prog_Iyor);
                    modifiedSequence.delete(modifiedSequence.length() - 1);
                    break;
                case StemChange:
                    return handleSpecialStems(lexiconItem);
                default:
                    break;
            }

        }
        originalState = createIfNotExist(originalState);
        modifiedState = createIfNotExist(modifiedState);

        RootState[] states = new RootState[2];
        states[0] = new RootState(lexiconItem.root, lexiconItem, originalState, true);
        states[1] = new RootState(modifiedSequence.toString(), lexiconItem, modifiedState, false);
        return states;

    }

    // handle stem changes demek-diyecek , beni-bana
    private RootState[] handleSpecialStems(LexiconItem lexiconItem) {
        if ((lexiconItem.root.equals("ye") || lexiconItem.root.equals("de")) && lexiconItem.primaryPos == Verb) {
            RootState[] states = new RootState[3];
            BoundaryState originalState = new BoundaryState(
                    lexiconItem.primaryPos, LastVowelFrontal, LastLetterVowel);
            originalState.addRestrictedsuffix(Prog_Iyor);
            states[0] = new RootState(lexiconItem.root, lexiconItem, originalState, true);

            BoundaryState progressiveState = new BoundaryState(lexiconItem.primaryPos, LastVowelFrontal);
            progressiveState.addExclusiveSuffix(Prog_Iyor);
            states[1] = new RootState(lexiconItem.root.substring(0, 1), lexiconItem, progressiveState, false);

            BoundaryState modifiedState = originalState.clone();
            modifiedState.addExclusiveSuffix(Fut_yAcAk, Opt_yA);
            if (lexiconItem.root.equals("ye")) {
                modifiedState.addExclusiveSuffix(AfterDoing_yIp);
            }
            states[2] = new RootState(lexiconItem.root.substring(0, 1) + "i", lexiconItem, modifiedState, false);
            return states;
        } else if ((lexiconItem.root.equals("ben") || lexiconItem.root.equals("sen")) && lexiconItem.primaryPos == Pronoun) {
            RootState[] states = new RootState[2];
            BoundaryState originalState = new BoundaryState(lexiconItem.primaryPos, LastVowelFrontal);
            originalState.addRestrictedsuffix(Dat_yA);
            states[0] = new RootState(lexiconItem.root, lexiconItem, originalState, true);
            BoundaryState modifiedState = originalState.clone();
            modifiedState.addExclusiveSuffix(Dat_yA);
            if (lexiconItem.root.equals("ben"))
                states[1] = new RootState("ban", lexiconItem, modifiedState, false);
            else
                states[1] = new RootState("san", lexiconItem, modifiedState, false);
            return states;
        }
        throw new IllegalArgumentException("Lexicon Item with special stem change cannot be handled:" + lexiconItem);
    }

    public static void main(String[] args) throws IOException {
        List<LexiconItem> items = TurkishLexiconLoader.load(new File("test/data/dev-lexicon.txt"));
        LexiconGraphGenerator generator = new LexiconGraphGenerator(items);
        generator.generate();
        for (BoundaryState bs : generator.boundaryStateMap.values()) {
            System.out.println(bs);
        }

        for (RootState rootState : generator.rootStates) {
            System.out.println(rootState);
        }
    }


}
