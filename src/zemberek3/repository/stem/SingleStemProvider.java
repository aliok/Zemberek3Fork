package zemberek3.repository.stem;

import zemberek3.structure.Stem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * provides a single stem. this may be useful for parsing words with stems provided by user.
 * This class does not require a stem repository.
 */
public class SingleStemProvider implements StemProvider {

    private final List<Stem> stemList;

    public SingleStemProvider(Stem stem) {
        stemList = new ArrayList<Stem>(1);
        stemList.add(stem);
    }

    public List<Stem> find(CharSequence word) {
        return stemList;
    }

    public Iterator findAndIterate(CharSequence word) {
        return stemList.iterator();
    }
}
