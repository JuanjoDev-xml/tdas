package ar.edu.uns.cs.ed.tdas.tps.tp8;
import ar.edu.uns.cs.ed.tdas.tdagrafo.Vertex;
import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.tdas.tps.tp4.ej1.ListaDobleEnlazadaCentinela;
import ar.edu.uns.cs.ed.tdas.tps.tp5.ej2.MapeoConHashAbierto;

public class Vertice<V,E>  extends MapeoConHashAbierto<Object, Object> implements Vertex<V>{
    private V rotulo;
    private PositionList<Arco<V,E>> adyacentes;
    private Position<Vertice<V,E>> posicionEnNodos;
    public Vertice( V rotulo ) {
        this.rotulo = rotulo;
        adyacentes = new ListaDobleEnlazadaCentinela<Arco<V,E>>();
    }
    public V element(){
        return rotulo;
    }
    // Setters y getters
    public void setRotulo(V nuevoRotulo) {
        rotulo = nuevoRotulo;
    }
    public PositionList<Arco<V,E>> getAdyacentes() {
        return adyacentes;
    }
    public void setPosicionEnNodos(Position<Vertice<V,E>> p ) {
        posicionEnNodos = p;
    }
    public Position<Vertice<V,E>> getPosicionEnNodos(){
        return posicionEnNodos;
    }
}
