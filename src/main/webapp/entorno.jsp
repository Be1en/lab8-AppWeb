<%-- 
    Document   : entorno
    Created on : 06/10/2023, 01:13:43 PM
    Author     : valer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--validacion de sesion--%>
<%
HttpSession misession= (HttpSession) request.getSession();
String usuario= (String) misession.getAttribute("usuario");  
String estado= (String) misession.getAttribute("estado");  
if(misession == null || misession.getAttribute("usuario") == null){
      out.print("<link rel=\"stylesheet\" \n"
                + "              href=\"webjars/bootstrap/5.3.1/css/bootstrap.min.css\" type=\"text/css\" />");
      out.println("<center>");           
      out.println("<h3 class=\"mt-5\">No tiene permisos para acceder a esta seccion</h3>");
      out.println("<a class='btn btn-primary ' href='/WebSistema/login.jsp'>Ir a pagina de acceso</a>");
      out.println("</center>");
      return;
  }    
%>
<!DOCTYPE html>
<html>
    <head>
        <title>SistemaWeb</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <frameset rows="80,*" border="0" frameborder="0" y framespacing="40">
          <frame src=titulo.html name=titulo>
          <frameset cols="250,*">
              <frame src=opciones.html name=izquierda>
              <frame src=contenido.html name=derecha>
          </frameset>
       </frameset> 
    </head>
</html>

