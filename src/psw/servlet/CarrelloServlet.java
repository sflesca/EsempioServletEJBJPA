package psw.servlet;

import psw.beans.Carrello;
import psw.ejb.ProdottoBean;
import psw.model.Prodotto;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Carrello" , urlPatterns = {"/carrello"})
public class CarrelloServlet extends HttpServlet {

    @EJB
    ProdottoBean pEJB;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Carrello cart = getCarrello(request);

        String action = request.getParameter("act");
        if (action == null) action="view";
        switch (action){
            case "view":
                gotoPage("/carrello.jsp",request,response);
                break;
            case "add":
                String pidStr = request.getParameter("pid");
                long pid = Integer.parseInt(pidStr);
                Prodotto p = pEJB.find(pid);
                cart.addProdotto(p);
                gotoPage("/carrello.jsp",request,response);
                break;
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
