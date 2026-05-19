package ar.edu.uns.cs.ed.tdas.tps.tp6.ej1;

import ar.edu.uns.cs.ed.tdas.excepciones.BoundaryViolationException;
import ar.edu.uns.cs.ed.tdas.excepciones.EmptyTreeException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidOperationException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidPositionException;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.tdas.Position;
import java.util.Iterator;

public class Arbol<E> implements Tree<E>{
    // Atributos de instancia
    protected int tamanio;
    protected TNodo<E> raiz;
    // Constructores
    public Arbol(){
        tamanio = 0;
        raiz = null;
    }
    // Métodos
    private TNodo<E> checkPosition(Position<E> p){
        try{
            if (p == null) throw new InvalidPositionException("Posición nula");
            if (p.element() == null) throw new InvalidPositionException("Posición removida");
            return (TNodo<E>) p;
        }
        catch(ClassCastException e){
            throw new InvalidPositionException("p no es un nodo del árbol");
        }
    }
    /**
	 * Consulta la cantidad de nodos en el árbol.
	 * @return Cantidad de nodos en el árbol.
	 */
	public int size(){
        return tamanio;
    }
	
	/**
	 * Consulta si el árbol está vacío.
	 * @return Verdadero si el árbol está vacío, falso en caso contrario.
	 */
	public boolean isEmpty(){
        return raiz == null;
    }
	
	/**
	 * Devuelve un iterador de los elementos almacenados en el árbol en preorden.
	 * @return Iterador de los elementos almacenados en el árbol.
	 */
	public Iterator<E> iterator(){

    }
	
	/**
	 * Devuelve una colección iterable de las posiciones de los nodos del árbol.
	 * @return Colección iterable de las posiciones de los nodos del árbol.
	 */
	public Iterable<Position<E>> positions(){

    }
	
	/**
	 * Reemplaza el elemento almacenado en la posición dada por el elemento pasado por parámetro. Devuelve el elemento reemplazado.
	 * @param v Posición de un nodo.
	 * @param e Elemento a reemplazar en la posición pasada por parámetro.
	 * @return Elemento reemplazado.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida.
	 */
	public E replace(Position<E> v, E e){
        TNodo<E> nodo = checkPosition(v);
        if (raiz.equals(nodo)){
			E res = raiz.element();
			raiz.setElemento(e);
			return res;
		}

    }
	
	/**
	 * Devuelve la posición de la raíz del árbol.
	 * @return Posición de la raíz del árbol.
	 * @throws EmptyTreeException si el árbol está vacío.
	 */
	public Position<E> root(){
		if (isEmpty()) throw new EmptyTreeException("Árbol vacío");
		return raiz;
	}
	
	/**
	 * Devuelve la posición del nodo padre del nodo correspondiente a una posición dada.
	 * @param v Posición de un nodo.
	 * @return Posición del nodo padre del nodo correspondiente a la posición dada.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida.
	 * @throws BoundaryViolationException si la posición pasada por parámetro corresponde a la raíz del árbol.
	 */
	public Position<E> parent(Position<E> v){
		TNodo<E> nodo = checkPosition(v);
		if (nodo.equals(root())) throw new BoundaryViolationException("La posición pasada por parámetro es la raíz");
		return nodo.getPadre();
	}
	
	/**
	 * Devuelve una colección iterable de los hijos del nodo correspondiente a una posición dada.
	 * @param v Posición de un nodo.
	 * @return Colección iterable de los hijos del nodo correspondiente a la posición pasada por parámetro.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida.
	 */
	public Iterable<Position<E>> children(Position<E> v);
	
	/**
	 * Consulta si una posición corresponde a un nodo interno.
	 * @param v Posición de un nodo.
	 * @return Verdadero si la posición pasada por parámetro corresponde a un nodo interno, falso en caso contrario.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida.
	 */
	public boolean isInternal(Position<E> v){
		TNodo<E> nodo = checkPosition(v);
		return !nodo.getHijos().isEmpty();
	}
	
	/**
	 * Consulta si una posición dada corresponde a un nodo externo.
	 * @param v Posición de un nodo.
	 * @return Verdadero si la posición pasada por parámetro corresponde a un nodo externo, falso en caso contrario.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida.
	 */
	public boolean isExternal(Position<E> v){
		TNodo<E> nodo = checkPosition(v);
		return nodo.getHijos().isEmpty();
	}
	
