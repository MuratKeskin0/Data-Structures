/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.objects;

/**
 *
 * @author murat
 */
public class Example1Class {
      private int i;
      private String name;
      public Example1Class next;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Example1Class(int i, String name) {
        this.i = i;
        this.name = name;
    }
    
    public Example1Class() {
        
    }
}
