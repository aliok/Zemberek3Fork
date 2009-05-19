package zemberek3.structure;

public class ScoredItem<T> {

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
}
