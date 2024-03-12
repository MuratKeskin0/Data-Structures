/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.objects;

/**
 *
 * @author murat
 */
public class Example2Main {

    public static void main(String[] args) {
        /*Example2LinkedList list = new Example2LinkedList();
        list.addNewNodeFromTail(25);
        list.addNewNodeFromTail(520);
        list.printList();*/
        Example2LinkedList list2 = new Example2LinkedList();
        list2.addNewNodeFromHead(50);
        list2.addNewNodeFromHead(80);
        //list2.printListFromHead();
        list2.addNewNodeFromTail(40);
        list2.addNewNodeFromHead(96);

        System.out.println(list2.head.data);
        System.out.println(list2.tail.data);

    }
}
