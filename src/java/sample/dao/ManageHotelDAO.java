/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import sample.dto.HotelDTO;
import sample.utils.DBUtils;

/**
 *
 * @author Bui Quang Huy
 */
public class ManageHotelDAO implements Serializable {

    public boolean checkLogin(String username, String password)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtils.makeConnection();

            if (con != null) {
                String sql = "SELECT * FROM tbl_account WHERE username = ? AND password = ?";

                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    private List<HotelDTO> listRoom;

    public List<HotelDTO> getListRoom() {
        return listRoom;
    }

    public void searchFloor(int floorSearch) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        this.listRoom = new ArrayList<>();

        try {
            con = DBUtils.makeConnection();

            if (con != null) {
                String sql = "SELECT * FROM tbl_room WHERE floor = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, floorSearch);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String roomID = rs.getString("roomID");
                    String description = rs.getString("description");
                    float hourPrice = rs.getFloat("hourPrice");
                    float dayPrice = rs.getFloat("dayPrice");
                    int floor = rs.getInt("floor");
                    HotelDTO dto = new HotelDTO(roomID, description, hourPrice, dayPrice, floor);

                    listRoom.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public void searchAll() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        this.listRoom = new ArrayList<>();
        try {
            con = DBUtils.makeConnection();

            if (con != null) {
                String sql = "SELECT * FROM tbl_room";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String roomID = rs.getString("roomID");
                    String description = rs.getString("description");
                    float hourPrice = rs.getFloat("hourPrice");
                    float dayPrice = rs.getFloat("dayPrice");
                    int floor = rs.getInt("floor");
                    HotelDTO dto = new HotelDTO(roomID, description, hourPrice, dayPrice, floor);
                    listRoom.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public String takeOrder(String orderDate, String fromDate, String toDate, float totalPrice,
            String customerName, String cartID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeConnection();
            String sql = "Select orderId From tbl_order";
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            String id = null;
            String code = "ABC1";
            while (rs.next()) {
                id = rs.getString("orderID");
            }
            if (id != null) {
                code = id.substring(3);
                int nCode = Integer.parseInt(code);
                nCode++;
                code = "ABC" + nCode;
            }
            sql = "INSERT INTO tbl_order values (?,?,?,?,?,?,?)";
            stm = con.prepareStatement(sql);
            stm.setString(1, code);
            stm.setString(2, orderDate);
            stm.setString(3, fromDate);
            stm.setString(4, toDate);
            stm.setFloat(5, totalPrice);
            stm.setString(6, customerName);
            stm.setString(7, cartID);
            int row = stm.executeUpdate();
            if (row > 0) {
                return code;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return "";
    }
    
     public boolean addOrderDetail(String roomID, float total,
            boolean hourPrice, String orderId) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "";
        float price = 0;
        try {
            con = DBUtils.makeConnection();
            if (hourPrice) {
                sql = "Select hourPrice as price From tbl_room Where roomID = ?";
            } else {
                sql = "Select dayPrice as price From tbl_room Where roomID = ?";
            }
            stm = con.prepareStatement(sql);
            stm.setString(1, roomID);
            rs = stm.executeQuery();
            if (rs.next()) {
                price = rs.getFloat("price");
            }

            sql = "Insert into tbl_orderDetail(price,total,hourPrice,orderID,roomID) values (?,?,?,?,?)";
            stm = con.prepareStatement(sql);
            stm.setFloat(1, price);
            stm.setFloat(2, total);
            stm.setBoolean(3, hourPrice);
            stm.setString(4, orderId);
            stm.setString(5, roomID);
            int row = stm.executeUpdate();
            if (row > 0) {
                return true;
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}
