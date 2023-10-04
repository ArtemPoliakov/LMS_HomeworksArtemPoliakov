package org.example.Homework_13;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.Request;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.IntStream.range;

public class UserRequestsTaskOne {
    private static final String TEST_HARDCODED_JSON = """
            {
              "name": "A",
              "username": "B",
              "email": "Sincere@april.biz",
              "address": {
                "street": "Kulas Light",
                "suite": "Apt. 556",
                "city": "Gwenborough",
                "zipcode": "92998-3874",
                "geo": {
                  "lat": "-37.3159",
                  "lng": "81.1496"
                }
              },
              "phone": "1-770-736-8031 x56442",
              "website": "hildegard.org",
              "company": {
                "name": "Romaguera-Crona",
                "catchPhrase": "Multi-layered client-server neural-net",
                "bs": "harness real-time e-markets"
              }
            }
            """;
    public static void main(String... args) throws URISyntaxException, IOException, InterruptedException {
        User user = User.builder()
                .name("Artem")
                        .email("apoliakov100@gmail.com")
                                .build();


      //  createNewUserToServer(User.builder().name("Artem").build(), "https://jsonplaceholder.typicode.com/users");
      //  updateUserInfo(user, "https://jsonplaceholder.typicode.com/users/1");
      //  deleteFromServer("https://jsonplaceholder.typicode.com/users/1");

      //  System.out.println(getUserById(10, "https://jsonplaceholder.typicode.com/users"));

        List list = getAllUsers("https://jsonplaceholder.typicode.com/users");
        list.forEach(System.out::println);
    }


    public static void createNewUserToServer(User user, String uri) throws URISyntaxException, IOException, InterruptedException {
        Gson gson = new Gson();
        gson.newBuilder().setPrettyPrinting().create();
        String userJson = gson.toJson(user);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(uri))
                .POST(HttpRequest.BodyPublishers.ofString(userJson))
                .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());
        System.out.println(response.body());
    }

    public static void updateUserInfo(User user, String uri) throws URISyntaxException, IOException, InterruptedException {
        Gson gson = new Gson();
        gson.newBuilder().setPrettyPrinting().create();
        String userJson = gson.toJson(user);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(uri))
                .PUT(HttpRequest.BodyPublishers.ofString(userJson))
       //         .PUT(HttpRequest.BodyPublishers.ofString(TEST_HARDCODED_JSON))
                .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println("response.body() = " + response.body());
    }

    public static void deleteFromServer(String uri) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(uri))
                .DELETE()
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
    }

    public static User getUserById(int id, String uri) throws URISyntaxException, IOException, InterruptedException {
        uri = uri+"/"+id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(uri))
                .GET()
                .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        Gson gson = new Gson();
        gson.newBuilder().setPrettyPrinting().create();
        return gson.fromJson(response.body(), User.class);
    }

    public static List<User> getAllUsers(String uri) throws URISyntaxException, IOException, InterruptedException {
        int id = 1;
        List<User> list = new LinkedList<>();
        while(true){
            String userUri = uri+"/"+id;
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(userUri))
                    .GET()
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode()!=200) break;
            Gson gson = new Gson();
            gson.newBuilder().setPrettyPrinting().create();
            User user = gson.fromJson(response.body(), User.class);
            list.add(user);
            id++;
        }
        return list;
    }
}
