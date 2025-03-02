package jek.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Customer {
    private int customerId;
    private String customerName;
    private int desiredTopping1;
    private int desiredTopping2;
    private int desiredTopping3;

    public Customer(){

            this.customerName = randomizeCustomerName();

        do {
            desiredTopping1 = randomizeDesiredTopping();
            desiredTopping2 = randomizeDesiredTopping();
            desiredTopping3 = randomizeDesiredTopping();
        }
        while (desiredTopping1 == desiredTopping2 || desiredTopping1 == desiredTopping3 || desiredTopping2 == desiredTopping3);

    }

    public int randomizeDesiredTopping(){
        Random rand = new Random();
        return rand.nextInt(1, 16);
    }

    public String randomizeCustomerName(){
        Random rand = new Random();
        List <String> listOfCustomerNames = new ArrayList<>(
                Arrays.asList(
                        "Diana", "Patrick", "Claudia", "John", "Kara", "Walter", "Olivia", "Peter", "Laura", "Lee", "Wendy", "Kyle", "Angelica",
                        "Thomas", "Parisa", "Daniel", "Nina", "Miles", "Mary", "Michael", "Patricia", "Robert", "Jennifer", "David", "Elizabeth",
                        "William", "Barbara", "Richard", "Susan", "Joseph", "Jessica", "Thomas", "Karen", "Christopher", "Sarah", "Charles", "Lisa",
                        "Nancy", "Matthew", "Sandra", "Anthony", "Betty", "Mark", "Ashley", "Donald", "Emily", "Steven", "Kimberly", "Andrew", "Margaret",
                        "Paul", "Donna", "Joshua", "Michelle", "Kenneth", "Carol", "Kevin", "Amanda", "Brian", "Melissa", "Timothy", "Deborah", "Ronald",
                        "Stephanie", "George", "Rebecca", "Jason", "Sharon", "Edward", "Jeffrey", "Cynthia", "Ryan", "Dorothy", "Jacob", "Amy", "Nicholas",
                        "Kathleen", "Gary", "Angela", "Eric", "Shirley", "Jonathan", "Emma", "Stephen", "Brenda", "Larry", "Pamela", "Justin", "Nicole",
                        "Scott", "Anna", "Brandon", "Samantha", "Benjamin", "Katherine", "Samuel", "Christine", "Gregory", "Debra", "Alexander", "Rachel",
                        "Carolyn", "Frank", "Janet", "Raymond", "Maria", "Jack", "Dennis", "Heather", "Jerry", "Helen", "Tyler", "Catherine", "Aaron", "Diane",
                        "Jose", "Julie", "Adam", "Victoria", "Nathan", "Joyce", "Henry", "Lauren", "Zachary", "Kelly", "Douglas", "Christina", "Ruth", "Joan",
                        "Noah", "Virginia", "Ethan", "Judith", "Jeremy", "Evelyn", "Christian", "Hannah", "Andrea", "Keith", "Megan", "Austin", "Cheryl",
                        "Roger", "Jacqueline", "Terry", "Madison", "Sean", "Teresa", "Gerald", "Abigail", "Carl", "Sophia", "Dylan", "Martha", "Harold",
                        "Sara", "Jordan", "Gloria", "Jesse", "Janice", "Bryan", "Kathryn", "Lawrence", "Ann", "Arthur", "Isabella", "Gabriel", "Judy",
                        "Bruce", "Charlotte", "Logan", "Julia", "Billy", "Grace", "Joe", "Amber", "Alan", "Alice", "Juan", "Jean", "Elijah", "Denise",
                        "Willie", "Frances", "Albert", "Danielle", "Wayne", "Marilyn", "Randy", "Natalie", "Mason", "Beverly", "Vincent", "Liam",
                        "Brittany", "Roy", "Theresa", "Bobby", "Kayla", "Caleb", "Alexis", "Bradley", "Doris", "Russell", "Lori", "Lucas", "Tiffany"));
        int name = rand.nextInt(0, listOfCustomerNames.size());
        return listOfCustomerNames.get(name);
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
