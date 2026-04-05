package ar.edu.uns.cs.ed.tdas.tdapila;

public class EmptyStackException extends RuntimeException{
    public EmptyStackException (String m){
        super(m);
    }
}

// Se heredan: getMessage(), printStackTrace()