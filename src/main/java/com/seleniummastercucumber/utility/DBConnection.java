package com.seleniummastercucumber.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public Connection connectToDataBaseServer(String dbUrl,String dbport,String username,
                                              String password,String defaultDB,ConnectionType connectionType){
        String JTDS_Driver = "net.sourceforge.jtds.jdbc.Driver";//MSSQL
        String MYSQL_Driver = "com.mysql.cj.jdbc.Driver";//MySQL
        Connection connection=null;
        switch (connectionType) {
            case MSSQL:
                try {
                    Class.forName(JTDS_Driver);//load the driver
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                String connectionUrl = "jdbc:jtds:sqlserver://" + dbUrl + ":" + dbport + ";DataBaseName=" + defaultDB;
                try {
                    connection = DriverManager.getConnection(connectionUrl, username, password);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;

            case MYSQL:
                try {
                    Class.forName(MYSQL_Driver);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                String mySqlconnectionUrl = "jdbc:mysql://" + dbUrl + ":" + dbport + "/" + defaultDB + "?useSSL=false";
                try {
                    connection = DriverManager.getConnection(mySqlconnectionUrl, username, password);
                    if (!connection.isClosed()) {
                        System.out.println("Connection is established!!!");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                System.out.println("You need to specify a data base connection type MSSQL or MYSQL!!!!");

        }
        return connection;

    }
    public void closeDataBaseConnection(Connection connection){
        try {
            if (connection.isClosed()){
                System.out.println("Connection already closed!");
            }
            else {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

