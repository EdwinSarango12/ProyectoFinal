import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class InterfazProfesor {
    private JTextField usuarioEst;
    private JPanel InterfazProfesor;
    private JTextField CursoEst;
    private JTextField Nota1;
    private JTextField Nota2;
    private JButton insertarButton;
    private JButton leerButton;
    private JButton modificarButton;
    private JButton borrarButton;
    private JComboBox HorarioProf;
    private JScrollPane ScrollProf;
    private Label resultArea;

    public InterfazProfesor() {
        // Inicializar el ComboBox con horarios
        HorarioProf.addItem("Lunes 10:00 - 12:00");
        HorarioProf.addItem("Martes 14:00 - 16:00");
        HorarioProf.addItem("Miércoles 09:00 - 11:00");

        // Inicializar el área de resultados
        JTextArea resultArea = new JTextArea(10, 30);
        ScrollProf = new JScrollPane(resultArea);

        // Escuchar eventos de los botones
        insertarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = usuarioEst.getText();
                String curso = CursoEst.getText();
                String nota1 = Nota1.getText();
                String nota2 = Nota2.getText();
                String horario = (String) HorarioProf.getSelectedItem();

                if (insertProfesor(usuario, curso, nota1, nota2, horario)) {
                    resultArea.setText("Estudiante y curso insertados correctamente.");
                } else {
                    resultArea.setText("Error al insertar estudiante.");
                }
            }
        });

        leerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String result = readProfesores();
                resultArea.setText(result);
            }
        });

        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = usuarioEst.getText();
                String curso = CursoEst.getText();
                String nota1 = Nota1.getText();
                String nota2 = Nota2.getText();
                String horario = (String) HorarioProf.getSelectedItem();

                if (updateProfesor(usuario, curso, nota1, nota2, horario)) {
                    resultArea.setText("Estudiante modificado correctamente.");
                } else {
                    resultArea.setText("Error al modificar Estudiante.");
                }
            }
        });

        borrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = usuarioEst.getText();

                if (deleteProfesor(usuario)) {
                    resultArea.setText("Estudiante eliminado correctamente.");
                } else {
                    resultArea.setText("Error al eliminar Estudiante.");
                }
            }
        });
    }

    public Container getProfesorPanel() {
        return InterfazProfesor;
    }
    private boolean insertProfesor(String usuario, String curso, String nota1, String nota2, String horario) {
        String query = "INSERT INTO Profesores (usuario, curso, nota1, nota2, horario) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexiones.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, usuario);
            ps.setString(2, curso);
            ps.setString(3, nota1);
            ps.setString(4, nota2);
            ps.setString(5, horario);
            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private String readProfesores() {
        String query = "SELECT * FROM estudiantes";
        StringBuilder result = new StringBuilder();

        try (Connection conn = Conexiones.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {
                result.append("Usuario: ").append(rs.getString("usuario"))
                        .append(", Curso: ").append(rs.getString("curso"))
                        .append(", Nota 1: ").append(rs.getString("nota1"))
                        .append(", Nota 2: ").append(rs.getString("nota2"))
                        .append(", Horario: ").append(rs.getString("horario"))
                        .append("\n");
            }
        } catch (SQLException e) {
            result.append("Error al leer profesores.");
        }

        return result.toString();
    }

    private boolean updateProfesor(String usuario, String curso, String nota1, String nota2, String horario) {
        String query = "UPDATE Profesores SET curso = ?, nota1 = ?, nota2 = ?, horario = ? WHERE usuario = ?";

        try (Connection conn = Conexiones.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, curso);
            ps.setString(2, nota1);
            ps.setString(3, nota2);
            ps.setString(4, horario);
            ps.setString(5, usuario);
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean deleteProfesor(String usuario) {
        String query = "DELETE FROM estudiantes WHERE id_usuario = ?";

        try (Connection conn = Conexiones.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, usuario);
            int rowsDeleted = ps.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para obtener el texto del JTextArea directamente
    public String obtenerTextoResultados() {
        return resultArea.getText();  // Leer el texto del JTextArea dentro del JScrollPane
    }
}
