package ar.edu.uns.cs.ed.tdas.tps.tp8;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidEdgeException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidVertexException;
import ar.edu.uns.cs.ed.tdas.tdagrafo.Edge;
import ar.edu.uns.cs.ed.tdas.tdagrafo.Graph;
import ar.edu.uns.cs.ed.tdas.tdagrafo.Vertex;
import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.tdas.tps.tp4.ej1.ListaDobleEnlazadaCentinela;

public class GrafoListaAdyacencia<V,E> implements Graph{
    // Atributos
	protected PositionList<Vertice<V,E>> nodos;
	protected PositionList<Arco<V,E>> arcos;
    // Constructor
	public GrafoListaAdyacencia(){
		nodos = new ListaDobleEnlazadaCentinela<Vertice<V,E>>();
		arcos = new ListaDobleEnlazadaCentinela<Arco<V,E>>();
	}
    // Métodos

    /**
	 * Devuelve una colección iterable de vértices.
	 * @return Una colección iterable de vértices.
	 */
	public Iterable<Vertex<V>> vertices(){
		PositionList<Vertex<V>> lista = new ListaDobleEnlazadaCentinela<Vertex<V>>();
		for( Vertex<V> v : nodos )
			lista.addLast(v);
		return lista;
    }
	
	/**
	 * Devuelve una colección iterable de arcos.
	 * @return Una colección iterable de arcos.
	 */
	public Iterable<Edge<E>> edges(){
		PositionList<Edge<E>> res = new ListaDobleEnlazadaCentinela<Edge<E>>();
		for (Edge<E> e : arcos)
			res.addLast(e);
		return res;
    }
	
