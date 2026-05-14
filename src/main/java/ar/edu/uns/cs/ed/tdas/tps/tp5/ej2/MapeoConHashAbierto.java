package ar.edu.uns.cs.ed.tdas.tps.tp5.ej2;

import java.security.Key;

import ar.edu.uns.cs.ed.tdas.Entry;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidKeyException;


public class MapeoConHashAbierto<K,V> implements Map<K,V>{
    // Atributos de clase
    protected static final float factorDeCarga = 0.7f;
    // Atributos de instancia
    protected int N; // tamaño del arreglo
    protected PositionList<Entry<K,V>>[] A; // Entry o Entrada ????????????? PositionList o ListaDobleEnlazada??????
    protected int tamanio; // cantidad total de entradas
    // Constructores
    public MapeoConHashAbierto(){
        N = 37;
        A = (PositionList<Entry<K,V>>[]) new ListaDobleEnlazadaCentinela[N]; // Entry???? PositionList o ListaDoble???
        for (int i = 0; i < N; i++){ // cada bucket deberia ser una lista o una entrada ???????
            A[i] = new ListaDobleEnlazadaCentinela<Entry<K,V>>();
        }
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

    private void rehash(){ // !!!!!!!!!!!!!!!!!! O(n^2) ??
        PositionList<Entry<K,V>>[] arregloViejo = A;
        int NViejo = N;
        N = N*2;
        A = (PositionList<Entry<K,V>>[]) new ListaDobleEnlazadaCentinela[N];
        for (int i = 0; i < N; i++){
            A[i] = new ListaDobleEnlazadaCentinela<>(); // O(n) ?????
        }
        for (int i = 0; i < NViejo; i++){
            for (Entry<K,V> e : arregloViejo[i]){ // O(n^2) ???????
                int indice = hashYCompresion(e.getKey());
                A[indice].addLast(e);
            }
        }

    }

    public V get(K key){ // O(n) ?????
        if (key == null) throw new InvalidKeyException("Clave nula");
        int i = hashYCompresion(key);
        PositionList<Entry<K,V>> bucket = A[i];
        for (Entry<K,V> e : bucket){
            if (e.getKey().equals(key))
                return e.getValue();
        }
        return null;
    }
    public V put(K key, V value){ // O(n)
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
        if (factorDeCarga < tamanio/N)
            rehash();
        return null;
    }
    public V remove(K key){
        if (key == null) throw new InvalidKeyException("Clave nula");
        int i = hashYCompresion(key);
        for (Position<Entry<K,V>> p : A[i].positions()){
            if (key.equals(p.element().getKey())){
                V res = p.element().getValue();
                A[i].remove(p);
                tamanio--;
                return res;
            }
        }
        return null;
    }
    public Iterable<K> keys(){ // O(n^2) ?????
        ListaDobleEnlazadaCentinela<K> res = new ListaDobleEnlazadaCentinela<>();
        for (int i = 0; i < N; i++){
            for (Entry<K,V> e : A[i]){
                res.addLast(e.getKey());
            }
        }
        return res;
    }
    public Iterable<V> values(){ // O(n^2)
        ListaDobleEnlazadaCentinela<V> res = new ListaDobleEnlazadaCentinela<>();
        for (int i = 0; i<N; i++){
            for(Entry<K,V> e : A[i]){
                res.addLast(e.getValue());
            }
        }
        return res;
    }
    public Iterable<Entry<K,V>> entries(){ // O(n^2)
        ListaDobleEnlazadaCentinela<Entry<K,V>> res = new ListaDobleEnlazadaCentinela<>();
        for (int i = 0; i < N; i++){
            for (Entry<K,V> e : A[i]){
                res.addLast(e);
            }
        }
        return res;
    }
}
