/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.objects;

/**
 *
 * @author murat
 */
public class Example2LinkedList {

    Example2Node head = null;
    Example2Node tail = null;

    void addNewNodeFromTail(int data) {
        // creating temporary node here
        Example2Node temp = new Example2Node();
        temp.data = data;
        temp.next = null;

        if (head == null) {
            head = temp;
            tail = temp;
            System.out.println("new list is created and new node added to the end of the list");
        } else {
            tail.next = temp;
            tail = temp;
            System.out.println("new node added to the end of the list");
        }
    }

    void addNewNodeFromHead(int data) {
        // creating temporary node here
        Example2Node temp = new Example2Node();
        temp.data = data;
        temp.next = null;

        if (head == null) {
            head = temp;
            tail = temp;
            System.out.println("new list is created and new node added to in the beg. of the list");
        } else {
            head.next = temp;
            head = temp;
            System.out.println("new node added to in the beginning of the list");
        }
    }

    void printListFromTail() {
        if (head == null) {
            System.out.println("the list is empty");
        } else {
            Example2Node temp = head;
            while (temp != null) {
                System.out.println(temp.data);
                temp = temp.next;
            }
        }
    }
    
     void printListFromHead() {
        if (head == null) {
            System.out.println("the list is empty");
        } else {
            Example2Node temp = tail;
            while (temp != null) {
                System.out.println(temp.data);
                temp = temp.next;
            }
        }
    }

}
