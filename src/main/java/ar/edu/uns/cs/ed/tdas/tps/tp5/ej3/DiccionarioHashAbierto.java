package ar.edu.uns.cs.ed.tdas.tps.tp5.ej3;

import java.security.Key;

import ar.edu.uns.cs.ed.tdas.Entry;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidKeyException;
public class DiccionarioHashAbierto<K,V> implements Dictionary<K,V>{
    // Atributos de clase
    protected static final float factorDeCarga = 0.7f;
    // Atributos de instancia
    protected PositionList<Entry<K,V>>[] A;
    protected int N;
    protected int tamanio;
}
