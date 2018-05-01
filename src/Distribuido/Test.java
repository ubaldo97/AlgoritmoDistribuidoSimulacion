/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Distribuido;

import java.util.ArrayList;

/**
 *
 * @author ruben
 */
public class Test {
    public static void main(String args[]){
     Recurso cont = new Recurso();
     ArrayList<Integer> orden= new ArrayList();
        
        Proceso p1 = new Proceso(cont,1,orden);
        Proceso p2 = new Proceso(cont,2,orden);
        Proceso p3 = new Proceso(cont,3,orden);
//        Proceso p4 = new Proceso(cont,4,orden);
//        Proceso p5 = new Proceso(cont,5,orden);
//        Proceso p6 = new Proceso(cont,6,orden);
        p1.start();
        p2.start();
        p3.start();
//        p4.start();
//        p5.start();
//        p6.start();
    }
}
