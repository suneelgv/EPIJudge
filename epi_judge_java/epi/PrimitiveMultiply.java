package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class PrimitiveMultiply {
    @EpiTest(testDataFile = "primitive_multiply.tsv")
    public static long multiply(long x, long y) {
        if (y == 1) { //edge case
            return x;
        }
        if (x == 0 || y == 0) { //edge case
            return 0;
        }
        long result = 0;
        while (y != 1) {
            if ((y & 1) == 1) {
                result = sum(result, x);
                y = y & ~1;
            } else {
                x <<= 1;
                y >>= 1;
            }
        }
        return sum(result, x);
    }

    public static long sum(long a, long b) {
        long result = 0;
        long mask = 1;
        long carry = 0;
        while (mask != (1l << 63)) {
            if (((a & mask) ^ (b & mask)) == mask) { //0 and 1
                if (carry == 0) {
                    result |= mask;
                } else {
                    carry = 1;
                }
            } else {
                if ((a & mask) > 0) { // 1 and 1
                    if (carry == 0) {
                        carry = 1;
                    } else {
                        result |= mask;
                        carry = 1;
                    }
                } else {  // 0 and 0
                    if (carry == 1) {
                        result |= mask;
                        carry = 0; //carry applied
                    }
                }
            }
            mask <<= 1;
        }
        return result;
    }

    public static void main(String[] args) {
//        System.out
//        .println(PrimitiveMultiply.multiply(3,2));
//        System.out.println(PrimitiveMultiply.multiply(3,5));
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "PrimitiveMultiply.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());

    }
}
