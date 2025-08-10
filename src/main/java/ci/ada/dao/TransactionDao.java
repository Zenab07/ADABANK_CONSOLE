package ci.ada.dao;

import ci.ada.models.Transaction;

import java.sql.*;

public class TransactionDao {
    private final static String INSERT = "INSERT INTO transaction (amount, transactiontype, idAccount, transactiondate, description) VALUES (?, ?, ?, ?, ?)";
    private final static String UPDATE_BY_NAME = "UPDATE transaction SET amount=?, transactiontype=?, idAccount=?, transactiondate=?, description=? WHERE id=?";
    private final static String DELETE_BY_NAME = "DELETE FROM transaction WHERE id=?";
    private final static String READ_BY_NAME = "SELECT * FROM transaction WHERE id=?";
    private final static String READ_BY_ID = "SELECT * FROM transaction WHERE id=?";
    private final static String EXIST_NAME = "SELECT 1 FROM transaction WHERE id=?";

    private final String url = "jdbc:postgresql://localhost:5432/adabank_db?currentSchema=public&sslmode=disable";
    private final String user = "postgres";
    private final String password = "password";


    public Transaction createTransaction(Transaction transaction) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {

            statement.setFloat(1, transaction.getAmount());
            statement.setString(2, transaction.getTransactionType().name());
            statement.setLong(3, transaction.getAccount().getId());
            if (transaction.getTransactionDate() != null) {
                statement.setTimestamp(4, new Timestamp(transaction.getTransactionDate().getTime()));
            } else {
                statement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            }
            statement.setString(5, transaction.getDescription());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating transaction failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    transaction.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating transaction failed, no ID obtained.");
                }
            }

            return transaction;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}