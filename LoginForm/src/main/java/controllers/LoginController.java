package controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import helpers.Parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpCookie;
import java.sql.SQLException;
import java.util.Map;

public class LoginController implements HttpHandler {

    public void handle(HttpExchange exchange) throws IOException {
        String response = "";
        try {
            login(exchange);
            response = "User authenticated";
            ResponseController.sendResponse(exchange, response, 200);
        } catch (Exception e) {
            e.printStackTrace();
            response = e.getMessage();
            ResponseController.sendResponse(exchange, response, 500);
        }
    }


    private void login(HttpExchange exchange) throws IOException, SQLException {
        InputStreamReader inputStreamReader = new InputStreamReader(exchange.getRequestBody(), "utf-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        Map<String, String> body = Parser.parseFormData(bufferedReader.readLine());

        String email = body.get("email");
        String password = body.get("password");
        System.out.println(body.get("email"));
        System.out.println(body.get("password"));
        HttpCookie cookie = new HttpCookie("user", email);
        exchange.getResponseHeaders().add("Set-Cookie", cookie.toString());
//        LoginDao loginDao = new LoginDao();
//        User user = loginDao.getByEmailPassword(email, password);
    }
}
