import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Register {
    private JTextField textField1;
    private JPanel RegisterPanel;
    private JTextField textField2;
    private JTextField textField3;
    private JComboBox rolesField;
    private JLabel nameField;
    private JLabel EmailField;
    private JLabel ContraField;
    private JLabel RolField;
    private JButton submitButton;

    public Register() {
        rolesField.addItem("...");
        rolesField.addItem("Estudiante");
        rolesField.addItem("Profesor");
        rolesField.addItem("Administrador");

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textField1.getText();
                String email = textField2.getText();
                String password = textField3.getText();
                String rol = (String) rolesField.getSelectedItem();

                if (registerUser(name, email, password, rol)) {
                    JOptionPane.showMessageDialog(RegisterPanel, "Registro exitoso. ¡Ahora puedes iniciar sesión!");
                } else {
                    JOptionPane.showMessageDialog(RegisterPanel, "Error al registrar. Inténtalo de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public JPanel getRegisterPanel() {
        return RegisterPanel;
    }

    private boolean registerUser(String name, String email, String password, String rol) {
        String query = "INSERT INTO Usuarios (nombre, email, contrasena, rol) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexiones.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            // Establecer parámetros
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, rol);

            // Ejecutar actualización
            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(RegisterPanel, "Error al registrar en la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
}
