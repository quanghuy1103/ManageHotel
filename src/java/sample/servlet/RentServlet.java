/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
@WebServlet(name = "RentServlet", urlPatterns = {"/RentServlet"})
public class RentServlet extends HttpServlet {

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
        String total = (String) request.getParameter("txtTotal");
        String fromHour = (String) request.getParameter("sltHour");
        String day = (String) request.getParameter("sltDay");
        String month = (String) request.getParameter("sltMonth");
        String year = (String) request.getParameter("sltYear");
        String fromDate = "";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        String orderDate = dateFormat.format(cal.getTime());
        String url = "confirm.jsp";
        int days = 0;
        boolean hourPrice = true;
        try {
            HttpSession session = request.getSession();
            List<OrderDTO> orders = (List<OrderDTO>) session.getAttribute("ORDER");
            if (orders != null) {
                fromHour = fromHour.substring(0, 2);
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(fromHour));
                cal.set(Calendar.YEAR, Integer.parseInt(year));
                cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
                cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
                Date from = cal.getTime();
                fromDate = dateFormat.format(from);
                for (OrderDTO order : orders) {
                    if (!order.isHourPrice()) {
                        hourPrice = false;
                    }
                }
                if (!hourPrice) {
                    for (OrderDTO order : orders) {
                        if (!order.isHourPrice()) {
                            if (days < order.getnDay()) {
                                days = order.getnDay();
                            }
                        }
                    }
                    cal.add(Calendar.DAY_OF_MONTH, days);
                } else {
                    for (OrderDTO order : orders) {
                        if (days < order.getnDay()) {
                            days = order.getnDay();
                        }
                    }
                    cal.add(Calendar.HOUR_OF_DAY, days);
                }
                Date to = cal.getTime();
                String toDate = dateFormat.format(to);
                session.setAttribute("ORDERDATE", orderDate);
                session.setAttribute("FROMDATE", fromDate);
                session.setAttribute("TODATE", toDate);
                session.setAttribute("TOTALPRICE", total);
                session.setAttribute("HOURPRICE", hourPrice);
            }
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
