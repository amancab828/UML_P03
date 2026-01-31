package agenda.dominio;

import java.util.ArrayList;
import java.util.List;


public class Agenda {
    private List<Contacto> contactos;
    private int siguienteId;

    public Agenda() {
        this.contactos = new ArrayList<>();
        this.siguienteId = 1;
    }

    public int getSiguienteId() {
        int id = this.siguienteId;
        this.siguienteId = this.siguienteId + 1;
        return id;
    }

    public Contacto crearContacto(String nombre, String apellidos, String email) {
        int id = getSiguienteId();

        Contacto nuevoContacto = new Contacto(id, nombre, apellidos, email);
        contactos.add(nuevoContacto);
        return nuevoContacto;
    }

    public List<Contacto> listarContactos() {
        return new ArrayList<>(contactos);
    }

    public Contacto obtenerPorId(int id) {
        for (Contacto c : this.contactos) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public boolean eliminarContactoPorId(int id) {
        int i = 0;
        while (i < contactos.size()) {
            if (contactos.get(i).getId() == id) {
                contactos.remove(i);
                return true;
            }
            i++;
        }
        return false;
    }

    public List<Contacto> buscarPorNombre(String texto) {
        String t = texto.toLowerCase();
        List<Contacto> resultados = new ArrayList<>();

        for (Contacto c : contactos) {
            String nombreCompleto = (c.getNombre() + " " + c.getApellidos()).toLowerCase();
            if (nombreCompleto.contains(t)) {
                resultados.add(c);
            }
        }
        return resultados;
    }
}