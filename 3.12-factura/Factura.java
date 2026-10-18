public class Factura {
    private String numeroPieza;
    private String descripcion;
    private int cantidad;
    private double precio;

    public Factura(String numeroPieza, String descripcion, int cantidad, double precio) {
        setNumeroPieza(numeroPieza);
        setDescripcion(descripcion);
        setCantidad(cantidad);
        setPrecio(precio);
    }
    public void setNumeroPieza(String valor) { numeroPieza = valor; }
    public String getNumeroPieza() { return numeroPieza; }
    public void setDescripcion(String valor) { descripcion = valor; }
    public String getDescripcion() { return descripcion; }
    public void setCantidad(int valor) { cantidad = valor > 0 ? valor : 0; }
    public int getCantidad() { return cantidad; }
    public void setPrecio(double valor) { precio = valor > 0 ? valor : 0.0; }
    public double getPrecio() { return precio; }
    public double obtenerMontoFactura() { return cantidad * precio; }
}
