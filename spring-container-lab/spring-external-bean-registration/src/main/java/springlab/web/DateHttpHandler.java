//: springlab.web.DateHttpHandler.java


package springlab.web;


import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.time.LocalDate;


@HttpController("/date")
public class DateHttpHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

        try (OutputStream os = exchange.getResponseBody()) {
            os.write(LocalDate.now().toString().getBytes() );
        }

    }

}///:~