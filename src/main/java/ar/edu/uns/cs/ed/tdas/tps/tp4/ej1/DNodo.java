package ar.edu.uns.cs.ed.tdas.tps.tp4.ej1;
import ar.edu.uns.cs.ed.tdas.Position;


public class DNodo<E> implements Position<E>{
    // Atributos de instancia
    protected E elemento;
    protected DNodo<E> siguiente;
    protected DNodo<E> anterior;

    // Constructores
    public DNodo(E item, DNodo<E> sig, DNodo<E> ant){
        elemento = item;
        siguiente = sig;
        anterior = ant;
    }
    public DNodo(E item){this(item, null, null);}
    
    // Setters
    public void setElemento(E item){
        elemento = item;
    }
    public void setSiguiente(DNodo<E> sig){
        siguiente = sig;
    }
    public void setAnterior(DNodo<E> ant){
        anterior = ant;
    }

    // Getters
    public E element(){
        return elemento;
    }
    public DNodo<E> getSiguiente(){
        return siguiente;
    }
    public DNodo<E> getAnterior(){
        return anterior;
    }
}
