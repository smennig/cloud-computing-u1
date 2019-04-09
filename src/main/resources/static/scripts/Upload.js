$(document).on('click','#addImageButton', function(){
    console.log("BUTTON: addImage ");

    $('#imgupload').trigger('click');
});

function loadImage(file) {
    console.log("Loaded Image");

    postData(file);
}

function postImage(id, file){
    var data = new FormData();
    console.log(file[0]);
    data.append('file',file[0]);

    fetch('/uploadImage/'+id,{
        method: 'POST',
        body: data,
        header: {
            'Content-Type':'multipart/form-data'
        }
    }).then(response => response.json()).then(response => console.log('Success:', JSON.stringify(response))).catch(error => console.error('Error:', error));
}

function postData(file){
    var data = {
        caption: $('#inputImageText').val(),
        location: $('#inputOrt').val(),
        datetime: Date.now()
    };

    fetch('/images',{
        method: 'POST',
        body: JSON.stringify(data),
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(response => response.json()).then(response => postImage(response.id, file)).catch(error => console.error('Error:', error));
}
