package ci.ada.dao;

import ci.ada.models.Account;
import ci.ada.models.Transaction;
import ci.ada.models.enumeration.TransactionType;
import ci.ada.singleton.ConnexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDao {
    private final static String INSERT = "INSERT INTO transaction (amount, transactiontype, idAccount, transactiondate, description) VALUES (?, ?, ?, ?, ?)";
    /*private final static String UPDATE_BY_NAME = "UPDATE transaction SET amount=?, transactiontype=?, idAccount=?, transactiondate=?, description=? WHERE id=?";
    private final static String DELETE_BY_NAME = "DELETE FROM transaction WHERE id=?";
    private final static String READ_BY_NAME = "SELECT * FROM transaction WHERE id=?";
    private final static String READ_BY_ID = "SELECT * FROM transaction WHERE id=?";
    private final static String EXIST_NAME = "SELECT 1 FROM transaction WHERE id=?";*/
    private final String SELECT_HISTORY = "SELECT * FROM transaction WHERE idAccount = ? ORDER BY transactiondate DESC";
    private final String SEARCH_FILTER = "SELECT * FROM transaction WHERE idAccount = ? AND transactiontype ILIKE ? ORDER BY transactiondate DESC";



    public Transaction createTransaction(Transaction transaction) {
        try (Connection connection = ConnexionDB.getInstance().getConnection();
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
                throw new SQLException("Echec");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    transaction.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Echec");
                }
            }

            return transaction;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Transaction> getTransactionsByAccount(Long accountId) {
        List<Transaction> transactions = new ArrayList<>();
        try (Connection con = ConnexionDB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_HISTORY)) {

            ps.setLong(1, accountId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                transactions.add(mapRowToTransaction(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    public List<Transaction> searchTransactions(Long accountId, String transactiontype) {
        List<Transaction> transactions = new ArrayList<>();
        try (Connection con = ConnexionDB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(SEARCH_FILTER)) {

            ps.setLong(1, accountId);
            ps.setString(2, transactiontype == null || transactiontype.isEmpty() ? "%" : "%" + transactiontype + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                transactions.add(mapRowToTransaction(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    private Transaction mapRowToTransaction(ResultSet rs) throws SQLException {
        Transaction t = new Transaction();
        t.setId(rs.getLong("id"));
        t.setAmount(rs.getFloat("amount"));

        // Convertir String en enum TransactionType
        String typeStr = rs.getString("transactiontype");
        if (typeStr != null) {
            t.setTransactionType(TransactionType.valueOf(typeStr));
        } else {
            t.setTransactionType(null);
        }

        t.setTransactionDate(rs.getTimestamp("transactiondate"));

        // Minimal account linkage
        Account account = new Account();
        account.setId(rs.getLong("idAccount"));
        t.setAccount(account);

        t.setDescription(rs.getString("description"));
        return t;
    }
}