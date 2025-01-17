import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    private JTextField userField;
    public JPanel LoginSistema;
    private JComboBox<String> ModoBox;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton RegisterButton;

    public Login() {
        // Agregar opciones al ComboBox
        ModoBox.addItem("...");
        ModoBox.addItem("Estudiante");
        ModoBox.addItem("Profesor");
        ModoBox.addItem("Administrador");

        // Botón Ingresar
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = userField.getText();
                String contrasena = new String(passwordField.getPassword());
                String rol = (String) ModoBox.getSelectedItem();

                if (authenticateUser(email, contrasena, rol)) {
                    JOptionPane.showMessageDialog(LoginSistema, "Login exitoso. ¡Bienvenido " + rol + "!");
                    abrirVentanaPorRol(rol);
                } else {
                    JOptionPane.showMessageDialog(LoginSistema, "Credenciales incorrectas. Inténtelo nuevamente.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Botón Registrar
        RegisterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame registerFrame = new JFrame("Registro de Usuario");
                registerFrame.setPreferredSize(new Dimension(550, 380));
                registerFrame.setContentPane(new Register().getRegisterPanel());
                registerFrame.pack();
                registerFrame.setVisible(true);
            }
        });
    }

    private boolean authenticateUser(String nombre, String contrasena, String rol) {
        String query = "SELECT contrasena, rol FROM Usuarios WHERE nombre = ? AND rol = ?";

        try (Connection conn = Conexiones.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            // Establecer parámetros
            ps.setString(1, nombre);
            ps.setString(2, rol);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Obtener la contraseña almacenada
                String storedPassword = rs.getString("contrasena");
                return storedPassword.equals(contrasena);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(LoginSistema, "Error al conectar con la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return false;
    }

    private void abrirVentanaPorRol(String rol) {
        JFrame nuevaVentana = new JFrame();
        switch (rol) {
            case "Administrador":
                nuevaVentana.setTitle("Interfaz Administrador");
                nuevaVentana.setContentPane(new InterfazAdmin().getAdminPanel());
                break;
            case "Profesor":
                nuevaVentana.setTitle("Interfaz Profesor");
                nuevaVentana.setContentPane(new InterfazProfesor().getProfesorPanel());
                break;
            case "Estudiante":
                nuevaVentana.setTitle("Interfaz Estudiante");
                nuevaVentana.setContentPane(new interfazEst().getEstudiantePanel());
                break;
            default:
                JOptionPane.showMessageDialog(LoginSistema, "Rol no reconocido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
        }

        nuevaVentana.setPreferredSize(new Dimension(800, 600));
        nuevaVentana.pack();
        nuevaVentana.setVisible(true);
    }
}
