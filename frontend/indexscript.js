document.getElementById('uploadbtn').addEventListener('change', function() {
    const fileName = this.files[0] ? this.files[0].name : '未選擇文件';
    document.getElementById('file-name').textContent = fileName;
});
document.getElementById('submitBtn').addEventListener('click', function() {
const filename = document.getElementById('filename').value;
const fileType = document.getElementById('selectItem').value;
const file = document.getElementById('uploadbtn').files[0];

const formData = new FormData();
formData.append("fileName", filename);
formData.append("fileType", fileType);
formData.append("fileUrl", file);

$.ajax({
    url: "http://localhost:8080/fileapi/createfile",
    type: "POST",
    processData: false,
    contentType: false,
    data: formData,
    success: function(data) {
        console.log("資料儲存成功", data);
        $("#filename, #selectItem").val('');
        $("#uploadbtn").val(null);
        $('#file-name').text('');
    },
    error: function(error) {
        console.error("資料儲存失敗", error);
    }
});
});