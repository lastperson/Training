package com.company;

import javax.lang.model.element.Name;

/**
 * Created by Admin on 01.04.15.
 */



public class Glass {

    public String Name;
    public int Filled;
    public int buffer;
    public static String Material = "Paper";
    public static int Height = 10;

    public Glass (String Name) {

        Filled = 0;
        this.Name = Name;

    }

    public void fill (int Filled){

    this.Filled = this.Filled + Filled;
    if (this.Filled > 100){
        this.Filled = 100;
        System.out.println("Glass is overflown, excessive water removed");
        }

    }

    public int percentFilled (){

        return this.Filled;

    }

    public int empty (){

        buffer = Filled; //remember how much it was filled
        Filled = 0; //empty the glass
        System.out.println("Glass is empty but it was " + buffer + "% full");
        return buffer;
    }

    public static int Volume (int Height){

        int Volume = Height * 20;
        return Volume;
    }

    public String Name (){

        return Name;

    }

    public static void main(String[]args){

        Glass s = new Glass("Super");
        Glass k = new Glass("Bigger");
        System.out.println("s: " + s.Name);
        System.out.println("k: " + k.Name);
        System.out.println("Glass: " + Glass.Material);
        System.out.println("Glass: " + Glass.Height);
        System.out.println("s: " + s.percentFilled());
        s.fill(30);
        System.out.println("s: " + s.percentFilled());

        int v = s.empty();

        System.out.println("Emptied: " + v);
        System.out.println("k: " + k.percentFilled());

        k.fill(v);
        System.out.println("k: " + k.percentFilled());

        s.fill(k.empty());
        System.out.println("s: " + s.percentFilled());
        System.out.println("k: " + k.percentFilled());

        s.fill(50);
        System.out.println("s: " + s.percentFilled());

        s.fill(45);
        System.out.println("s: " + s.percentFilled());

        s.Name = "ReSuper";
        System.out.println("s: " + s.Name());
        System.out.println("k: " + k.Name());
        System.out.println("Volume of the glass 20 cm high: " + Glass.Volume(20));


    }

}




