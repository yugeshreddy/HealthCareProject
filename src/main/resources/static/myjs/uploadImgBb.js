function uploadFile() {
  var file = document.getElementById("fileOb");
  var form = new FormData();
  form.append("image", file.files[0]);

  var inputs = {
    url: "https://api.imgbb.com/1/upload?key=7a035565af307a4d6a0df9cdc5e80a27",
    method: "POST",
    timeout: 0,
    processData: false,
    mimeType: "multipart/form-data",
    contentType: false,
    data: form,
  };

  $.ajax(inputs).done(function (response) {
    var job = JSON.parse(response);
    $("#photoLoc").val(job.data.url);
  });
}