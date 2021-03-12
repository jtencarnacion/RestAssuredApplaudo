import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import Pojo.ListPojo;

import java.io.IOException;
import java.util.Arrays;


public class Tests {

@Test
    public void getBirthdayOnly_test() throws IOException {

    Response response =  RestAssured.get("https://www.breakingbadapi.com/api/characters/1");
    String data = response.asString();
    String character = new String(data.substring( 1, data.length() - 1 ));
    ObjectMapper mapper = new ObjectMapper();
    ListPojo readValue = mapper.readValue(character, ListPojo.class);
    System.out.println("Name: " + readValue.getName());
    System.out.println("BirthDay: " + readValue.getBirthday());
    System.out.println("------------------------------------");
}

    @Test
    public void getAllCharacters() throws IOException {

        Response response =  RestAssured.get("https://www.breakingbadapi.com/api/characters");
        String data = response.asString();
        ListPojo[] characters = response.body().as(ListPojo[].class);
        Arrays.stream(characters).forEach(value -> {
            System.out.print("Name: " + value.getName());
            System.out.print("\nPortrayed: " + value.getPortrayed());
            System.out.print("\n---------------------------\n");
        });

    }

}
