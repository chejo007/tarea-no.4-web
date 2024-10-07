package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Clase Puesto.
 * Responsable de manejar la información relacionada con los puestos de trabajo.
 * Se conecta a la base de datos para obtener la información y retornarla en estructuras de datos útiles.
 */
public class Puesto {
    // Atributos de la clase Puesto
    private int id_puesto;
    private String puesto;
    private Conexion cn;

    // Constructor sin parámetros
    public Puesto() {
        cn = new Conexion(); // Inicializar la conexión en el constructor
    }

    // Constructor con parámetros
    public Puesto(int id_puesto, String puesto) {
        this.id_puesto = id_puesto;
        this.puesto = puesto;
        cn = new Conexion(); // Inicializar la conexión en el constructor
    }

    // Métodos getter y setter para id_puesto y puesto
    public int getId_puesto() {
        return id_puesto;
    }

    public void setId_puesto(int id_puesto) {
        this.id_puesto = id_puesto;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    /**
     * Método para obtener un HashMap de los puestos disponibles en la base de datos.
     * Utiliza la conexión a la base de datos para ejecutar una consulta y cargar los resultados en un HashMap.
     * El HashMap contiene pares de <ID del Puesto, Nombre del Puesto> para ser utilizados en los formularios.
     * 
     * @return HashMap<String, String> con el id y nombre de cada puesto.
     */
   public HashMap<String, String> drop_sangre() {
    HashMap<String, String> drop = new HashMap<>();
    try {
        cn = new Conexion(); // Asegúrate de inicializar la conexión aquí
        cn.abrir_conexion(); // Abre la conexión antes de usarla
        
        if (cn.conexionBD != null) { // Verificar si la conexión fue establecida correctamente
            String query = "SELECT id_puesto as id, puesto FROM puestos;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);

            // Agrega los resultados al HashMap
            while (consulta.next()) {
                drop.put(consulta.getString("id"), consulta.getString("puesto"));
            }
        } else {
            System.out.println("Error: La conexión a la base de datos no fue establecida.");
        }
    } catch (SQLException ex) {
        System.out.println("Error en drop_sangre(): " + ex.getMessage());
    } finally {
        // Asegúrate de cerrar la conexión para liberar recursos
        if (cn != null) {
            cn.cerrar_conexion();
        }
    }
    return drop;
}
}
