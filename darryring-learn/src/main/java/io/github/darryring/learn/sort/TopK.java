package io.github.darryring.learn.sort;

/**
 * @author darryring
 **/
public class TopK {
    private int n, k;
    private double[][] nSelK = null;    // Only k*(n-k) elements are eventually computed.

    public TopK(int n, int k) {
        this.n = n;
        this.k = k;
    }

    /**
     * @return Return this for convenience.
     */
    public TopK compute() {
        nSelK = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                nSelK[i][j] = -1.0;
            }
        }
        counts(n, k);
        return this;
    }

    /**
     * Average counts of comparison when selecting top k from n.
     * Only k >= 1 and n >= k is valid.
     */
    private double counts(int n, int k) {
        double r = nSelK[n - 1][k - 1];
        if (r < 0) {    // Get in first time. Compute.
            r = 0.0;
            if (n == 1 && k == 1) {
            } else {
                for (int i = 1; i < k; i++) {
                    r += counts(n - i, k - i);
                }
                for (int i = k + 1; i <= n; i++) {
                    r += counts(i - 1, k);
                }
                r = r / n + n - 1;
            }
            nSelK[n - 1][k - 1] = r;
            //   System.out.printf("Got %d-%d: %f: \n", n, k, r);
        }
        return r;
    }

    public void printCol() {
        for (int i = k; i <= n; i++) {
            System.out.printf("%d\t%d\t%f \n", i, k, nSelK[i - 1][k - 1]);
        }
    }

    public void printAll() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.printf("%.2f\t", nSelK[i - 1][j - 1]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int n = 100, k = 5;
        new TopK(n, k).compute().printCol();
    }
}
