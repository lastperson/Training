package com.company;

/**
 * Created by Dimonaz on 31.03.2015.
 */
public class ProDoor {

    public int height;
    public int width;
    public boolean isOpened;
    public boolean isLocked;
    public boolean isClosed;

    public ProDoor (int h, int w, boolean isOpened, boolean isLocked){

        height = h;
        width = w;
        this.isOpened = isOpened;
        this.isLocked = isLocked;

    }

    public String toString(){

        /*System.out.println("Size:"+ height + "x" + width);
        System.out.println("Opened: "+ isOpened);
        System.out.println("Locked: "+ isLocked);
*/
        String stats = "Size:"+ height + "x" + width + "\n" + "Opened: "+ isOpened + "\n" + "Locked: "+ isLocked;
        return stats;

    }

    public void lock(){

        System.out.println("Locking...");
        isLocked = true;
        System.out.println("Door is locked!");

    }

    public void close(){

        System.out.println("Closing...");

        if (isLocked){

            System.out.println("Lock is in the way, cannot close");
            isClosed = false;
        } else {

            isClosed = true;
            System.out.println("Door is closed!");
        }

    }

    public void unlock(){

        System.out.println("Unlocking...");
        this.isLocked = false;
        System.out.println("Door is unlocked!");
    }

    public void open(){

        System.out.println("Opening...");
        if (isLocked){

            System.out.println("Door is locked, cannot open!");
            isOpened = false;
        } else {

            isOpened = true;
            System.out.println("Door is opened!");
        }

    }

}
