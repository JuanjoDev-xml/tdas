package ar.edu.uns.cs.ed.tdas.tps.tp5.ej3;

import java.security.Key;

import ar.edu.uns.cs.ed.tdas.Entry;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.tdas.tps.tp4.ej1.ListaDobleEnlazadaCentinela;
import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidEntryException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidKeyException;
public class DiccionarioHashAbierto<K,V> implements Dictionary<K,V>{
    // Atributos de clase
    protected static final float factorDeCarga = 0.7f;
    // Atributos de instancia
    protected PositionList<Entry<K,V>>[] A;
    protected int N;
    protected int tamanio;
    // Constructors
    public DiccionarioHashAbierto(){
        N = 37;
        tamanio = 0;
        A = (PositionList<Entry<K,V>>[]) new ListaDobleEnlazadaCentinela[N];
        for (int i = 0; i < N; i++){
            A[i] = new ListaDobleEnlazadaCentinela<Entry<K,V>>();
        }
    }
    // Methods
    public int size(){
        return tamanio;
    }
    public boolean isEmpty(){
        return size() == 0;
    }

    public int hashYCompresion(K key){
        return Math.abs(key.hashCode()%N);
    }
    
    public void rehash(){
        int NViejo = N;
        PositionList<Entry<K,V>>[] arregloViejo = A;
        N = N*2;
        A = (PositionList<Entry<K,V>>[]) new ListaDobleEnlazadaCentinela[N];
        for (int i = 0; i < N; i++){
            A[i] = new ListaDobleEnlazadaCentinela<Entry<K,V>>();
        }
        int indice;
        for (int i = 0; i < NViejo; i++){
            for (Entry<K,V> e : arregloViejo[i]){
                indice = hashYCompresion(e.getKey());
                A[indice].addLast(e);
            }
        }
    }

    public Entry<K,V> find(K key){
        if (key == null) throw new InvalidKeyException("Clave nula");
        int i = hashYCompresion(key);
        for (Entry<K,V> e : A[i]){
            if (e.getKey().equals(key))
                return e;
        }
        return null;
    }
    public Iterable<Entry<K,V>> findAll(K key){ // las claves con el mismo key van siempre en el mismo bucket ??????
        if (key == null) throw new InvalidKeyException("Clave nula");
        PositionList<Entry<K,V>> res = new ListaDobleEnlazadaCentinela<>();
        int i = hashYCompresion(key);
        for (Entry<K,V> e : A[i]){
            if (e.getKey().equals(key))
                res.addLast(e);
        }
        return res;
    }
    public Entry<K,V> insert(K key, V value){
        if (key == null) throw new InvalidKeyException("Clave nula");
        int i = hashYCompresion(key);
        Entry<K,V> e = new Entrada<>(key, value);
        A[i].addLast(e);
        tamanio++;
        if (tamanio/N > factorDeCarga)
            rehash();
        return e;
    }
    public Entry<K,V> remove(Entry<K,V> e){
        if (e == null) throw new InvalidEntryException("Entrada nula");
        int i = hashYCompresion(e.getKey());
        for (Position<Entry<K,V>> p : A[i].positions()){
            if (p.element().equals(e)){
                Entry<K,V> res = p.element();
                A[i].remove(p);
                tamanio--;
                return res;
            }
        }
        throw new InvalidEntryException("La entrada no pertenece al diccionario");
    }
    public Iterable<Entry<K,V>> entries(){
        PositionList<Entry<K,V>> res = new ListaDobleEnlazadaCentinela<>();

    }
}
