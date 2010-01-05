package zemberek3.structure;

/**
 * This is a class for scoring an item. it contains the item and related double score value.
 * it is immutable providing T is immutable.
 */
public class ScoredItem<T> implements Comparable<ScoredItem> {

    private final T item;
    private final double score;

    public ScoredItem(T item, double score) {
        this.item = item;
        this.score = score;
    }

    public T getItem() {
        return item;
    }

    public double getScore() {
        return score;
    }

    public int compareTo(ScoredItem o) {
        if (o.score > score)
            return -1;
        if (o.score < score)
            return 1;
        return 0;
    }
}
