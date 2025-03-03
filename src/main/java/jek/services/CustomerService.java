package jek.services;

import jek.models.Customer;
import jek.repositories.CustomerRepository;

import java.sql.SQLException;
import java.util.List;

public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public void createCustomer(Customer customer) throws SQLException {
        customerRepository.createCustomer(customer);
    }

    public Customer getCustomerById(int id) throws SQLException {
        return customerRepository.getCustomerById(id);
    }

    public List<Customer> getAllCustomers() throws SQLException {
        return customerRepository.getAllCustomers();
    }

    public void deleteCustomerById(int customerId) throws SQLException {
        customerRepository.deleteCustomerById(customerId);
    }

    public void deleteAllCustomers() throws SQLException {
        customerRepository.deleteAllCustomers();
    }
}

