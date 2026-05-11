package ar.edu.uns.cs.ed.tdas.tps.tp5.ej2;

import ar.edu.uns.cs.ed.tdas.Entry;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidKeyException;


public class MapeoConHashAbierto<K,V> implements Map<K,V>{
    // Atributos de clase
    protected static final float factorDeCarga = 0.7f;
    // Atributos de instancia
    protected int N; // tamaño del arreglo
    protected PositionList<Entry<K,V>>[] A;
    protected int tamanio; // cantidad total de entradas
    // Constructores
    public MapeoConHashAbierto(){
        N = 37;
        A = (PositionList<Entry<K,V>>[]) new ListaDobleEnlazadaCentinela[N];
    }
    // Métodos
    public int size(){
        return tamanio;
    }
    public boolean isEmpty(){
        return size() == 0;
    }
    public V get(K key){
        if (key == null) throw new InvalidKeyException("Clave nula");
        int i = Math.abs(key.hashCode()%N);
        PositionList<Entry<K,V>> bucket = A[i];
        for (Position<Entry<K,V>> p : bucket.positions()){
            if (p.element().getKey().equals(key))
                return p.element().getValue();
        }
        return null;
    }
}
