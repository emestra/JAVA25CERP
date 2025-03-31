/************ Archivo: Pregunta.java ************/
/**
 * Clase que representa una pregunta del juego
 */
public class Pregunta {
    private final String texto;
    private final String[] opciones;
    private final int respuestaCorrecta;
    
    /**
     * Constructor de la pregunta
     * @param texto Texto de la pregunta
     * @param opciones Arreglo con las opciones de respuesta
     * @param respuestaCorrecta Índice de la respuesta correcta
     */
    public Pregunta(String texto, String[] opciones, int respuestaCorrecta) {
        this.texto = texto;
        this.opciones = opciones;
        this.respuestaCorrecta = respuestaCorrecta;
    }
    
    /**
     * Obtiene el texto de la pregunta
     * @return Texto de la pregunta
     */
    public String getTexto() {
        return texto;
    }
    
    /**
     * Obtiene las opciones de respuesta
     * @return Arreglo con las opciones
     */
    public String[] getOpciones() {
        return opciones;
    }
    
    /**
     * Obtiene el índice de la respuesta correcta
     * @return Índice de la respuesta correcta
     */
    public int getRespuestaCorrecta() {
        return respuestaCorrecta;
    }
}
