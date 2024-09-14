package org.upana;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BufferCompartido {
    private final BlockingQueue<String> queue; //maneja los valores

    public BufferCompartido(int capacidad) {

        this.queue = new ArrayBlockingQueue<String>(capacidad); //capacidad de la cola
    }

    public void producir(String item) throws InterruptedException {
        queue.put(item); // agrega un elemento ala cola
        System.out.println("Elemento producido: " + item);
    }


    public String consumir() throws InterruptedException { // para retirar elemento de la cola
        String item = queue.take();//sila esta vacio se bloquea hasta que haya algo
        System.out.println("Elemento consumido: " + item);
        return item; // devuelve el dato consumido

    }
}
