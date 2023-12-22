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
    public static boolean getNewlyAddedTaxRule(Connection connection,String taxRuleName){
        boolean isTaxRuleNameExist=false;
        Statement statement=null;
        ResultSet resultSet=null;
        CachedRowSet cachedRowSet=null;

        try {
            cachedRowSet= RowSetProvider.newFactory().createCachedRowSet();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String taxRuleSqlScript=String.format("select * from i9362596_mg2.mg_tax_calculation_rule where code='%s';",taxRuleName);
        try {
            resultSet=statement.executeQuery(taxRuleSqlScript);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(resultSet==null){
            System.out.println("no records found");
            return isTaxRuleNameExist;
        }else{
            try {
                cachedRowSet.populate(resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            int count=0;
            while (true){
                try {
                    if(!cachedRowSet.next()){
                        break;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    String code = cachedRowSet.getString("code");
                    System.out.println(code);
                    count = cachedRowSet.getRow();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
            if(count>=1)
                isTaxRuleNameExist=true;
            return isTaxRuleNameExist;
        }
    }
}