	/**
	 * Consulta si una posición dada corresponde a la raíz del árbol.
	 * @param v Posición de un nodo.
	 * @return Verdadero, si la posición pasada por parámetro corresponde a la raíz del árbol,falso en caso contrario.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida.
	 */
	public boolean isRoot(Position<E> v){
		TNodo<E> nodo = checkPosition(v);
		return nodo.getPadre() == null;
	}

	/**
	 * Crea un nodo con rótulo e como raíz del árbol.
	 * @param E Rótulo que se asignará a la raíz del árbol.
	 * @throws InvalidOperationException si el árbol ya tiene un nodo raíz.
	 */
	public void createRoot(E e){
		if (raiz != null) throw new InvalidOperationException("El árbol ya tiene raíz");
		raiz = new TNodo<E>(e);
		tamanio++;
	}
	
	/**
	 * Agrega un nodo con rótulo e como primer hijo de un nodo dado.
	 * @param e Rótulo del nuevo nodo.
	 * @param padre Posición del nodo padre.
	 * @return La posición del nuevo nodo creado.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida o el árbol está vacío.
	 */
	public Position<E> addFirstChild(Position<E> p, E e){
		if (isEmpty()) throw new InvalidPositionException("Árbol vacío");
		TNodo<E> padre = checkPosition(p);
		TNodo<E> nuevo = new TNodo<E>(e);
		padre.getHijos().addFirst(nuevo);
		tamanio++;
		return nuevo;
	}
	
	/**
	 * Agrega un nodo con rótulo e como último hijo de un nodo dado.
	 * @param e Rótulo del nuevo nodo.
	 * @param p Posición del nodo padre.
	 * @return La posición del nuevo nodo creado.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida o el árbol está vacío.
	 */
	public Position<E> addLastChild(Position<E> p, E e){
		if (isEmpty()) throw new InvalidPositionException("Árbol vacío");
		TNodo<E> padre = checkPosition(p);
		TNodo<E> nuevo = new TNodo<E>(e);
		padre.getHijos().addLast(nuevo);
		tamanio++;
		return nuevo;
	}
	
	/**
	 * Agrega un nodo con rótulo e como hijo de un nodo padre dado. El nuevo nodo se agregará delante de otro nodo también dado.
	 * @param e Rótulo del nuevo nodo.
	 * @param p Posición del nodo padre.
	 * @param rb Posición del nodo que será el hermano derecho del nuevo nodo.
	 * @return La posición del nuevo nodo creado.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida, o el árbol está vacío, o la posición rb no corresponde a un nodo hijo del nodo referenciado por p.
	 */
	public Position<E> addBefore(Position<E> p, Position<E> rb, E e){
		if (isEmpty()) throw new InvalidPositionException("Árbol vacío");
		TNodo<E> padre = checkPosition(p);
		TNodo<E> hd = checkPosition(rb);
		TNodo<E> nuevo = new TNodo<E>(e);
		PositionList<TNodo<E>> hijos = padre.getHijos();
		// Buscar donde esta rb en hijos de p
		boolean encontre = false;
		Position<TNodo<E>> pp = hijos.first(); // Por qué Position<TNodo<E>> y no TNodo<E> ?????????????????????
		while (pp != null && !encontre){
			if (hd == pp.element()) encontre = true;
			else pp = (pp != hijos.last() ? hijos.next(pp) : null);
		}
		if (!encontre) throw new InvalidPositionException("p no es padre de rb");
		hijos.addBefore(pp, nuevo);
		tamanio++;
		return nuevo;
	}

	/**
	 * Agrega un nodo con rótulo e como hijo de un nodo padre dado. El nuevo nodo se agregará a continuación de otro nodo también dado.
	 * @param e Rótulo del nuevo nodo.
	 * @param p Posición del nodo padre.
	 * @param lb Posición del nodo que será el hermano izquierdo del nuevo nodo.
	 * @return La posición del nuevo nodo creado.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida, o el árbol está vacío, o la posición lb no corresponde a un nodo hijo del nodo referenciado por p.
	 */
	public Position<E> addAfter (Position<E> p, Position<E> lb, E e){
		if (isEmpty()) throw new InvalidPositionException("Árbol vacío");
		TNodo<E> padre = checkPosition(p);
		TNodo<E> hi = checkPosition(lb);
		TNodo<E> nuevo = new TNodo<E>(e);
		PositionList<TNodo<E>> hijos = padre.getHijos();
		boolean encontre = false;
		Position<TNodo<E>> pp = hijos.first(); // Por qué Position<TNodo<E>> y no TNodo<E> ?????????????????????
		while (pp != null && !encontre) {
			if (pp.element() == hi) encontre = true;
			else{
				if (pp == hijos.last()) pp = null;
				else pp = hijos.next(pp); 
			}
		}
		if (!encontre) throw new InvalidPositionException("p no es padre de lb");
		hijos.addAfter(pp, nuevo);
		tamanio++;
		return nuevo;
	}
	
