package ru.smak;

import ru.smak.db.Customer;
import ru.smak.db.DbHelper;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        var customers = new ArrayList<Customer>();
        var c1 = new Customer();
        try {
            c1.setPhone(9271110500L);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        c1.setName("Иванов И. И.");
        c1.setPassword("123456");
        customers.add(c1);
        var c2 = new Customer();
        try {
            c2.setPhone(9171110890L);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        c2.setName("Петров П. П.");
        c2.setPassword("12345678");
        customers.add(c2);

        try {
            var dbh = new DbHelper("localhost", 3306, "root", "");
            dbh.createDb();
            dbh.createUsersTable();
            dbh.clearAll();
            for (var c : customers){
                dbh.addCustomer(c);
            }
            ArrayList<Customer> cust = null;
            try {
                cust = dbh.getCustomers();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            try {
                cust.add(dbh.getCustomerByPhone(9271110500L));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            try {
                cust.add(dbh.getCustomerByPhone(9991110000L));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            for(var c : cust){
                System.out.println(c);
            }
            try {
                System.out.println(dbh.getCustomerByPhone(9171110890L).checkPassword("12345678"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            try {
                System.out.println(dbh.getCustomerByPhone(9171110890L).checkPassword("qwerty"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
