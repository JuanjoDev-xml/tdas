package ar.edu.uns.cs.ed.tdas.tps.tp4.ej1;

import ar.edu.uns.cs.ed.tdas.excepciones.EmptyListException;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.tdas.Position;

public class ListaDobleEnlazadaCentinela<E> implements PositionList<E>{
    protected DNodo<E> head;
    protected DNodo<E> tail;
    protected int tamanio;

    
    public ListaDobleEnlazadaCentinela(){
        head = new DNodo<E>(null);
        tail = new DNodo<E>(null);
        tamanio = 0;
    }

    public int size(){
        return tamanio;
    }

    public boolean isEmpty(){
        return tamanio == 0;
    }

    public Position<E> first() throws EmptyListException{
        if (isEmpty()) throw new EmptyListException("Lista vacía");
        return head.siguiente;
    }
}
