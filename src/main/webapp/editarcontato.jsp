<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editar contato</title>

<style type="text/css">
@import
	url('https://fonts.googleapis.com/css2?family=Comfortaa:wght@300&display=swap')
	;

@import
	url('https://fonts.googleapis.com/css2?family=Comfortaa:wght@300&family=Inter:wght@200&display=swap')
	;

body {
	background: rgb(2, 0, 36);
	background: linear-gradient(90deg, rgba(2, 0, 36, 1) 0%,
		rgba(9, 9, 121, 1) 35%, rgba(0, 212, 255, 1) 100%);
}

.page-title {
	text-align: center;
	font-family: 'Comfortaa';
	text-align: center;
}

form {
	margin: auto;
	text-align: center;
	display: grid;
}

main {
	border: 1px solid none;
	background-color: white;
	width: 300px;
	border-radius: 4px;
	height: 250px;
	margin: auto;
	padding: 10px;
	margin: auto;
	width: 300px;
	height: 250px;
	-webkit-box-shadow: 18px 10px 25px 1px rgba(0, 0, 0, 0.74);
	box-shadow: 18px 10px 25px 1px rgba(0, 0, 0, 0.74);
}

form input {
	margin: 5px;
}

.new-button {
	width: 150px;
	cursor: pointer;
	margin: auto;
	cursor: pointer;
}
</style>
</head>
<body>
	<h1 class="page-title">Editar contato</h1>
	<main>
	<form name="frmContato" action="update">

		<table>
			<tr>
				<td><input type="text" name="idcontato" id="caixa1"
					placeholder="Id" readonly
					value="<%out.println(request.getAttribute("idcontato"));%>"></td>
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
		<input type="reset" value="Resetar campos" class="new-button">
		<input type="button" value="Salvar" class="new-button"
			onclick="validation()">

	</form>
	</main>
	<script src="scripts/validator.js"></script>

</body>
</html>