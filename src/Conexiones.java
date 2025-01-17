import javax.print.MultiDocPrintService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexiones {
    // Configuración de la conexión
    private static final String url = "jdbc:postgresql://localhost:5432/ProyectoFinal";
    private static String user = "postgres";
    private static String password = "123123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
