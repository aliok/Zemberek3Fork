package zemberek3.structure;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

/**
 * this is a helper class for scoring an item. it contains the item and related double score value.
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
