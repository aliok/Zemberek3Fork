package zemberek3.structure;

public class ScoredItem<T> implements Comparable<ScoredItem> {

    final T item;
    final double score;

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
