package gestor;

import modelos.Administrador;
import modelos.Usuario;

import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class GestorDatos {

    private static final String ARCHIVO_USUARIOS = "usuarios.dat";
    private static final String ARCHIVO_ADMINISTRADORES = "administradores.dat";

    private static ArrayList<Usuario> usuarios = cargar(ARCHIVO_USUARIOS);
    private static ArrayList<Administrador> administradores = cargar(ARCHIVO_ADMINISTRADORES);

    @SuppressWarnings("unchecked")
    private static <T> ArrayList<T> cargar(String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        if (!archivo.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (ArrayList<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private static <T> void guardar(ArrayList<T> lista, String nombreArchivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void guardarTodo() {
        guardar(usuarios, ARCHIVO_USUARIOS);
        guardar(administradores, ARCHIVO_ADMINISTRADORES);
    }

    public static void registrarUsuario(Usuario usuario) {
        if (buscarUsuarioPorId(usuario.getId()) != null || buscarAdministradorPorId(usuario.getId()) != null) {
            JOptionPane.showMessageDialog(null, "El ID ya está registrado como usuario o administrador.");
            return;
        }
        usuarios.add(usuario);
        guardar(usuarios, ARCHIVO_USUARIOS);
    }

    public static void registrarAdministrador(Administrador admin) {
        if (buscarAdministradorPorId(admin.getId()) != null || buscarUsuarioPorId(admin.getId()) != null) {
            JOptionPane.showMessageDialog(null, "El ID ya está registrado como administrador o usuario.");
            return;
        }
        administradores.add(admin);
        guardar(administradores, ARCHIVO_ADMINISTRADORES);
    }

    public static ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public static ArrayList<Administrador> getAdministradores() {
        return administradores;
    }

    public static Usuario buscarUsuarioPorId(String id) {
        for (Usuario u : usuarios) {
            if (u.getId().equals(id)) return u;
        }
        return null;
    }

    public static Administrador buscarAdministradorPorId(String id) {
        for (Administrador a : administradores) {
            if (a.getId().equals(id)) return a;
        }
        return null;
    }

    public static void eliminarUsuario(String id) {
        usuarios.removeIf(u -> u.getId().equals(id));
        guardar(usuarios, ARCHIVO_USUARIOS);
    }

    public static void agregarUsuario(Usuario nuevoUsuario) {
    }

    public static void agregarAdministrador(Administrador administrador) {
    }
}
