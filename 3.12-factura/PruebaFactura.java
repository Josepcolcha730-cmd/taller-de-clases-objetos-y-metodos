import java.util.Scanner;

public class PruebaFactura {
    public static void main(String[] args) {
        try (Scanner entrada = new Scanner(System.in)) {
            System.out.print("Numero de pieza: ");
            String numero = entrada.nextLine();
            System.out.print("Descripcion: ");
            String descripcion = entrada.nextLine();
            int cantidad = Entrada.entero(entrada, "Cantidad: ");
            double precio = Entrada.decimal(entrada, "Precio por articulo: ");
            Factura factura = new Factura(numero, descripcion, cantidad, precio);
            System.out.printf("%nPieza: %s%nDescripcion: %s%nCantidad: %d%nPrecio: %.2f%nTotal: %.2f%n",
                    factura.getNumeroPieza(), factura.getDescripcion(), factura.getCantidad(),
                    factura.getPrecio(), factura.obtenerMontoFactura());
        }
    }
}
