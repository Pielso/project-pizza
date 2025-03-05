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

    public void createCustomer(Customer customer) {
        customerRepository.createCustomer(customer);
    }

    public Customer getCustomerById(int id) {
        return customerRepository.getCustomerById(id);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }

    public void deleteCustomerById(int customerId) {
        customerRepository.deleteCustomerById(customerId);
    }

    public void deleteAllCustomers() {
        customerRepository.deleteAllCustomers();
    }
}

