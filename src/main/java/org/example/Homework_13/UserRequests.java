package org.example.Homework_13;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.SneakyThrows;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class UserRequests {
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
      //   updateUserInfo(user, "https://jsonplaceholder.typicode.com/users/1");
      //  deleteFromServer("https://jsonplaceholder.typicode.com/users/1");

      //  System.out.println(getUserById(10, "https://jsonplaceholder.typicode.com/users"));

        //  List list = getAllUsers("https://jsonplaceholder.typicode.com/users");
        //  list.forEach(System.out::println);
       // getUserByName("https://jsonplaceholder.typicode.com/users", "Clementine Bauch");

       // getUserLastPostComments("https://jsonplaceholder.typicode.com", 1);
       // printTodosForUser("https://jsonplaceholder.typicode.com", 1);
    }


    public static void createNewUserToServer(User user, String uri) throws URISyntaxException, IOException, InterruptedException {
        Gson gson = new Gson();
        gson.newBuilder().setPrettyPrinting().create();
        String userJson = gson.toJson(user);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(uri))
                .header("content-type", "application/json; charset=utf-8")
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
                .header("content-type", "application/json; charset=utf-8")
                .PUT(HttpRequest.BodyPublishers.ofString(userJson))
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
        ExecutorService service = Executors.newFixedThreadPool(10);
        AtomicInteger id = new AtomicInteger(0);
        CopyOnWriteArrayList<User> list = new CopyOnWriteArrayList<>();
        for(int i = 0; i < 10; i++){
            service.execute(()->{
                String userUri = uri+"/"+id.incrementAndGet();
                HttpRequest request;
                try {
                    request = HttpRequest.newBuilder()
                            .uri(new URI(userUri))
                            .GET()
                            .build();
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
                HttpClient client = HttpClient.newHttpClient();
                HttpResponse<String> response;
                try {
                    response = client.send(request, HttpResponse.BodyHandlers.ofString());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if(response.statusCode()!=200) service.shutdown();
                Gson gson = new Gson();
                gson.newBuilder().setPrettyPrinting().create();
                User user = gson.fromJson(response.body(), User.class);
                list.add(user);
            });
        }
        service.close();
        return list;
    }
    @SneakyThrows
    public static void getUserByName(String uri, String name){
        Gson gson = new Gson();
        gson.newBuilder().setPrettyPrinting().create();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(uri+"?name="+name.replace(" ", "%20")))
                .header("content-type", "application/json; charset=utf-8")
                .GET()
                .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.body());
    }

    @SneakyThrows
    public static void getUserLastPostComments(String uri, int userId){
        String PostsUri = uri+String.format("/users/%s/posts", userId);
        Gson gson = new Gson();
        gson.newBuilder().setPrettyPrinting().create();
        String userPostsJson = sendGetJsonRequest(PostsUri);
        List<PostDto> list = gson.fromJson(userPostsJson, new TypeToken<List<PostDto>>(){}.getType());
        System.out.println(list);
        int last = list.stream().map(PostDto::getId).max(Comparator.naturalOrder()).orElseThrow();

        String lastPostCommentsJson = sendGetJsonRequest(uri+String.format("/posts/%s/comments", last));
        System.out.println(lastPostCommentsJson);
        String filePath =
                "src/main/java/org/example/Homework_13/"+String.format("user-%s-post-%s-comments.json",userId,last);
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){
                writer.write(lastPostCommentsJson);
        } catch(IOException e){
            throw e;
        }
    }
    @SneakyThrows
    public static void printTodosForUser(String uri, int userId){
        String todosUri = uri+String.format("/users/%s/todos", userId);
        String todosJson = sendGetJsonRequest(todosUri);
        Gson gson = new Gson();
        gson.newBuilder().setPrettyPrinting().create();
        List<TodoDto> list = gson.fromJson(todosJson, new TypeToken<List<TodoDto>>(){}.getType());
        List openTodos = list.stream().filter(t->t.isCompleted()==false).toList();
        openTodos.forEach(System.out::println);
    }
    @SneakyThrows
    private static String sendGetJsonRequest(String uri){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(uri))
                .header("content-type", "application/json; charset=utf-8")
                .GET()
                .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        return response.body();
    }
}
