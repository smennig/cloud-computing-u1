$(document).on('click','#home', function(){
        console.log("BUTTON: home ");

        enableDisable("#home");

        $("#content").empty().load("main.html");
});

$(document).on('click','#upload', function(){
    console.log("BUTTON: upload");

    enableDisable("#upload");

    $("#content").empty().load("upload.html");
});

$(document).on('click','#view', function(){
    console.log("BUTTON: view");

    enableDisable("#view");

    $("#content").empty().load("galerie.html");
});

function enableDisable(name){
    var home = $("#home");
    var upload = $("#upload");
    var view = $("#view");

    home.removeClass("chosen").attr("disabled", false);
    upload.removeClass("chosen").attr("disabled", false);
    view.removeClass("chosen").attr("disabled", false);

    switch (name) {
        case "#home":
            home.addClass("chosen").attr("disabled", true);
            break;
        case "#upload":
            upload.addClass("chosen").attr("disabled", true);
            break;
        case "#view":
            view.addClass("chosen").attr("disabled", true);
            break;
    }
}