<%@ page import="java.util.List" %>
<html>

    <head>
           <title>Worker list</title>
    </head>

    <body>

        <!-- request -->
        <!-- response -->
        <!-- out -->
        <!-- session -->
        <!-- page -->
        <ul>
            <% for (String worker : (List<String>)request.getAttribute("workers")) { %>
                  <li> <%= worker %> </li>
            <% } %>
        </ul>

    </body>

</html>