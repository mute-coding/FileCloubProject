let Formdialog = document.querySelector('.Formdialog');
let Videodialog = document.querySelector('.Videodialog');
let Editdialog = document.querySelector('.Editdialog');
let overlay = document.querySelector('.overlay');
let imgElement =document.querySelector('.videoPeth'); // 獲取 img 元素
let selectedFileId; // 全局變數來儲存選中的 fileid
let selectedFileNAME; 
$(".dialogBTN").click(function() {
    Formdialog.style.display = 'block';
    overlay.style.display = 'block';
});
$(".xmarkBTN").click(function() {
    Formdialog.style.display = 'none';
    overlay.style.display = 'none';
});
 
document.getElementById('uploadbtn').addEventListener('change', function() {
    const fileName = this.files[0] ? this.files[0].name : '未選擇文件';
    document.getElementById('file-name').textContent = fileName;
});
function openMenu(){
    const menuToggle = document.querySelector('.menu');
    menuToggle.classList.toggle('active');
}
document.getElementById('submitBtn').addEventListener('click', function() {
    const filename = document.getElementById('filename').value;
    const fileType = document.getElementById('selectItem').value;
    const file = document.getElementById('uploadbtn').files[0];
    
    const formData = new FormData();
    formData.append("fileName", filename);
    formData.append("fileType", fileType);
    formData.append("fileUrl", file);
    
    //創建資料
    $.ajax({
        url: "http://localhost:8080/fileapi/createfile",
        type: "POST",
        processData: false,
        contentType: false,
        data: formData,
        success: function(data) {
            console.log("資料儲存成功", data);

            // 清空表單
            $("#filename, #selectItem").val('');
            $("#uploadbtn").val(null);
            $('#file-name').text('');

            // 立即執行載入資料庫資料
            loadDatabaseData(); // Call the function to load data from the database
        },
        error: function(error) {
            console.error("資料儲存失敗", error);
        }
    });
});

// 定義載入資料庫資料的函數
function loadDatabaseData() {
    $.ajax({
        url: "http://localhost:8080/fileapi/getall",
        type: "GET",
        success: function(data) {
            // 清空現有的表格內容
            $('#tablebody').empty();

            // 遍歷JSON數據並創建表格行
            data.forEach(function(file) {
                var row = '<tr>' +
                    '<td>' + file.id + '</td>' +
                    '<td>' + file.fileName + '</td>' +
                    '<td>' + file.fileType + '</td>' +
                    '<td><button class="btn_previwe" data-id="' + file.fileName + '">檔案預覽</button></td>' +
                    '<td>' +
                    '<button class="btn_pen" data-id="' + file.id + '"><i class="fa-solid fa-pen-to-square"></i></button>' +
                    '<button class="btn_download" data-id="' + file.fileName + '"><i class="fa-solid fa-download"></i></button>' +
                    '<button class="btn_trash" data-id="' + file.id + '"><i class="fa-solid fa-trash"></i></button>' +
                    '</td>' +
                    '<td>test2</td>' +
                    '</tr>';
                // 將新的行添加到表格的tbody中
                $('#tablebody').append(row);
            });

            // 綁定刪除按鈕的點擊事件
            $(".btn_trash").click(function() {
                selectedFileId = $(this).data('id');
                if (confirm("是否確認刪除此檔案?")) {
                    $.ajax({
                        url: "http://localhost:8080/fileapi/deleteitem/" + selectedFileId,
                        type: "DELETE",
                        success: function(result) {
                            alert("檔案已刪除");
                            loadDatabaseData(); // 刷新資料表
                        },
                        error: function(error) {
                            console.error("Error deleting file: ", error);
                        }
                    });
                }
            });
            
            // 綁定編輯按鈕的點擊事件
            $(".btn_pen").click(function() {
                selectedFileId = $(this).data('id');
                var fileName = $(this).closest('tr').find('td:eq(1)').text();
                $("#updateInput").val(fileName); // Set the file name to the input field

                Editdialog.style.display = 'block';
                overlay.style.display = 'block';
            });
            $(".savebtn").click(function() {     
                var newFileName = $("#updateInput").val();

                $.ajax({
                    url: "http://localhost:8080/fileapi/update/" + selectedFileId,
                    type: "PUT",
                    data: { fileName: newFileName }, 
                    success: function(data) {
                        alert("檔案名稱已更新");
                        loadDatabaseData(); // 更新後重新載入資料
                    },
                    error: function(error) {
                        console.error("Error updating file: ", error);
                    }
                });

                Editdialog.style.display = 'none';
                overlay.style.display = 'none';
            });

            // 綁定儲存修改按鈕的點擊事件
            $(".btn_previwe").click(function() {
                selectedFileNAME = $(this).data('id');
                $.ajax({
                    url:'http://localhost:8080/fileapi/image/'+selectedFileNAME,
                    method: 'GET',
                    xhrFields: {
                        responseType: 'blob'  //使用blob處理數據
                    },
                    success: function(data) {
                        var url = URL.createObjectURL(data);
                        $('.videoPeth').attr('src', url);
                        $('.Videodialog').show();
                        $('.overlay').show();
                    },
                    error: function() {
                        alert('Error loading image');
                    }
                });
            });

            $(".closeDialogBtn").click(function() {
                Videodialog.style.display = 'none';
                overlay.style.display = 'none';
            });

            //串接下載功能的API
            $(".btn_download").click(function() {
                selectedFileNAME = $(this).data('id');  // 使用 data('id') 获取文件名
                var downloadUrl = "http://localhost:8080/fileapi/downloadFile/" + selectedFileNAME;
                
                // 創建一個隱藏的a標籤
                var a = document.createElement('a');
                a.href = downloadUrl;
                a.download = selectedFileNAME;  
                document.body.appendChild(a);
                a.click();
                document.body.removeChild(a);
            });
          
        },
        error: function(error) {
            console.error("Error fetching data: ", error);
        }
    });
}

$(document).ready(function() {
    // 這裡可以初始化載入資料
    loadDatabaseData(); // 初次載入資料
});