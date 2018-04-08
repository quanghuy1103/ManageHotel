<%-- 
    Document   : showOrder
    Created on : Oct 25, 2017, 11:02:10 PM
    Author     : Bui Quang Huy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Order Page</h1>
        <c:set var="cusName" value="${sessionScope.CUSNAME}"/>
        Customer name : ${cusName}<br/>
        Order Date : ${sessionScope.ORDERDATE}<br/>
        From Date : ${sessionScope.FROMDATE}<br/>
        To Date : ${sessionScope.TODATE}<br/>
        <c:set var="orders" value="${sessionScope.ORDER}"/>
        <table border="1">
            <thead>
                <tr>
                    <th>No.1</th>
                    <th>Room</th>
                    <th>Price</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="dto" varStatus="counter" items="${orders}">
                    <tr>
                        <td>${counter.count}</td>
                        <td>${dto.roomID}</td>
                        <td>${dto.totalPrice}</td>
                    </tr>
                </c:forEach>
                
            </tbody>
        </table>

    </body>
</html>
