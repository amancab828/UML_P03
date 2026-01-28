package agenda.app;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase de la capa de aplicación que centraliza la entrada y salida de datos por consola.
 */
public class Consola {

	// Faltaba agregar el atributo Scanner
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
    	String texto;
    		
    	escribir(mensaje);
    	texto = sc.nextLine().trim();
    	
    	return texto;
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
    		String texto;
    		
    	    do {
    	        escribir(mensaje);
    	        texto = sc.nextLine().trim();
    	        if (texto.isEmpty()) {
    	            escribirLinea("Error: el texto no puede estar vacío.");
    	        }
    	    } while (texto.isEmpty());
		
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
        	boolean error;
        	
    	    do {
    	        try {
    	            escribir(mensaje);
    	            numero = sc.nextInt();
    	            sc.nextLine();
    	            error = false;
    	        } catch (InputMismatchException e) {
    	            escribirLinea("Error: debes introducir un número entero válido.");
    	            sc.nextLine();
    	            error = true;   	            
    	        }
    	    } while (error);
        	
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
        int numero = 0;
        do {
        		numero = leerEntero(mensaje);
            if (numero < min || numero > max) {
                escribirLinea("Error: el número debe estar entre " + min + " y " + max + ".");
            }
        } while (numero < min || numero > max);

        return numero;
    }
}