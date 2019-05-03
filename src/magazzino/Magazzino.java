/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magazzino;

/**
 *
 * @author Cagni
 */
public class Magazzino {
    private int prodotti;
    
    public Magazzino(int prodotti){
        this.prodotti = prodotti;
    }
    
    public int getProdotti(){
        return prodotti;
    }
    
    public int setProdotti(int prodotti){
        return this.prodotti = prodotti;
    }
    
    public synchronized int incrementa(Thread t, int prodotti, int i){                    //notifyAll() e wait() hanno bisogno di metodi synchronized       il synchronized annulla il parallelismo
        int x;
        x = setProdotti(getProdotti()+prodotti);
        notifyAll();
        return x;
    }
    
    public synchronized int decrementa(Thread t, int var, int i){
        while(var>getProdotti()){
            System.out.println(t.getName()+" "+i+" Devo aspettare");
            try{
                wait();                                                               //il wait ha bisogno del try e catch 
            }catch(InterruptedException e){
                e.printStackTrace();
            }    
        }
        return setProdotti(getProdotti()-var);
    }
}

