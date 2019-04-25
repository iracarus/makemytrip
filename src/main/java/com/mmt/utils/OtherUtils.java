package com.mmt.utils;

public class OtherUtils {
    /**
     * Removes the string part i.e. 'Rs ' and the comma from the string
     * @param stringValue
     * @return int Amount converted into int
     */
    public static int getPrice(String stringValue)
    {
        String str1 = stringValue.replace("Rs ", "");
        String str2 = str1.replace(",", "");
        int i = Integer.parseInt(str2);
        return i;
    }

    /**
     * Returns a random number between the specified range
     * @param min
     * @param max
     * @return double random number
     */
    public static double getRandomIntegerBetweenRange(double min, double max){
        double x = (int)(Math.random()*((max-min)+1))+min;
        return x;
    }

    /**
     * pads the provided string with the given size to the right
     * @param s
     * @param n
     * @return String padded string
     */
    public static String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }

    /**
     * pads the provided string with the given size to the left
     * @param s
     * @param n
     * @return String padded string
     */
    public static String padLeft(String s, int n) {
        return String.format("%" + n + "s", s);
    }
}
