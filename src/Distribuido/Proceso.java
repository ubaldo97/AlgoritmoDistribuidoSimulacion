/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Distribuido;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ruben
 */
public class Proceso extends Thread {
     private Recurso cont;
     private int id;
     private ArrayList<Integer> orden;

    public Proceso(Recurso c,int i,ArrayList ord) {
        this.cont = c;
        this.id = i;
        this.orden=ord;
    }
    public void run(){
         try {
            
             Random rnd = new Random();
             int tiempo = rnd.nextInt(5)+1;
             Thread.sleep(tiempo*1000);
             solicitarRecurso();
             
             
         } catch (InterruptedException ex) {
             System.out.println(ex.getMessage());
         }
    }

    private synchronized void  solicitarRecurso() throws InterruptedException {
        orden.add(id);
         try {
        System.out.println("El proceso: "+id+" solicita el recurso");
        while(!orden.isEmpty()){
        if(!cont.hayDato){
           
                 System.out.println("El proceso: "+id+" espera el recurso");           
            wait();
               
           
        }else{
               cont.sacar();
               System.out.println("El proceso: "+orden.get(0)+" Recibe OK");
               Thread.sleep(1000);
                System.out.println("El proceso: "+orden.get(0)+" esta usando el recurso");
                Thread.sleep(3000);
                System.out.println("El proceso: "+orden.get(0)+" terminó de usar el recurso");
                cont.poner(""+id);
                orden.remove(0);
                notifyAll();
           
        }}
         } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }finally{notifyAll();}
    }

}
