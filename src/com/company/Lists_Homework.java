package com.company;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DimaN on 04.04.2015.
 */
public class Lists_Homework {

    public static void print (List s){

        for (int i=0; i < s.size(); i++ ){

            System.out.println(s.get(i));
        }

    public static boolean isListEqual (List s, List f){

        for (int i = 0; i < s.size(); i++){

            if (s.get(i) == f.get(i)){;

            return true;
        }  { else return false;}

        }

    }

    public static void main(String[] args)  {

        List<String> s = new ArrayList<String>();
        s.add("a");
        s.add("b");
        s.add("c");
        s.add("d");
        print(s);

        List<String> f = new ArrayList<String>();
        f.add("a");
        f.add("b");
        f.add("c");
        f.add("d");
        System.out.println(isListsEqual(s, f));

    }

}
