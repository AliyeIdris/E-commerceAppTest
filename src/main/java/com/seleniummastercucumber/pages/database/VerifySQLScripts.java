package com.seleniummastercucumber.pages.database;

import org.apache.log4j.Logger;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VerifySQLScripts {
    Logger logger=Logger.getLogger(VerifySQLScripts.class.getName());
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

    public boolean newlyRegisteredUser(String userEmail, Connection connection) {
        boolean isUserExist = false;
        Statement statement = null;
        ResultSet resultSet = null;
        CachedRowSet cachedRowSet = null;
        try {
            cachedRowSet = RowSetProvider.newFactory().createCachedRowSet();
            statement = connection.createStatement();

        String selectUser = String.format("select entity_id,email from mg_customer_entity where email='%s'", userEmail);
            resultSet = statement.executeQuery(selectUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (resultSet == null) {
            System.out.println("no records found");
            return isUserExist;
        } else {
            try {
                cachedRowSet.populate(resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            int count = 0;
            String email="";
            while (true) {
                try {
                    if (!cachedRowSet.next()) {
                        break;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                try {
                    int entity_id = cachedRowSet.getInt("entity_id");
                     email = cachedRowSet.getString("email");
                    System.out.println(String.format("entity_id=%d email=%s", entity_id,email));
                    count = cachedRowSet.getRow();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (count >= 1 && email.equalsIgnoreCase(userEmail))
                isUserExist = true;
            return isUserExist;
        }

    }
    public boolean getCustomerInfo(Connection connection,String customerEmail){
        Statement statement;
        ResultSet resultSet;
        CachedRowSet cachedRowSet;
        try {
            statement=connection.createStatement();
            String sqlScriptForCustomer=String.format("Select * from mg_customer_entity where email='%s';",customerEmail);
            resultSet=statement.executeQuery(sqlScriptForCustomer);
            cachedRowSet=RowSetProvider.newFactory().createCachedRowSet();
            cachedRowSet.populate(resultSet);
            if (!cachedRowSet.next()){
                logger.info("No record fund!");
                return false;
            }else {
                int rowCount;
                do {
                    rowCount = cachedRowSet.getRow();
                    int entityId = cachedRowSet.getInt("entity_id");
                    String email = cachedRowSet.getString("email");
                    String createDate = cachedRowSet.getString("created_at");
                    if (rowCount>0 && email.equalsIgnoreCase(customerEmail)) {
                        logger.info("Customer is found!");
                        System.out.printf("Customer_Id: %d, Customer_Email: %s, Created Date: %s",
                                entityId, email, createDate);
                    } else {
                        logger.info("Customer info does not match!!!");
                    }
                }while (cachedRowSet.next());
                System.out.println("\nRow count is: "+rowCount);
                return true;
                }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    //ibrahim
    public boolean getNewlyAddedCartRule(Connection connection,String cartRuleId) {
        boolean isCartRuleNameExist = false;
        Statement statement = null;
        ResultSet resultSet = null;
        CachedRowSet cachedRowSet = null;

        try {
            cachedRowSet = RowSetProvider.newFactory().createCachedRowSet();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String cartRuleSqlScript = String.format("select * from i9362596_mg2.mg_salesrule where rule_id='%s';", cartRuleId);
        try {
            resultSet = statement.executeQuery(cartRuleSqlScript);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (resultSet == null) {
            System.out.println("no records found");
            return isCartRuleNameExist;
        } else {
            try {
                cachedRowSet.populate(resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            int count = 0;
            while (true) {
                try {
                    if (!cachedRowSet.next()) {
                        break;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    String rule_id = cachedRowSet.getString("rule_id");
                    System.out.println(rule_id);
                    count = cachedRowSet.getRow();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (count >= 1)
                isCartRuleNameExist = true;
            System.out.println(count + " row(s) returned");

            return isCartRuleNameExist;
        }
    }

    public boolean getAddedRootCategoriesInfo(Connection connection, String rootCategoryName) {
        boolean isSubCategoryExist = false;
        Statement statement;
        ResultSet resultSet;
        CachedRowSet cachedRowSet;
        try {
            statement = connection.createStatement();
            String sqlScriptForSubCategory = String.format("select value_id,entity_id,value from i9362596_mg2." +
                            "mg_catalog_category_entity_varchar where value='%s'"
                    , rootCategoryName);
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
                if (rowCount >= 1 && value.equalsIgnoreCase(rootCategoryName)) {
                    isSubCategoryExist = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isSubCategoryExist;

    }

    public boolean VerifyNewlyAddedStock(Connection connection, String productID) {
        boolean isNewlyAddedStockExist = false;
        Statement statement = null;
        ResultSet resultSet = null;
        CachedRowSet cachedRowSet = null;
        try {
            cachedRowSet = RowSetProvider.newFactory().createCachedRowSet();
            statement = connection.createStatement();
            String sqlScripAddedStocks = String.format("select * from i9362596_mg2.mg_cataloginventory_stock_status_idx where product_id='%s';", productID);

            resultSet = statement.executeQuery(sqlScripAddedStocks);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (resultSet == null) {
            System.out.println("No records found");
            return isNewlyAddedStockExist;
        } else {
            int rowCount = 0;
            try {
                cachedRowSet.populate(resultSet);

                while (true) {
                    if (!cachedRowSet.next()) {
                        break;
                    }
                    String productId = cachedRowSet.getString("product_id");
                    System.out.println(productId);
                    rowCount = cachedRowSet.getRow();
                }
            }catch (SQLException e) {
                    e.printStackTrace();
            }
            if (rowCount >= 1) {
                isNewlyAddedStockExist = true;
                System.out.println(rowCount+"Row Return");
            }
            return isNewlyAddedStockExist;

        }

    }

    public boolean getCreditMemosInfo(Connection connection,String orderId){
        boolean isCreditMemoExist = false;
        Statement statement;
        ResultSet resultSet;
        CachedRowSet cachedRowSet;

        try {
            statement=connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            cachedRowSet= RowSetProvider.newFactory().createCachedRowSet();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sqlScriptForNewlyAddedCreditMemos = String.format("select * from mg_sales_flat_creditmemo_grid where order_increment_id='%s'",orderId);
        try {
            resultSet=statement.executeQuery(sqlScriptForNewlyAddedCreditMemos);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (resultSet==null){
            System.out.println("No records found");
            return isCreditMemoExist;

        }
        try {
            cachedRowSet.populate(resultSet);

            while (cachedRowSet.next()){
                isCreditMemoExist=true;
                logger.info(String.format("Found %d record with order id '%s'",cachedRowSet.getRow(),orderId));
                logger.info("Invoice_id:"+cachedRowSet.getInt("invoice_id") + "\t\t" + "billing_name : "+cachedRowSet.getString("billing_name"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if(!isCreditMemoExist)
            logger.info("no matching record");


        return isCreditMemoExist;
    }
}