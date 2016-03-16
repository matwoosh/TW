/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2.zad3;

public class Shop {
    
    Semaphore s1;
    Semaphore s2;

    Shop(Semaphore s1, Semaphore s2) {
       this.s1 = s1;
       this.s2 = s2;
    }
    
    public void in(){
        s1.P();
        s2.V();
    }
    
    public void out(){
        s2.P();
        s1.V();
    }
}