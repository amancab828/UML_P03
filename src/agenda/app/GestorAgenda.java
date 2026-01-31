package agenda.app;
//TODO: Crea todos los atributos y métodos de la clase en base al UML, la documentación y el código que ya tenéis.

import java.util.List;

import agenda.dominio.Agenda;
import agenda.dominio.Contacto;
import agenda.dominio.TipoVia;
import agenda.dominio.TipoTelefono;

/**
 * Clase de la capa de aplicación que gestiona las acciones de la Agenda desde consola.
 */
public class GestorAgenda {
	private Consola consola;
	private Agenda agenda;
	
    /**
     * Crea un gestor de agenda que utilizará una consola para interactuar con el usuario
     * y una agenda para realizar las operaciones del dominio.
     *
     * @param consola Consola utilizada para leer y escribir información.
     * @param agenda  Agenda sobre la que se realizan las operaciones.
     */
	public GestorAgenda(Consola consola, Agenda agenda) {
		this.consola = consola;
		this.agenda = agenda;
	}
  
    /**
     * Solicita al usuario los datos de un nuevo contacto, lo crea en la agenda y
     * permite definir su dirección y sus teléfonos.
     */
	// No hace falta parámetros en la firma
	public void agregarContacto() {
		Contacto contacto = agenda.crearContacto(consola.leerTextoNoVacio("Nombre: "), consola.leerTextoNoVacio("Apellidos: "), consola.leerTexto("Email (opcional): "));
		crearDireccion(contacto);
		crearTelefonos(contacto);
		consola.escribir("Contacto creado\n");
	}
  
    /**
     * Pide al usuario los datos de una dirección y la asigna al contacto indicado.
     *
     * @param contacto Contacto al que se le asignará la dirección.
     */
	private void crearDireccion(Contacto contacto) {
        TipoVia tipoVia = elegirTipoVia();
        int numero = consola.leerEntero("Número: ");
        String bloque = consola.leerTexto("Bloque (opcional): ");
        String escalera = consola.leerTexto("Escalera (opcional): ");
        String portal = consola.leerTexto("Portal (opcional): ");
        String letra = consola.leerTexto("Letra (opcional): ");
        
        contacto.definirDireccion(tipoVia, numero, bloque, escalera, portal, letra);
	}
  
    /**
     * Pregunta cuántos teléfonos se desean añadir y solicita sus datos uno a uno,
     * agregándolos al contacto indicado.
     *
     * @param contacto Contacto al que se le añadirán los teléfonos.
     */
	private void crearTelefonos(Contacto contacto) {
        int cuantos = consola.leerEntero("¿Cuántos teléfonos quieres añadir ahora?  ");
        int i = 0;
        while (i < cuantos) {
            System.out.println("\n--- Teléfono ---");
            String numTel = consola.leerTextoNoVacio("Número de teléfono: ");
            TipoTelefono tipoTel = elegirTipoTelefono();
            contacto.agregarTelefono(numTel, tipoTel);
            i++;
        }
	}
  
    /**
     * Muestra por consola todos los contactos de la agenda.
     * Si la agenda está vacía, muestra un mensaje informativo.
     */
	public void listarContactos() {
        List<Contacto> contactos = agenda.listarContactos();
        if (contactos.isEmpty()) {
            System.out.println("La agenda está vacía.");
        } else {
            for (Contacto c : contactos) {
                System.out.println(c);
                System.out.println("---------------------------------");
            }
        }
	}
  
    /**
     * Solicita un texto de búsqueda y muestra los contactos cuyo nombre o apellidos
     * coincidan con el texto indicado.
     */
	public void buscarContactos() {
        String texto = consola.leerTextoNoVacio("Buscar por nombre/apellidos: ");
        List<Contacto> resultados = agenda.buscarPorNombre(texto);

        if (resultados.isEmpty()) {
            System.out.println("No se encontraron contactos.");
        } else {
            for (Contacto c : resultados) {
                System.out.println(c);
                System.out.println("---------------------------------");
            }
        }
	}
  
    /**
     * Solicita el ID de un contacto y, si existe, lo elimina de la agenda.
     * Muestra por consola si la operación ha tenido éxito o no.
     */
	public void borrarContactos() {
        int id = consola.leerEntero("ID del contacto a borrar: ");
        boolean borrado = agenda.eliminarContactoPorId(id);
        if (borrado) {
            System.out.println("Contacto borrado.");
        } else {
            System.out.println("No existe un contacto con ese ID.");
        }
	}
  
    /**
     * Solicita el ID de un contacto y añade un nuevo teléfono si el contacto existe.
     * Si no existe, muestra un mensaje informativo.
     */
	public void agregarTelefono() {
        int id = consola.leerEntero("ID del contacto al que añadir teléfono: ");
        Contacto c = agenda.obtenerPorId(id);

        if (c == null) {
            System.out.println("No existe un contacto con ese ID.");
        } else {       	
            String numTel = consola.leerTextoNoVacio("Número de teléfono: ");
            TipoTelefono tipoTel = elegirTipoTelefono();
            c.agregarTelefono(numTel, tipoTel);                	
        	
            System.out.println("Teléfono añadido.");
        }
	}
  
    /**
     * Muestra por consola las opciones disponibles de {@link TipoVia} y devuelve el tipo elegido.
     *
     * @return Tipo de vía seleccionado por el usuario.
     */
	private TipoVia elegirTipoVia() {
        System.out.println("Tipo de vía:");
        TipoVia[] valores = TipoVia.values();

        int i = 0;
        while (i < valores.length) {
            System.out.println((i + 1) + ") " + valores[i]);
            i++;
        }

        int opcion = consola.leerEnteroRango("Elige tipo (1-" + valores.length + "): ", 1, valores.length);
        return valores[opcion - 1];
	}
  
    /**
     * Muestra por consola las opciones disponibles de {@link TipoTelefono} y devuelve el tipo elegido.
     *
     * @return Tipo de teléfono seleccionado por el usuario.
     */
	private TipoTelefono elegirTipoTelefono() {
        System.out.println("Tipo de teléfono:");
        TipoTelefono[] valores = TipoTelefono.values();

        int i = 0;
        while (i < valores.length) {
            System.out.println((i + 1) + ") " + valores[i]);
            i++;
        }

        int opcion = consola.leerEnteroRango("Elige tipo (1-" + valores.length + "): ", 1, valores.length);
        return valores[opcion - 1];
	}
}