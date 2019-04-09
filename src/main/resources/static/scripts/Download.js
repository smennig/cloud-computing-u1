$(document).ready(function () {
    console.log("Download Images");

    var data = {};

    fetch('/images',{
        method: 'GET',
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(data)
    }).then(response => response.json()).then(data => getImage(JSON.stringify(data))).catch(error => console.error(error))
});

function getImage(json){
    console.log(json);

    var file = json;

    $('<div class="imageDiv">' +
        '<img src="" class="image" />' +
        '<div class="imageText">' +
        '<p>Text</p>' +
        '<p>Text</p>' +
        '<p>Date</p>' +
        '</div>' +
        '</div>');
}

//            <div class="imageDiv">
//                 <img class="image" src="" />
//                 <div class="imageText">
//                     <p>Irgendwelcher Stuff</p>
//                     <p>Irgendein Ort</p>
//                     <p>Das Datum halt</p>
//                 </div>
//             </div>


