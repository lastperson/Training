package com.company;

import javax.lang.model.element.Name;

/**
 * Created by Admin on 01.04.15.
 */



public class Glass {

    public String Name;
    public int Filled;
    public int buffer;
    public static String Material = "paper";
    public static int Height = 10;

    public Glass (String Name) {

        Filled = 0;
        this.Name = Name;

    }





    public void fill (int Filled){

    this.Filled = this.Filled + Filled;
    if (this.Filled > 100){

        this.Filled = 100;
        }
    }

    public void empty (){

        buffer = Filled; //remember how much it was filled
        Filled = 0; //empty the glass
        System.out.println("Glass is empty but it was " + buffer + "% full");
    }

    public static int Volume (int Height){

        int Volume = Height * 20;
        return Volume;
    }

    public static void main(String[]args){

        Glass glass1 = new Glass("MyGlass1");
        glass1.fill(50);
        System.out.println(glass1.Filled);
        glass1.fill(60);
        System.out.println(glass1.Filled);
        glass1.empty();
        System.out.println(glass1.Filled);

    }

}




