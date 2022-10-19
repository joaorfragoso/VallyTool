function abrirMenu() {
    let abaMenu = document.getElementById("menu-extendido-div");
    let abaMenuDisplay = abaMenu.style.display;

    if (abaMenuDisplay ===  "") {
        abaMenu.style.display = "grid";
    } else {abaMenu.style.display = "";}
}