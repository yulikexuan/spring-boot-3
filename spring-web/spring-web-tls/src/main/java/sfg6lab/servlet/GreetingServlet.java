//: sfg6lab.servlet.StatisticServlet.java

package sfg6lab.servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


// @WebServlet(urlPatterns = "/stat") // Must be used with @ServletComponentScan
public class GreetingServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.getWriter().write("Welcome to Spring Boot 3!");
    }

} ///:~