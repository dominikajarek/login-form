package controllers;

import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;

public class ResponseController {

    protected static void sendResponse(HttpExchange exchange, String response, int status) throws IOException {
        if (status == 200) {
            exchange.getResponseHeaders().put("Content-type", Collections.singletonList("application/json"));
        }
        exchange.getResponseHeaders().put("Access-Control-Allow-Origin", Collections.singletonList("http://localhost:63342"));
        exchange.sendResponseHeaders(status, response.getBytes().length);

        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(response.getBytes());
        outputStream.close();
    }
}
