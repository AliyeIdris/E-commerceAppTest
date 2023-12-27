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

    public static String taxRuleName=faker.name().title();
    public static int customerIndexNumber=faker.number().numberBetween(0,5);
    public static int productIndexNumber=faker.number().numberBetween(0,2);
    public static int taxIndexNumber=faker.number().numberBetween(0,6);

    public static int number=faker.number().numberBetween(1,5);
}
