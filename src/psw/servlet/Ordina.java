package psw.servlet;

import psw.beans.Carrello;
import psw.ejb.OrderException;
import psw.ejb.OrderManagerBean;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@WebServlet(name = "Ordina", urlPatterns = {"/ordina"})
public class Ordina extends HttpServlet {

    @EJB
    OrderManagerBean oEJB;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Carrello cart = getCarrello(request);
        if(cart.size()>0){

            try {
                oEJB.PlaceOrder(new Date(), cart.getContenuto());
            } catch (OrderException e) {
                cart.svuota();
                gotoPage("ordinenoneffettuato.jsp",request,response);
            }
            cart.svuota();
            gotoPage("ordineeffettuato.jsp",request,response);
        }else{
            gotoPage("nothingtoorder.jsp",request,response);
        }
    }

    private Carrello getCarrello(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Carrello cart = (Carrello) session.getAttribute("cart");
        if (cart==null){
            cart =  new Carrello();
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    private void gotoPage(String address, HttpServletRequest request,
                          HttpServletResponse response) throws ServletException,IOException{
        RequestDispatcher dispatcher =
                request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
}
