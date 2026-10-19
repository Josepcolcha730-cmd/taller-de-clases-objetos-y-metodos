package ejercicio313;

public class Empleado {
    private String nombre;
    private String apellido;
    private double salarioMensual;

    public Empleado(String nombre, String apellido, double salarioMensual) {
        setNombre(nombre);
        setApellido(apellido);
        setSalarioMensual(salarioMensual);
    }
    public void setNombre(String valor) { nombre = valor; }
    public String getNombre() { return nombre; }
    public void setApellido(String valor) { apellido = valor; }
    public String getApellido() { return apellido; }
    public void setSalarioMensual(double valor) {
        if (valor > 0) salarioMensual = valor;
    }
    public double getSalarioMensual() { return salarioMensual; }
    public double obtenerSalarioAnual() { return salarioMensual * 12; }
}
