package ejercicio317;

import ejercicio316.FrecuenciasCardiacas;
public class PerfilMedico {
    private String nombre;
    private String apellido;
    private String sexo;
    private int diaNacimiento;
    private int mesNacimiento;
    private int anioNacimiento;
    private double altura;
    private double peso;

    public PerfilMedico(String nombre, String apellido, String sexo,
                        int dia, int mes, int anio, double altura, double peso) {
        setNombre(nombre);
        setApellido(apellido);
        setSexo(sexo);
        setDiaNacimiento(dia);
        setMesNacimiento(mes);
        setAnioNacimiento(anio);
        setAltura(altura);
        setPeso(peso);
    }
    public void setNombre(String valor) { nombre = valor; }
    public String getNombre() { return nombre; }
    public void setApellido(String valor) { apellido = valor; }
    public String getApellido() { return apellido; }
    public void setSexo(String valor) { sexo = valor; }
    public String getSexo() { return sexo; }
    public void setDiaNacimiento(int valor) { diaNacimiento = valor; }
    public int getDiaNacimiento() { return diaNacimiento; }
    public void setMesNacimiento(int valor) { mesNacimiento = valor; }
    public int getMesNacimiento() { return mesNacimiento; }
    public void setAnioNacimiento(int valor) { anioNacimiento = valor; }
    public int getAnioNacimiento() { return anioNacimiento; }
    public void setAltura(double valor) {
        if (!Double.isFinite(valor) || valor <= 0) throw new IllegalArgumentException("Altura invalida.");
        altura = valor;
    }
    public double getAltura() { return altura; }
    public void setPeso(double valor) {
        if (!Double.isFinite(valor) || valor <= 0) throw new IllegalArgumentException("Peso invalido.");
        peso = valor;
    }
    public double getPeso() { return peso; }
    private FrecuenciasCardiacas datosCardiacos() {
        return new FrecuenciasCardiacas(nombre, apellido, mesNacimiento, diaNacimiento, anioNacimiento);
    }
    public int calcularEdad() { return datosCardiacos().calcularEdad(); }
    public int calcularFrecuenciaMaxima() { return datosCardiacos().calcularFrecuenciaMaxima(); }
    public double[] calcularFrecuenciaEsperada() { return datosCardiacos().calcularFrecuenciaEsperada(); }
    public double calcularIMC() {
        double metros = altura / 100.0;
        return peso / (metros * metros);
    }
}
