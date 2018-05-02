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
import javax.swing.JLabel;

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
             int tiempo = rnd.nextInt(5000)+1000;
             Thread.sleep(tiempo);
             solicitarRecurso();
             
             
         } catch (InterruptedException ex) {
             System.out.println(ex.getMessage());
         }
    }

    private synchronized void  solicitarRecurso() throws InterruptedException {
        orden.add(id);
        String cad="";
         try {
        System.out.println("El proceso: "+id+" solicita el recurso");
        GUI.mostrar.setText(GUI.mostrar.getText()+"El proceso: "+id+" solicita el recurso \n");
        GUI.mostrar.setCaretPosition(GUI.mostrar.getDocument().getLength());
      
        while(!orden.isEmpty()){
        if(!cont.hayDato){          
                 System.out.println("El proceso: "+id+" espera el recurso");
                 GUI.mostrar.setText(GUI.mostrar.getText()+"El proceso: "+id+" espera el recurso \n");
                 GUI.mostrar.setCaretPosition(GUI.mostrar.getDocument().getLength());
            wait();          
        }else{
               cont.sacar();
               System.out.println("El proceso: "+orden.get(0)+" Recibe OK");
               GUI.mostrar.setText(GUI.mostrar.getText()+"El proceso: "+orden.get(0)+" Recibe OK \n");
               GUI.mostrar.setCaretPosition(GUI.mostrar.getDocument().getLength());
               Thread.sleep(1000);
                System.out.println("El proceso: "+orden.get(0)+" esta usando el recurso");
                GUI.mostrar.setText(GUI.mostrar.getText()+"El proceso: "+orden.get(0)+" esta usando el recurso \n");
                GUI.mostrar.setCaretPosition(GUI.mostrar.getDocument().getLength());
                Thread.sleep(3000);
                System.out.println("El proceso: "+orden.get(0)+" terminó de usar el recurso");
                  GUI.mostrar.setText(GUI.mostrar.getText()+"El proceso: "+orden.get(0)+" terminó de usar el recurso \n"); 
                  GUI.mostrar.setCaretPosition(GUI.mostrar.getDocument().getLength());
                cont.poner("P"+orden.get(0)+" ");
                GUI.lista.setText(GUI.lista.getText()+"P"+orden.get(0)+" ");
                orden.remove(0);
                notifyAll();
           
        }}
         } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
    }

}
