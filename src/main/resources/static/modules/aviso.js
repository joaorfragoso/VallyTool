let parametrosString = window.location.search.substring(1);
if(parametrosString.includes("error")) {
	document.getElementsByClassName("aviso")[0].style.display = "grid";
}