/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.objects;

/**
 *
 * @author murat
 */
public class Example1 {

    public static void main(String[] args) {
     
        Example1Class A= new Example1Class();
        A.setI(15);
        A.setName("Class A");
        Example1Class B= new Example1Class();
        B.setI(25);
        B.setName("Class B");
        Example1Class C= new Example1Class();
        C.setI(50);
        C.setName("Class C");
        
        A.next=B;
        B.next=C;
        C.next=null;
        
        Example1Class temp = A;
        while( temp != null){
            System.out.println(temp.getI()+" "+temp.getName());
            temp= temp.next;
        }
        
    }
}
