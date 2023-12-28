package com.seleniummastercucumber.utility;

import com.github.javafaker.Faker;

/**
 * @author : user
 * @created : 8.12.2023,16:57
 * @Email :aliyeidiris@gmail.com
 **/
public class TestDataHolder {
    static Faker faker = new Faker();
    public static String firstName = faker.name().firstName();
    public static String comments=faker.internet().domainWord();
    public static String timeStamp(){
        long timeStamp=System.currentTimeMillis();
        String time=(Long.toString(timeStamp).toString().substring(10));
        return time;
    }

    String mavenCommand="mvn -Dtest=CucumberTestRunner test";

    public static String taxRuleName="ibrahim "+faker.name().lastName();
    public static int customerIndexNumber=faker.number().numberBetween(0,4);
    public static int productIndexNumber=faker.number().numberBetween(0,1);
    public static int taxIndexNumber=faker.number().numberBetween(0,20);
    public static int number=faker.number().numberBetween(1,100);
    public static String startDate= String.valueOf(01/01/2022);
    public static String endDate=String.valueOf(28/12/2023);
}
