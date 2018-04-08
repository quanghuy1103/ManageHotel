/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.dto.OrderDTO;

/**
 *
 * @author Bui Quang Huy
 */
@WebServlet(name = "BookServlet", urlPatterns = {"/BookServlet"})
public class BookServlet extends HttpServlet {

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
        String roomID = request.getParameter("txtRoomID");
        String wPrice = request.getParameter("rdoPrice");
        String hourPrice = request.getParameter("txtHourPrice");
        String dayPrice = request.getParameter("txtDayPrice");
        String nHour = request.getParameter("sltHour");
        String nDay = request.getParameter("sltDay");
        String searchValue = request.getParameter("txtLastSearchValue");
        
        float totalPrice = 0;
        String url = "";
        OrderDTO order = null;
        try {
            HttpSession session = request.getSession();
            List<OrderDTO> orders = (List<OrderDTO>) session.getAttribute("ORDER");

            if (orders == null) {
                orders = new ArrayList<>();
            }
            if (wPrice.equals("hour")) {
                totalPrice = Float.parseFloat(hourPrice) * Integer.parseInt(nHour);
                order = new OrderDTO(totalPrice, roomID, hourPrice, true, Integer.parseInt(nHour));
            } else {
                totalPrice = Float.parseFloat(dayPrice) * Integer.parseInt(nDay);
                order = new OrderDTO(totalPrice, roomID, dayPrice , false, Integer.parseInt(nDay));
            }
            orders.add(order);
            session.setAttribute("ORDER", orders);
            url = "SearchFloorServlet?"
                    + "&txtSearchValue=" + searchValue;
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
