package com.wcs.APICallingWithHttpClient.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wcs.APICallingWithHttpClient.DTO.UserDTORequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

@Service
public class ApiService {

    public ResponseEntity<Object> saveUser(UserDTORequest user) throws URISyntaxException, IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(user);

         HttpRequest postRequest =HttpRequest.newBuilder()
                 .uri(new URI("https://reqres.in/api/users"))
                 .header("x-api-key","reqres-free-v1")
                 .header("Content-Type", "application/json")
                 .POST(HttpRequest.BodyPublishers.ofString(userJson))
                 .build();

        HttpClient client = HttpClient.newHttpClient();
         HttpResponse<String> postResponse= client.send(postRequest, HttpResponse.BodyHandlers.ofString());
        Map<String,Object> response = objectMapper.readValue(postResponse.body(), new TypeReference<Map<String, Object>>() {});
          return ResponseEntity.ok(response);

    }

    public ResponseEntity<Object> fetchUser() throws URISyntaxException, IOException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
       HttpRequest getRequest= HttpRequest.newBuilder()
                .uri(new URI("https://reqres.in/api/users/2"))
                .build();

       HttpClient client = HttpClient.newHttpClient();
       HttpResponse<String> getResponse = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
       Map<String,Object > jsonResponse = mapper.readValue(getResponse.body(), new TypeReference<Map<String, Object>>(){});
      return ResponseEntity.ok(jsonResponse);
    }

    public ResponseEntity<Object> deleteUser() throws URISyntaxException, IOException, InterruptedException {

        HttpRequest deleteRequest = HttpRequest.newBuilder()
                .uri(new URI("https://reqres.in/api/users/2"))
                .header("x-api-key","reqres-free-v1")
                .DELETE()
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> deleteResponse = client.send(deleteRequest, HttpResponse.BodyHandlers.ofString());
         if(deleteResponse.statusCode() == 204){

             return ResponseEntity.noContent().build();
         }
         return ResponseEntity.ok("user deletion failed ");
    }
}
