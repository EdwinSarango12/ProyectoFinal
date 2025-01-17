import javax.swing.*;
import javax.xml.transform.Result;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {
    public static void main(String[] args) {
       /* // Configuración de la conexión
        String url = "jdbc:postgresql://localhost:5432/ProyectoFinal";
        String user = "postgres";
        String password = "123123";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Conexión exitosa a PostgreSQL!");

            // Ejemplo de consulta
            String query = "SELECT * FROM usuarios";
            try (PreparedStatement ps = conn.prepareStatement(query);
                ResultSet rs = ps.executeQuery()){
                    while (rs.next()) {
                        System.out.println("Dato: " + rs.getString("nombre"));
                    }
                }
            } catch (Exception e) {
            e.printStackTrace();
        }*/

        JFrame frame = new JFrame("EduPlan");
        frame.setContentPane(new Login().LoginSistema);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550,200);
        frame.setPreferredSize(new Dimension(550,200));
        frame.pack();
        frame.setVisible(true);
    }

}