package agenda.app;

import java.util.List;

import agenda.dominio.*;

//TODO: Crea todos los atributos y métodos de la clase en base al UML, la documentación y el código que ya tenéis.

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
		this.agenda  = agenda;
	}

  /**
   * Solicita al usuario los datos de un nuevo contacto, lo crea en la agenda y
   * permite definir su dirección y sus teléfonos.
   */
	public void agregarContacto(Consola consola, Agenda agenda) {
		
	    int id = agenda.getSiguienteId();
	    String nombre = consola.leerTexto("Nombre: ");
	    String apellidos = consola.leerTexto("Apellidos: ");
	    String email = consola.leerTexto("Email: ");

	    Direccion direccion = crearDireccion();

	    Contacto contacto = new Contacto(id, nombre, apellidos, email, direccion);

	    crearTelefonos(contacto);
	    
	    agenda.crearContacto(contacto);
	    consola.escribirLinea("Contacto añadido: " + contacto.getNombre());
	}


  /**
   * Pide al usuario los datos de una dirección y la asigna al contacto indicado.
   *
   * @param contacto Contacto al que se le asignará la dirección.
   */
	
	// Le he quitado los parametros y he añadido que devuelva el objeto para poder crear un contacto
	private Direccion crearDireccion() {
	    consola.escribirLinea("--- Dirección ---");

	    TipoVia tipoVia = elegirTipoVia();
	    int numero = consola.leerEntero("Número: ");
	    String bloque = consola.leerTexto("Bloque: ");
	    String escalera = consola.leerTexto("Escalera: ");
	    String portal = consola.leerTexto("Portal: ");
	    String letra = consola.leerTexto("Letra: ");
	    
	    Direccion direccion = new Direccion(tipoVia, numero, bloque, escalera, portal, letra);
	    
	    return direccion;
	}

  /**
   * Pregunta cuántos teléfonos se desean añadir y solicita sus datos uno a uno,
   * agregándolos al contacto indicado.
   *
   * @param contacto Contacto al que se le añadirán los teléfonos.
   */
	private void crearTelefonos(Contacto contacto) {
	    consola.escribirLinea("--- Teléfonos ---");

	    boolean mas = true;
	    while (mas) {
	        TipoTelefono tipo = elegirTipoTelefono();
	        String numero = consola.leerTexto("Número de teléfono: ");

	        Telefono t = new Telefono(numero, tipo);
	        contacto.agregarTelefono(t);

	        String resp = consola.leerTexto("¿Añadir otro teléfono? (s/n): ");
	        mas = resp.equalsIgnoreCase("s");
	    }
	}


  /**
   * Muestra por consola todos los contactos de la agenda.
   * Si la agenda está vacía, muestra un mensaje informativo.
   */
	public void listarContactos() {
		// Mirar bien las listas, no las hemos dado
	    List<Contacto> lista = agenda.listarContactos();

	    if (lista.isEmpty()) {
	        consola.escribirLinea("La agenda está vacía.");
	    } else {
	        consola.escribirLinea("--- LISTA DE CONTACTOS ---");
	        for (Contacto c : lista) {
	            consola.escribirLinea(c.toString());
	        }
	    }
	}

  /**
   * Solicita un texto de búsqueda y muestra los contactos cuyo nombre o apellidos
   * coincidan con el texto indicado.
   */
	public void buscarContactos() {
	    String texto = consola.leerTexto("Introduce nombre o apellidos a buscar: ");
	    
	    // Mirar bien las listas, no las hemos dado
	    List<Contacto> resultados = agenda.buscarPorNombre(texto);

	    if (resultados.isEmpty()) {
	        consola.escribirLinea("No se encontraron contactos que coincidan.");
	    } else {
	        consola.escribirLinea("--- RESULTADOS DE BÚSQUEDA ---");
	        for (Contacto c : resultados) {
	            consola.escribirLinea(c.toString());
	        }
	    }
	}

  /**
   * Solicita el ID de un contacto y, si existe, lo elimina de la agenda.
   * Muestra por consola si la operación ha tenido éxito o no.
   */
	public void borrarContactos() {
	    int id = consola.leerEntero("Introduce el ID del contacto a eliminar: ");
	    boolean eliminado = agenda.eliminarContactoPorId(id);

	    if (eliminado) {
	        consola.escribirLinea("Contacto eliminado correctamente.");
	    } else {
	        consola.escribirLinea("No existe un contacto con ese ID.");
	    }
	}

  /**
   * Solicita el ID de un contacto y añade un nuevo teléfono si el contacto existe.
   * Si no existe, muestra un mensaje informativo.
   */
	public void agregarTelefono() {
	    int id = consola.leerEntero("Introduce el ID del contacto: ");
	    Contacto contacto = agenda.obtenerPorId(id);

	    if (contacto == null) {
	        consola.escribirLinea("No existe un contacto con ese ID.");
	        return;
	    }

	    TipoTelefono tipo = elegirTipoTelefono();
	    String numero = consola.leerTexto("Número del teléfono: ");

	    Telefono t = new Telefono(numero, tipo);
	    contacto.agregarTelefono(t);

	    consola.escribirLinea("Teléfono añadido correctamente.");
	}

  /**
   * Muestra por consola las opciones disponibles de {@link TipoVia} y devuelve el tipo elegido.
   *
   * @return Tipo de vía seleccionado por el usuario.
   */
	private TipoVia elegirTipoVia() {
	    consola.escribirLinea("Tipo de vía:");
	    consola.escribirLinea("1. Calle");
	    consola.escribirLinea("2. Avenida");
	    consola.escribirLinea("3. Plaza");
	    consola.escribirLinea("4. Carretera");


	    int op = consola.leerEnteroRango("Elige opción: ", 1, 4);

	    switch (op) {
	        case 1: return TipoVia.CALLE;
	        case 2: return TipoVia.AVENIDA;
	        case 3: return TipoVia.PLAZA;
	        case 4: return TipoVia.CARRETERA;
	        default: return TipoVia.CARRETERA;
	    }
	}


  /**
   * Muestra por consola las opciones disponibles de {@link TipoTelefono} y devuelve el tipo elegido.
   *
   * @return Tipo de teléfono seleccionado por el usuario.
   */
	private TipoTelefono elegirTipoTelefono() {
	    consola.escribirLinea("Tipo de teléfono:");
	    consola.escribirLinea("1. Casa");
	    consola.escribirLinea("2. Trabajo");
	    consola.escribirLinea("3. Personal");
	    consola.escribirLinea("4. Movil");
	    consola.escribirLinea("5. Otro");


	    int op = consola.leerEnteroRango("Elige opción: ", 1, 5);

	    switch (op) {
	        case 1: return TipoTelefono.CASA;
	        case 2: return TipoTelefono.TRABAJO;
	        case 3: return TipoTelefono.PERSONAL;
	        case 4: return TipoTelefono.MOVIL;
	        case 5: return TipoTelefono.OTRO;
	        default: return TipoTelefono.OTRO;
	    }
	}

}
