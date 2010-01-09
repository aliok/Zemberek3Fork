package zemberek3.repository.affix;

import java.util.Map;
import java.util.Set;

public interface EkKuralBilgisi {
    Set<Character> sesliKuralKarakterleri();

    Set<Character> harfKuralKarakterleri();

    Map<Character, EkUretimKurali> karakterKuralTablosu();

    EkUretimKurali harfEklemeKurali();
}
