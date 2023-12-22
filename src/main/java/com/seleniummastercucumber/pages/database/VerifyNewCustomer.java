package com.seleniummastercucumber.pages.database;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.mysql.cj.conf.PropertyKey.logger;

/**
 * @author : SalmanUyghur
 * @created : 12/21/2023,1:09 PM
 * @Email : salmanuyghur3@gmail.com
 **/
public class VerifyNewCustomer {
public boolean verifyNewlyRegisteredCustomer(Connection connection,String customerEmail){
    boolean isCustomerExist=false;
    Statement statement=null;
    ResultSet resultSet=null;
    CachedRowSet cachedRowSet=null;
    try {
        cachedRowSet= RowSetProvider.newFactory().createCachedRowSet();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    try {
        statement= connection.createStatement();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    String sqlQueryForCustomer=String.format
            ("select entity_id,website_id,email,group_id from i9362596_mg2."+
                    "mg_customer_entity where email='%s'"+customerEmail);
    try {
        resultSet=statement.executeQuery(sqlQueryForCustomer);
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    if (resultSet==null){
                 System.out.println("no records found!");
                 return isCustomerExist;
             }else {
        try {
            cachedRowSet.populate(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        int rowCount=0;
        while (true){
            try {
                if (!cachedRowSet.next()){
                    break;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                int entity_id=cachedRowSet.getInt("entity_id");
                int website_id=cachedRowSet.getInt("website_id");
                String email=cachedRowSet.getNString("email");
                int group_id=cachedRowSet.getInt("group_id");
                System.out.println(String.format("entity_id=%s website_id=%s email=%s group_id=%s",entity_id,website_id,email,group_id));
                rowCount=cachedRowSet.getRow();
            }catch (SQLException e){
                e.printStackTrace();
            }

        }
        if (rowCount>=1){
            isCustomerExist=true;
        }

    }
    return isCustomerExist;
}
}
