package zemberek3.lexicon;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import zemberek3.structure.TurkicLetter;
import zemberek3.structure.TurkicLetterSequence;
import zemberek3.structure.TurkishAlphabet;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * This class gets a list of Lexicon Items and builds the lexicon part of the main graph.
 */
public class LexiconGraphGenerator {
    List<LexiconItem> lexicon;
    Map<BoundaryState, BoundaryState> boundaryStateMap = Maps.newHashMap();
    Set<RootState> rootStates = new HashSet<RootState>();
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
            MorphemicAttribute.Doubling,
            MorphemicAttribute.LastVowelDrop,
            MorphemicAttribute.ProgressiveVowelDrop,
            MorphemicAttribute.Voicing,
            MorphemicAttribute.StemChange
    );

    Set<MorphemicAttribute> boundaryStateAttributes = Sets.newHashSet(
            MorphemicAttribute.LastLetterVowel,
            MorphemicAttribute.LastVowelFrontal,
            MorphemicAttribute.LastVowelRounded,
            MorphemicAttribute.LastLetterVoicelessStop,
            MorphemicAttribute.ExpectsProgressive,
            MorphemicAttribute.ExpectsNonProgressive,
            MorphemicAttribute.ExpectsVowel,
            MorphemicAttribute.ExpectsConsonant,
            MorphemicAttribute.ExpectsStemChanger,
            MorphemicAttribute.ExpectsNonStemChanger,
            MorphemicAttribute.Aorist_A,
            MorphemicAttribute.Aorist_I
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
                    modifiedState.add(MorphemicAttribute.ExpectsVowel);
                    modifiedState.remove(MorphemicAttribute.LastLetterVoicelessStop);
                    originalState.add(MorphemicAttribute.ExpectsConsonant);
                    break;
                case Doubling:
                    modifiedState.add(MorphemicAttribute.ExpectsVowel);
                    originalState.add(MorphemicAttribute.ExpectsConsonant);
                    modifiedSequence.append(modifiedSequence.lastLetter());
                    break;
                case LastVowelDrop:
                    modifiedState.add(MorphemicAttribute.ExpectsVowel);
                    originalState.add(MorphemicAttribute.ExpectsConsonant);
                    modifiedSequence.delete(modifiedSequence.length() - 2);
                    break;
                case ProgressiveVowelDrop:
                    modifiedState.add(MorphemicAttribute.ExpectsProgressive);
                    originalState.add(MorphemicAttribute.ExpectsNonProgressive);
                    modifiedSequence.delete(modifiedSequence.length() - 1);
                    break;
                case StemChange:
                    //TODO: this info may better be read from the lexicon file.
                    if (lexiconItem.root.equals("ben"))
                        modifiedSequence = new TurkicLetterSequence("ban", alphabet);
                    else if (lexiconItem.root.equals("sen"))
                        modifiedSequence = new TurkicLetterSequence("san", alphabet);
                    else if (lexiconItem.root.equals("de"))
                        modifiedSequence = new TurkicLetterSequence("di", alphabet);
                    else if (lexiconItem.root.equals("ye"))
                        modifiedSequence = new TurkicLetterSequence("yi", alphabet);
                    else throw new LexiconGenerationException("Unexpected stem change!");
                    modifiedState.add(MorphemicAttribute.ExpectsStemChanger);
                    originalState.add(MorphemicAttribute.ExpectsNonStemChanger);
                    break;
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

    public static void main(String[] args) throws IOException {
        List<LexiconItem> items = TurkishLexiconLoader.load(new File("test/data/dev-lexicon.txt"));
        LexiconGraphGenerator generator = new LexiconGraphGenerator(items);
        generator.generate();
        for(BoundaryState bs : generator.boundaryStateMap.values()) {
            System.out.println(bs);
        }

    }


}
