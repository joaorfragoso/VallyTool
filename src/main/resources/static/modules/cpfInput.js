let cpfInput = document.getElementById("input-cpf");

const bloqueiaCaractere = (function () {
    let ValueAnterior = cpfInput.value;
    return function (event) {
        if (isNaN(event.data)) {cpfInput.value = ValueAnterior}
        ValueAnterior = cpfInput.value;
    }
})()
cpfInput.addEventListener("input", bloqueiaCaractere);

const cpfMask = (function () {
        let ValueAnterior = cpfInput.value;
        return function() {
            let tamanhoAnterior = ValueAnterior.length;
            let tamanhoAtual = cpfInput.value.length;
            let cpf = cpfInput.value.replaceAll(".", "").replace("-", "");

            //Apagando
            if (tamanhoAtual <= tamanhoAnterior) {
                //Verifica se o usuário apagou um ponto ou um hifen
                if (cpfInput.value + "." === ValueAnterior || cpfInput.value + "-" === ValueAnterior) {
                    cpf = cpf.slice(0,-1);
                }
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