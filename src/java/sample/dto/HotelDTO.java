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
public class HotelDTO implements Serializable{
    private String roomID;
    private String description;
    private Float hourPrice;
    private Float dayPrice;
    private int floor;

    public HotelDTO() {
    }

    public HotelDTO(String roomID, String description, Float hourPrice, Float dayPrice, int floor) {
        this.roomID = roomID;
        this.description = description;
        this.hourPrice = hourPrice;
        this.dayPrice = dayPrice;
        this.floor = floor;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getHourPrice() {
        return hourPrice;
    }

    public void setHourPrice(Float hourPrice) {
        this.hourPrice = hourPrice;
    }

    public Float getDayPrice() {
        return dayPrice;
    }

    public void setDayPrice(Float dayPrice) {
        this.dayPrice = dayPrice;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }
    
    
}
