package magentocucumber.universalfunctions;

import com.github.javafaker.Faker;

/**
 * @author : user
 * @created : 8.12.2023,16:57
 * @Email :aliyeidiris@gmail.com
 **/
public class TestDataHolder {
    static Faker faker = new Faker();
    public static String firstName = faker.name().firstName();
    public static String timeStamp(){
        long timeStamp=System.currentTimeMillis();
        String time=(Long.toString(timeStamp).toString().substring(10));
        return time;
    }
}
