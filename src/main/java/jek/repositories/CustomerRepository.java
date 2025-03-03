package jek.repositories;

import jek.models.Customer;
import jek.services.system.DatabaseService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {
    private final DatabaseService databaseService;

    public CustomerRepository(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public void createCustomer(Customer newCustomer) {

        String query = "INSERT INTO customers (customer_name, desired_topping1, desired_topping2, desired_topping3) VALUES (?, ?, ?, ?);";

        try (Connection connection = databaseService.getConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, newCustomer.getCustomerName());
            ps.setInt(2, newCustomer.getDesiredTopping1());
            ps.setInt(3, newCustomer.getDesiredTopping2());
            ps.setInt(4, newCustomer.getDesiredTopping3());
            ps.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // READ

    public Customer getCustomerById(int customerId){
        Customer customer = new Customer();
        String query = "SELECT customer_id, customer_name, desired_topping1, desired_topping2, desired_topping3 FROM customers WHERE customer_id = ?;";
        try (Connection connection = databaseService.getConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                customer.setCustomerId(rs.getInt("customer_id"));
                customer.setCustomerName(rs.getString("customer_name"));
                customer.setDesiredTopping1(rs.getInt("desired_topping1"));
                customer.setDesiredTopping2(rs.getInt("desired_topping2"));
                customer.setDesiredTopping3(rs.getInt("desired_topping3"));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customer;
    }

    public List<Customer> getAllCustomers() throws SQLException {
        String query = "SELECT * FROM customers;";
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = databaseService.getConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt("customer_id"));
                customer.setCustomerName(rs.getString("customer_name"));
                customer.setDesiredTopping1(rs.getInt("desired_topping1"));
                customer.setDesiredTopping2(rs.getInt("desired_topping2"));
                customer.setDesiredTopping3(rs.getInt("desired_topping3"));
                customers.add(customer);
            }
            return customers;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // DELETE

    public void deleteCustomerById(int customerId){
        String query = "DELETE FROM customers WHERE customer_id = ?;";
        try (Connection connection = databaseService.getConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, customerId);
            ps.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAllCustomers(){
        String query = "DELETE FROM customers;";
        try (Connection connection = databaseService.getConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ps.execute();
            ps = connection.prepareStatement("ALTER TABLE customers AUTO_INCREMENT = 1");
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
