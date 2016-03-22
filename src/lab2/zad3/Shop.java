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
    
    public void getIn(int id){
        s1.P(id);
        s2.V(id);
    }
    
    public void getOut(int id){
        s2.P(id);
        s1.V(id);
    }
}