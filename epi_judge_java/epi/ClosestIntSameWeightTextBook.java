package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ClosestIntSameWeightTextBook {
    @EpiTest(testDataFile = "closest_int_same_weight.tsv")
    public static long closestIntSameBitCount(long x) {
        for (int i = 0; i < 63; i++) {
            if ((x >>> i & 1) != (x >>> (i + 1) & 1)) {
                return x ^ (((1 << i)) | (1 << (i + 1)));
            }
        }
        throw new IllegalArgumentException("Bad input");
    }

    public static void main(String[] args) {
        ClosestIntSameWeightTextBook.closestIntSameBitCount(39698800462691l);
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ClosestIntSameWeight.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
