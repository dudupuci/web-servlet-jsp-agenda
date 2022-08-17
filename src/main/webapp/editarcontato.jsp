<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editar contato</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
	<h1>Editar contato</h1>
	<form name="frmContato" action="update">

		<table>
			<tr>
				<td><input type="text" name="idcontato" id="caixa1"
					placeholder="Id" readonly value="<%out.println(request.getAttribute("idcontato")); %>"></td>
			</tr>
			<tr>
				<td><input type="text" name="nome" id="caixa2"
					placeholder="Nome"
					value="<%out.println(request.getAttribute("nome"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="fone" id="caixa3"
					placeholder="Fone"
					value="<%out.println(request.getAttribute("fone"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="email" id="caixa4"
					placeholder="Email"
					value="<%out.println(request.getAttribute("email"));%>"></td>
			</tr>
		</table>
		<input type="reset" value="Resetar campos"> <input
			type="button" value="Salvar" onclick="validation()">

	</form>
	<script src="scripts/validator.js"></script>

</body>
</html>