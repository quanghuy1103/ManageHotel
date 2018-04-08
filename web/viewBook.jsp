<%-- 
    Document   : viewBook
    Created on : Oct 24, 2017, 9:43:40 PM
    Author     : Bui Quang Huy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Booking details</title>
    </head>
    <body>
        <h2>Booking details</h2><br/><br/>

        <b>Your order information includes</b><br/><br/>

        <c:set var="orders" value="${sessionScope.ORDER}"/>
        <c:if test="${not empty orders}">
            <form action="bookInfo" method="POST">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Room</th>
                            <th>Price</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="total" value="${0}"/>
                        <c:forEach var="dto" items="${orders}" varStatus="counter">
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${dto.roomID}
                                </td>
                                <td>
                                    ${dto.price}

                                </td>
                                <td>
                                    <input type="checkbox" name="cbxAction" value="${dto.roomID}" />
                                </td>
                                <c:set var="total" value="${dto.totalPrice + total}"/>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table><br/>

                Total : ${total}<br/>
                <input type="hidden" name="txtTotal" value="${total}" />
                Include Your date you will take room : 
                <select name="sltHour">
                        <option>08:00</option><option>09:00</option><option>10:00</option><option>11:00</option>
                        <option>12:00</option><option>13:00</option><option>14:00</option><option>15:00</option>
                        <option>16:00</option><option>17:00</option><option>18:00</option>
                    </select>&nbsp; 
                <select name="sltDay">
                    <option>1</option><option>2</option><option>3</option><option>4</option>
                    <option>5</option><option>6</option><option>7</option><option>8</option>
                    <option>9</option><option>10</option><option>11</option><option>12</option>
                    <option>13</option><option>14</option><option>15</option><option>16</option>
                    <option>17</option><option>18</option><option>19</option><option>20</option>
                    <option>21</option><option>22</option><option>23</option><option>24</option>
                    <option>25</option><option>26</option><option>27</option><option>28</option>
                    <option>29</option><option>30</option>
                </select>&nbsp;
                <select name="sltMonth">
                    <option>1</option><option>2</option><option>3</option><option>4</option>
                    <option>5</option><option>6</option><option>7</option><option>8</option>
                    <option>9</option><option>10</option><option>11</option><option>12</option>
                </select>&nbsp;
                <select name="sltYear">
                    <option>2017</option><option>2018</option><option>2019</option><option>2020</option>
                </select><br/>
                <input type="submit" value="Book more" name="btAction"/> &nbsp;&nbsp;&nbsp;
                <input type="submit" value="Remove" name="btAction"/> &nbsp;&nbsp;&nbsp;
                <input type="submit" value="Rent" name="btAction"/>
            </form><br/><br/>
        </c:if>
        <c:if test="${empty orders}">
            You not book any room 
            <a href="search.jsp">Click here to book more</a>
        </c:if>
    </body>
</html>
