package org.upana;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        //agregar el BufferCompartido este tendra 10 espacios
        BufferCompartido buffer = new BufferCompartido(10);

//        String rutaArchivoSalida = "salida.txt";

        //Crear 3 productores
        //se agrega como parametro el buffer y el nombre del archivo txt y el nombre del productor
        Thread productor1 = new Thread(new Productor(buffer, "input1.txt", "Productor 1"));
        Thread productor2 = new Thread(new Productor(buffer, "input2.txt", "Productor 2"));
        Thread productor3 = new Thread(new Productor(buffer, "input3.txt", "Productor 3"));

        productor1.start();
        productor2.start();
        productor3.start();




        Thread consumidor1 = new Thread(new Consumidor(buffer, "Consumidor 1"));
        Thread consumidor2 = new Thread(new Consumidor(buffer, "Consumidor 2"));


        //si el consumidor es diferente a nulo se empieza a ejecutar el hilo

            consumidor1.start();
            consumidor2.start();

        //ahora validar a que todos esperen aser terminados
        try {
            productor1.join();
            productor2.join();
            productor3.join();
            consumidor1.join();
            consumidor2.join();

        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }


    }
}
