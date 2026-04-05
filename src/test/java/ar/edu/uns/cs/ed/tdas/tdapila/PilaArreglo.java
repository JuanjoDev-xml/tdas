package ar.edu.uns.cs.ed.tdas.tdapila;

public class PilaArreglo<E> implements Stack<E>{
    protected int tamanio;
    protected E [] datos;
    protected int incremento;

    public PilaArreglo(int max){
        datos = (E[]) new Object[max];
        tamanio = 0;
    }
    public PilaArreglo(){
        this(20);
    }
    public PilaArreglo(int max, int incrementoTamanio){
        datos = (E[]) new Object[max];
        tamanio = 0;
        incremento = incrementoTamanio;
    }

    public boolean isEmpty(){
        return tamanio == 0;
    }
    public void push(E element){
        if (tamanio == datos.length){
            // crear nuevo arreglo con mas tamanio, copiar datos, insertar element
            E [] datosIncrementado = (E[]) new Object[tamanio + incremento];
            for (int i = 0; i < tamanio; i++){
                datosIncrementado[i] = datos[i];
            }
            datos = null;
            datos = datosIncrementado;
        }
        datos[tamanio++] = element;
    }
    public E top() throws EmptyStackException{
        if (tamanio == 0){
            throw new EmptyStackException("Pila vacía");
        }
        return datos[tamanio-1];
    }
    public E pop(){
        E res = top();
        datos[tamanio--] = null;
        return res;
    }
    public int size(){
        return tamanio;
    }
}
