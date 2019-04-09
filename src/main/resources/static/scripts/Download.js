$(document).ready(function () {
    console.log("Download Images");
    fetch('/images',{
        method: 'GET'
    }).then(response => response.json()).then(data => getImage(data)).catch(error => console.error(error))
});

function getImage(json){

    json.forEach(function (image) {

       const markup = `<div class="imageDiv">
            <img src="${image.path}" class="image" />
            <div class="imageText">
            <p>${image.caption}</p>
            <p>${image.location}</p>
            <p>${new Date(image.datetime).toLocaleString("de-DE")}</p>
            </div>
            </div>`;

        $('#fillDiv').append(markup);
    })
}

//            <div class="imageDiv">
//                 <img class="image" src="" />
//                 <div class="imageText">
//                     <p>Irgendwelcher Stuff</p>
//                     <p>Irgendein Ort</p>
//                     <p>Das Datum halt</p>
//                 </div>
//             </div>


