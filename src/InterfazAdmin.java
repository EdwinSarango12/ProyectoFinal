import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class InterfazAdmin {
    public JPanel AdminPanel;
    private JTextField usuarioField;
    private JComboBox<String> comboBox1;
    private JButton insertarButton;
    private JButton leerButton;
    private JButton modificarButton;
    private JButton eliminarButton;
    private JTextArea resultArea;
    private JScrollPane Scroll;

    public InterfazAdmin() {
        // Inicializar el ComboBox con roles
        comboBox1.addItem("Estudiante");
        comboBox1.addItem("Profesor");
        comboBox1.addItem("Administrador");

        // Escuchar eventos de los botones
        insertarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = usuarioField.getText();
                String rol = (String) comboBox1.getSelectedItem();
                if (insertUser(nombre, rol)) {
                    resultArea.setText("Usuario insertado correctamente.");
                } else {
                    resultArea.setText("Error al insertar usuario.");
                }
            }
        });

        leerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String result = readUsers();
                resultArea.setText(result);
            }
        });

        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = usuarioField.getText();
                String rol = (String) comboBox1.getSelectedItem();
                if (updateUser(nombre, rol)) {
                    resultArea.setText("Usuario modificado correctamente.");
                } else {
                    resultArea.setText("Error al modificar usuario.");
                }
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = usuarioField.getText();
                if (deleteUser(nombre)) {
                    resultArea.setText("Usuario eliminado correctamente.");
                } else {
                    resultArea.setText("Error al eliminar usuario.");
                }
            }
        });
    }


    public JPanel getAdminPanel() {
        return AdminPanel;
    }

    private boolean insertUser(String email, String rol) {
        String query = "INSERT INTO Usuarios (email, rol) VALUES (?, ?)";

        try (Connection conn = Conexiones.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, email);
            ps.setString(2, rol);
            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private String readUsers() {
        String query = "SELECT * FROM Usuarios";
        StringBuilder result = new StringBuilder();

        try (Connection conn = Conexiones.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {
                result.append("ID: ").append(rs.getInt("id"))
                        .append(", Email: ").append(rs.getString("email"))
                        .append(", Rol: ").append(rs.getString("rol"))
                        .append("\n");
            }
        } catch (SQLException e) {
            result.append("Error al leer usuarios.");
        }

        return result.toString();
    }

    private boolean updateUser(String email, String rol) {
        String query = "UPDATE Usuarios SET rol = ? WHERE email = ?";

        try (Connection conn = Conexiones.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, rol);
            ps.setString(2, email);
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean deleteUser(String email) {
        String query = "DELETE FROM Usuarios WHERE email = ?";

        try (Connection conn = Conexiones.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, email);
            int rowsDeleted = ps.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
