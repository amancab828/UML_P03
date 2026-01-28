package agenda.app;

import agenda.dominio.Agenda;

public class Main {

    public static void main(String[] args) {

        //TODO: Crear una instancia de la clase Consola
        Consola consola = new Consola();
        //TODO: Crear una instancia de la clase Menu
        Menu menu = new Menu(consola);
        //TODO: Crear una instancia de la clase Agenda
        Agenda agenda = new Agenda();
        //TODO: Crear una instancia de la clase GestorAgenda
        GestorAgenda gestorAgenda = new GestorAgenda(consola, agenda);
        
        
        //TODO: Declarar una variable opcion
        int opcion;
        
        //TODO: Realizar un bucle do-while para gestionar el menú de la aplicación (6 opciones, 0 para Salir)
        //      Utilizad los métodos de las instancias que habéis generado previamente y la variable opcion
        do {
            menu.mostrar();
            opcion = menu.leerOpcion();

            switch (opcion) {
            	case 1 -> gestorAgenda.agregarContacto(consola, agenda);
            	case 2 -> {
            		gestorAgenda.listarContactos();
                    consola.leerTexto("\nPulsa Enter para volver al menú...");
            	}
            	case 3 -> {
            		gestorAgenda.buscarContactos();
                    consola.leerTexto("\nPulsa Enter para volver al menú...");
            	}
            	case 4 -> gestorAgenda.borrarContactos();
            	case 5 -> gestorAgenda.agregarTelefono();
            	case 0 -> consola.escribirLinea("¡Hasta luego!");
            	default -> consola.escribirLinea("Opción no válida, inténtalo de nuevo.");
            }

        } while (opcion != 0);
        
        //TODO: Cerrar el recurso Scanner asociado a la entrada estándar (ver métodos de la clase Consola).
        consola.cerrar();
    }
}