	/**
	 * Elimina el nodo referenciado por una posición dada, si se trata de un nodo externo. 
	 * @param n Posición del nodo a eliminar.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida o no corresponde a un nodo externo, o el árbol está vacío.
	 */
	public void removeExternalNode (Position<E> p){
		if (isEmpty()) throw new InvalidPositionException("Árbol vacío");
		TNodo<E> nodo = checkPosition(p);
		if (!isExternal(nodo)) throw new InvalidPositionException("p no es una hoja");
		if (p == root()){
			raiz = null;
			tamanio = 0;
			raiz.setElemento(null);
			return;
		}
		TNodo<E> padre = nodo.getPadre();
		PositionList<TNodo<E>> hijosPadre = padre.getHijos();
		boolean encontre = false;
		Position<TNodo<E>> pp = null;
		Iterable<Position<TNodo<E>>> posiciones = hijosPadre.positions();
		Iterator<Position<TNodo<E>>> it = posiciones.iterator();
		while (it.hasNext() && !encontre){
			pp = it.next();
			if (pp.element().equals(nodo))
				encontre = true;
		}
		if (!encontre) throw new InvalidPositionException("p no aparece en los hijos de su padre");
		hijosPadre.remove(pp);
		nodo.setElemento(null);
		tamanio--;
	}
	
	/**
	 * Elimina el nodo referenciado por una posición dada, si se trata de un nodo interno. Los hijos del nodo eliminado lo reemplazan en el mismo orden en el que aparecen. 
	 * Si el nodo a eliminar es la raíz del árbol, únicamente podrá ser eliminado si tiene un solo hijo, el cual lo reemplazará.
	 * @param n Posición del nodo a eliminar.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida o no corresponde a un nodo interno o corresponde a la raíz (con más de un hijo), o el árbol está vacío.
	 */
	public void removeInternalNode (Position<E> p){
		if (isEmpty()) throw new InvalidPositionException("Árbol vacío");
		TNodo<E> nodo = checkPosition(p);
		if (!isInternal(nodo)) throw new InvalidOperationException("p no es un nodo interno");
		if (p == root()){
			if (raiz.getHijos().size() != 1) throw new InvalidPositionException("p es raíz y tiene más de un hijo");
			TNodo<E> unicoHijo = nodo.getHijos().first().element();
			raiz = unicoHijo;
			unicoHijo.setPadre(null);
		}
		else{
			TNodo<E> padre = nodo.getPadre();
			PositionList<TNodo<E>> hermanos = padre.getHijos();
			Position<TNodo<E>> posNodoEnH = null;
			for (Position<TNodo<E>> pos : hermanos.positions()){
				if (pos.element() == nodo){
					posNodoEnH = pos;
					break;
				}
			}
			if (posNodoEnH == null){
                throw new InvalidPositionException("p no aparece en la lista de hijos de su padre");
            }
            for (TNodo<E> nodoHijo : nodo.getHijos()){
                nodoHijo.setPadre(padre);
                hermanos.addBefore(posNodoEnH, nodoHijo);
            }
			hermanos.remove(posNodoEnH);
		}
		nodo.setElemento(null);
		nodo.setPadre(null);
		tamanio--;
	}
	
	/**
	 * Elimina el nodo referenciado por una posición dada. Si se trata de un nodo interno, los hijos del nodo eliminado lo reemplazan en el mismo orden en el que aparecen. 
	 * Si el nodo a eliminar es la raíz del árbol, únicamente podrá ser eliminado si tiene un solo hijo, el cual lo reemplazará.
	 * @param n Posición del nodo a eliminar.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida o corresponde a la raíz (con más de un hijo), o el árbol está vacío.
	 */
	public void removeNode (Position<E> p);
}
