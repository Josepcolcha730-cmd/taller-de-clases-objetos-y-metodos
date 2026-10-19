package pruebas;

import ejercicio312.Factura;
import ejercicio312.PruebaFactura;
import ejercicio313.Empleado;
import ejercicio313.PruebaEmpleado;
import ejercicio314.Fecha;
import ejercicio316.FrecuenciasCardiacas;
import ejercicio317.PerfilMedico;
import util.Entrada;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Scanner;

/** Pruebas ejecutables sin bibliotecas externas. Fallan con AssertionError. */
public final class Verificacion {
    private static int comprobaciones;
    private Verificacion() { }

    private static void comprobar(boolean condicion, String caso) {
        if (!condicion) throw new AssertionError(caso);
        comprobaciones++;
    }
    private static void numero(double esperado, double obtenido, String caso) {
        comprobar(Math.abs(esperado - obtenido) < 0.00001, caso);
    }
    private static String ejecutarConsola(String entrada, Runnable programa) throws Exception {
        InputStream originalEntrada = System.in;
        PrintStream originalSalida = System.out;
        ByteArrayOutputStream salida = new ByteArrayOutputStream();
        try (PrintStream captura = new PrintStream(salida, true, "UTF-8")) {
            System.setIn(new ByteArrayInputStream(entrada.getBytes(StandardCharsets.UTF_8)));
            System.setOut(captura);
            programa.run();
        } finally {
            System.setIn(originalEntrada);
            System.setOut(originalSalida);
        }
        return salida.toString("UTF-8").replace(',', '.');
    }
    public static void main(String[] args) throws Exception {
        Factura f = new Factura("A1", "Tornillos", 5, 2.5);
        comprobar(f.getNumeroPieza().equals("A1"), "Numero de pieza");
        comprobar(f.getDescripcion().equals("Tornillos"), "Descripcion");
        numero(12.5, f.obtenerMontoFactura(), "Monto inicial");
        f.setCantidad(-3); f.setPrecio(-4);
        numero(0, f.getCantidad(), "Cantidad negativa");
        numero(0, f.getPrecio(), "Precio negativo");
        numero(0, f.obtenerMontoFactura(), "Monto corregido");
        f.setCantidad(8); f.setPrecio(2.5);
        numero(20, f.obtenerMontoFactura(), "Actualizar factura");
        f.setCantidad(0); f.setPrecio(0);
        numero(0, f.obtenerMontoFactura(), "Factura con ceros");

        Empleado a = new Empleado("Ana", "Perez", 1000);
        Empleado b = new Empleado("Luis", "Lopez", 1500);
        numero(12000, a.obtenerSalarioAnual(), "Anual empleado 1");
        numero(18000, b.obtenerSalarioAnual(), "Anual empleado 2");
        a.setSalarioMensual(a.getSalarioMensual() * 1.10);
        b.setSalarioMensual(b.getSalarioMensual() * 1.10);
        numero(13200, a.obtenerSalarioAnual(), "Aumento empleado 1");
        numero(19800, b.obtenerSalarioAnual(), "Aumento empleado 2");
        a.setSalarioMensual(-20); a.setSalarioMensual(0);
        numero(1100, a.getSalarioMensual(), "Conservar salario anterior");
        numero(0, new Empleado("A", "B", -5).getSalarioMensual(), "Salario inicial invalido");

        Fecha fecha = new Fecha(10, 18, 2026);
        comprobar(fecha.obtenerFecha().equals("10/18/2026"), "Formato fecha");
        fecha.setMes(12); fecha.setDia(31); fecha.setAnio(2027);
        comprobar(fecha.obtenerFecha().equals("12/31/2027"), "Setters fecha");
        comprobar(ejecutarConsola("", fecha::mostrarFecha).trim().equals("12/31/2027"), "Salida mostrarFecha");

        LocalDate referencia = LocalDate.of(2026, 10, 18);
        FrecuenciasCardiacas p = new FrecuenciasCardiacas("Ana", "Perez", 10, 18, 2000);
        numero(26, p.calcularEdad(referencia), "Cumpleanios hoy");
        p.setDiaNacimiento(19);
        numero(25, p.calcularEdad(referencia), "Cumpleanios pendiente");
        p.setDiaNacimiento(17);
        numero(26, p.calcularEdad(referencia), "Cumpleanios pasado");
        FrecuenciasCardiacas bisiesto = new FrecuenciasCardiacas("A", "B", 2, 29, 2000);
        numero(24, bisiesto.calcularEdad(LocalDate.of(2025, 2, 28)), "Edad antes de aniversario bisiesto");
        numero(25, bisiesto.calcularEdad(LocalDate.of(2025, 3, 1)), "Edad despues de aniversario bisiesto");
        boolean futuroRechazado = false;
        try { p.calcularEdad(LocalDate.of(1999, 1, 1)); }
        catch (IllegalArgumentException ex) { futuroRechazado = true; }
        comprobar(futuroRechazado, "Nacimiento futuro rechazado");
        LocalDate n = LocalDate.now().minusYears(26);
        p = new FrecuenciasCardiacas("Ana", "Perez", n.getMonthValue(), n.getDayOfMonth(), n.getYear());
        numero(26, p.calcularEdad(), "Edad actual");
        numero(194, p.calcularFrecuenciaMaxima(), "Maxima cardiaca");
        numero(97, p.calcularFrecuenciaEsperada()[0], "Limite 50%");
        numero(164.9, p.calcularFrecuenciaEsperada()[1], "Limite 85%");

        PerfilMedico perfil = new PerfilMedico("Ana", "Perez", "F", n.getDayOfMonth(), n.getMonthValue(), n.getYear(), 175, 70);
        numero(70.0 / (1.75 * 1.75), perfil.calcularIMC(), "Conversion cm a m e IMC");
        numero(26, perfil.calcularEdad(), "Edad del perfil");
        numero(194, perfil.calcularFrecuenciaMaxima(), "Reutilizar frecuencia maxima");
        numero(97, perfil.calcularFrecuenciaEsperada()[0], "Rango perfil inferior");
        numero(164.9, perfil.calcularFrecuenciaEsperada()[1], "Rango perfil superior");
        for (double invalido : new double[] {0, -1, Double.NaN, Double.POSITIVE_INFINITY}) {
            boolean alturaRechazada = false;
            boolean pesoRechazado = false;
            try { perfil.setAltura(invalido); } catch (IllegalArgumentException ex) { alturaRechazada = true; }
            try { perfil.setPeso(invalido); } catch (IllegalArgumentException ex) { pesoRechazado = true; }
            comprobar(alturaRechazada && pesoRechazado, "Altura y peso invalidos: " + invalido);
        }

        String factura = ejecutarConsola("A1\nTornillos\nabc\n5\nNaN\n2,50\n", () -> PruebaFactura.main(new String[0]));
        comprobar(factura.contains("Total: 12.50"), "Aplicacion Factura y reintento de entradas invalidas");
        String empleados = ejecutarConsola("Ana\nPerez\n1000\nLuis\nLopez\n1500\n", () -> PruebaEmpleado.main(new String[0]));
        comprobar(empleados.contains("12000.00") && empleados.contains("18000.00"), "Aplicacion Empleado: originales");
        comprobar(empleados.contains("13200.00") && empleados.contains("19800.00"), "Aplicacion Empleado: aumentos");
        try (Scanner scanner = new Scanner("3.25\n")) {
            String lectura = ejecutarConsola("", () -> numero(3.25, Entrada.decimal(scanner, ""), "Decimal con punto"));
            comprobar(lectura.isEmpty(), "Lectura sin mensajes de error");
        }
        for (String clase : new String[] {"ejercicio314.PruebaFecha", "ejercicio316.PruebaFrecuenciasCardiacas", "ejercicio317.PruebaPerfilMedico"}) {
            comprobar(Class.forName(clase).getMethod("main", String[].class) != null, "Cargar aplicacion: " + clase);
        }
        System.out.println("OK: " + comprobaciones + " comprobaciones. Compilacion conjunta sin clases duplicadas.");
    }
}
