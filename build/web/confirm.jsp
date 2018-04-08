<%-- 
    Document   : confirm
    Created on : Oct 25, 2017, 7:24:01 PM
    Author     : Bui Quang Huy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirm Page</title>
    </head>
    <body>
        <h2>Confirm</h2>
        <form action="confirm" method="POST">
            Customer Name <input type="text" name="txtCustomerName" value="" /><br/><br/>
            CardID &nbsp;&nbsp;&nbsp;<input type="text" name="txtCardID" value="" /><br/><br/>
            <input type="submit" value="Confirm" name="btAction" />&nbsp;&nbsp;
            <input type="submit" value="Cancel" name="btAction" />
        </form>
    </body>
</html>
