package jek.repositories;

import jek.models.Customer;
import jek.services.system.DatabaseService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRepository {
    private final DatabaseService databaseService;

    public CustomerRepository(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    // CREATE

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

    // UPDATE

    public void UpdateCustomerById(int customerId, String customerName, int desiredTopping1, int desiredTopping2, int desiredTopping3){
        String query = "UPDATE customers SET customer_name = ?, desired_topping1 = ?, desired_topping2 = ?, desired_topping3 = ? WHERE customer_id = ?;";
        try (Connection connection = databaseService.getConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, customerName);
            ps.setInt(2, desiredTopping1);
            ps.setInt(3, desiredTopping2);
            ps.setInt(3, desiredTopping3);
            ps.setInt(3, customerId);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // DELETE



}
