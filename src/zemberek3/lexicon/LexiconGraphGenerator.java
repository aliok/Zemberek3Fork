package zemberek3.lexicon;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import zemberek3.structure.TurkicLetter;
import zemberek3.structure.TurkicLetterSequence;
import zemberek3.structure.TurkishAlphabet;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class gets a list of Lexicon Items and builds the lexicon part of the main morphologic graph.
 */
public class LexiconGraphGenerator {
    List<LexiconItem> lexicon;
    Set<BoundaryState> boundaryStates = new HashSet<BoundaryState>();
    Set<RootState> rootStates = new HashSet<RootState>();
    TurkishAlphabet alphabet = new TurkishAlphabet();

    public LexiconGraphGenerator(List<LexiconItem> lexicon) {
        this.lexicon = lexicon;
    }

    public void generate() {
        for (LexiconItem lexiconItem : lexicon) {
            if (hasModifierAttribute(lexiconItem)) {
                RootState modifiedState = generateModifiedRootState(lexiconItem);
                rootStates.add(modifiedState);
            }
            rootStates.add(generateRootState(lexiconItem));


        }
    }

    private RootState generateRootState(LexiconItem lexiconItem) {
        BoundaryState boundaryState = new BoundaryState(lexiconItem.primaryPos);
        for (MorphemicAttribute attribute : lexiconItem.attributes) {
            if (boundaryStateAttributes.contains(attribute))
                boundaryState.add(attribute);
        }
        if (!boundaryStates.contains(boundaryState)) {
            boundaryStates.add(boundaryState);
        }
        return new RootState(lexiconItem.root, lexiconItem, boundaryState, true);

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
            MorphemicAttribute.LastLetterVoicelessStop
    );


    static Map<TurkicLetter, TurkicLetter> voicingMap = Maps.newHashMap();

    static {
        voicingMap.put(TurkishAlphabet.L_p, TurkishAlphabet.L_b);
        voicingMap.put(TurkishAlphabet.L_k, TurkishAlphabet.L_gg);
        voicingMap.put(TurkishAlphabet.L_cc, TurkishAlphabet.L_c);
        voicingMap.put(TurkishAlphabet.L_t, TurkishAlphabet.L_d);
        voicingMap.put(TurkishAlphabet.L_g, TurkishAlphabet.L_gg);
    }

    private RootState generateModifiedRootState(LexiconItem lexiconItem) {

        TurkicLetterSequence sequence = new TurkicLetterSequence(lexiconItem.root, alphabet);

        for (MorphemicAttribute attribute : lexiconItem.attributes) {
            switch (attribute) {
                case Voicing:
                    TurkicLetter last = sequence.lastLetter();
                    if (!voicingMap.containsKey(last)) {
                        throw new LexiconGenerationException("Voicing letter is not proper in:" + lexiconItem);
                    }
                    sequence.changeLetter(sequence.length() - 1, voicingMap.get(last));
                    String modified = sequence.toString();


                    break;
                case Doubling:
                    break;
                case LastVowelDrop:
                    break;
                case ProgressiveVowelDrop:
                    break;
                case StemChange:
                    break;
                default:
                    break;
            }

        }

        return null;


    }


}
