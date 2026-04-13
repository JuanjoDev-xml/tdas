package ar.edu.uns.cs.ed.tdas.tps.tp4.ej1;

import ar.edu.uns.cs.ed.tdas.excepciones.BoundaryViolationException;
import ar.edu.uns.cs.ed.tdas.excepciones.EmptyListException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidPositionException;
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
        return head.getSiguiente();
    }

    public Position<E> last(){
        if (isEmpty()) throw new EmptyListException("Lista vacía");
        return tail.getAnterior();
    }

    private DNodo<E> checkPosition(Position<E> p ) {
        try {
            if ( p == null ) throw new InvalidPositionException("Posición nula");
            if (p.element() == null) throw new InvalidPositionException("p eliminada previamente");
            return (DNodo<E>) p; // Puede fallar si p es una posición que corresponde a un nodo de otro tipo de estructura de datos
        }
        catch( ClassCastException e ) { // Vengo acá porque falló el casting a Nodo
                throw new InvalidPositionException("p no es un nodo de lista");
        }
    }
    public Position<E> next(Position<E> p){
        DNodo<E> n = checkPosition(p); // Propaga InvalidPositionException
        if (n.getSiguiente() == tail) throw new BoundaryViolationException("La posicion corresponde al ultimo elemento de la lista");
        return n.getSiguiente();
    }

    
}
