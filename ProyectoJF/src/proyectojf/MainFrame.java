/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Singleton.java to edit this template
 */
package proyectojf;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private JButton agregarFincasButton;
    private JButton verMapaButton;
    private Finca[] fincas;
    
    public MainFrame() {
        this.setTitle("Sistema de Gestión de Fincas");
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        agregarFincasButton = new JButton("Agregar Fincas");
        agregarFincasButton.setFont(new Font("Arial", Font.BOLD, 16));
        agregarFincasButton.setBackground(new Color(52, 152, 219));
        agregarFincasButton.setForeground(Color.WHITE);
        agregarFincasButton.setFocusPainted(false);
        agregarFincasButton.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent e) {
                int cantidadFincas = pedirCantidadFincas();
                if (cantidadFincas >= 0) {
                    fincas = new Finca[cantidadFincas];
                    for (int i = 0; i < cantidadFincas; i++) {
                        fincas[i] = crearFinca(i + 1);
                    }
                    mostrarFincasPrecargadas(); // Llama a la función para mostrar las fincas precargadas
                    verMapaButton.setEnabled(true);
                }
            }
        });
        

          verMapaButton = new JButton("Ver Mapa");
        verMapaButton.setFont(new Font("Arial", Font.BOLD, 16));
        verMapaButton.setBackground(new Color(46, 204, 113));
        verMapaButton.setForeground(Color.WHITE);
        verMapaButton.setFocusPainted(false);
        verMapaButton.setEnabled(false);
        verMapaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int fincaSeleccionada = seleccionarFinca();
                if (fincaSeleccionada >= 0) {
                    mostrarMapa(fincas[fincaSeleccionada]);
                }
            }
        });


        JButton fincasPrecargadasButton = new JButton("Fincas Precargadas");
        fincasPrecargadasButton.setFont(new Font("Arial", Font.BOLD, 16));
        fincasPrecargadasButton.setBackground(new Color(241, 196, 15));
        fincasPrecargadasButton.setForeground(Color.WHITE);
        fincasPrecargadasButton.setFocusPainted(false);
        fincasPrecargadasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarFincasPrecargadas();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(agregarFincasButton);
        buttonPanel.add(verMapaButton);
        buttonPanel.add(fincasPrecargadasButton);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        this.add(mainPanel);
    }
    

    private int pedirCantidadFincas() {
        int cantidadFincas = -1;
        boolean cantidadValida = false;

        while (!cantidadValida) {
            try {
                JTextField inputField = new JTextField(10);
                JPanel panel = new JPanel();
                panel.add(new JLabel("Ingrese la cantidad de fincas:"));
                panel.add(inputField);

                int result = JOptionPane.showConfirmDialog(null, panel, "Cantidad de Fincas", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    cantidadFincas = Integer.parseInt(inputField.getText());
                    if (cantidadFincas >= 0) {
                        cantidadValida = true;
                    } else {
                        JOptionPane.showMessageDialog(null, "La cantidad de fincas debe ser mayor o igual a cero.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    cantidadFincas = -1; // Usuario canceló
                    cantidadValida = true;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error al ingresar la cantidad de fincas. Por favor, ingrese un valor numérico válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        return cantidadFincas;
    }

    private Finca crearFinca(int numeroFinca) {
        String nombreFinca = JOptionPane.showInputDialog(null, "Ingrese el nombre de la finca " + numeroFinca + ":");
        String ubicacion = JOptionPane.showInputDialog(null, "Ingrese la ubicación de la finca " + nombreFinca + ":");
        String nombreEncargado = JOptionPane.showInputDialog(null, "Ingrese el nombre del encargado de la finca " + nombreFinca + ":");
        int cedulaEncargado = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cédula del encargado de la finca " + nombreFinca + ":"));
        int telefonoEncargado = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el teléfono del encargado de la finca " + nombreFinca + ":"));
        int cantidadPotreros = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad de potreros de la finca " + nombreFinca + ":"));
        int tamañoFinca = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el tamaño de la finca " + nombreFinca + ":"));
        int cantidadAnimales = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad de animales de la finca " + nombreFinca + ":"));

        Potrero[] potreros = new Potrero[cantidadPotreros];
        for (int i = 0; i < cantidadPotreros; i++) {
            potreros[i] = crearPotrero("Potrero " + (i + 1));
        }

        return new Finca(nombreFinca, ubicacion, nombreEncargado, cedulaEncargado, telefonoEncargado,
                cantidadPotreros, tamañoFinca, cantidadAnimales, potreros);
}

    private Potrero crearPotrero(String nombrePotrero) {
        String estado = JOptionPane.showInputDialog(null, "Ingrese el estado del potrero " + nombrePotrero + ":");
        String fechaEntrada = JOptionPane.showInputDialog(null, "Ingrese la fecha de entrada del potrero " + nombrePotrero + ":");
        String fechaSalida = JOptionPane.showInputDialog(null, "Ingrese la fecha de salida del potrero " + nombrePotrero + ":");
        int cantidadAnimales = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad de animales del potrero " + nombrePotrero + ":"));
        boolean poseeAgua = JOptionPane.showConfirmDialog(null, "El potrero " + nombrePotrero + " posee agua?", "Posee Agua", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
        String tipoTerreno = JOptionPane.showInputDialog(null, "Ingrese el tipo de terreno del potrero " + nombrePotrero + ":");

        Ganado[] ganado = new Ganado[cantidadAnimales];
        for (int i = 0; i < cantidadAnimales; i++) {
            ganado[i] = crearGanado("Ganado " + (i + 1));
        }

        return new Potrero(nombrePotrero, estado, fechaEntrada, fechaSalida, cantidadAnimales, poseeAgua, tipoTerreno, ganado);
    }

    private Ganado crearGanado(String nombreGanado) {
        String codigo = JOptionPane.showInputDialog(null, "Ingrese el código del ganado " + nombreGanado + ":");
        String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del ganado " + nombreGanado + ":");
        String fechaNacimiento = JOptionPane.showInputDialog(null, "Ingrese la fecha de nacimiento del ganado " + nombreGanado + ":");
        double ultimoPeso = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese el último peso registrado del ganado " + nombreGanado + ":"));
        String sexo = JOptionPane.showInputDialog(null, "Ingrese el sexo del ganado " + nombreGanado + ":");
        String raza = JOptionPane.showInputDialog(null, "Ingrese la raza del ganado " + nombreGanado + ":");

        return new Ganado(codigo, nombre, fechaNacimiento, ultimoPeso, sexo, raza);
    }

    private void mostrarFincasPrecargadas() {
        // Crear instancias de Finca con datos precargados
    Finca finca1 = new Finca("Villa Verde", "Quepos", "Mariana", 486029578, 58402854, 3, 100, 50, null);
    Finca finca2 = new Finca("Los Pinos", "Palmares", "Fernando", 104573917, 30857192, 2, 75, 40, null);
    Finca finca3 = new Finca("Mira Flores", "Turrialba", "Jeremy", 135799572, 24680842, 4, 120, 60, null);
    
    StringBuilder infoHTML = new StringBuilder("<html><body>");

    // Estilos CSS aplicados para que coincidan con los de mostrarMapa
     infoHTML.append("<h1>Información de Fincas Precargadas</h1>");

    // Agregar información de la primera finca precargada
    infoHTML.append("<div>");
    infoHTML.append("<h2>").append(finca1.getNombre()).append("</h2>");
    infoHTML.append("<p>Ubicación: ").append(finca1.getUbicacion()).append("</p>");
    infoHTML.append(obtenerInfoFinca(finca1));
    infoHTML.append("</div>");

    // Agregar información de la segunda finca precargada
    infoHTML.append("<div>");
    infoHTML.append("<h2>").append(finca2.getNombre()).append("</h2>");
    infoHTML.append("<p>Ubicación: ").append(finca2.getUbicacion()).append("</p>");
    infoHTML.append(obtenerInfoFinca(finca2));
    infoHTML.append("</div>");

    // Agregar información de la tercera finca precargada
    infoHTML.append("<div>");
    infoHTML.append("<h2>").append(finca3.getNombre()).append("</h2>");
    infoHTML.append("<p>Ubicación: ").append(finca3.getUbicacion()).append("</p>");
    infoHTML.append(obtenerInfoFinca(finca3));
    infoHTML.append("</div>");

    // Cerrar las etiquetas HTML
    infoHTML.append("</body></html>");

    // Mostrar la información en un cuadro de diálogo
    JOptionPane.showMessageDialog(this, infoHTML.toString(), "Fincas Precargadas", JOptionPane.PLAIN_MESSAGE);
}     
    private void mostrarInformacionFinca(Finca finca) {
    String infoHTML = "<html><body>";
    infoHTML += "<h1>Información de la Finca</h1>";
    infoHTML += "<p>Nombre: " + finca.getNombre() + "</p>";
    // Agrega más información aquí...
    infoHTML += "</body></html>";

    JOptionPane.showMessageDialog(this, infoHTML, "Información de la Finca", JOptionPane.PLAIN_MESSAGE);
}

    private void mostrarMapa(Finca finca) {
        StringBuilder mapaHTML = new StringBuilder("<html><body>");
        mapaHTML.append("<h1>Sistema de Gestión de Fincas - Mapa de ").append(finca.getNombre()).append("</h1><hr>");
        mapaHTML.append("<div style='border: 2px solid black; padding: 10px;'>"); // Cuadro de finca
        mapaHTML.append("<h2>").append("Nombre de la Finca: ").append(finca.getNombre()).append("</h2>");
        mapaHTML.append("<p>").append("<b>Ubicación:</b> ").append(finca.getUbicacion()).append("<br>");
        mapaHTML.append(obtenerInfoFinca(finca));
        mapaHTML.append("</p></div>");
        mapaHTML.append("</body></html>");

        JOptionPane.showMessageDialog(this, mapaHTML.toString(), "Mapa de Finca", JOptionPane.PLAIN_MESSAGE);
    }


    private String obtenerInfoFinca(Finca finca) {
        StringBuilder infoHTML = new StringBuilder();
        infoHTML.append("<b><u>Detalles:</u></b><br>");
        infoHTML.append("Nombre del Encargado: ").append(finca.getNombreEncargado()).append("<br>");
        infoHTML.append("Cédula del Encargado: ").append(finca.getCedulaEncargado()).append("<br>");
        infoHTML.append("Teléfono del Encargado: ").append(finca.getTelefonoEncargado()).append("<br>");
        infoHTML.append("Cantidad de Potreros: ").append(finca.getCantidadPotreros()).append("<br>");
        infoHTML.append("Tamaño de la Finca: ").append(finca.getTamañoFinca()).append("<br>");
        infoHTML.append("Cantidad de Animales: ").append(finca.getCantidadAnimales()).append("<br>");

        Potrero[] potreros = finca.getPotreros();
        if (potreros != null) {
            for (Potrero potrero : potreros) {
                infoHTML.append("<hr>");
                infoHTML.append("<b>Potrero: ").append(potrero.getNombre()).append("</b><br>");
                infoHTML.append("Estado: ").append(potrero.getEstado()).append("<br>");
                infoHTML.append("Fecha de Entrada: ").append(potrero.getFechaEntrada()).append("<br>");
                infoHTML.append("Fecha de Salida: ").append(potrero.getFechaSalida()).append("<br>");
                infoHTML.append("Cantidad de Animales: ").append(potrero.getCantidadAnimales()).append("<br>");
                infoHTML.append("Posee Agua: ").append(potrero.isPoseeAgua() ? "Sí" : "No").append("<br>");
                infoHTML.append("Tipo de Terreno: ").append(potrero.getTipoTerreno()).append("<br>");

                Ganado[] ganado = potrero.getGanado();
                if (ganado != null) {
                    for (Ganado animal : ganado) {
                        infoHTML.append("<b>Ganado: ").append(animal.getNombre()).append("</b><br>");
                        infoHTML.append("Código: ").append(animal.getCodigo()).append("<br>");
                        infoHTML.append("Fecha de Nacimiento: ").append(animal.getFechaNacimiento()).append("<br>");
                        infoHTML.append("Último Peso: ").append(animal.getUltimoPeso()).append("<br>");
                        infoHTML.append("Sexo: ").append(animal.getSexo()).append("<br>");
                        infoHTML.append("Raza: ").append(animal.getRaza()).append("<br>");
                    }
                }
            }
        }

        return infoHTML.toString();
    }

   private int seleccionarFinca() {
        if (fincas == null || fincas.length == 0) {
            JOptionPane.showMessageDialog(this, "No hay fincas disponibles.", "Error", JOptionPane.ERROR_MESSAGE);
            return -1;
        }

        String[] fincaNames = new String[fincas.length];
        for (int i = 0; i < fincas.length; i++) {
            fincaNames[i] = fincas[i].getNombre();
        }

        return JOptionPane.showOptionDialog(
                this, "Seleccione la finca que desea ver:", "Seleccionar Finca",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                fincaNames, fincaNames[0]);
    }
     public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            } catch (Exception e) {
                e.printStackTrace();
            }

            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}






















