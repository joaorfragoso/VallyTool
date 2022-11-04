document.getElementsByClassName("tema-botao")[0].addEventListener("click", function() {
	modoEscuroAtivado = localStorage.getItem("modoEscuro");
	if(modoEscuroAtivado === "true") {
		localStorage.setItem("modoEscuro", false);
	} else {
		localStorage.setItem("modoEscuro", true);
	}
	location.reload()
});