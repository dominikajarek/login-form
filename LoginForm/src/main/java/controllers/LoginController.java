package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.LoginDao;
import helpers.Parser;
import models.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpCookie;
import java.util.Map;

public class LoginController implements HttpHandler {

    private ObjectMapper mapper;
    private LoginDao loginDao;

    public LoginController() {
        this.mapper = new ObjectMapper();
        this.loginDao = new LoginDao();
    }

    public void handle(HttpExchange exchange) throws IOException {
        String response = "";
        try {
            getUserInput(exchange);
            response = "User authenticated";
            ResponseController.sendResponse(exchange, response, 200);
        } catch (Exception e) {
            e.printStackTrace();
            response = e.getMessage();
            ResponseController.sendResponse(exchange, response, 500);
        }
    }


    private void getUserInput(HttpExchange exchange) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(exchange.getRequestBody(), "utf-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        Map<String, String> body = Parser.parseFormData(bufferedReader.readLine());

        String email = body.get("email");
        String password = body.get("password");

        HttpCookie cookie = new HttpCookie("user", email);
        exchange.getResponseHeaders().add("Set-Cookie", cookie.toString());

        User user = new User(email, password);
        loginDao.insertUser(user);
        System.out.println(user.getEmail() + " " + user.getPassword());
    }
}
