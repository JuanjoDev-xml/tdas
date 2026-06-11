package ar.edu.uns.cs.ed.tdas.tps.tp7;
import ar.edu.uns.cs.ed.tdas.tdagrafo.Edge;
import ar.edu.uns.cs.ed.tdas.Position;

public class Arco<V,E>
    implements Edge<E> {
    private E rotulo;
    private Vertice<V,E> v1, v2;
    private Position<Arco<V,E>> posicionEnArcos;
    private Position<Arco<V,E>> posicionEnIv1, posicionEnIv2;
    public Arco( E rotulo, Vertice<V,E> v1, Vertice<V,E> v2 ){
        /* solo setea atributos */
        this.rotulo = rotulo;
        this.v1 = v1;
        this.v2 = v2;
    }
    public E element(){
        return rotulo;
    }
    // Setters y getters

}