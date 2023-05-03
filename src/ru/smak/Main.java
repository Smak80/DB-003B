package ru.smak;

import ru.smak.db.DbHelper;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            var dbh = new DbHelper("localhost", 3306, "root", "");
            dbh.createDb();
            dbh.createUsersTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
