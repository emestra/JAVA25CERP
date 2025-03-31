/************ Archivo: VentanaJuego.java ************/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Clase que representa la ventana del juego de preguntas
 */
public class Ventana extends JFrame {
    private JPanel panelPrincipal;
    private JLabel etiquetaTitulo;
    private JLabel etiquetaPregunta;
    private JLabel etiquetaID;
    private JRadioButton[] opciones;
    private ButtonGroup grupoOpciones;
    private JButton botonSiguiente;
    private JButton botonConfirmar;
    private JButton botonAtras;
    
    private final GestorPreguntas gestorPreguntas;
    private int preguntaActual;
    private int respuestasCorrectas;
    private final MenuPrincipal menuPrincipal;
    
    /**
     * Constructor de la ventana del juego
     * @param gestorPreguntas Gestor de preguntas del juego
     * @param menuPrincipal Referencia al menú principal
     */
    public Ventana(GestorPreguntas gestorPreguntas, MenuPrincipal menuPrincipal) {
        // Configuración de la ventana
        super("Juego de preguntas para Programación 3 - CeRP del Suroeste - Prof. Domingo Pérez");
        this.gestorPreguntas = gestorPreguntas;
        this.menuPrincipal = menuPrincipal;
        
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Inicializar componentes
        inicializarComponentes();
        
        // Mostrar primera pregunta
        mostrarPregunta(0);
        
        // Añadir listener para cuando se cierre esta ventana
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Mostrar el menú principal nuevamente
                menuPrincipal.setVisible(true);
            }
        });
    }
    
    /**
     * Inicializa los componentes de la interfaz
     */
    private void inicializarComponentes() {
        // Panel principal
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Panel superior
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new BorderLayout());
        etiquetaTitulo = new JLabel("Juego de preguntas para Programación 3 - CeRP del Suroeste - Prof. Domingo Pérez");
        etiquetaTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        etiquetaTitulo.setHorizontalAlignment(JLabel.CENTER);
        panelSuperior.add(etiquetaTitulo, BorderLayout.NORTH);
        
        // Panel de pregunta
        JPanel panelPregunta = new JPanel();
        panelPregunta.setLayout(new BorderLayout(5, 10));
        etiquetaID = new JLabel("ID de pregunta: Numero");
        etiquetaPregunta = new JLabel();
        etiquetaPregunta.setFont(new Font("Arial", Font.BOLD, 12));
        panelPregunta.add(etiquetaID, BorderLayout.NORTH);
        panelPregunta.add(etiquetaPregunta, BorderLayout.CENTER);
        panelSuperior.add(panelPregunta, BorderLayout.CENTER);
        
        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
        
        // Panel de opciones
        JPanel panelOpciones = new JPanel();
        panelOpciones.setLayout(new GridLayout(5, 1, 5, 5));
        opciones = new JRadioButton[5];
        grupoOpciones = new ButtonGroup();
        
        for (int i = 0; i < 5; i++) {
            opciones[i] = new JRadioButton("aca otra opcion");
            grupoOpciones.add(opciones[i]);
            panelOpciones.add(opciones[i]);
        }
        
        panelPrincipal.add(panelOpciones, BorderLayout.CENTER);
        
        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        
        botonSiguiente = new JButton("Siguiente");
        botonConfirmar = new JButton("Confirmar");
        botonAtras = new JButton("Atrás");
        
        botonSiguiente.setPreferredSize(new Dimension(100, 30));
        botonConfirmar.setPreferredSize(new Dimension(100, 30));
        botonAtras.setPreferredSize(new Dimension(100, 30));
        
        panelBotones.add(botonSiguiente);
        panelBotones.add(botonConfirmar);
        panelBotones.add(botonAtras);
        
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
        
        // Agregar eventos a los botones
        botonSiguiente.addActionListener((ActionEvent e) -> {
            siguientePregunta();
        });
        
        botonConfirmar.addActionListener((ActionEvent e) -> {
            verificarRespuesta();
        });
        
        botonAtras.addActionListener((ActionEvent e) -> {
            preguntaAnterior();
        });
        
        // Inicializar valores
        preguntaActual = 0;
        respuestasCorrectas = 0;
        
        // Agregar panel principal a la ventana
        add(panelPrincipal);
    }
    
    /**
     * Muestra una pregunta en la interfaz
     * @param indice Índice de la pregunta a mostrar
     */
    private void mostrarPregunta(int indice) {
        if (indice >= 0 && indice < gestorPreguntas.getCantidadPreguntas()) {
            Pregunta p = gestorPreguntas.getPreguntas().get(indice);
            etiquetaID.setText("ID de pregunta: " + (indice + 1));
            etiquetaPregunta.setText(p.getTexto());
            
            // Limpiar selección anterior
            grupoOpciones.clearSelection();
            
            // Mostrar opciones
            String[] opcionesPregunta = p.getOpciones();
            for (int i = 0; i < 5; i++) {
                opciones[i].setText(opcionesPregunta[i]);
            }
            
            // Actualizar pregunta actual
            preguntaActual = indice;
            
            // Habilitar/deshabilitar botones
            botonAtras.setEnabled(indice > 0);
            botonSiguiente.setEnabled(indice < gestorPreguntas.getCantidadPreguntas() - 1);
        }
    }
    
    /**
     * Avanza a la siguiente pregunta
     */
    private void siguientePregunta() {
        if (preguntaActual < gestorPreguntas.getCantidadPreguntas() - 1) {
            mostrarPregunta(preguntaActual + 1);
        }
    }
    
    /**
     * Retrocede a la pregunta anterior
     */
    private void preguntaAnterior() {
        if (preguntaActual > 0) {
            mostrarPregunta(preguntaActual - 1);
        }
    }
    
    /**
     * Verifica la respuesta seleccionada
     */
    private void verificarRespuesta() {
        // Obtener la opción seleccionada
        int seleccionada = -1;
        for (int i = 0; i < 5; i++) {
            if (opciones[i].isSelected()) {
                seleccionada = i;
                break;
            }
        }
        
        if (seleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona una opción.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Verificar si es correcta
        Pregunta p = gestorPreguntas.getPreguntas().get(preguntaActual);
        if (seleccionada == p.getRespuestaCorrecta()) {
            respuestasCorrectas++;
            JOptionPane.showMessageDialog(this, "¡Respuesta correcta!", "Resultado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Respuesta incorrecta. La opción correcta es: " + 
                    p.getOpciones()[p.getRespuestaCorrecta()], "Resultado", JOptionPane.ERROR_MESSAGE);
        }
        
        // Si es la última pregunta, mostrar resultado final
        if (preguntaActual == gestorPreguntas.getCantidadPreguntas() - 1) {
            JOptionPane.showMessageDialog(this, "Fin del juego. Has acertado " + respuestasCorrectas + 
                    " de " + gestorPreguntas.getCantidadPreguntas() + " preguntas.", "Resultado final", JOptionPane.INFORMATION_MESSAGE);
            
            // Cerrar ventana del juego y volver al menú principal
            dispose();
            menuPrincipal.setVisible(true);
        } else {
            // Pasar a la siguiente pregunta
            siguientePregunta();
        }
    }
}
