<%@ page import="java.util.Date" %>
<html>

    <head>
           <title>JSP-page</title>
    </head>

    <body>

           <h1>It is JSP page</h1>

            <!-- Фрагмент кода -->
            <%
                System.out.println("Hello");
            %>

            <div style="color: #FF0000">
                <!-- Выражение (выводится в HTML в виде строки) -->
                <%=
                    "Hello"
                %>
            </div>

            <%-- Комментарий в JSP --%>

            <!-- Объявление переменной -->
            <%! Date d = new Date(); %>

            <h1>
                <%=
                    d
                %>
            </h1>

            <ul>
                <% for (int i = 0; i < 10; i++) { %>
                       <li>
                        <%= i %>
                       </li>
                <% } %>
            </ul>

    </body>

</html>