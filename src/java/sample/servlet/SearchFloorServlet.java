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
import sample.dto.HotelDTO;
import sample.err.ErrMsg;

/**
 *
 * @author Bui Quang Huy
 */
@WebServlet(name = "SearchFloorServlet", urlPatterns = {"/SearchFloorServlet"})
public class SearchFloorServlet extends HttpServlet {

    private final String search = "search.jsp";

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
        String searchValue = request.getParameter("txtSearchValue");
        int floor = 0;
        ErrMsg error = new ErrMsg();
        String matched = "[0-9]+";
        HttpSession session = request.getSession();
        ManageHotelDAO dao = new ManageHotelDAO();
        String url = search;
        try {
            if (searchValue.equals("")) {
                dao.searchAll();
                List<HotelDTO> listRoom = dao.getListRoom();
                session.setAttribute("SEARCHRESULT", listRoom);
            } else if (searchValue.matches(matched)) {
                floor = Integer.parseInt(searchValue);
                dao.searchFloor(floor);
                List<HotelDTO> listRoom = dao.getListRoom();
                session.setAttribute("SEARCHRESULT", listRoom);
            } else {
                error.setSearchErr("You must input a number");
                request.setAttribute("ERROR", error);
                List<HotelDTO> listRoom = dao.getListRoom();
                session.setAttribute("SEARCHRESULT", listRoom);
            }
        } catch (SQLException ex) {
            log("SearchFloorServlet - SQLException: " + ex.getMessage());
        } catch (NamingException ex) {
            log("SearchFloorServlet - NamingException: " + ex.getMessage());
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
