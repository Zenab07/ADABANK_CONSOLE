package ci.ada.singleton;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionDB {
    private static ConnexionDB instance;
    private Connection connection;

    // Paramètres de connexion à ma BD
    private final String url = "jdbc:postgresql://localhost:5432/adabank_db?currentSchema=public&sslmode=disable";
    private final String user = "postgres";
    private final String password = "password";

    private ConnexionDB() throws SQLException {
        this.connection = DriverManager.getConnection(url, user, password);
    }

    // Méthode statique pour récupérer l’instance unique
    public static ConnexionDB getInstance() throws SQLException {
        if (instance == null || instance.getConnection().isClosed()) {
            instance = new ConnexionDB();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
