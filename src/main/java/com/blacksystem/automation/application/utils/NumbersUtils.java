package com.blacksystem.automation.application.utils;

import java.util.Random;

public class NumbersUtils {


    public static int getRandomNumberInts(int min, int max){
        Random random = new Random();
        return random.ints(min,(max+1)).findFirst().getAsInt();
    }


    public static void getStreamOfRandomInts(int num) {
        Random random = new Random();
        random.ints(num).sorted().forEach(System.out::println);
    }

}
