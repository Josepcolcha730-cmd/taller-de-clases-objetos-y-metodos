# Taller de clases, objetos y métodos

## Abrir en IntelliJ IDEA

1. Descarga o actualiza el repositorio completo. Si usas el ZIP corregido, extráelo en una carpeta nueva; no lo mezcles con los archivos anteriores.
2. Abre el archivo `pom.xml` como proyecto y selecciona un JDK 8 o posterior.
3. Espera la importación y utiliza **Build > Rebuild Project**.
4. Abre una de las clases `Prueba...` bajo `src/main/java` y ejecuta su método `main` con el botón verde.

## Organización

| Carpeta bajo src/main/java | Ejercicio | Clase ejecutable |
|---|---|---|
| ejercicio312 | 3.12 Factura | ejercicio312.PruebaFactura |
| ejercicio313 | 3.13 Empleado | ejercicio313.PruebaEmpleado |
| ejercicio314 | 3.14 Fecha | ejercicio314.PruebaFecha |
| ejercicio316 | 3.16 Frecuencias cardíacas | ejercicio316.PruebaFrecuenciasCardiacas |
| ejercicio317 | 3.17 Perfil médico | ejercicio317.PruebaPerfilMedico |
| util | Lectura compartida | No tiene main |

Existe una sola clase `util.Entrada` y una sola clase `ejercicio316.FrecuenciasCardiacas`.
PerfilMedico importa esta última. Los paquetes distinguen los cinco ejercicios y
permiten compilar todo el proyecto a la vez. Ya no se necesitan carpetas fuente independientes.

## Compilar y verificar sin Maven (Windows PowerShell)

Desde la carpeta del proyecto:

```powershell
powershell -ExecutionPolicy Bypass -File .\verificar.ps1
```

La opción de ejecución se aplica únicamente al proceso de este comando. El script
compila todos los fuentes con advertencias tratadas como errores y ejecuta las
pruebas de regresión de modelos y aplicaciones de consola.

Después puedes ejecutar cualquiera de los cinco programas:

```powershell
java -cp build ejercicio312.PruebaFactura
java -cp build ejercicio313.PruebaEmpleado
java -cp build ejercicio314.PruebaFecha
java -cp build ejercicio316.PruebaFrecuenciasCardiacas
java -cp build ejercicio317.PruebaPerfilMedico
```

Los tres últimos requieren un escritorio gráfico. Para comprobar automáticamente
también las ventanas (se abrirán y cerrarán durante las pruebas), ejecuta:

```powershell
java -cp build pruebas.VerificacionVentanas
```

Se prueban los tres programas con datos válidos e inválidos y su cancelación.
Fecha asume valores de calendario correctos, como permite el enunciado.
Los datos de ejemplo son ficticios y las fórmulas de salud son las del ejercicio académico.

Si IntelliJ conserva clases antiguas, elimina del proyecto la copia anterior de las
carpetas `3.12-factura` a `3.17-perfil-medico` y vuelve a abrir el proyecto corregido.
No deben quedar versiones antiguas de los archivos Java junto a `src`.
