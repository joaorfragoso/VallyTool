//Abrir Janela;
function abrirJanela(elemento) {
	document.getElementById(elemento).classList.remove("oculto");
}

//Botão de Fechar Janela;
const botoesFechar = document.getElementsByClassName("fechar-botao");

for (i = 0; i < botoesFechar.length; i++) {
	botoesFechar[i].addEventListener("click", function() {
		this.parentNode.classList.add("oculto");
	});
}