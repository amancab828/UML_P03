package agenda.app;

public class Menu {
	//TODO: Crear la clase Menu completa, con la documentación 
	//  (fijaros en la clase Consola, en el UML y en el código que ya tenéis y estamos refactorizando).
	private Consola consola;
	
	public Menu(Consola consola) {
		this.consola = consola;
	}
	
	public void mostrar() {
	    consola.escribirLinea("=== MENÚ DE LA AGENDA ===");
	    consola.escribirLinea("1. Agregar contacto");
	    consola.escribirLinea("2. Listar contactos");
	    consola.escribirLinea("3. Buscar contactos");
	    consola.escribirLinea("4. Borrar contacto");
	    consola.escribirLinea("5. Agregar teléfono a contacto");
	    consola.escribirLinea("0. Salir");
	}
	
	public int leerOpcion() {
		return consola.leerEnteroRango("Eliga una opción", 0, 5);
	}
}
