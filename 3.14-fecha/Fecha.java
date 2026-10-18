public class Fecha {
    private int mes;
    private int dia;
    private int anio;

    public Fecha(int mes, int dia, int anio) {
        setMes(mes);
        setDia(dia);
        setAnio(anio);
    }
    public void setMes(int valor) { mes = valor; }
    public int getMes() { return mes; }
    public void setDia(int valor) { dia = valor; }
    public int getDia() { return dia; }
    public void setAnio(int valor) { anio = valor; }
    public int getAnio() { return anio; }
    public String obtenerFecha() { return mes + "/" + dia + "/" + anio; }
    public void mostrarFecha() { System.out.println(obtenerFecha()); }
}
