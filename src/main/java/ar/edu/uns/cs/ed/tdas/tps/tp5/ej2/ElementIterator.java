package ar.edu.uns.cs.ed.tdas.tps.tp4.ej1;

import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.tdas.Position;

import java.util.*;
public class ElementIterator<E> implements Iterator <E> {
    protected PositionList<E> lista; // Lista a iterar
    protected Position<E> cursor; // Posición del elemento corriente
    public ElementIterator (PositionList <E> l ) {
        lista = l; // Guardo la lista a iterar
        if (lista.isEmpty()) cursor = null; // Si la lista está vacía, la posición corriente es nula
        else cursor = lista.first(); // Sino la posición corriente es la primera de la lista
    }
    // Devuelve true si hay algún elemento más para ver.
    // Hay siguiente si el cursor no está más allá de la última posición
    public boolean hasNext() { return cursor != null; }
    // Devuelve el siguiente elemento y avanza el cursor. Falla si hasNext es falso.
    public E next () {
        if ( cursor == null ) // Si el cursor es null, el cliente no testeó que hasNext fuera true
            throw new NoSuchElementException ("Iterador de lista: No hay siguiente");
        E resultado = cursor.element(); // Salvo el elemento corriente
        cursor = (cursor == lista.last()) ? null : lista.next(cursor); // Avanzo a la siguiente posición
        return resultado; // Retorno el elemento salvado
    }
    // Remueve el último elemento retornado por el iterador
    public void remove() { /* No lo haremos. */ }
}