package ci.ada.dao;

import ci.ada.models.Bank;
import ci.ada.singleton.ConnexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BankDao {
    private final static String INSERT = "INSERT INTO bank (name, country, city, creationdate) VALUES (?, ?, ?, ?)";
    /*private final static String UPDATE_BY_NAME = "UPDATE bank SET name=?, country=?, city=?, creationdate=? WHERE name=?";
    private final static String DELETE_BY_NAME = "DELETE FROM bank WHERE name=?";
    private final static String READ_BY_NAME = "SELECT * FROM bank WHERE name=?";
    private final static String READ_BY_ID = "SELECT * FROM bank WHERE id=?";
    private final static String EXIST_NAME = "SELECT 1 FROM bank WHERE name=?";*/
    private final static String TOP_15_BANKS_BY_CLIENTS = "SELECT b.id, b.name, b.country, b.city, COUNT(c.id) AS client_count " + "FROM bank b " + "LEFT JOIN customer c ON c.idBank = b.id " + "GROUP BY b.id " + "ORDER BY client_count DESC " + "LIMIT 15";


    public Bank createBank(Bank bank) {
        try (Connection connection = ConnexionDB.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, bank.getName());
            statement.setString(2, bank.getCountry());
            statement.setString(3, bank.getCity());
            if (bank.getCreationDate() != null) {
                statement.setTimestamp(4, new Timestamp(bank.getCreationDate().getTime()));
            } else {
                statement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            }


            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Echec");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    bank.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("EChec");
                }
            }

            return bank;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Bank> getTop15BanksByClientCount() {
        List<Bank> banks = new ArrayList<>();
        try (Connection connection = ConnexionDB.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(TOP_15_BANKS_BY_CLIENTS);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Bank bank = new Bank();
                bank.setId(rs.getLong("id"));
                bank.setName(rs.getString("name"));
                bank.setCountry(rs.getString("country"));
                bank.setCity(rs.getString("city"));
                bank.setNumberOfCustomers(rs.getInt("client_count"));  // champ calculé
                banks.add(bank);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return banks;
    }

    public List<Bank> searchBanks(String country, String city) {
        List<Bank> banks = new ArrayList<>();

        // Construction dynamique de la requête
        StringBuilder sql = new StringBuilder("SELECT id, name, country, city, creationdate FROM bank WHERE 1=1");
        List<String> params = new ArrayList<>();

        if (country != null && !country.trim().isEmpty()) {
            sql.append(" AND country ILIKE ?");
            params.add("%" + country.trim() + "%");
        }

        if (city != null && !city.trim().isEmpty()) {
            sql.append(" AND city ILIKE ?");
            params.add("%" + city.trim() + "%");
        }

        try (Connection connection = ConnexionDB.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql.toString())) {

            // Remplissage des paramètres
            for (int i = 0; i < params.size(); i++) {
                stmt.setString(i + 1, params.get(i));
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Bank bank = new Bank();
                bank.setId(rs.getLong("id"));
                bank.setName(rs.getString("name"));
                bank.setCountry(rs.getString("country"));
                bank.setCity(rs.getString("city"));
                bank.setCreationDate(rs.getTimestamp("creationdate"));
                banks.add(bank);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return banks;
    }

    public Bank findByName(String name) {
        String sql = "SELECT * FROM bank WHERE name = ?";
        try (Connection con = ConnexionDB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Bank bank = new Bank();
                    bank.setId(rs.getLong("id"));
                    bank.setName(rs.getString("name"));
                    bank.setCountry(rs.getString("country"));
                    bank.setCity(rs.getString("city"));
                    return bank;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
