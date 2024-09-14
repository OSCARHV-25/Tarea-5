package org.upana;

import java.util.Objects;

public class Datos {
    private int id;
    private String Nombre;
    private String id_productor;
    private String id_consumidor;

    public Datos() {}

    public Datos(int id) {
        this.id = id;
    }

    public Datos(String Nombre, String id_productor, String id_consumidor) {
        this.Nombre = Nombre;
        this.id_productor = id_productor;
        this.id_consumidor = id_consumidor;
    }

    public Datos(int id, String Nombre, String id_productor, String id_consumidor) {
        this.id = id;
        this.Nombre = Nombre;
        this.id_productor = id_productor;
        this.id_consumidor = id_consumidor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getId_productor() {
        return id_productor;
    }

    public void setId_productor(String id_productor) {
        this.id_productor = id_productor;
    }

    public String getId_consumidor() {
        return id_consumidor;
    }

    public void setId_consumidor(String id_consumidor) {
        this.id_consumidor = id_consumidor;
    }

    @Override
    public String toString() {
        return "Datos{" +
                "id=" + id +
                ", Nombre='" + Nombre + '\'' +
                ", id_productor='" + id_productor + '\'' +
                ", id_consumidor='" + id_consumidor + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Datos datos = (Datos) o;
        return id == datos.id && Objects.equals(Nombre, datos.Nombre) && Objects.equals(id_productor, datos.id_productor) && Objects.equals(id_consumidor, datos.id_consumidor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, Nombre, id_productor, id_consumidor);
    }



}
