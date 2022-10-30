let parametrosString = window.location.search.substring(1);
let urlParametros = {}; 

parametrosString.split("&").forEach(function(element) {
    urlParametros[String(element.split("=")[0])] = element.split("=")[1]
})