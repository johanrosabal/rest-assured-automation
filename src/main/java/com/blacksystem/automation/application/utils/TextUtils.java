package com.blacksystem.automation.application.utils;

import java.util.Random;

public class TextUtils {

    public static int getRandom(int[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }
}
