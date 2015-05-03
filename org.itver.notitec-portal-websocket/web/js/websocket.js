
var socket = new WebSocket("ws://" + document.location.host + "/NotiTec/despachador");
socket.onmessage = onMessage;

function onMessage(event) {
    var noticia = JSON.parse(event.data);
    var notificacion = "Nueva noticia:" + noticia.titulo
            + "\nContenido:\n" + noticia.contenido
            + "\nAutor: " + noticia.autor;
    alert(notificacion);
}

//function informaNuevaNoticia(titulo, contenido, autor) {
//    var noticia = {
//        titulo: titulo,
//        contenido: contenido,
//        autor: autor
//    };
//    socket.send(JSON.stringify(noticia));
//}
//
//function formSubmit() {
//    var form = document.getElementById("noticia");
//    var titulo = form.elements["titulo"].value;
//    var contenido = form.elements["contenido"].value;
//    var autor = form.elements["autor"].value;    
//    informaNuevaNoticia(titulo, contenido, autor);
//}
