import javax.swing.JOptionPane;

public class PruebaFrecuenciasCardiacas {
    public static void main(String[] args) {
        try {
            String nombre = Entrada.textoVentana("Primer nombre:");
            String apellido = Entrada.textoVentana("Apellido:");
            int[] fecha = Entrada.nacimientoVentana();
            FrecuenciasCardiacas persona = new FrecuenciasCardiacas(nombre, apellido,
                    fecha[0], fecha[1], fecha[2]);
            double[] rango = persona.calcularFrecuenciaEsperada();
            JOptionPane.showMessageDialog(null, String.format(
                    "Nombre: %s %s%nNacimiento (mes/dia/anio): %d/%d/%d%nEdad: %d anios%n"
                    + "Frecuencia maxima: %d lpm%nRango esperado: %.2f - %.2f lpm%n%n"
                    + "Estimaciones del ejercicio; no constituyen una evaluacion medica.",
                    persona.getNombre(), persona.getApellido(), persona.getMesNacimiento(),
                    persona.getDiaNacimiento(), persona.getAnioNacimiento(), persona.calcularEdad(),
                    persona.calcularFrecuenciaMaxima(), rango[0], rango[1]));
        } catch (Entrada.Cancelacion ex) { }
    }
}
