package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLConnection {

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://sql.freeasphost.net\\MSSQL2016;Database=lknight97_QLKS;user=lknight97;password=123456;");
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }

}
