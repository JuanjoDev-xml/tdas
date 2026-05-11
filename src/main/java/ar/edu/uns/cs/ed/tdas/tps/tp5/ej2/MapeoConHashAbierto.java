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
    protected PositionList<Entry<K,V>>[] A; // Entry o Entrada???
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

    private int hashYCompresion(K key){ // !!!!!!!!!!!!!!
        return Math.abs(key.hashCode()%N);
    }

    private void rehash(){
        
    }

    public V get(K key){
        if (key == null) throw new InvalidKeyException("Clave nula");
        int i = hashYCompresion(key);
        PositionList<Entry<K,V>> bucket = A[i];
        for (Entry<K,V> e : bucket){
            if (e.getKey().equals(key))
                return e.getValue();
        }
        return null;
    }
    public V put(K key, V value){
        if (key == null) throw new InvalidKeyException("Clave nula");
        int i = hashYCompresion(key);
        PositionList<Entry<K,V>> bucket = A[i];
        for(Position<Entry<K,V>> p : bucket.positions()){
            if(p.element().getKey().equals(key)){
                V res = p.element().getValue();
                bucket.set(p, new Entrada<K,V>(key, value)); // en diapositiva, se hace p.element().setValue(value),
                // pero acá no hay setters en Entry
                return res;
            }
        }
        bucket.addLast(new Entrada<K,V>(key, value));
        tamanio++;
        if (factorDeCarga < N/tamanio)
            rehash();
        return null;
    }
}
