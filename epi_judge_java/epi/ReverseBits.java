package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ReverseBits {
    @EpiTest(testDataFile = "reverse_bits.tsv")
    public static long reverseBits(long x) {
        for (int i = 0; i < 32; i++) {
            if (((x >>> i) & 1) != ((x >>> (63 - i)) & 1)) {
                long mask = (1l << i) | (1l << (63 - i)); //Note: Make sure you use 1l, otherwise Java will assume 1 as a 32bit integer, which when shifted over 31 times becomes negative
                // and when you do a XOR with long, JVM will up cast it long negative number, giving wrong results.
                x ^= mask;
            }
        }
        return x;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ReverseBits.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
