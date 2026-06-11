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
    public void setPosicionEnIv2(Position<Arco<V,E>> p){

    }
    public void setPosicionEnIv1(Position<Arco<V,E>> p){

    }
    public void setPosEnArcos(Position<Arco<V,E>> p){

    }
    public Vertice<V,E> getV1(){
        return v1;
    }
    public Vertice<V,E> getV2(){
        return v2;
    }
    public Position<Arco<V,E>> getPosicionEnIv1(){
        return posicionEnIv1;
    }
    public Position<Arco<V,E>> getPosicionEnIv2(){
        return posicionEnIv2;
    }
    public Position<Arco<V,E>> getPosicionEnArcos(){
        return posicionEnArcos;
    }
}