let modoEscuroAtivado = localStorage.getItem("modoEscuro");
const root = document.querySelector(":root");

if(modoEscuroAtivado === null) {
	localStorage.setItem("modoEscuro", false);
}

if(modoEscuroAtivado === "true") {
	root.style.setProperty('--bgcor-primaria', 'rgb(30, 30, 30)');
	root.style.setProperty('--bgcor-secundaria', 'rgb(20, 20, 20');
	root.style.setProperty('--bgcor-input', 'rgb(20, 20, 20');
	root.style.setProperty('--txtcor-input', 'rgba(255, 255, 255, 0.6');
	root.style.setProperty('--txtcor-placeholder', 'rgba(255, 255, 255, 0.4)');
	root.style.setProperty('--inverter', '1');
	root.style.setProperty('color', 'white');
}