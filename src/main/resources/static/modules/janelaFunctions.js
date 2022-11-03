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

//Janela de Confirmação
const confirmacaoJanela = document.querySelector(".aviso.confirmacao");

const requisicaoConfirmacaoBotoes = document.getElementsByClassName("requisitarConfirmacao");

for (i = 0; i < requisicaoConfirmacaoBotoes.length; i++) {
	requisicaoConfirmacaoBotoes[i].addEventListener("click", function() {
		confirmacaoJanela.classList.remove("oculto");
		const elemento = this;
		document.getElementById("mensagem-confirmacao").innerHTML = elemento.getAttribute("confirmacaomensagem");
		confirmacaoJanela.querySelector(".confirmar-botao").onclick = function() {
			elemento.form.submit();
		};
	});
}