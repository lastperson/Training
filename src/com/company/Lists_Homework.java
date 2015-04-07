package com.company;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DimaN on 04.04.2015.
 */
public class Lists_Homework {

    public static void print (List<String> s) {

        for (int i = 0; i < s.size(); i++) {

            System.out.println(s.get(i));
        }
    }

    public static boolean areListsEqual (List s, List f){

          if (s.equals(f)){
              return true;
          }

        return false;
    }

    public static void print (String[] a){

        for (int i = 0; i < a.length; i++) {

            System.out.print(a[i]);

        }

        System.out.println();

    }

    public static boolean isEqual(String[]a, List<String> s){

        boolean result = false;
        if (a.length == s.size()) {

            for (int i = 0; i < a.length; i++) {

                if (a[i] == s.get(i)){
                    System.out.println(a[i] + " = " + s.get(i));
                    result = true;
                    } else result = false;
            }
        }

        return result;
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
        System.out.println(areListsEqual(s, f));

        f.add("d");
        System.out.println(areListsEqual(s, f));

        s.add("b");
        System.out.println(areListsEqual(s, f));

        List<String> s1 = new ArrayList<String>();
        List<String> s2 = new ArrayList<String>();
        System.out.println(areListsEqual(s1, s2));

        String[] a = {"a", "b", "c", "d", "b"};
        print(a);

        System.out.println(isEqual(a, s));

    }

}
