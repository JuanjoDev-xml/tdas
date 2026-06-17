package ar.edu.uns.cs.ed.tdas.tps.tp8;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidEdgeException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidVertexException;
import ar.edu.uns.cs.ed.tdas.tdagrafo.Edge;
import ar.edu.uns.cs.ed.tdas.tdagrafo.Graph;
import ar.edu.uns.cs.ed.tdas.tdagrafo.Vertex;
import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.tdas.tps.tp4.ej1.ListaDobleEnlazadaCentinela;
import ar.edu.uns.cs.ed.tdas.tdacola.ColaArreglo;
import ar.edu.uns.cs.ed.tdas.tdacola.Queue;

public class GrafoListaAdyacencia<V,E> implements Graph<V,E>{
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
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e){ // ?????? O(1) ?
        Arco<V,E> a =(Arco<V,E>) e;
		Vertice<V,E> vv = (Vertice<V,E>) v;
		if (a.getV1() == vv) return a.getV2();
		if (a.getV2() == vv) return a.getV1();
		throw new InvalidEdgeException("El arco no es incidente al vértice");
    }
	
	/**
	 * Devuelve un Arreglo de 2 elementos con lo vértices extremos de un Arco e.
	 * @param  e Un arco
	 * @return Un Arreglo de 2 elementos con los extremos de un Arco e.
	 * @throws InvalidEdgeException si el arco es inválido.
	 */
	public Vertex<V> [] endvertices(Edge<E> e){ // ????????? O(1) ???
        Vertex<V> [] res = (Vertex<V> []) new Vertice [2];
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
			if (a.getV1() == vv || a.getV2() == vv)
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
	public E replace(Edge<E> e, E x){ // ??????????????
        Arco<V,E> a = (Arco<V,E>) e;
		E res = a.element();
		a.setRotulo(x);
		return res;
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



	// -------------------------------------------------------

	private final Object VISITADO = new Object();
	private final Object NOVISITADO = new Object();
	private final Object ESTADO = new Object();

	// Ejercicio 3
	// Dado un grafo no dirigido g, escriba un método que determine si g es conexo.
	// Resuelva este ejercicio implementando un recorrido depth-first-search (DFS).

	public boolean esConexo(Graph<V,E> g){
		for (Vertex<V> v : g.vertices()){
			v.put(ESTADO, NOVISITADO);
		}
		
		Vertex<V> primero = g.vertices().iterator().next();
		dfs(g, primero);

		for (Vertex<V> v : g.vertices())
			if (v.get(ESTADO) == NOVISITADO)
				return false;
		return true;
	}

	private void dfs(Graph<V,E> g, Vertex<V> v){
		// procesamiento de v previo al recorrido

		v.put(ESTADO, VISITADO);
		Iterable<Edge<E>> adyacentes = g.incidentEdges(v); // si el grafo es dirigido cambiar por g.emergentEdges(v)
		for (Edge<E> e : adyacentes){
			Vertex<V> w = g.opposite(v, e);
			if (w.get(ESTADO) == NOVISITADO)
				dfs(g, w);
		}
		// procesamiento de v posterior al recorrido

	}


	// Ejercicio 4
	// Dado un grafo conexo no dirigido g y dos vértices v1 y v2
	// (pertenecientes al grafo), escriba un método que determine la longitud
	// del camino más corto entre v1 y v2. Resuelva este ejercicio implementando un
	// recorrido primero en anchura (Breadth-First Search - BFS).

	public int caminoMasCorto(Graph<V,E> g, Vertex<V> v1, Vertex<V> v2){
		for (Vertex<V> v : g.vertices())
			v.put(ESTADO, NOVISITADO);

		return bfsDistancia(g, v1, v2);
	}
	private int bfsDistancia(Graph<V,E> g, Vertex<V> origen, Vertex<V> destino){
		Queue<Vertex<V>> cola = new ColaArreglo<Vertex<V>>(100);
		cola.enqueue(origen);
		origen.put(ESTADO, VISITADO);
		// Decoro el origen indicando que la distancia a sí mismo es 0
		origen.put("DISTANCIA", 0);
	
		while (!cola.isEmpty()){
			Vertex<V> u = cola.dequeue();
			// OPTIMIZACIÓN: Si 'u' es el destino, ya terminamos
            if (u == destino) {
                return (int) u.get("DISTANCIA");
            }

			Iterable<Edge<E>> adyacentes = g.incidentEdges(u);
			for (Edge<E> e : adyacentes){
				Vertex<V> x = g.opposite(u, e);
				if (x.get(ESTADO) == NOVISITADO){
					x.put(ESTADO, VISITADO);
					// La distancia de x es la distancia del vértice que lo descubrió (u) + 1
					x.put("DISTANCIA", (int) u.get("DISTANCIA") + 1);

					cola.enqueue(x);
				}
			}
		}
		return -1; // retorno de seguridad, no debería llegar acá con un grafo conexo
	}


	// Ejercicio 5
	// Dado un grafo G y dos vértices v1 y v2, escriba un método que determine si
	// existe un camino entre v1 y v2. El método debe imprimir el camino.

	public boolean existeCamino(Graph<V,E> g, Vertex<V> v1, Vertex<V> v2) {
    	// Inicializamos todos como no visitados
		for (Vertex<V> v : g.vertices())
			v.put(ESTADO, NOVISITADO);
		
    	// Creamos la lista que va a guardar el camino actual
		PositionList<Vertex<V>> camino = new ListaDobleEnlazadaCentinela<>();

		return dfsCamino(g, v1, v2, camino);
	}

	private boolean dfsCamino(Graph<V,E> g, Vertex<V> v, Vertex<V> destino, PositionList<Vertex<V>> camino) {
    	// Marcamos v como visitado y lo agregamos al camino actual
		v.put(ESTADO, VISITADO);
		camino.addLast(v);

    	// Caso base: llegamos al destino → imprimimos y avisamos
		if (v == destino) {
			for (Vertex<V> nodo : camino)
				System.out.print(nodo.element() + " → ");
			System.out.println("FIN");
			return true;
		}

    	// Caso recursivo: exploramos los vecinos no visitados
		for (Edge<E> e : g.incidentEdges(v)) {
			Vertex<V> w = g.opposite(v, e);
			if (w.get(ESTADO) == NOVISITADO) {
				if (dfsCamino(g, w, destino, camino))
                	return true;  // si algún vecino encontró camino, propagamos
			}
		}

    	// BACKTRACK: este vértice no llevó al destino, lo sacamos del camino
		camino.remove(camino.last());
		return false;
	}
}
