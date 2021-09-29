<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="js/libro.js"></script>
<title>Insert title here</title>
</head>
<body>
<%!String nombre,isbn,codigo,fecha_dev; %>
<%
if(request.getParameter("isbn")!=null){
nombre=request.getParameter("titulo");	
isbn=request.getParameter("isbn");
}

if(request.getParameter("men")!=null){
String mensaje=request.getParameter("men");
out.print("<script>alert('"+mensaje+"');</script>");//Mensaje con alert js
}
%>
<form action="Prestamo" method="post">
<div><label>Codigo Libro: </label><select class="listaLibros" name="codigo">
</select>
<input type="submit" name="consultarLib" value="Consultar"></div>
</form>
<form action="Prestamo" method="post">
<label> Codigo Prestamo: <%=codigo%></label>
<div><label>Nombre del Libro: </label><input type="text" name="nombreLib" value="<%=nombre%>">
<input type="hidden" name="isbnLib" value="<%=isbn%>"></div>
<div><label>Estudiante:</label><input type="text" name="est" required>
<div><label>Fecha_Prestamo: </label><input type="date" name="fecha" required>
<div><label>Fecha Devolucion: <%=fecha_dev%></label></div>
<div><input type="submit" name="registrar" value="Registrar"></div></form>
</body>
</html>