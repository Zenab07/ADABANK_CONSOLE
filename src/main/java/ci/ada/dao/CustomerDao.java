package ci.ada.dao;

import ci.ada.models.Customer;
import ci.ada.singleton.ConnexionDB;

import java.util.ArrayList;
import java.util.List;

import java.sql.*;

public class CustomerDao {
    private final static String INSERT = "INSERT INTO customer (firstname, lastname, email, phone, idBank, registrydate) VALUES (?, ?, ?, ?, ?, ?)";
    /*private final static String UPDATE_BY_CUSTOMER = "UPDATE customer SET firstname=?, lastname=?, email=?, phone=?, idBank=?, registrydate=? WHERE id=?";
    private final static String DELETE_BY_CUSTOMER = "DELETE FROM customer WHERE id=?";
    private final static String READ_BY_CUSTOMER = "SELECT * FROM customer WHERE id=?";
    private final static String READ_BY_ID = "SELECT * FROM customer WHERE id=?";
    private final static String EXIST_CUSTOMER = "SELECT 1 FROM customer WHERE id=?";*/
    private final String SEARCH_BY_BANK = "SELECT * FROM customer WHERE idbank = ?";
    private final String SEARCH_MULTI = "SELECT * FROM customer WHERE " +
            " (firstname ILIKE ? OR lastname ILIKE ?) " +
            " AND (email ILIKE ? OR ? IS NULL) " +
            " AND (CAST(id AS TEXT) = ? OR ? IS NULL)";

    public Customer createCustomer(Customer customer) {
        try (Connection connection = ConnexionDB.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, customer.getFirstname());
            statement.setString(2, customer.getLastname());
            statement.setString(3, customer.getEmail());
            statement.setString(4, customer.getPhone());
            statement.setLong(5, customer.getBank().getId());

            if (customer.getRegistryDate() != null) {
                statement.setTimestamp(6, new Timestamp(customer.getRegistryDate().getTime()));
            } else {
                statement.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            }

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Echec");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    customer.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Echec");
                }
            }

            return customer;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Customer> findByBankId(Long bankId) {
        List<Customer> customers = new ArrayList<>();
        try (Connection con = ConnexionDB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(SEARCH_BY_BANK)) {

            ps.setLong(1, bankId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                customers.add(mapRowToCustomer(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public List<Customer> searchCustomers(String name, String email, String id) {
        List<Customer> customers = new ArrayList<>();
        try (Connection con = ConnexionDB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(SEARCH_MULTI)) {

            String likeName = "%" + (name == null ? "" : name) + "%";
            String likeEmail = "%" + (email == null ? "" : email) + "%";
            String idVal = (id == null || id.isEmpty()) ? null : id;

            ps.setString(1, likeName);
            ps.setString(2, likeName);
            ps.setString(3, likeEmail);
            ps.setString(4, likeEmail);
            ps.setString(5, idVal);
            ps.setString(6, idVal);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                customers.add(mapRowToCustomer(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    private Customer mapRowToCustomer(ResultSet rs) throws SQLException {
        Customer c = new Customer();
        c.setId(rs.getLong("id"));
        c.setFirstname(rs.getString("firstname"));
        c.setLastname(rs.getString("lastname"));
        c.setEmail(rs.getString("email"));
        c.setPhone(rs.getString("phone"));
        // c.setBankId(rs.getLong("idbank"));
        c.setRegistryDate(rs.getTimestamp("registrydate"));
        return c;
    }
}