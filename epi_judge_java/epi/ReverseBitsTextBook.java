package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ReverseBitsTextBook {
    private static final int MASK_SIZE = 16;
    //The variables must be initialized before static block. After static block means, the vars are not initialized.
    static long cache[] = new long[2<<16];
    static int shortmask = 0xFFFF;


    static {
        buildCache();
    }

    @EpiTest(testDataFile = "reverse_bits.tsv")
    public static long reverseBits(long x) {
        return cache[(int) ((x >>> 3 * MASK_SIZE) & shortmask)] |
                cache[(int) ((x >>> 2 * MASK_SIZE) & shortmask)] << MASK_SIZE |
                cache[(int) ((x >>> 1 * MASK_SIZE) & shortmask)] << (MASK_SIZE * 2) |
                cache[(int) (x & shortmask)] << (MASK_SIZE * 3);
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ReverseBits.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
//        System.out.println(reverseBitsUncached((short) 21));
//        System.out.println(reverseBitsUncached((short) 54));
//        System.out.println(reverseBits(21));
//        System.out.println(Long.toBinaryString(reverseBits(21)));
    }


    static void buildCache() {
        for (int i = 0; i <= 2<<16-1; i++) {
            cache[i] = reverseBitsUncached(i);
        }
    }

    public static long reverseBitsUncached(long x) {
        for (int i = 0; i < 8; i++) {
            if (((x >>> i) & 1) != ((x >>> (15 - i)) & 1)) {
                long mask = (1 << i) | (1 << (15 - i));
                x ^= mask;
            }
        }
        return x;
    }
}
