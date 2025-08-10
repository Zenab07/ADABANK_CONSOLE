package ci.ada.dao;

import ci.ada.models.Account;

import java.sql.*;

public class AccountDao {
    private final static String INSERT = "INSERT INTO account (comptenumber, balance, comptetype, openingdate, status, idCustomer) VALUES (?, ?, ?, ?, ?, ?)";
    private final static String UPDATE_BY_COMPTENUMBER = "UPDATE account SET comptenumber=?, balance=?, comptetype=?, openingdate=?, status=?, idCustomer=? WHERE comptenumber=?";
    private final static String DELETE_BY_COMPTENUMBER = "DELETE FROM account WHERE comptenumber=?";
    private final static String READ_BY_COMPTENUMBER = "SELECT * FROM account WHERE comptenumber=?";
    private final static String READ_BY_ID = "SELECT * FROM account WHERE id=?";
    private final static String EXIST_COMPTENUMBER = "SELECT 1 FROM account WHERE comptenumber=?";

    private final String url = "jdbc:postgresql://localhost:5432/adabank_db?currentSchema=public&sslmode=disable";
    private final String user = "postgres";
    private final String password = "password";

    public Account createAccount(Account account) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, account.getCompteNumber());
            statement.setFloat(2, account.getBalance());
            statement.setString(3, account.getCompteType().name());
            if (account.getOpeningDate() != null) {
                statement.setTimestamp(4, new Timestamp(account.getOpeningDate().getTime()));
            } else {
                statement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            }
            statement.setString(5, account.getStatus());
            statement.setLong(6, account.getCustomer().getId());


            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating account failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    account.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating account failed, no ID obtained.");
                }
            }

            return account;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }



}
