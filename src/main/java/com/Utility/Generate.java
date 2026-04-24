package com.Utility;

import java.util.Random;

public class Generate {
    public static final Random rand = new Random();

    public static String nextIP() {
        return rand.nextInt(0, 255) + "."
                + rand.nextInt(0, 255) + "."
                + rand.nextInt(0, 255) + "."
                + rand.nextInt(0, 255);
    }
}
