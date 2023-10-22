<%-- 
    Document   : matriculaMostrarMatriculas
    Created on : 21/10/2023, 06:07:31 PM
    Author     : valer
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <link rel="stylesheet" 
              href="webjars/bootstrap/5.3.1/css/bootstrap.min.css" type="text/css" />
    </head>
    <body>
    <div class="card" style="padding: 30px 0px 0px 30px;">
        <h4 class="display-8">Listado de Matriculas</h4>
        <form method=POST action="/WebSistema/controladorPrincipal">
            <input type=HIDDEN name=accion value="listadoMatriculas">
            <input type=HIDDEN name=modo value="busqueda">
            
            <div>Buscar Matricula: </div>
                <input name="xmat" value="<c:out value='${nro_doc}'/>"size=60>
                <input type="submit" name="boton" class="btn btn-primary" value="Buscar">
                
                
            <table class="table table-striped table-hover">
            <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Codigo</th>
                        <th scope="col">Fecha</th>
                        <th scope="col">Nro Doc.</th>
                        <th scope="col">Alumno</th>
                        <th scope="col">Total</th>
                        <th scope="col">Estado</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${arrMatriculas}" var="matricula">
                        <tr>
                            <td><input type="checkbox" name='xcod' value="<c:out value='${matricula.codigo}'/>"></td>
                            <td><c:out value='${matricula.codigo}'/></td>
                            <td><c:out value='${matricula.fecha}' /></td>
                            <td><c:out value='${matricula.nro_doc}'/></td>
                            <td><c:out value='${matricula.nombreAlumno}'/></td>
                            <td><c:out value='${matricula.total}'/></td>
                            <td><c:out value='${matricula.estado}'/></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </form>
    </div>
    </body>
</html>

