/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2.zad3;


public class Customer extends Thread{

    int n;
    Shop shop;

    public Customer(int n, Shop shop) {
        this.n = n;
        this.shop = shop;
    }
      
    public void run(){
        while(n>0){
            n--;
            shop.in();
            shop.out();
        }
    }
    
}