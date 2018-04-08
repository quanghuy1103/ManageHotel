/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.dao.ManageHotelDAO;
import sample.dto.OrderDTO;

/**
 *
 * @author Bui Quang Huy
 */
@WebServlet(name = "TakeOrderServlet", urlPatterns = {"/TakeOrderServlet"})
public class TakeOrderServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String customerName = request.getParameter("txtCustomerName");
        String cartID = request.getParameter("txtCardID");
        String url = "";
        try {
            HttpSession session = request.getSession();
            String orderDate = (String) session.getAttribute("ORDERDATE");
            String fromDate = (String) session.getAttribute("FROMDATE");
            String toDate = (String) session.getAttribute("TODATE");
            String totalst = (String) session.getAttribute("TOTALPRICE");
            float total = Float.parseFloat(totalst);
            boolean hourPrice = (boolean) session.getAttribute("HOURPRICE");
            ManageHotelDAO dao = new ManageHotelDAO();
            String code = dao.takeOrder(orderDate, fromDate, toDate, total, customerName, cartID);
            if (!code.equals("")) {
                List<OrderDTO> list = (List<OrderDTO>) session.getAttribute("ORDER");
                for (OrderDTO order : list) {
                    dao.addOrderDetail(order.getRoomID(), order.getTotalPrice(), order.isHourPrice(), code);
                }
            }
            url = "showOrder.jsp";
            session.setAttribute("CUSNAME", customerName);
            session.setAttribute("CARTID", cartID);

        } catch (SQLException ex) {
            log("TakeOrderServlet - SQLException: " + ex.getMessage());
        } catch (NamingException ex) {
            log("TakeOrderServlet - NamingException: " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
