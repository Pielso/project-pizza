package jek.models;

public class Customer {
    private int customerId;
    private String customerName;
    private int desiredTopping1;
    private int desiredTopping2;
    private int desiredTopping3;

    public Customer(String customerName, int desiredTopping1, int desiredTopping2, int desiredTopping3){
        this.customerName = customerName;
        this.desiredTopping1 = desiredTopping1;
        this.desiredTopping2 = desiredTopping2;
        this.desiredTopping3 = desiredTopping3;
    }

    public Customer(){

    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getDesiredTopping1() {
        return desiredTopping1;
    }

    public void setDesiredTopping1(int desiredTopping1) {
        this.desiredTopping1 = desiredTopping1;
    }

    public int getDesiredTopping2() {
        return desiredTopping2;
    }

    public void setDesiredTopping2(int desiredTopping2) {
        this.desiredTopping2 = desiredTopping2;
    }

    public int getDesiredTopping3() {
        return desiredTopping3;
    }

    public void setDesiredTopping3(int desiredTopping3) {
        this.desiredTopping3 = desiredTopping3;
    }
}
