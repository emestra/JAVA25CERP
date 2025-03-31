/************ Archivo: MenuPrincipal.java ************/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Clase que representa la ventana del menú principal
 */
public class MenuPrincipal extends JFrame {
    private final GestorPreguntas gestorPreguntas;
    private JLabel etiquetaCantidad;
    private JButton botonJugar;
    
    /**
     * Constructor del menú principal
     * @param gestorPreguntas Gestor de preguntas del juego
     */
    public MenuPrincipal(GestorPreguntas gestorPreguntas) {
        super("Menú Principal");
        this.gestorPreguntas = gestorPreguntas;
        
        // Configuración de la ventana
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Inicializar componentes
        inicializarComponentes();
    }
    
    /**
     * Inicializa los componentes de la interfaz
     */
    private void inicializarComponentes() {
        // Panel principal con BorderLayout
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        
        // Panel superior con pestañas
        JTabbedPane pestanas = new JTabbedPane();
        
        // Panel para la pestaña Administración
        JPanel panelAdmin = new JPanel();
        panelAdmin.setLayout(new BorderLayout());
        
        // Panel para la pestaña Jugar
        JPanel panelJugar = new JPanel();
        panelJugar.setLayout(new BorderLayout());
        panelJugar.setBackground(new Color(176, 196, 222)); // Color azul claro
        
        // Añadir pestañas
        pestanas.addTab("Administración", panelAdmin);
        pestanas.addTab("Jugar", panelJugar);
        
        // Panel central con mensaje
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        etiquetaCantidad = new JLabel("Hay en el sistema " + gestorPreguntas.getCantidadPreguntas() + " preguntas");
        etiquetaCantidad.setFont(new Font("Arial", Font.BOLD, 16));
        panelCentral.add(etiquetaCantidad);
        
        // Botón Jugar en el panel inferior
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        botonJugar = new JButton("Iniciar Juego");
        botonJugar.setPreferredSize(new Dimension(150, 30));
        panelBotones.add(botonJugar);
        
        // Acción para el botón Jugar
        botonJugar.addActionListener((ActionEvent e) -> {
            iniciarJuego();
        });
        
        // Añadir componentes a los paneles
        panelPrincipal.add(pestanas, BorderLayout.NORTH);
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
        
        // Seleccionar la pestaña "Jugar" por defecto
        pestanas.setSelectedIndex(1);
        
        // Añadir panel principal a la ventana
        add(panelPrincipal);
    }
    
    /**
     * Inicia el juego de preguntas
     */
    private void iniciarJuego() {
        // Verificar si hay preguntas
        if (gestorPreguntas.getCantidadPreguntas() == 0) {
            JOptionPane.showMessageDialog(this, 
                "No hay preguntas en el sistema. No se puede iniciar el juego.",
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Ocultar menú principal
        setVisible(false);
        
        // Iniciar juego
        Ventana ventana = new Ventana(gestorPreguntas, this);
        ventana.setVisible(true);
    }
    
    /**
     * Actualiza la etiqueta con la cantidad de preguntas
     */
    public void actualizarCantidadPreguntas() {
        etiquetaCantidad.setText("Hay en el sistema " + gestorPreguntas.getCantidadPreguntas() + " preguntas");
    }
}
