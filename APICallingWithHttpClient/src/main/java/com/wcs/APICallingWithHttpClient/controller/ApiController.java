package com.wcs.APICallingWithHttpClient.controller;

import com.wcs.APICallingWithHttpClient.DTO.UserDTORequest;
import com.wcs.APICallingWithHttpClient.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;

@RestController
public class ApiController {

    @Autowired
    ApiService apiService;


    @PostMapping("/saveUser")
    public ResponseEntity<Object> saveUser(@RequestBody UserDTORequest user) throws URISyntaxException, IOException, InterruptedException {
        return apiService.saveUser(user);
    }

    @GetMapping("/fetchUser")
    public ResponseEntity<Object> fetchUser() throws URISyntaxException, IOException, InterruptedException {
        return apiService.fetchUser();
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<Object> deleteUser() throws URISyntaxException, IOException, InterruptedException {
             return apiService.deleteUser();
    }
}