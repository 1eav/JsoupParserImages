package org.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
      The code represents an example of parsing a JSON file
     using json.simple library in Java.
      Note that the code handles exceptions
     IOException and ParseException, and if they occur
     Prints the stack trace to the console.
*/

public class JsonParser {
    public static void main(String[] args) {

        // Create a JSONParser object that will be used to parse the JSON file.
        JSONParser parser = new JSONParser();

        try {

            // Create a FileReader object to read the JSON file.
            FileReader reader = new FileReader("src/main/resources/test.json");
            // Parse the JSON file and get an object representing the entire JSON file.
            Object obj = parser.parse(reader);
            // Cast the object to type JSONObject, since we know that the root element is a JSON object.
            JSONObject jsonObject = (JSONObject) obj;

            // Extract the value of each field (key) from the JSON object and cast it to the appropriate type.
            String name = (String) jsonObject.get("name");
            Long age = (Long) jsonObject.get("age");
            String email = (String) jsonObject.get("email");
            Boolean isPremium = (Boolean) jsonObject.get("isPremium");
            JSONArray friendsArray = (JSONArray) jsonObject.get("friends");

            // Create a list to store friends
            List<String> friends = new ArrayList<>();

            // We go through the array of friends and add them to the list
            for (Object friend : friendsArray) {
                friends.add((String) friend);
            }

            // Output the extracted data to the console
            System.out.println("name: " + name);
            System.out.println("age: " + age);
            System.out.println("email: " + email);
            System.out.println("isPremium: " + isPremium);
            System.out.println("friends: " + friends);

        } catch (IOException | ParseException ex) {
            ex.printStackTrace();
        }
    }
}