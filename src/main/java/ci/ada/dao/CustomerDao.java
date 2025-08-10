package ci.ada.dao;

import ci.ada.models.Customer;

import java.sql.*;

public class CustomerDao {
    private final static String INSERT = "INSERT INTO customer (firstname, lastname, email, phone, idBank, registrydate) VALUES (?, ?, ?, ?, ?, ?)";
    private final static String UPDATE_BY_CUSTOMER = "UPDATE customer SET firstname=?, lastname=?, email=?, phone=?, idBank=?, registrydate=? WHERE id=?";
    private final static String DELETE_BY_CUSTOMER = "DELETE FROM customer WHERE id=?";
    private final static String READ_BY_CUSTOMER = "SELECT * FROM customer WHERE id=?";
    private final static String READ_BY_ID = "SELECT * FROM customer WHERE id=?";
    private final static String EXIST_CUSTOMER = "SELECT 1 FROM customer WHERE id=?";

    private final String url = "jdbc:postgresql://localhost:5432/adabank_db?currentSchema=public&sslmode=disable";
    private final String user = "postgres";
    private final String password = "password";

    public Customer createCustomer(Customer customer) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
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
                throw new SQLException("Creating customer failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    customer.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating customer failed, no ID obtained.");
                }
            }

            return customer;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}