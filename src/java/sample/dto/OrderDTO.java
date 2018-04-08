/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dto;

import java.io.Serializable;

/**
 *
 * @author Bui Quang Huy
 */
public class OrderDTO implements Serializable{
    private String name;
    private String orderDate;
    private String fromDate;
    private String toDate;
    private float totalPrice;
    private String cartID;
    private String roomID;
    private String price;
    private boolean hourPrice;
    private int nDay;

    public OrderDTO(float totalPrice, String roomID, String price, boolean hourPrice, int nDay) {
        this.totalPrice = totalPrice;
        this.roomID = roomID;
        this.price = price;
        this.hourPrice = hourPrice;
        this.nDay = nDay;
    }
    
    public OrderDTO() {
    }

    public int getnDay() {
        return nDay;
    }

    public void setnDay(int nDay) {
        this.nDay = nDay;
    }

    

    public boolean isHourPrice() {
        return hourPrice;
    }

    public void setHourPrice(boolean hourPrice) {
        this.hourPrice = hourPrice;
    }

    


    
    public OrderDTO(String name, String orderDate, String fromDate, String toDate, Float totalPrice, String cartID) {
        this.name = name;
        this.orderDate = orderDate;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.totalPrice = totalPrice;
        this.cartID = cartID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCartID() {
        return cartID;
    }

    public void setCartID(String cartID) {
        this.cartID = cartID;
    }
    
    
}
