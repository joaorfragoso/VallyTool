//Fazer aparecer o botão de submit;
const novoItemInput = document.getElementById("novoItemInput");
novoItemInput.addEventListener("focusin", function(){
	this.nextElementSibling.classList.remove("oculto");
})
//Fazer desaparecer o botão de submit caso não tenha nenhum valor no input;
novoItemInput.addEventListener("focusout", function(){
	if (novoItemInput.value.length === 0) {
		this.nextElementSibling.classList.add("oculto");
	} 
})