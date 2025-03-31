/************ Archivo: GestorPreguntas.java ************/
import java.util.ArrayList;

/**
 * Clase que gestiona las preguntas del juego
 */
public class GestorPreguntas {
    private final ArrayList<Pregunta> preguntas;
    
    /**
     * Constructor del gestor de preguntas
     */
    public GestorPreguntas() {
        preguntas = new ArrayList<>();
    }
    
    /**
     * Inicializa las preguntas del juego
     */
    public void inicializarPreguntas() {
        // Pregunta 1
        Pregunta p1 = new Pregunta(
            "¿Qué es un objeto en Java?",
            new String[] {
                "Una instancia de una clase que encapsula estado y comportamiento",
                "Un tipo de dato primitivo",
                "Una función global del programa",
                "Un método estático",
                "Un archivo de código fuente"
            },
            0 // La respuesta correcta es la primera opción (índice 0)
        );
        
        // Pregunta 2
        Pregunta p2 = new Pregunta(
            "¿Qué significa JVM en Java?",
            new String[] {
                "Java Visual Machine",
                "Java Virtual Method",
                "Java Virtual Machine",
                "Java Verified Module",
                "Java Version Manager"
            },
            2 // La respuesta correcta es la tercera opción (índice 2)
        );
        
        // Pregunta 3
        Pregunta p3 = new Pregunta(
            "¿Cuál es el modificador de acceso más restrictivo en Java?",
            new String[] {
                "public",
                "protected",
                "default (sin modificador)",
                "private",
                "static"
            },
            3 // La respuesta correcta es la cuarta opción (índice 3)
        );
        
        // Pregunta 4
        Pregunta p4 = new Pregunta(
            "¿Qué palabra clave se utiliza para heredar de una clase en Java?",
            new String[] {
                "inherits",
                "extends",
                "implements",
                "super",
                "this"
            },
            1 // La respuesta correcta es la segunda opción (índice 1)
        );
        
        // Pregunta 5
        Pregunta p5 = new Pregunta(
            "¿Qué método se ejecuta automáticamente cuando se crea un objeto en Java?",
            new String[] {
                "finalize()",
                "main()",
                "init()",
                "start()",
                "constructor()"
            },
            4 // La respuesta correcta es la quinta opción (índice 4)
        );
        
        // Agregar preguntas a la lista
        preguntas.add(p1);
        preguntas.add(p2);
        preguntas.add(p3);
        preguntas.add(p4);
        preguntas.add(p5);
    }
    
    /**
     * Obtiene la lista de preguntas
     * @return Lista de preguntas
     */
    public ArrayList<Pregunta> getPreguntas() {
        return preguntas;
    }
    
    /**
     * Obtiene el número de preguntas
     * @return Cantidad de preguntas
     */
    public int getCantidadPreguntas() {
        return preguntas.size();
    }
    
    /**
     * Agrega una pregunta a la lista
     * @param pregunta Pregunta a agregar
     */
    public void agregarPregunta(Pregunta pregunta) {
        preguntas.add(pregunta);
    }
    
    /**
     * Elimina una pregunta de la lista
     * @param indice Índice de la pregunta a eliminar
     */
    public void eliminarPregunta(int indice) {
        if (indice >= 0 && indice < preguntas.size()) {
            preguntas.remove(indice);
        }
    }
}
