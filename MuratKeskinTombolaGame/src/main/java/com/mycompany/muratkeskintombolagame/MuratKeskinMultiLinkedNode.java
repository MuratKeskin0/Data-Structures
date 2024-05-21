/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.muratkeskintombolagame;

public class MuratKeskinMultiLinkedNode {
    int data;
    MuratKeskinMultiLinkedNode next;    
    MuratKeskinMultiLinkedNode child;
    boolean marked;

    public MuratKeskinMultiLinkedNode(int data) {
        this.data = data;
        this.next = null;
        this.child = null;
        this.marked = false;
    }
}
