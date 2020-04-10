package psw.servlet;

import psw.ejb.CategoriaBean;
import psw.ejb.ProdottoBean;
import psw.model.Categoria;
import psw.model.Prodotto;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;

@WebServlet(name = "Catalogo", urlPatterns = {"/", "/catalogo"})
public class Catalogo extends HttpServlet {

    @EJB
    ProdottoBean pEJB;

    @EJB
    CategoriaBean cEJB;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String catidStr = request.getParameter("catid");
        Collection<Prodotto> prodotti;
        Collection<Categoria> categorie =cEJB.findAll();;
        if(catidStr!=null){
            long catid = Integer.parseInt(catidStr);
            prodotti = pEJB.findAllProductsByCategoria(catid);
        }else{
            prodotti = new LinkedList<Prodotto>();
        }
        request.setAttribute("prodotti", prodotti);
        request.setAttribute("categorie", categorie);

        gotoPage("/catalogo.jsp", request, response);
    }

    private void gotoPage(String address, HttpServletRequest request,
                          HttpServletResponse response) throws ServletException,IOException{
        RequestDispatcher dispatcher =
                request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
}
