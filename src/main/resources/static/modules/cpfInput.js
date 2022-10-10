let cpfInput = document.getElementById("input-cpf");

const bloqueiaCaractere = (function () {
    let ValueAnterior = cpfInput.value;
    return function (event) {
		if (event.inputType == "insertText") {
        	if (isNaN(event.data)) {cpfInput.value = ValueAnterior}
        }
        //Caso o usuário cole um valor, ou caso ele use um valor gravado no navegador;
        if (event.inputType == "insertFromPaste" || event.inputType == undefined) {
			cpfInput.value = numeroParaString(cpfInput.value);
		}
        ValueAnterior = cpfInput.value;
    }
})()
cpfInput.addEventListener("input", bloqueiaCaractere);

const cpfMask = (function () {
        let ValueAnterior = cpfInput.value;
        return function() {
            let cpf = cpfInput.value.replaceAll(".", "").replace("-", "");

            //Verifica se o usuário apagou um ponto ou um hifen
            if (cpfInput.value + "." === ValueAnterior || cpfInput.value + "-" === ValueAnterior) {
                cpf = cpf.slice(0,-1);
            }

            novoCpf = "";
            for (let i = 0; i < cpf.length; i++) {
                novoCpf += cpf.substring(i, i+1);
                if (i == 2 || i == 5) {novoCpf += "."}
                if (i == 8) {novoCpf += "-"}
            }

            cpfInput.value = novoCpf;
            ValueAnterior = cpfInput.value;
        }
})()
cpfInput.addEventListener("input", cpfMask);

function numeroParaString(string) {
	let num = "";
	for(i = 0; i < string.length; i++) {
		if(!isNaN(string[i])) {
			num += string[i];
		}
	}
	return num;
}