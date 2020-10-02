import com.sun.net.httpserver.HttpServer;
import controllers.LoginController;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Login {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8005), 0);
        server.createContext("/login", new LoginController());
        server.setExecutor(null);
        server.start();
        System.out.println("Server has started on port: " + server.getAddress().getPort());
    }
}
