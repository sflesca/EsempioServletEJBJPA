package psw.servlet;

import org.jboss.weld.context.ejb.Ejb;
import psw.ejb.CaricaDatiBean;
import psw.ejb.HelloBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Hello", urlPatterns = {"/hello"})
public class Hello extends HttpServlet {

    @EJB(name="HelloEJB")
    HelloBean eb;

    @EJB(name= "CaricaDatiEJB")
    CaricaDatiBean cb;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.getWriter().println(eb.message());
        cb.caricaTutto();
    }
}
