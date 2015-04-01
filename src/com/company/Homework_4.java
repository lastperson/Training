package com.company;

/**
 * Created by Dimonaz on 31.03.2015.
 */
public class Homework_4 {

    public static void main(String[] args) {

//1
        String t ="Hello Class";
        System.out.println(t);

//2

        String ts = ", and Student!";
        System.out.println(t + ts);

//3

        String[] u = {"0","1","Under key one but three","3","4","Under key five"};
        System.out.println(u[5]);

//4
        System.out.println(u[3-1]);

//5
        int x = 8;
        for (; x < 11; x++) {
            System.out.println(x);
        }

//6

        String[] s = {"How", "are", "you"};
        for (int i = 0; i < 3; i++) {
            System.out.println(s[i]);
        }

//7
        int b = 25;
        int[] m = {10, 20, 25, 30};
        int i = 0;
        while (i < 4) {
            if (m[i] == b) {
                break;
            }
            System.out.println(m[i]);
            i++;
        }

//8
        int d = 10;
        int[] n = {10, 20, 25, 30};
        int j = 0;
        while (j < 4) {
            if (n[j] == d) {
                j++;
                continue;
            }
            System.out.println(n[j]);
            j++;
        }

//9

        TestClass c = new TestClass();
        c.say();

//10
        c.update("Goodbye, World");
        c.say();

//11

        ProDoor door = new ProDoor(2000, 900, true, false);
        String doorInfo = door.toString();
        System.out.println("Door info:\n" + doorInfo);


        ProDoor door2 = new ProDoor(1900, 800, false, true);
        System.out.println("Door info:\n" + door2.toString());

        door.lock();
        System.out.println(door.isLocked);

        door.close();
        System.out.println(door.isClosed);

        System.out.println("Door info:\n" + door.toString());

        door.unlock();
        System.out.println(door.isLocked);

        door.close();
        System.out.println(door.isClosed);

        door.lock();

        door2.open();
        System.out.println(door2.isOpened);

        door2.unlock();

        door2.open();
        System.out.println(door2.isOpened);

        System.out.println("Door info:\n" + door2.toString());

    }
}
