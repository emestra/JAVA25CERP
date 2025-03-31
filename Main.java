/************ Archivo: JuegoPreguntasJava.java ************/
import javax.swing.SwingUtilities;

/**
 * Clase principal que inicia la aplicación
 */
public class Main {
    public static void main(String[] args) {
        // Crear el gestor de preguntas e inicializar las preguntas
        GestorPreguntas gestorPreguntas = new GestorPreguntas();
        gestorPreguntas.inicializarPreguntas();
        
        // Iniciar la aplicación en el hilo de eventos de Swing
        SwingUtilities.invokeLater(() -> {
            // Crear y mostrar el menú principal
            MenuPrincipal menuPrincipal = new MenuPrincipal(gestorPreguntas);
            menuPrincipal.setVisible(true);
        });
    }
}
