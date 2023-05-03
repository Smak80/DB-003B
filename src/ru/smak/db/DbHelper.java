package ru.smak.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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

    public void createUsersTable() throws SQLException {
        var sql = "CREATE TABLE IF NOT EXISTS `customers`(" +
                "phone bigint not null primary key," +
                "name varchar(200) not null," +
                "password varchar(512) not null" +
                ")";
        conn.createStatement().execute(sql);
    }

    public addCustomer(){

    }
}
