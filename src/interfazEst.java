import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class interfazEst {
    private JTextField NombreEstudiante;
    private JPanel InterfazEstudiante;
    private JTextField CursoEstudiante;
    private JButton salirDelSistemaButton;
    private JComboBox HorarioEstudiante;
    private JButton revisarButton;
    private JScrollPane ScrollEstudiante;

    public interfazEst() {
        // Inicializar los componentes
        HorarioEstudiante.addItem("Lunes 10:00 - 12:00");
        HorarioEstudiante.addItem("Martes 14:00 - 16:00");
        HorarioEstudiante.addItem("Miércoles 09:00 - 11:00");

        // Acción del botón "Salir del sistema"
        salirDelSistemaButton.addActionListener(e -> System.exit(0));  // Cierra la aplicación

        // Acción del botón "Revisar"
        revisarButton.addActionListener(e -> mostrarDatosEstudiante());
    }

    public Container getEstudiantePanel() {
        return InterfazEstudiante;
    }

    // Método para mostrar todos los datos del estudiante, incluidas las notas, desde la base de datos
    private void mostrarDatosEstudiante() {
        String nombreEstudiante = NombreEstudiante.getText();

        // Validación para asegurarse de que el campo no esté vacío
        if (nombreEstudiante.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor ingrese un nombre.");
            return;
        }

        // Consultar la base de datos para obtener los datos del estudiante
        String query = "SELECT curso, horario, nota1, nota2 FROM estudiantes WHERE nombre = ?";

        try (Connection conn = Conexiones.getConnection();  // Usa tu clase de conexión aquí
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, nombreEstudiante);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Si el estudiante existe, mostramos los datos
                CursoEstudiante.setText(rs.getString("curso"));
                HorarioEstudiante.setSelectedItem(rs.getString("horario"));

            } else {
                // Si no se encuentra el estudiante
                JOptionPane.showMessageDialog(null, "Estudiante no encontrado.");
                CursoEstudiante.setText("");
                HorarioEstudiante.setSelectedItem("Seleccionar Horario");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener los datos del estudiante.");
            e.printStackTrace();
        }
    }
}

