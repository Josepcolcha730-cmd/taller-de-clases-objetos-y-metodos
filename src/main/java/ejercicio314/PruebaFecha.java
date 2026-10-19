package ejercicio314;

import util.Entrada;
import javax.swing.JOptionPane;

public class PruebaFecha {
    public static void main(String[] args) {
        try {
            int mes = Entrada.enteroVentana("Mes:");
            int dia = Entrada.enteroVentana("Dia:");
            int anio = Entrada.enteroVentana("Anio:");
            Fecha fecha = new Fecha(mes, dia, anio);
            fecha.mostrarFecha();
            JOptionPane.showMessageDialog(null, "Fecha (mes/dia/anio): " + fecha.obtenerFecha());
        } catch (Entrada.Cancelacion ex) {
            // Cancelar termina el programa sin mostrar un error.
        }
    }
}
