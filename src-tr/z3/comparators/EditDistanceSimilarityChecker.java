package z3.comparators;

import z3.util.Preconditions;

/**
 * A Similarity checker based on Damareu-levensthtein string distance algorithm.
 */
public class EditDistanceSimilarityChecker implements SimilarityChecker {

    final int similarityTreshold;

    public EditDistanceSimilarityChecker(int similarityTreshold) {
        Preconditions.checkArgument(
                similarityTreshold >= 0,
                "Similarity threshold value cannot be negative. current:" + similarityTreshold);
        this.similarityTreshold = similarityTreshold;
    }

    public boolean isSimilar(CharSequence t1, CharSequence t2) {
        final int n = t1.length(); //length of s
        final int m = t2.length(); //length of t

        if (Math.abs(n - m) > similarityTreshold)
            return false;

        int[][] d = new int[n + 1][m + 1]; // matrix
        int cost; // cost
        // Step 1
        if (n == 0) return m <= similarityTreshold;
        if (m == 0) return n <= similarityTreshold;
        // Step 2
        for (int i = 0; i <= n; d[i][0] = i++) ;
        for (int j = 0; j <= m; d[0][j] = j++) ;
        // Step 3
        for (int i = 1; i <= n; i++) {
            //Step 4
            for (int j = 1; j <= m; j++) {
                // Step 5
                cost = (t1.charAt(j - 1) == t2.charAt(i - 1) ? 0 : 1);
                // Step 6
                d[i][j] = Math.min(Math.min(d[i - 1][j] + 1, d[i][j - 1] + 1), d[i - 1][j - 1] + cost);
                // Step 6A
                if (i > 1 && j > 1) {
                    int trans = d[i - 2][j - 2] + 1;
                    if (t2.charAt(i - 2) != t1.charAt(j - 1)) trans++;
                    if (t2.charAt(i - 1) != t1.charAt(j - 2)) trans++;
                    if (d[i][j] > trans) d[i][j] = trans;
                }
            }
        }
        // Step 7
        return d[n][m] <= similarityTreshold;
    }
}
