package zemberek3.service;

import zemberek3.parser.syllable.SyllableParser;
import zemberek3.structure.TurkicLetter;
import zemberek3.structure.TurkicLetterSequence;
import zemberek3.structure.TurkishAlphabet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This syllable service is designed for extracting syllable information from Turkish words.
 * This class uses a strict syllable extraction algorithm, meaning that it cannot parse words like
 * "tren", "spor", "sfinks", "angstrom", "mavimtrak", "stetoskop" etc.
 *
 */
public class StrictTurkishSyllableService extends BaseSyllableService implements SyllableService {

    private final TurkishAlphabet alphabet = new TurkishAlphabet();
    private final SyllableParser<TurkicLetterSequence> parser = new TurkishSyllableParser();

    public List<String> syllables(String input) {
        return parser.parse(new TurkicLetterSequence(input, alphabet));
    }

    private class TurkishSyllableParser implements SyllableParser<TurkicLetterSequence> {

        public List<String> parse(TurkicLetterSequence input) {
            List<String> list = new ArrayList<String>();
            while (input.length() > 0) {
                int index = letterCountForLastSyllable(input);
                if (index < 0) {
                    return Collections.emptyList();
                }
                int basla = input.length() - index;
                list.add(input.toString(basla));
                input.clip(basla);
            }
            Collections.reverse(list);
            return list;
        }


        /**
         * Giren harf dizisinin sonunda mantikli olarak yer alan hecenin harf
         * sayisini dondurur.
         * Sistem, -trak ve benzeri harf dizilimine sahip kelimeleri hecelemiyor.
         *
         * @param word: turkce harf dizisi.
         * @return int, 1,2,3 ya da 4 donerse giris dizisinin dizinin sondan o
         *         kadarharfi heceyi temsil eder -1 donerse hecenin bulunamadigi
         *         anlamina gelir. sistem yabanci harf ya da isaretlerin oldugu ya
         *         da kural disi kelimeleri heceleyemez. (ornegin, three, what vs.)
         *         sistem su anda basta bulunan iki harf sessiz oldugu
         *         durumlari kabul etmekte ama buna kisitlama getirilmesi iyi olur.
         *         sadece "tr", "st", "kr" gibi girislere izin verilmeli
         */
        private int letterCountForLastSyllable(TurkicLetterSequence word) {

            final int boy = word.length();
            TurkicLetter harf = word.getLetter(boy - 1);
            TurkicLetter oncekiHarf = word.getLetter(boy - 2);

            if (boy == 0)
                return -1;

            if (harf.isVowel()) {
                //word sadece sesli.
                if (boy == 1)
                    return 1;
                //onceki harf sesli word="saa" ise son ek "a"
                if (oncekiHarf.isVowel())
                    return 1;
                //onceki harf sessiz ise ve word sadece 2 harf ise hece tum word. "ya"
                if (boy == 2)
                    return 2;

                TurkicLetter ikiOncekiHarf = word.getLetter(boy - 3);

                //ste-tos-kop -> ste
                if (!ikiOncekiHarf.isVowel() && boy == 3) {
                    return 3;
                }
                return 2;
            } else {

                // tek sessiz ile hece olmaz.
                if (boy == 1)
                    return -1;

                TurkicLetter ikiOncekiHarf = word.getLetter(boy - 3);
                if (oncekiHarf.isVowel()) {

                    //word iki harfli (el, al) ya da iki onceki harf sesli (saat)
                    if (boy == 2 || ikiOncekiHarf.isVowel())
                        return 2;

                    TurkicLetter ucOncekiHarf = word.getLetter(boy - 4);
                    // word uc harfli (kal, sel) ya da uc onceki harf sesli (kanat),
                    if (boy == 3 || ucOncekiHarf.isVowel())
                        return 3;

                    //word dort harfli ise yukaridaki kurallari gecmesi nedeniyle hecelenemez sayiyoruz.
                    // tren, strateji, krank, angstrom gibi kelimeler henuz hecelenmiyor.
                    if (boy == 4)
                        return -1;

                    TurkicLetter dortOncekiHarf = word.getLetter(boy - 5);
                    if (!dortOncekiHarf.isVowel())
                        return 3;
                    return 3;

                } else {

                    if (boy == 2 || !ikiOncekiHarf.isVowel())
                        return -1;
                    TurkicLetter ucOncekiHarf = word.getLetter(boy - 4);
                    if (boy > 3 && !ucOncekiHarf.isVowel())
                        return 4;
                    return 3;
                }

            }

        }

    }
}