	/**
	 * Devuelve una colección iterable de arcos incidentes a un vértice v.
	 * @param v Un vértice.
	 * @return Una colección iterable de arcos incidentes a un vértice v.
	 * @throws InvalidVertexException si el vértice es inválido.
	 */
	public Iterable<Edge<E>> incidentEdges(Vertex v){
		PositionList<Edge<E>> lista = new ListaDobleEnlazadaCentinela<Edge<E>>();
		Vertice<V,E> vert = (Vertice<V,E>) v;
		for( Edge<E> e : vert.getAdyacentes() )
			lista.addLast(e);
		return lista;
    }
	
	
	/**
	 * Devuelve el vértice opuesto a un Arco E y un vértice V.
	 * @param v Un vértice
	 * @param e Un arco
	 * @return El vértice opuesto a un Arco E y un vértice V.
	 * @throws InvalidVertexException si el vértice es inválido.
	 * @throws InvalidEdgeException si el arco es inválido.
	 */
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e){ // ?????? O(deg(G)) ?
        Vertice<V,E> vert = (Vertice<V,E>) v;
		for (Arco<V,E> a : vert.getAdyacentes()){
			if (a.element().equals(e)){
				if (a.getV1().equals(vert))
					return a.getV2();
				else if (a.getV2().equals(vert))
					return a.getV1();
			}	
		}
    }
	
	/**
	 * Devuelve un Arreglo de 2 elementos con lo vértices extremos de un Arco e.
	 * @param  e Un arco
	 * @return Un Arreglo de 2 elementos con los extremos de un Arco e.
	 * @throws InvalidEdgeException si el arco es inválido.
	 */
	public Vertex<V> [] endvertices(Edge<E> e){ // ????????? O(1) ???
        Vertex<V> [] res = (Vertice<V,E> []) new Object [2];
		Arco<V,E> arc = (Arco<V,E>) e;
		res[0] = arc.getV1();
		res[1] = arc.getV2();
		return res;
    }
	
	/**
	 * Devuelve verdadero si el vértice w es adyacente al vértice v.
	 * @param v Un vértice
	 * @param w Un vértice
	 * @return Verdadero si el vértice w es adyacente al vértice v, falso en caso contrario.
	 * @throws InvalidVertexException si uno de los vértices es inválido.
	 */
	public boolean areAdjacent(Vertex<V> v, Vertex<V> w){ // ?????? O(deg(g)) ??
        Vertice<V,E> vv = (Vertice<V,E>) v;
		Vertice<V,E> ww = (Vertice<V,E>) w;
		for (Arco<V,E> a : ww.getAdyacentes()){
			if (a.getV1().equals(vv) || a.getV2().equals(vv))
				return true;
		}
		return false;
    }
	
	/**
	 * Reemplaza el rótulo de v por un rótulo x.
	 * @param v Un vértice
	 * @param x Rótulo
	 * @return El rótulo anterior del vértice v al reemplazarlo por un rótulo x.
	 * @throws InvalidVertexException si el vértice es inválido.
	 */
	public V replace(Vertex<V> v, V x){ // ?????  O(1)?
        Vertice<V,E> vv = (Vertice<V,E>) v;
		V res = vv.element();
		vv.setRotulo(x);
		return res;
    }

	/**
	 * Reemplaza el rótulo de e por un rótulo x.
	 * @param e Un arco
	 * @param x Rótulo
	 * @return El rótulo anterior del arco e al reemplazarlo por un rótulo x.
	 * @throws InvalidEdgeException si el arco es inválido.
	 */
	public E replace(Edge<E> e, E x){
        
    }
	
	/**
	 * Inserta un nuevo vértice con rótulo x.
	 * @param x rótulo del nuevo vértice
	 * @return Un nuevo vértice insertado.
	 */
	public Vertex<V> insertVertex(V x){
        Vertice<V,E> v = new Vertice<V,E>(x);
		nodos.addLast(v);
		v.setPosicionEnNodos(nodos.last());
		return v;
    }
	
	/**
	 * Inserta un nuevo arco con rótulo e, con vértices extremos v y w.
	 * @param v Un vértice
	 * @param w Un vértice
	 * @param e rótulo del nuevo arco.
	 * @return Un nuevo arco.
	 * @throws InvalidVertexException si uno de los vértices es inválido.
	 */
	public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E e){
        // Obtengo los vertices v y w:
		Vertice<V,E> vv = (Vertice<V,E>) v; Vertice<V,E> ww = (Vertice<V,E>) w;
		Arco<V,E> arco = new Arco<V,E>(e, vv, ww ); // Construyo un arco
		// Agrego el arco al final de la lista de adyacentes de v y anoto su posición:
		vv.getAdyacentes().addLast( arco );
		arco.setPosicionEnIv1( vv.getAdyacentes().last() );
		// Agrego el arco al final de la lista de adyacentes
		// de w y anoto su posición:
		ww.getAdyacentes().addLast( arco );
		arco.setPosicionEnIv2( ww.getAdyacentes().last() );
		// Agrego el arco al final de lista de arcos y le seteo
		// su posición:
		arcos.addLast(arco);
		arco.setPosEnArcos(arcos.last());
		return arco;
    }
	
	/**
	 * Remueve un vértice V y retorna su rótulo.
	 * @param v Un vértice
	 * @return rótulo de V.
	 * @throws InvalidVertexException si el vértice es inválido.
	 */
	// Precondición: Asume que v no tiene arcos adyacentes.
	public V removeVertex(Vertex<V> v) {
		Position<Vertice<V,E>> pos = ((Vertice<V,E>) v).getPosicionEnNodos();
		return nodos.remove( pos ).element();
	}
	
	/**
	 * Remueve un arco e y retorna su rótulo.
	 * @param e Un arco
 	 * @return rótulo de E.
	 * @throws InvalidEdgeException si el arco es inválido.
	 */
	public E removeEdge(Edge<E> e){
        Arco<V,E> ee = (Arco<V,E>) e; // Recupero extremos del arco:
		Vertice<V,E> v1 = ee.getV1(); Vertice<V,E> v2 = ee.getV2();
		// Elimino a e de la lista de adyacentes de v1:
		v1.getAdyacentes().remove(ee.getPosicionEnIv1());
		// Elimino a e de la lista de adyacentes de v2:
		v2.getAdyacentes().remove(ee.getPosicionEnIv2());
		// Elimino a e de la lista de arcos y retorno
		// el rótulo del arco:
		Position<Arco<V,E>> pee = ee.getPosicionEnArcos();
		return arcos.remove(pee).element();
    }
}
