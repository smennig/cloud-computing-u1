$(document).on('click','#addImageButton', function(){
    console.log("BUTTON: addImage ");

    $('#imgupload').trigger('click');
});

function loadImage(file) {
    console.log("Loaded Image");

    var data = {
        caption: "header",
        path: "",
        location: "ort",
        filename: "name",
        image: file
    };

    fetch('/uploadImage/id',{
        method: 'POST',
        body: JSON.stringify(data),
        headers: {
            'Content-Type': 'application.json'
        }
    }).then(response => response.json()).then(response => console.log('Success:', JSON.stringify(response))).catch(error => console.error('Error:', error));
}
