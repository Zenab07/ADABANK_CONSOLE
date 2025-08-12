package ci.ada.dao;

import ci.ada.models.Account;
import ci.ada.models.Customer;
import ci.ada.singleton.ConnexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDao {
    private final static String INSERT = "INSERT INTO account (comptenumber, balance, comptetype, openingdate, status, idCustomer) VALUES (?, ?, ?, ?, ?, ?)";
    //private final static String UPDATE_BY_COMPTENUMBER = "UPDATE account SET comptenumber=?, balance=?, comptetype=?, openingdate=?, status=?, idCustomer=? WHERE comptenumber=?";
    //private final static String DELETE_BY_COMPTENUMBER = "DELETE FROM account WHERE comptenumber=?";
    private final static String READ_BY_COMPTENUMBER = "SELECT * FROM account WHERE comptenumber=?";
    private final static String READ_BY_ID = "SELECT * FROM account WHERE id=?";
    //private final static String EXIST_COMPTENUMBER = "SELECT 1 FROM account WHERE comptenumber=?";
    private final static String UPDATE_BY_ID = "UPDATE account SET comptenumber=?, balance=?, comptetype=?, openingdate=?, status=?, idCustomer=? WHERE id=?";


    public Account createAccount(Account account) {
        try (Connection connection = ConnexionDB.getInstance().getConnection();
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
                throw new SQLException("Echec");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    account.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Echec");
                }
            }

            return account;
        } catch (SQLException e) {
            e.printStackTrace();
            //System.out.println("Cet Utilisateur a déjà un compte");
            return null;
        }
    }

    public Account readByCompteNumber(int compteNumber) {
        try (Connection con = ConnexionDB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(READ_BY_COMPTENUMBER)) {

            ps.setInt(1, compteNumber);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapRowToAccount(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Mise à jour du status
    public boolean updateAccountStatus(int compteNumber, String newStatus) {
        String UPDATE_STATUS = "UPDATE account SET status=? WHERE comptenumber=?";
        try (Connection con = ConnexionDB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE_STATUS)) {

            ps.setString(1, newStatus);
            ps.setInt(2, compteNumber);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Liste des comptes d'un client
    public List<Account> findByClientId(Long clientId) {
        List<Account> accounts = new ArrayList<>();
        String QUERY_BY_CLIENT = "SELECT * FROM account WHERE idCustomer = ?";
        try (Connection con = ConnexionDB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(QUERY_BY_CLIENT)) {

            ps.setLong(1, clientId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                accounts.add(mapRowToAccount(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    private Account mapRowToAccount(ResultSet rs) throws SQLException {
        Account account = new Account();
        account.setId(rs.getLong("id"));
        account.setCompteNumber(rs.getInt("comptenumber"));
        account.setBalance(rs.getFloat("balance"));
        account.setCompteType(ci.ada.models.enumeration.CompteType.valueOf(rs.getString("comptetype")));
        account.setOpeningDate(rs.getTimestamp("openingdate"));
        account.setStatus(rs.getString("status"));

        // Création minimale du client (juste l'ID)
        Customer customer = new Customer();
        customer.setId(rs.getLong("idCustomer"));
        account.setCustomer(customer);

        return account;
    }

    public Account readById(Long id) {
        try (Connection connection = ConnexionDB.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(READ_BY_ID)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Account account = new Account();
                account.setId(rs.getLong("id"));
                account.setCompteNumber(rs.getInt("comptenumber"));
                account.setBalance(rs.getFloat("balance"));
                account.setCompteType(ci.ada.models.enumeration.CompteType.valueOf(rs.getString("comptetype")));
                account.setOpeningDate(rs.getTimestamp("openingdate"));
                account.setStatus(rs.getString("status"));

                // Ici on peut juste mettre l'ID client dans un objet Customer minimaliste
                ci.ada.models.Customer customer = new ci.ada.models.Customer();
                customer.setId(rs.getLong("idCustomer"));
                account.setCustomer(customer);

                return account;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Account updateAccount(Account account) {
        try (Connection connection = ConnexionDB.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(UPDATE_BY_ID)) {

            stmt.setInt(1, account.getCompteNumber());
            stmt.setFloat(2, account.getBalance());
            stmt.setString(3, account.getCompteType().name());
            if (account.getOpeningDate() != null) {
                stmt.setTimestamp(4, new Timestamp(account.getOpeningDate().getTime()));
            } else {
                stmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            }
            stmt.setString(5, account.getStatus());
            stmt.setLong(6, account.getCustomer().getId());
            stmt.setLong(7, account.getId());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Echec");
            }
            return account;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
