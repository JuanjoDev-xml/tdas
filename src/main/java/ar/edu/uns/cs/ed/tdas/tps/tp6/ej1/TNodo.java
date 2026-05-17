package ar.edu.uns.cs.ed.tdas.tps.tp6.ej1;

import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;

public class TNodo<E> implements Position<E>{
    protected E elem;
    protected TNodo<E> padre;
    protected PositionList<TNodo<E>> hijos;

    public TNodo(E e, TNodo<E> p){
        elem = e;
        p = padre;
        hijos = new ListaDobleEnlazadaCentinela<TNodo<E>>();
    }
    public TNodo (E elemento){
        this(elemento, null);
    }

    public E element(){
        return elem;
    }
    public PositionList<TNodo<E>> getHijos(){
        return hijos;
    }
    public void setElemento(E elemento){
        elem = elemento;
    }
    public TNodo<E> getPadre(){
        return padre;
    }
    public void setPadre(TNodo<E> p){
        padre = p;
    }
}
