package ejercicio313;

import util.Entrada;
import java.util.Scanner;

public class PruebaEmpleado {
    private static Empleado leerEmpleado(Scanner entrada, int numero) {
        System.out.println("\nEmpleado " + numero);
        System.out.print("Nombre: ");
        String nombre = entrada.nextLine();
        System.out.print("Apellido paterno: ");
        String apellido = entrada.nextLine();
        double salario = Entrada.decimal(entrada, "Salario mensual: ");
        return new Empleado(nombre, apellido, salario);
    }
    private static void mostrar(Empleado empleado) {
        System.out.printf("%s %s: salario anual = %.2f%n", empleado.getNombre(),
                empleado.getApellido(), empleado.obtenerSalarioAnual());
    }
    public static void main(String[] args) {
        try (Scanner entrada = new Scanner(System.in)) {
            Empleado primero = leerEmpleado(entrada, 1);
            Empleado segundo = leerEmpleado(entrada, 2);
            System.out.println("\nAntes del aumento:");
            mostrar(primero);
            mostrar(segundo);
            primero.setSalarioMensual(primero.getSalarioMensual() * 1.10);
            segundo.setSalarioMensual(segundo.getSalarioMensual() * 1.10);
            System.out.println("\nDespues del aumento del 10%:");
            mostrar(primero);
            mostrar(segundo);
        }
    }
}
