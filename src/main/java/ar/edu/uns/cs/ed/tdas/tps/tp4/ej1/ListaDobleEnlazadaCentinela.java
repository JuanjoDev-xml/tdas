package ar.edu.uns.cs.ed.tdas.tps.tp4.ej1;

import ar.edu.uns.cs.ed.tdas.excepciones.BoundaryViolationException;
import ar.edu.uns.cs.ed.tdas.excepciones.EmptyListException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidPositionException;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.tdas.Position;

public class ListaDobleEnlazadaCentinela<E> implements PositionList<E>{
    protected DNodo<E> header;
    protected DNodo<E> trailer;
    protected int tamanio;

    
    public ListaDobleEnlazadaCentinela(){
        header = new DNodo<E>(null);
        trailer = new DNodo<E>(null);
        header.setSiguiente(trailer);
        trailer.setAnterior(header);
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
        return header.getSiguiente();
    }

    public Position<E> last(){
        if (isEmpty()) throw new EmptyListException("Lista vacía");
        return trailer.getAnterior();
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
        if (n.getSiguiente() == trailer) throw new BoundaryViolationException("La posicion corresponde al ultimo elemento de la lista");
        return n.getSiguiente();
    }
    
    public Position<E> prev(Position<E> p){
        DNodo<E> n = checkPosition(p);
        if (n.getAnterior() == header) throw new BoundaryViolationException("La poisicon corresponde al primer elemento de la lista");
        return n.getAnterior();
    }

    public void addFirst(E element){
        DNodo<E> nuevo = new DNodo<E>(element);
        nuevo.setAnterior(header);
        nuevo.setSiguiente(header.getSiguiente());
        header.getSiguiente().setAnterior(nuevo);
        header.setSiguiente(nuevo);
        tamanio++;
    }
}
