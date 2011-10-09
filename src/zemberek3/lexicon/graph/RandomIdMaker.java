package zemberek3.lexicon.graph;

import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomIdMaker {
    Random random = new Random();
    Set<String> ids = Collections.synchronizedSet(new HashSet<String>());
    int letterCount;

    public RandomIdMaker(int letterCount) {
        this.letterCount = letterCount;
    }

    public String getNew() {
        StringBuilder sb = new StringBuilder(letterCount);
        for (int i = 0; i < letterCount; i++) {
            sb.append((char) (random.nextInt(25) + 'A'));
        }
        String res = sb.toString();
        if (!ids.contains(res)) {
            ids.add(res);
            return res;
        }
        return getNew();
    }

    public String getNew(String toAppend) {
        return toAppend + "_" + getNew();
    }
}
