/*
 * Lisans bilgisi icin lutfen proje ana dizinindeki zemberek2-lisans.txt dosyasini okuyunuz.
 */

package net.zemberek.araclar.turkce;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class TurkceMetinOkuyucu {


    public String[] MetinOku(String path) {
        String[] kelimeler;
        TurkishTokenStream stream = new TurkishTokenStream(path, "utf-8");
        List<String> list = new ArrayList<String>();
        while (true) {
            String str = stream.nextWord();
            if (str == null) break;
            list.add(str);
        }
        System.out.println(" Metin kelime sayisi: " + list.size());
        kelimeler = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            kelimeler[i] = list.get(i);
        }
        return kelimeler;
    }

}
