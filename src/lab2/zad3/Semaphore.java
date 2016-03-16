/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2.zad3;
import static jdk.nashorn.internal.objects.NativeError.printStackTrace;

public class Semaphore {
    int value;

    Semaphore(int initial){
        value = initial;
    }

    synchronized public void P() {
        while(value == 0){
            try{
                System.out.println("Cannot add another basket");
                wait();
            }
            catch(InterruptedException e){
                printStackTrace(e);
                System.out.println("Cannot add another basket!");
            }
        }
        value--;
        System.out.println("Semaphore_P: " + value);
    }
    synchronized public void V(){
        value++;
        System.out.println("Semaphore_V: " + value);
        notifyAll();
    }
}

