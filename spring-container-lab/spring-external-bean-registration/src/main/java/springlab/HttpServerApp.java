package springlab;


import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springlab.web.DateHttpHandler;
import springlab.web.HttpController;

import java.io.IOException;
import java.net.InetSocketAddress;


@SpringBootApplication
public class HttpServerApp {

    public static void main( String[] args) throws IOException {

        final HttpServer httpServer = HttpServer.create(
                new InetSocketAddress(8000), 0);

        /*
         * Gives Spring Component, "dateHttpHandler", to another framework,
         * in this case, the HTTP server from JDK, HttpServer's Context
         */
        SpringApplication.run(HttpServerApp.class, args)
                .getBeansWithAnnotation(HttpController.class)
                .forEach((__, handler) -> httpServer.createContext(
                                httpControllerValue(), (HttpHandler)handler));

        httpServer.start();
    }

    private static String httpControllerValue() {
        return DateHttpHandler.class.getAnnotation(HttpController.class).value();
    }

}