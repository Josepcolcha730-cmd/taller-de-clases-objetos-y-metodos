import java.time.LocalDate;
import java.time.Period;

public class FrecuenciasCardiacas {
    private String nombre;
    private String apellido;
    private int mesNacimiento;
    private int diaNacimiento;
    private int anioNacimiento;

    public FrecuenciasCardiacas(String nombre, String apellido, int mes, int dia, int anio) {
        setNombre(nombre);
        setApellido(apellido);
        mesNacimiento = mes;
        diaNacimiento = dia;
        anioNacimiento = anio;
    }
    public void setNombre(String valor) { nombre = valor; }
    public String getNombre() { return nombre; }
    public void setApellido(String valor) { apellido = valor; }
    public String getApellido() { return apellido; }
    public void setMesNacimiento(int valor) { mesNacimiento = valor; }
    public int getMesNacimiento() { return mesNacimiento; }
    public void setDiaNacimiento(int valor) { diaNacimiento = valor; }
    public int getDiaNacimiento() { return diaNacimiento; }
    public void setAnioNacimiento(int valor) { anioNacimiento = valor; }
    public int getAnioNacimiento() { return anioNacimiento; }
    public int calcularEdad() { return calcularEdad(LocalDate.now()); }
    public int calcularEdad(LocalDate referencia) {
        LocalDate nacimiento = LocalDate.of(anioNacimiento, mesNacimiento, diaNacimiento);
        if (nacimiento.isAfter(referencia)) {
            throw new IllegalArgumentException("El nacimiento no puede ser posterior a la fecha actual.");
        }
        return Period.between(nacimiento, referencia).getYears();
    }
    public int calcularFrecuenciaMaxima() { return 220 - calcularEdad(); }
    public double calcularFrecuenciaMinimaEsperada() { return calcularFrecuenciaMaxima() * 0.50; }
    public double calcularFrecuenciaMaximaEsperada() { return calcularFrecuenciaMaxima() * 0.85; }
    public double[] calcularFrecuenciaEsperada() {
        return new double[] { calcularFrecuenciaMinimaEsperada(), calcularFrecuenciaMaximaEsperada() };
    }
}
