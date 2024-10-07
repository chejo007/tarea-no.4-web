package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase Conexion.
 * Responsable de manejar la conexión a la base de datos MySQL.
 * Incluye métodos para abrir y cerrar la conexión, que se utilizarán en otras clases.
 */
public class Conexion {
    // La conexión a la base de datos
    public Connection conexionBD;
    
    // Datos necesarios para la conexión a la base de datos
    private final String puerto = "3306"; // Puerto en el que se ejecuta la base de datos
    private final String bd = "empleado_web_bd"; // Nombre de la base de datos
    private final String urlConexion = String.format("jdbc:mysql://localhost:%s/%s?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", puerto, bd);
    private final String usuario = "root"; // Usuario de la base de datos
    private final String contra = "1234"; // Contraseña de la base de datos
    private final String jdbc = "com.mysql.cj.jdbc.Driver";  // Driver de conexión para MySQL

    /**
     * Método para abrir la conexión a la base de datos.
     * Utiliza el driver JDBC y los parámetros definidos para establecer la conexión.
     * Imprime un mensaje en caso de éxito o error.
     */
    public void abrir_conexion() {
        try {
            // Cargar el controlador de MySQL
            Class.forName(jdbc);
            // Establecer la conexión utilizando la URL, usuario y contraseña
            conexionBD = DriverManager.getConnection(urlConexion, usuario, contra);
            System.out.println("Conexion Exitosa...");
        } catch (ClassNotFoundException | SQLException ex) {
            // Imprimir cualquier error que ocurra al abrir la conexión
            System.out.println("Error al abrir la conexión: " + ex.getMessage());
        }
    }

    /**
     * Método para cerrar la conexión a la base de datos.
     * Verifica si la conexión es distinta de null antes de intentar cerrarla.
     * Imprime un mensaje en caso de éxito o error.
     */
    public void cerrar_conexion() {
        try {
            // Verificar si la conexión está activa
            if (conexionBD != null) {
                conexionBD.close();
                System.out.println("Conexion Cerrada...");
            }
        } catch (SQLException ex) {
            // Imprimir cualquier error que ocurra al cerrar la conexión
            System.out.println("Error al cerrar la conexión: " + ex.getMessage());
        }
    }
}
