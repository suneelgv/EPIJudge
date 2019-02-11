package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ClosestIntSameWeight {
    @EpiTest(testDataFile = "closest_int_same_weight.tsv")
    public static long closestIntSameBitCount(long x) {
        long mask = 1l;
        int zloc = -1, oloc = -1;
        int i = 0;
        while ((zloc == -1 || oloc == -1) && i < 32) {
            if ((x & mask) > 0) {
                oloc = i;
            } else {
                zloc = i;
            }
            i++;
            mask <<= 1;
        }
        return x ^ (1l << zloc | 1l << oloc);
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ClosestIntSameWeight.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
