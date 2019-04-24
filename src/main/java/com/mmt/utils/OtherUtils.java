package com.mmt.utils;

public class OtherUtils {
    public static int getPrice(String stringValue)
    {
        String str1 = stringValue.replace("Rs ", "");
        String str2 = str1.replace(",", "");
        int i = Integer.parseInt(str2);
        return i;
    }


    public static double getRandomIntegerBetweenRange(double min, double max){
        double x = (int)(Math.random()*((max-min)+1))+min;
        return x;
    }

    public static String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }

    public static String padLeft(String s, int n) {
        return String.format("%" + n + "s", s);
    }
}
