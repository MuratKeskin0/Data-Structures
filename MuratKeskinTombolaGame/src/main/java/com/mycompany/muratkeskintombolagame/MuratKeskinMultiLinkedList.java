/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.muratkeskintombolagame;

public class MuratKeskinMultiLinkedList {

    MuratKeskinMultiLinkedNode head;
    MuratKeskinMultiLinkedNode currentRowStart; // Pointer to the start of the current row

    public MuratKeskinMultiLinkedList() {
        this.head = null;
        this.currentRowStart = null;
    }

    public void append(int data) {
        MuratKeskinMultiLinkedNode newNode = new MuratKeskinMultiLinkedNode(data);
        if (head == null) {
            head = newNode;
            currentRowStart = newNode;
        } else {
            MuratKeskinMultiLinkedNode temp = currentRowStart;
            int count = 0;
            while (temp.next != null && count < 4) {
                temp = temp.next;
                count++;
            }
            if (count < 4) { 
                temp.next = newNode;
            } else {
                if (currentRowStart.child == null) { // If this is the first node of a new row
                    currentRowStart.child = newNode; // Establish the new row
                } else { // If we are already in a new row, find the correct starting point
                    MuratKeskinMultiLinkedNode rowStart = currentRowStart;
                    while (rowStart.child != null) {
                        rowStart = rowStart.child; // Move to the last row
                    }
                    rowStart.child = newNode; // Append the new node as the start of an even newer row
                }
                currentRowStart = newNode; // Update the start of the new current row
            }
        }
    }

    public void printList() {
        MuratKeskinMultiLinkedNode rowStart = head;
        while (rowStart != null) {
            MuratKeskinMultiLinkedNode temp = rowStart;
            while (temp != null) {
                System.out.print(temp.data + " ");
                temp = temp.next;
            }
            System.out.println(); // Move to the next line at the end of a row
            rowStart = rowStart.child; // Move to the start of the next row
        }
    }
    
    public void markNumber(int number) {
        MuratKeskinMultiLinkedNode temp = head;
        while (temp != null) {
            MuratKeskinMultiLinkedNode rowNode = temp;
            while (rowNode != null) {
                if (rowNode.data == number) {
                    rowNode.marked = true;
                    return; // Number found and marked; exit the method
                }
                rowNode = rowNode.next;
            }
            temp = temp.child; // Move to the next row
        }
    }

}
