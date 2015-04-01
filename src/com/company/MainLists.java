package com.company;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 01.04.15.
 */
public class MainLists {

    public static void main(String[] args) {

        List<String> f = new ArrayList<String>();

        f.add("Hello");
        f.add("Class");
        f.add(".");

        System.out.println(f.get(0));
        System.out.println(f.get(1));
        System.out.println(f.get(2));

        System.out.println(f.size());

    }
}
