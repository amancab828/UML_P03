package agenda.app;

import java.util.Scanner;

/**
 * Clase de la capa de aplicación que centraliza la entrada y salida de datos por consola.
 */
public class Consola {

	private Scanner sc;

	/**
     * Crea un objeto Consola e inicializa el lector de entrada estándar.
     */
    public Consola() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Cierra el recurso Scanner asociado a la entrada estándar.
     * Debe llamarse al finalizar la ejecución del programa.
     */
    public void cerrar() {
        this.sc.close();
    }

    /**
     * Muestra un texto por consola seguido de un salto de línea.
     *
     * @param texto Texto que se desea mostrar.
     */
    public void escribirLinea(String texto) {
        System.out.println(texto);
    }

    /**
     * Muestra un texto por consola sin salto de línea.
     *
     * @param texto Texto que se desea mostrar.
     */
    public void escribir(String texto) {
        System.out.print(texto);
    }

    //TODO: Métodos que debéis realizar vosotros.
    //      **IMPORTANTE** No podéis usar "print", ni "println" de aquí en adelante, 
    //      pero si hacer llamadas a métodos ya creados como "escribir" y "escribirLinea".
  
    /**
     * Muestra un mensaje por consola y lee una línea de texto introducida por el usuario.
     *
     * @param mensaje Mensaje que se muestra antes de la lectura.
     * @return Texto introducido por el usuario, sin espacios iniciales ni finales.
     */
    public String leerTexto(String mensaje) {
        System.out.print(mensaje);
        String texto = sc.nextLine();
        return texto.trim();
    }

    /**
     * Muestra un mensaje por consola y lee un texto no vacío.
     * Si el usuario introduce una cadena vacía o solo con espacios,
     * se vuelve a pedir el dato.
     *
     * @param mensaje Mensaje que se muestra antes de la lectura.
     * @return Texto no vacío introducido por el usuario.
     */
    public String leerTextoNoVacio(String mensaje) {
        String texto = "";
        while (texto.isBlank()) {
            System.out.print(mensaje);
            texto = sc.nextLine();
            texto = texto.trim();
            if (texto.isBlank()) {
                System.out.println("ERROR - No puede estar vacío.");
            }
        }
        return texto;
    }

    /**
     * Muestra un mensaje por consola y lee un número entero.
     * Si el valor introducido no es un entero válido, se vuelve a pedir.
     *
     * @param mensaje Mensaje que se muestra antes de la lectura.
     * @return Número entero introducido por el usuario.
     */
    public int leerEntero(String mensaje) {
        int numero = 0;
        boolean ok = false;

        while (!ok) {
            System.out.print(mensaje);
            String texto = sc.nextLine();
            texto = texto.trim();

            try {
                numero = Integer.parseInt(texto);
                ok = true;
            } catch (NumberFormatException e) {
                System.out.println("ERROR - Introduce un número entero válido.");
            }
        }
        return numero;
    }

    /**
     * Lee un número entero dentro de un rango determinado.
     * Si el número está fuera del rango, se vuelve a solicitar.
     *
     * @param mensaje Mensaje que se muestra antes de la lectura.
     * @param min Valor mínimo permitido (incluido).
     * @param max Valor máximo permitido (incluido).
     * @return Número entero dentro del rango indicado.
     */
    public int leerEnteroRango(String mensaje, int min, int max) {
        int numero = leerEntero(mensaje);
        while (numero < min || numero > max) {
            System.out.println("ERROR - Debe estar entre " + min + " y " + max + ".");
            numero = leerEntero(mensaje);
        }
        return numero;
    }
}