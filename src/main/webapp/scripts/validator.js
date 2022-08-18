/**
 * @author @dudupuci
   @param idcontato
 */

function validation() {
	let nome = frmContato.nome.value;
	let fone = frmContato.fone.value;

	if (nome === "" && fone === "") {
		alert('Preencha os campos nome e fone')
		frmContato.nome.focus();
		return false;
	} else if (nome === "") {
		alert('Preencha o campo nome')
		frmContato.nome.focus();
		return false;
	} else if (fone === "") {
		alert('Preencha o campo fone')
		frmContato.fone.focus();
		return false;


	} else {
		document.forms["frmContato"].submit();
		console.log('Envio sucedido!')
	}

}



