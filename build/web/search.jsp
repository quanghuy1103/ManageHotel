<%-- 
    Document   : search
    Created on : Oct 23, 2017, 10:45:26 PM
    Author     : Bui Quang Huy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hotel Page</title>
    </head>
    <body>
        <b>Welcome to ABC Hotel</b><br/><br/><br/>
        <form action="searchFloor" method="POST">
            Floor &nbsp;&nbsp;&nbsp; <input type="text" name="txtSearchValue" value="" /> &nbsp;&nbsp; Để trống-search tất cả<br/><br/>
            <input type="submit" value="Search" />
        </form><br/><br/>
        <c:set var="error" value="${requestScope.ERROR}"/>
        <c:if test="${not empty error}">
            <font color="red">${error.searchErr}</font>
        </c:if>

        <c:set var="order" value="${sessionScope.ORDER}"/>
        <c:if test="${not empty order}">
            <c:forEach var="item" items="${order}">

            </c:forEach>
        </c:if>



        <c:if test="${empty error}">
            <c:set var="searchValue" value="${param.txtSearchValue}"/>
            <c:if test="${not empty searchValue}">
                <c:set var="searchResult" value="${sessionScope.SEARCHRESULT}"/>
                <c:if test="${not empty searchResult}">   
                    <h2>Search Result</h2>
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>roomID</th>
                                <th>description</th>
                                <th>HourPrice</th>
                                <th>DayPrice</th>
                                <th>Book</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="dto" items="${searchResult}" varStatus="counter">
                                <c:if test="${not empty order}">
                                    <c:forEach var="item" items="${order}">
                                        <c:if test="${item.roomID == dto.roomID}">
                                            <c:set var="isBook" value="yes"/>
                                        </c:if>
                                    </c:forEach>
                                </c:if>

                            <form action="book">
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>${dto.roomID}
                                        <input type="hidden" name="txtRoomID" value="${dto.roomID}" />
                                    </td>
                                    <td>${dto.description}
                                        <input type="hidden" name="txtDescription" value="${dto.description}" />
                                    </td>
                                    <td><select name="sltHour">
                                            <option>1</option>
                                            <option>2</option>
                                            <option>3</option>
                                            <option>4</option>
                                            <option>5</option>
                                            <option>6</option>
                                        </select>
                                        <input type="radio" name="rdoPrice" value="hour" checked="checked">Hour
                                        <input type="hidden" name="txtHourPrice" value="${dto.hourPrice}" />
                                    </td>
                                    <td><select name="sltDay">
                                            <option>1</option>
                                            <option>2</option>
                                            <option>3</option>
                                            <option>4</option>
                                            <option>5</option>
                                            <option>6</option>
                                        </select>
                                        <input type="radio" name="rdoPrice" value="day">Day
                                        <input type="hidden" name="txtDayPrice" value="${dto.dayPrice}" />
                                    </td>
                                    <td>
                                        <c:if test="${not empty isBook}">
                                            Booked
                                        </c:if>
                                        <c:if test="${empty isBook}">
                                            <input type="hidden" name="txtLastSearchValue" value="${searchValue}" />
                                            <input type="submit" value="Book" />
                                        </c:if>
                                    </td>
                                </tr>
                            </form>
                        </c:forEach>
                    </tbody>
                </table>

            </c:if>
            <c:if test="${empty searchResult}">
                <h2>No recorder matched</h2>
            </c:if>
        </c:if>
        <c:if test="${empty searchValue}">
            <c:set var="searchResult" value="${sessionScope.SEARCHRESULT}"/>
            <c:if test="${not empty searchResult}">
                <h2>Search Result</h2>
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>roomID</th>
                            <th>description</th>
                            <th>HourPrice</th>
                            <th>DayPrice</th>
                            <th>Book</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${searchResult}" varStatus="counter">
                            <c:if test="${not empty order}">
                                <c:forEach var="item" items="${order}">
                                    <c:if test="${item.roomID == dto.roomID}">
                                        <c:set var="isBook" value="yes"/>
                                    </c:if>
                                </c:forEach>
                            </c:if>

                        <form action="book" method="POST">
                            <tr>
                                <td>${counter.count}</td>
                                <td>${dto.roomID}
                                    <input type="hidden" name="txtRoomID" value="${dto.roomID}" />
                                </td>
                                <td>${dto.description}
                                    <input type="hidden" name="txtDescription" value="${dto.description}" />
                                </td>
                                <td><select name="sltHour">
                                        <option>1</option>
                                        <option>2</option>
                                        <option>3</option>
                                        <option>4</option>
                                        <option>5</option>
                                        <option>6</option>
                                    </select>
                                    <input type="radio" name="rdoPrice" value="hour" checked="checked">Hour
                                    <input type="hidden" name="txtHourPrice" value="${dto.hourPrice}" />
                                </td>
                                <td><select name="sltDay">
                                        <option>1</option>
                                        <option>2</option>
                                        <option>3</option>
                                        <option>4</option>
                                        <option>5</option>
                                        <option>6</option>
                                    </select>
                                    <input type="radio" name="rdoPrice" value="day">Day
                                    <input type="hidden" name="txtDayPrice" value="${dto.dayPrice}" />
                                </td>
                                <td>
                                    <c:if test="${not empty isBook}">
                                        Booked
                                    </c:if>
                                    <c:if test="${empty isBook}">
                                        <input type="hidden" name="txtLastSearchValue" value="${searchValue}" />
                                        <input type="submit" value="Book" />
                                    </c:if>
                                </td>
                            </tr>
                        </form>
                        <c:if test="${not empty isBook}">
                            <c:set var="isBook" value=""/>
                        </c:if>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </c:if><br/>
    <a href="viewBook.jsp">View Booking Details</a>
</c:if>
</body>
</html>
