package zemberek3.cache;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


public class SimpleCache<K, V> {

    final Map<K, CacheEntry> map = new ConcurrentHashMap<K, CacheEntry>();
    final int capasity;

    public SimpleCache(int capasity) {
        if (capasity < 1)
            throw new IllegalArgumentException("Capacity cannot be less than 1");
        this.capasity = capasity;
    }

    private class CacheEntry {
        int hitCount;
        final V value;

        private CacheEntry(V value) {
            this.value = value;
            this.hitCount = 0;
        }
    }

    boolean containsKey(K key) {
        return map.containsKey(key);
    }

    void add(K key, V value) {
        if (!map.containsKey(key)) {
            if (map.size() == capasity) {
                retire(capasity / 2);
            }
            map.put(key, new CacheEntry(value));
        }
    }

    V check(K key) {
        if (map.containsKey(key)) {
            final CacheEntry ce = map.get(key);
            if (ce.hitCount < Integer.MAX_VALUE)
                ce.hitCount++;
            return ce.value;
        } else
            return null;
    }

    void flush() {
        map.clear();
    }

    private void retire(int amount) {
        List<K> sorted = getSortedList();
        int i = 0;
        for (K k : sorted) {
            if (i >= sorted.size() - amount) {
                map.remove(k);
            } else {
                map.get(k).hitCount = 1;
            }
            i++;
        }
    }

    /**
     * returns the Elements in a list sorted by count, descending..
     *
     * @return Elements in a list sorted by count, descending..
     */
    private List<K> getSortedList() {
        List<Map.Entry<K, CacheEntry>> l = new ArrayList<Map.Entry<K, CacheEntry>>(map.entrySet());
        Collections.sort(l, new CountComparator());
        List<K> list = new ArrayList<K>();
        for (Map.Entry<K, CacheEntry> entry : l) {
            list.add(entry.getKey());
        }
        return list;
    }


    private class CountComparator implements Comparator<Map.Entry<K, CacheEntry>> {
        public int compare(Map.Entry<K, CacheEntry> o1, Map.Entry<K, CacheEntry> o2) {
            return (o2.getValue().hitCount < o1.getValue().hitCount) ? -1 :
                    ((o2.getValue().hitCount > o1.getValue().hitCount) ? 1 : 0);
        }
    }

}

