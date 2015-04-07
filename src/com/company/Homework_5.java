package com.company;

/**
 * Created by DimaN on 07.04.2015.
 */
public class Homework_5 {

    public static String multiply(String s, int number){

       String result = "";

        for (int i = 0; i < number; i++) {

            result+= s;
        }

        return result;

        }

    public static String multiply(int number, String s){

        String result = "";

        for (int i = 0; i < number; i++) {

            result+= s;
        }

        return result;

    }

    public static String multiplyNewLine (String s, int number){

        String result = "";

        for (int i = 0; i< number; i++){

            result += s + "\n";
        }

        return result;

    }

    public static void doubleAndPrint(String s){

        System.out.println(s+s);

    }




    public static void main(String[] args) {

        int a = 7;
        int b = 3;
        String s = "Hello";
        String m = multiply(s, 5);
        System.out.println(m);
        System.out.println(multiply(s, b));
        System.out.println(multiplyNewLine("NewLine", a));
        System.out.println(multiplyNewLine("Hey, this one on new line", 3));
        doubleAndPrint("TwoTimes");
        doubleAndPrint("StillTwoTimes");
        System.out.println(multiply(3, "switch"));
        System.out.println(multiply(1, "switchAgain"));

    }
}
