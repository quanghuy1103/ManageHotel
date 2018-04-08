/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.err;

/**
 *
 * @author Bui Quang Huy
 */
public class ErrMsg {
    private String searchErr;
    private String bookErr;
    private String roomBooked;

    public String getBookErr() {
        return bookErr;
    }

    public String getRoomBooked() {
        return roomBooked;
    }

    public void setRoomBooked(String roomBooked) {
        this.roomBooked = roomBooked;
    }

    
    public void setBookErr(String bookErr) {
        this.bookErr = bookErr;
    }

    public ErrMsg() {
    }

    public String getSearchErr() {
        return searchErr;
    }

    public void setSearchErr(String searchErr) {
        this.searchErr = searchErr;
    }
    
    
}
