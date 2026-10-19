package ejercicio317;

import util.Entrada;
import javax.swing.JOptionPane;

public class PruebaPerfilMedico {
    public static void main(String[] args) {
        try {
            String nombre = Entrada.textoVentana("Primer nombre:");
            String apellido = Entrada.textoVentana("Apellido:");
            String sexo = Entrada.textoVentana("Sexo:");
            int[] fecha = Entrada.nacimientoVentana();
            double altura = Entrada.positivoVentana("Altura en centimetros:");
            double peso = Entrada.positivoVentana("Peso en kilogramos:");
            PerfilMedico perfil = new PerfilMedico(nombre, apellido, sexo,
                    fecha[1], fecha[0], fecha[2], altura, peso);
            double[] rango = perfil.calcularFrecuenciaEsperada();
            JOptionPane.showMessageDialog(null, String.format(
                    "Nombre: %s %s%nSexo: %s%nNacimiento (dia/mes/anio): %d/%d/%d%n"
                    + "Altura: %.2f cm%nPeso: %.2f kg%nEdad: %d anios%nIMC: %.2f%n"
                    + "Frecuencia maxima: %d lpm%nRango esperado: %.2f - %.2f lpm%n%n"
                    + "VALORES DE IMC (tabla del enunciado)%nBajo peso: menos de 18.5%n"
                    + "Normal: entre 18.5 y 24.9%nSobrepeso: entre 25 y 29.9%nObeso: 30 o mas%n%n"
                    + "Estimaciones del ejercicio; no constituyen una evaluacion medica.",
                    perfil.getNombre(), perfil.getApellido(), perfil.getSexo(), perfil.getDiaNacimiento(),
                    perfil.getMesNacimiento(), perfil.getAnioNacimiento(), perfil.getAltura(), perfil.getPeso(),
                    perfil.calcularEdad(), perfil.calcularIMC(), perfil.calcularFrecuenciaMaxima(), rango[0], rango[1]));
        } catch (Entrada.Cancelacion ex) { }
    }
}
