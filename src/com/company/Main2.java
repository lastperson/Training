package com.company;

/**
 * Created by Admin on 25.03.15.
 */
public class Main2 {

    public static void main(String[] args) {

        //args[0] = "Hello";
        //args[1] = "ssdsf";

        /*if (args[0].equals("Hello")&&args[1].equals("Class")){
            System.out.println("Success");
        } else {
            System.out.println("Good bye!");
        }*/


        if (args[0].equals("Hello")){
            if (args[1].equals("Class")) {
                System.out.println("Success");
            } else {
                System.out.println("Good bye!");
            }
        } else {
            System.out.println("Good bye!");
        }

        int a = 10;

        while (a < 15){
            System.out.println("a is "+a);
            a++;
        }
    System.out.println("While is over!");

        int b = 10;

        while (true){
            System.out.println("b is "+b);
            b++;
            if (b >= 15){
                break;
            }
        }
    }


}
