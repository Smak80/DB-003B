package ru.smak.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class DbHelper{

    private Connection conn;
    public DbHelper(
        String host,
        int port,
        String user,
        String password
    ) throws SQLException {
        var connStr = "jdbc:mysql://" + host + ":" + port;
        conn = DriverManager.getConnection(connStr, user, password);
    }

    public void createDb() throws SQLException {
        var sql1 = "CREATE SCHEMA IF NOT EXISTS `db003b`";
        var sql2 = "USE `db003b`";
        var st = conn.createStatement();
        st.addBatch(sql1);
        st.addBatch(sql2);
        st.executeBatch();
    }

    public void createCustomersTable() throws SQLException {
        var sql = "CREATE TABLE IF NOT EXISTS `customers`(" +
                "phone bigint not null primary key," +
                "name varchar(200) not null," +
                "password varchar(512) not null" +
                ")";
        conn.createStatement().execute(sql);
    }

    public void addCustomer(Customer c) throws SQLException {
        var sql = "INSERT INTO `customers` (phone, name, password) " +
                "VALUES (?, ?, ?)";
        var st = conn.prepareStatement(sql);
        st.setLong(1, c.getPhone());
        st.setString(2, c.getName());
        st.setString(3, c.getPassword());
        st.executeUpdate();
    }

    public ArrayList<Customer> getCustomers() throws SQLException {
        var customers = new ArrayList<Customer>();
        var sql = "SELECT * FROM `customers`";
        var st = conn.createStatement();
        var cursor = st.executeQuery(sql);
        while (cursor.next()){
            Customer c = new Customer();
            c.setPhone(cursor.getLong("phone"));
            c.setName(cursor.getString("name"));
            c.setPasswordHash(cursor.getString("password"));
            customers.add(c);
        }
        return customers;
    }

    public Customer getCustomerByPhone(long phone) throws SQLException {
        Customer c = new Customer();
        var sql = "SELECT * FROM `customers` WHERE phone=?";
        var st = conn.prepareStatement(sql);
        st.setLong(1, phone);
        var cursor = st.executeQuery();
        if (cursor.next()){
            c.setPhone(cursor.getLong("phone"));
            c.setName(cursor.getString("name"));
            c.setPasswordHash(cursor.getString("password"));
            return c;
        } else return null;
    }

    public void clearAll() throws SQLException {
        conn.createStatement().executeUpdate("DELETE FROM `customers`");
    }
}
