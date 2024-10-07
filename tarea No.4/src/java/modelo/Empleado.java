package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;

/**
 * Clase Empleado.
 * Responsable de gestionar la información relacionada con los empleados.
 * Hereda de la clase Persona y contiene la funcionalidad de conexión a la base de datos para operaciones CRUD.
 */
public class Empleado extends Persona {
    private String codigo;
    private int id_puesto;
    private Conexion cn;

    // Constructor sin parámetros
    public Empleado() {
        cn = new Conexion(); // Inicializa la conexión
    }

    // Constructor con parámetros
    public Empleado(String codigo, int id_puesto, int id, String nombres, String apellidos, String direccion, String telefono, String fecha_nacimiento) {
        super(id, nombres, apellidos, direccion, telefono, fecha_nacimiento);
        this.codigo = codigo;
        this.id_puesto = id_puesto;
        cn = new Conexion(); // Inicializa la conexión
    }

    // Métodos getter y setter
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getId_puesto() {
        return id_puesto;
    }

    public void setId_puesto(int id_puesto) {
        this.id_puesto = id_puesto;
    }

    /**
     * Método para leer todos los registros de empleados desde la base de datos.
     * @return DefaultTableModel que contiene todos los empleados con sus respectivos campos.
     */
    public DefaultTableModel leer() {
    DefaultTableModel tabla = new DefaultTableModel(); 
    try {
        cn = new Conexion(); // Asegúrate de inicializar la conexión aquí
        cn.abrir_conexion(); // Abre la conexión antes de usarla
        
        if (cn.conexionBD != null) { // Verificar si la conexión fue establecida correctamente
            String query = "SELECT e.id_empleado as id, e.codigo, e.nombres, e.apellidos, e.direccion, e.telefono, e.fecha_nacimiento, p.puesto, p.id_puesto FROM empleados as e INNER JOIN puestos as p ON e.id_puesto = p.id_puesto;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);

            String encabezado[] = {"id", "codigo", "nombres", "apellidos", "direccion", "telefono", "nacimiento", "puesto", "id_puesto"};
            tabla.setColumnIdentifiers(encabezado);

            String datos[] = new String[9];
            while (consulta.next()) {
                datos[0] = consulta.getString("id");
                datos[1] = consulta.getString("codigo");
                datos[2] = consulta.getString("nombres");
                datos[3] = consulta.getString("apellidos");
                datos[4] = consulta.getString("direccion");
                datos[5] = consulta.getString("telefono");
                datos[6] = consulta.getString("fecha_nacimiento");
                datos[7] = consulta.getString("puesto");
                datos[8] = consulta.getString("id_puesto");
                tabla.addRow(datos);
            }
        } else {
            System.out.println("Error: La conexión a la base de datos no fue establecida.");
        }
        cn.cerrar_conexion();
    } catch (SQLException ex) {
        System.out.println("Error en leer(): " + ex.getMessage());
    }
    return tabla;
}


    /**
     * Método para agregar un nuevo empleado a la base de datos.
     * @return int que indica el estado del insert (1 si es exitoso, 0 si falló).
     */
    @Override
    public int agregar() {
        int retorno = 0;
        try {
            PreparedStatement Parametro;
            String query = "INSERT INTO empleados (codigo, nombres, apellidos, direccion, telefono, fecha_nacimiento, id_puesto) VALUES (?,?,?,?,?,?,?);";
            cn.abrir_conexion();
            Parametro = cn.conexionBD.prepareStatement(query);
            Parametro.setString(1, getCodigo());
            Parametro.setString(2, getNombres());
            Parametro.setString(3, getApellidos());
            Parametro.setString(4, getDireccion());
            Parametro.setString(5, getTelefono());
            Parametro.setString(6, getFecha_nacimiento());
            Parametro.setInt(7, getId_puesto());
            retorno = Parametro.executeUpdate();
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error en agregar(): " + ex.getMessage());
        }
        return retorno;
    }

    /**
     * Método para modificar un empleado existente en la base de datos.
     * @return int que indica el estado de la modificación (1 si es exitosa, 0 si falló).
     */
    @Override
    public int modificar() {
        int retorno = 0;
        try {
            PreparedStatement Parametro;
            String query = "UPDATE empleados SET codigo=?, nombres=?, apellidos=?, direccion=?, telefono=?, fecha_nacimiento=?, id_puesto=? WHERE id_empleado = ?;";
            cn.abrir_conexion();
            Parametro = cn.conexionBD.prepareStatement(query);
            Parametro.setString(1, getCodigo());
            Parametro.setString(2, getNombres());
            Parametro.setString(3, getApellidos());
            Parametro.setString(4, getDireccion());
            Parametro.setString(5, getTelefono());
            Parametro.setString(6, getFecha_nacimiento());
            Parametro.setInt(7, getId_puesto());
            Parametro.setInt(8, getId());
            retorno = Parametro.executeUpdate();
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error en modificar(): " + ex.getMessage());
        }
        return retorno;
    }

    /**
     * Método para eliminar un empleado de la base de datos.
     * @return int que indica el estado de la eliminación (1 si es exitosa, 0 si falló).
     */
    @Override
    public int eliminar() {
        int retorno = 0;
        try {
            PreparedStatement Parametro;
            String query = "DELETE FROM empleados WHERE id_empleado = ?;";
            cn.abrir_conexion();
            Parametro = cn.conexionBD.prepareStatement(query);
            Parametro.setInt(1, getId());
            retorno = Parametro.executeUpdate();
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error en eliminar(): " + ex.getMessage());
        }
        return retorno;
    }
}
