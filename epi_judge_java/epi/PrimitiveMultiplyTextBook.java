package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class PrimitiveMultiplyTextBook {
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
        long carry = 0;
        long sumbit = 1;
        while (a != 0 || b != 0) { //#silly - do not use && here
            long ab = a & 1, bb = b & 1;
            long s = ab ^ bb ^ carry;
            carry = (ab & bb) | ((ab ^ bb) & carry);
            if (s == 1) {
                result |= sumbit;
            }
            sumbit <<= 1;
            a >>>= 1;
            b >>>= 1;
        }
        if (carry == 1) {
            result |= sumbit;
        }
        return result;
    }

    public static void main(String[] args) {
//        System.out
//        .println(PrimitiveMultiply.multiply(3,2));
//        System.out.println(PrimitiveMultiply.multiply(3,5));
        System.out.println(PrimitiveMultiplyTextBook.sum(3, 4));
        System.out.println(PrimitiveMultiplyTextBook.sum(1, 1));
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "PrimitiveMultiplyTextBoo.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());

    }
}
