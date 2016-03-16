/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2.zad3;


public class Main {
    public static void main(String[] args) {

        int value = 8;
        int threadsNo = 50;

        Semaphore s1 = new Semaphore(value);
        Semaphore s2 = new Semaphore(0);

        Shop shop = new Shop(s1, s2);

        for (int x = 0; x < threadsNo; x++) {
            Customer c = new Customer(100, shop);
            c.start();
        }
        
    }
    
}
