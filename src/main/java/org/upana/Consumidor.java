package org.upana;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Consumidor implements Runnable {

    private final BufferCompartido buffer;
    private final String idConsumidor;
    private static final Lock lock = new ReentrantLock(); //establecemos un bloqueo para que solo un hilo acceda a ala bd

    public Consumidor(BufferCompartido buffer, String idConsumidor) { //inicializa el constreuctor el buffer y el id del consumidor
        this.buffer = buffer;
        this.idConsumidor = idConsumidor;
    }

    @Override
    public void run() {
        try (Connection conexion = Conexion.getConexion()) { //obtiene la conexion a la bd
            while (true) {
                String mensaje = buffer.consumir();//toma el mensaje
                if (mensaje.startsWith("END")) {
                    break;// si el mensaje es end sale del bucle
                }

                lock.lock(); //aquiere bloqueo para
                try {
                    String[] partes = mensaje.split(",", 3);
                    if (partes.length == 3) {
                        String nombreCompleto = partes[0] + " " + partes[1];
                        String idProductor = partes[2];

                        // Crear objeto Datos sin ID
                        Datos datos = new Datos(nombreCompleto, idProductor, idConsumidor);

                        String sql = "INSERT INTO consumidor (Nombre, id_productor, id_consumidor) VALUES (?, ?, ?)";

                        try (PreparedStatement ps = conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                            ps.setString(1, datos.getNombre());
                            ps.setString(2, datos.getId_productor());
                            ps.setString(3, datos.getId_consumidor());
                            ps.executeUpdate();

                            try (ResultSet rs = ps.getGeneratedKeys()) {
                                if (rs.next()) {
                                    int id = rs.getInt(1);
                                    datos.setId(id);  // Asignar el ID generado al objeto Datos
                                    System.out.println("Consumido: " + datos);
                                }
                            }
                        }
                    }
                } finally {
                    lock.unlock();
                }
            }
        } catch (InterruptedException | SQLException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}