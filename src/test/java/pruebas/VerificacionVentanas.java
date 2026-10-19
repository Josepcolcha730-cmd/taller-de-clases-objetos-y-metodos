package pruebas;

import ejercicio314.PruebaFecha;
import ejercicio316.PruebaFrecuenciasCardiacas;
import ejercicio317.PruebaPerfilMedico;
import java.awt.Component;
import java.awt.Container;
import java.awt.GraphicsEnvironment;
import java.awt.Window;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Set;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/** Integracion de los dialogos Swing del propio proceso, sin Robot ni entrada del usuario. */
public final class VerificacionVentanas {
    private VerificacionVentanas() { }

    private static JOptionPane buscar(Component c) {
        if (c instanceof JOptionPane) return (JOptionPane) c;
        if (c instanceof Container) {
            for (Component hijo : ((Container) c).getComponents()) {
                JOptionPane encontrado = buscar(hijo);
                if (encontrado != null) return encontrado;
            }
        }
        return null;
    }
    private static void caso(String nombre, Runnable programa, String[] datos, String... mensajesEsperados) throws Exception {
        List<String> mensajes = new ArrayList<>();
        Set<Window> atendidas = Collections.newSetFromMap(new IdentityHashMap<Window, Boolean>());
        int[] indice = {0};
        boolean[] expiro = {false};
        long inicio = System.nanoTime();
        Timer timer = new Timer(60, evento -> {
            if (System.nanoTime() - inicio > 15000000000L) {
                expiro[0] = true;
                for (Window ventana : Window.getWindows()) ventana.dispose();
                return;
            }
            for (Window ventana : Window.getWindows()) {
                if (!(ventana instanceof JDialog) || !ventana.isShowing() || atendidas.contains(ventana)) continue;
                JOptionPane panel = buscar(ventana);
                if (panel == null) continue;
                atendidas.add(ventana);
                if (panel.getWantsInput()) {
                    if (indice[0] >= datos.length) {
                        panel.setInputValue(null);
                        panel.setValue(JOptionPane.CANCEL_OPTION);
                    } else {
                        String dato = datos[indice[0]++];
                        panel.setInputValue(dato);
                        panel.setValue(dato == null ? JOptionPane.CANCEL_OPTION : JOptionPane.OK_OPTION);
                    }
                } else {
                    mensajes.add(String.valueOf(panel.getMessage()));
                    panel.setValue(JOptionPane.OK_OPTION);
                }
            }
        });
        SwingUtilities.invokeAndWait(timer::start);
        try { programa.run(); }
        finally {
            SwingUtilities.invokeAndWait(() -> {
                timer.stop();
                for (Window ventana : Window.getWindows()) ventana.dispose();
            });
        }
        if (expiro[0]) throw new AssertionError("Tiempo agotado: " + nombre);
        if (indice[0] != datos.length) throw new AssertionError("Entradas sin consumir: " + nombre);
        for (String esperado : mensajesEsperados) {
            boolean encontrado = mensajes.stream().anyMatch(m -> m.contains(esperado));
            if (!encontrado) throw new AssertionError(nombre + ": falta " + esperado + " en " + mensajes);
        }
        if (datos.length == 1 && datos[0] == null && !mensajes.isEmpty())
            throw new AssertionError("Cancelar mostro un resultado: " + nombre);
        System.out.println("OK: " + nombre);
    }
    public static void main(String[] args) throws Exception {
        if (GraphicsEnvironment.isHeadless()) throw new IllegalStateException("Las pruebas requieren escritorio grafico.");
        caso("Fecha: entrada invalida y resultado", () -> PruebaFecha.main(new String[0]),
                new String[] {"abc", "10", "18", "2026"}, "numero entero valido", "10/18/2026");
        caso("Frecuencias: datos y fecha invalida", () -> PruebaFrecuenciasCardiacas.main(new String[0]),
                new String[] {"Ana", "Perez", "2", "31", "2000", "10", "18", "2000"},
                "fecha valida", "Ana Perez", "Frecuencia maxima:", "Rango esperado:");
        caso("Perfil: altura y peso invalidos", () -> PruebaPerfilMedico.main(new String[0]),
                new String[] {"Ana", "Perez", "F", "10", "18", "2000", "0", "175", "NaN", "70"},
                "numero positivo valido", "Ana Perez", "IMC: 22", "VALORES DE IMC");
        caso("Cancelar Fecha", () -> PruebaFecha.main(new String[0]), new String[] {null});
        caso("Cancelar Frecuencias", () -> PruebaFrecuenciasCardiacas.main(new String[0]), new String[] {null});
        caso("Cancelar Perfil", () -> PruebaPerfilMedico.main(new String[0]), new String[] {null});
        System.out.println("OK: 6 casos de integracion de ventanas.");
    }
}
