package modelo;

/**
 * Clase Persona.
 * Clase base que representa a una persona con atributos comunes, como nombres, apellidos, etc.
 * Esta clase sirve como base para otras clases, como `Empleado`.
 */
public class Persona {
    private int id;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String telefono;
    private String fecha_nacimiento;

    // Constructor sin parámetros
    public Persona() {
    }

    // Constructor con parámetros
    public Persona(int id, String nombres, String apellidos, String direccion, String telefono, String fecha_nacimiento) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fecha_nacimiento = fecha_nacimiento;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    // Métodos CRUD abstractos (a implementar en clases hijas)
    public int agregar() {
        throw new UnsupportedOperationException("Operación no soportada. Implementar en clase hija.");
    }

    public int modificar() {
        throw new UnsupportedOperationException("Operación no soportada. Implementar en clase hija.");
    }

    public int eliminar() {
        throw new UnsupportedOperationException("Operación no soportada. Implementar en clase hija.");
    }
}
