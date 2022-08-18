<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="agenda.model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>

<%
@SuppressWarnings("unchecked")
ArrayList<JavaBeans> list = (ArrayList<JavaBeans>) request.getAttribute("contatos");
%>
<%--for (int i = 0; i < list.size(); i++) {
	out.println(list.get(i).getIdcontato());
	out.println(list.get(i).getNome());
	out.println(list.get(i).getFone());
	out.println(list.get(i).getEmail());

}
--%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Agenda de contatos</title>
</head>
<body>
	<h1>Agenda de contatos</h1>
	<a href="novocontato.html">Novo contato</a>
	<a href="report">Relatório</a>
	<table id="tabela">

		<thead>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Fone</th>
				<th>Email</th>
				<th>Opções</th>
			</tr>



		</thead>

		<tbody>
			<%
			for (int i = 0; i < list.size(); i++) {
			%>


			<tr>
				<td><%=list.get(i).getIdcontato()%></td>
				<td><%=list.get(i).getNome()%></td>
				<td><%=list.get(i).getFone()%></td>
				<td><%=list.get(i).getEmail()%></td>
				<td><a href="select?idcontato= <%=list.get(i).getIdcontato()%>">Editar</a></td>
				<td><a
					href="javascript: confirmation(<%=list.get(i).getIdcontato()%>)">Excluir</a></td>
			</tr>
			<%
			}
			%>

			<script src="scripts/confirmator.js"></script>
		</tbody>


	</table>

</body>
</html>