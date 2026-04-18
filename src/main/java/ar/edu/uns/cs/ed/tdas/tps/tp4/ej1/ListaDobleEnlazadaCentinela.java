package ar.edu.uns.cs.ed.tdas.tps.tp4.ej1;

import ar.edu.uns.cs.ed.tdas.excepciones.BoundaryViolationException;
import ar.edu.uns.cs.ed.tdas.excepciones.EmptyListException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidPositionException;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.tdas.Position;

import java.util.Iterator;

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
        if (isEmpty()) throw new InvalidPositionException("Lista vacía");
        if (n.getSiguiente() == trailer) throw new BoundaryViolationException("La posicion corresponde al ultimo elemento de la lista");
        return n.getSiguiente();
    }
    
    public Position<E> prev(Position<E> p){
        DNodo<E> n = checkPosition(p);
        if (isEmpty()) throw new InvalidPositionException("Lista vacía");
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

    public void addLast(E element){
        DNodo<E> nuevo = new DNodo<E>(element);
        nuevo.setAnterior(trailer.getAnterior());
        nuevo.setSiguiente(trailer);
        nuevo.getAnterior().setSiguiente(nuevo);
        trailer.setAnterior(nuevo);
        tamanio++;
    }

    public void addAfter(Position<E> p, E element){
        DNodo<E> pos = checkPosition(p);
        if (isEmpty()) throw new InvalidPositionException("Lista vacía");
        DNodo<E> nuevo = new DNodo<E>(element);
        nuevo.setAnterior(pos);
        nuevo.setSiguiente(pos.getSiguiente());
        pos.setSiguiente(nuevo);
        nuevo.getSiguiente().setAnterior(nuevo);
        tamanio++;
    }

    public void addBefore(Position<E> p, E element){
        DNodo<E> pos = checkPosition(p);
        if (isEmpty()) throw new InvalidPositionException("Lista vacía");
        DNodo<E> nuevo = new DNodo<E>(element);
        nuevo.setSiguiente(pos);
        nuevo.setAnterior(pos.getAnterior());
        pos.getAnterior().setSiguiente(nuevo);
        pos.setAnterior(nuevo);
        tamanio++;
    }

    public E remove(Position<E> p){
        DNodo<E> pos = checkPosition(p);
        if (isEmpty()) throw new InvalidPositionException("Lista vacía");
        E res = pos.element();
        pos.getAnterior().setSiguiente(pos.getSiguiente());
        pos.getSiguiente().setAnterior(pos.getAnterior());
        pos.setSiguiente(null);
        pos.setAnterior(null);
        return res;
    }

    public E set(Position<E> p, E element){
        DNodo<E> pos = checkPosition(p);
        if (isEmpty()) throw new InvalidPositionException("Lista vacía");
        E res = pos.element();
        pos.setElemento(element);
        return res;
    }



    public Iterator<E> iterator(){
        return new ElementIterator<E>(this);
    }

    public Iterable<Position<E>> positions(){
        PositionList<Position<E>> lista = new ListaDobleEnlazadaCentinela<>();
        DNodo<E> cursor = header.getSiguiente();
        while (cursor != trailer){
            lista.addLast(cursor);
            cursor = cursor.getSiguiente();
        }
        return lista;
    }






    // Ejercicio 2

    public void Ejercicio2(E e1, E e2){
        if (isEmpty()){
            addFirst(e1);
            addFirst(e2);
        }
        addAfter(header.siguiente, e1);
        addBefore(trailer.anterior, e2);
    }


    // Ejercicio 3

    // Inciso a

    public boolean pertenece(PositionList<E> l, E e1){
        Iterator<E> it = l.iterator();
        while (it.hasNext()) {
            if (it.next().equals(e1))
                return true;
        }
        return false;
    }

    // Inciso b

    public int cantidad(PositionList<E> l, E e1){
        int res = 0;
        Iterator<E> it = l.iterator();
        while(it.hasNext()){
            if (it.next().equals(e1))
                res++;
        }
        return res;
    }

    // Inciso c

    public boolean alMenosNVeces(PositionList<E> l, E x, int n){
        Iterator<E> it = l.iterator();
        int cant = 0;
        while(it.hasNext()){
            if (it.next() == x)
                cant++;
            if (cant >= n)
                return true;
        }
        return false;
    }
}
