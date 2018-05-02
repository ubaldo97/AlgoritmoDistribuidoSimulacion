/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Distribuido;

/**
 *
 * @author ruben
 */
public class Recurso {
    private String dato="";
    public boolean hayDato = true;
    
    public Recurso(){}
    
    public synchronized String sacar(){
        while(hayDato == false){
            try {
                wait();
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
        hayDato = false;
        notifyAll();
        return dato;
    }
    public synchronized void poner(String valor){
        while(hayDato==true){
            try {
                wait();
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
        dato =dato+valor;
        hayDato=true;
        notifyAll();
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }
    
}
