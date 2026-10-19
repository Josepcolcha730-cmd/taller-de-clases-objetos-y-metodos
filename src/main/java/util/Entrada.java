package util;

import java.time.LocalDate;
import java.time.DateTimeException;
import java.util.Scanner;
import javax.swing.JOptionPane;

// Utilidad compartida: evita repetir la lectura y la validacion de datos.
public final class Entrada {
    private Entrada() { }
    public static class Cancelacion extends RuntimeException {
        private static final long serialVersionUID = 1L;
    }
    public static int entero(Scanner entrada, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try { return Integer.parseInt(entrada.nextLine().trim()); }
            catch (NumberFormatException ex) { System.out.println("Ingrese un numero entero valido."); }
        }
    }
    public static double decimal(Scanner entrada, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                double valor = Double.parseDouble(entrada.nextLine().trim().replace(',', '.'));
                if (Double.isFinite(valor)) return valor;
            } catch (NumberFormatException ex) { }
            System.out.println("Ingrese un numero decimal valido.");
        }
    }
    public static String textoVentana(String mensaje) {
        String texto = JOptionPane.showInputDialog(null, mensaje);
        if (texto == null) throw new Cancelacion();
        return texto.trim();
    }
    public static int enteroVentana(String mensaje) {
        while (true) {
            try { return Integer.parseInt(textoVentana(mensaje)); }
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Ingrese un numero entero valido.");
            }
        }
    }
    public static double positivoVentana(String mensaje) {
        while (true) {
            try {
                double valor = Double.parseDouble(textoVentana(mensaje).replace(',', '.'));
                if (Double.isFinite(valor) && valor > 0) return valor;
            } catch (NumberFormatException ex) { }
            JOptionPane.showMessageDialog(null, "Ingrese un numero positivo valido.");
        }
    }
    public static int[] nacimientoVentana() {
        while (true) {
            int mes = enteroVentana("Mes de nacimiento:");
            int dia = enteroVentana("Dia de nacimiento:");
            int anio = enteroVentana("Anio de nacimiento:");
            try {
                LocalDate fecha = LocalDate.of(anio, mes, dia);
                if (anio >= 1 && !fecha.isAfter(LocalDate.now())) return new int[] {mes, dia, anio};
            } catch (DateTimeException ex) { }
            JOptionPane.showMessageDialog(null, "Ingrese una fecha valida que no sea futura.");
        }
    }
}
