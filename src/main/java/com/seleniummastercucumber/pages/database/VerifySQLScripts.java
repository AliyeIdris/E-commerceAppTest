package com.seleniummastercucumber.pages.database;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VerifySQLScripts {
    public boolean getAddedSubCategoriesInfo(Connection connection, String subCategoryName){
        boolean isSubCategoryExist = false;
        Statement statement;
        ResultSet resultSet;
        CachedRowSet cachedRowSet;
        try {
            statement = connection.createStatement();
            String sqlScriptForSubCategory = String.format("select value_id,entity_id,value from i9362596_mg2." +
                            "mg_catalog_category_entity_varchar where value='%s'"
                    ,subCategoryName);
            resultSet = statement.executeQuery(sqlScriptForSubCategory);
            cachedRowSet = RowSetProvider.newFactory().createCachedRowSet();
            if (resultSet == null) {
                System.out.println("No records found");
            } else {
                cachedRowSet.populate(resultSet);
                int rowCount = 0;
                String value = "";
                while (true) {
                    if (!cachedRowSet.next()) {
                        break;
                    }
                    int value_id = cachedRowSet.getInt("value_id");
                    int entity_id = cachedRowSet.getInt("entity_id");
                    value = cachedRowSet.getString("value");
                    System.out.println(String.format("value_id=%d entity_id=%d value=%s", value_id, entity_id, value));
                    rowCount = cachedRowSet.getRow();
                }
                System.out.println("row Count: " + rowCount);
                if (rowCount >= 1 && value.equalsIgnoreCase(subCategoryName)) {
                    isSubCategoryExist = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isSubCategoryExist;

    }
    public boolean getNewlyAddedStoreView(Connection connection,String storeViewName){
        boolean isStoreViewExist=false;
        Statement statement;
        ResultSet resultSet;
        CachedRowSet cachedRowSet;
        try {
            statement = connection.createStatement();
            String sqlScriptStoreView = String.format("select store_id,code,group_id,name from mg_core_store where name='%s'"
                    ,storeViewName);
            resultSet = statement.executeQuery(sqlScriptStoreView);
            cachedRowSet = RowSetProvider.newFactory().createCachedRowSet();
            if (resultSet == null) {
                System.out.println("No records found");
            } else {
                cachedRowSet.populate(resultSet);
                int rowCount = 0;
                String name=" ";
                while (true) {
                    if (!cachedRowSet.next()) {
                        break;
                    }
                    int store_id = cachedRowSet.getInt("store_id");
                    String code = cachedRowSet.getString("code");
                    int group_id = cachedRowSet.getInt("group_id");
                     name = cachedRowSet.getString("name");
                    System.out.println(String.format("store_id=%d code=%s group_id=%d name=%s", store_id,code,group_id,name));
                    rowCount = cachedRowSet.getRow();
                }
                System.out.println("row Count: " + rowCount);
                if (rowCount >= 1 && name.equalsIgnoreCase(storeViewName)) {
                    isStoreViewExist = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isStoreViewExist;

    }
}
