/**
 * 
 */

function confirmation(idcontato) {

	let resposta = confirm('Confirma a exclusao deste contato?');

	if (resposta === true) {
		window.document.href = "delete?idcontato=" + idcontato;
	}

}