$(document).ready(function() {
    var dialog = document.querySelector('.dialog');
    var dialog02 = document.querySelector('.dialog02');
    var overlay = document.querySelector('.overlay');
    var selectedFileId; // 全局變數來儲存選中的 fileid
    // 載入資料庫資料
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
                    '<td><button class="btn_previwe">檔案預覽</button></td>' +
                    '<td>' +
                    '<button class="btn_pen" data-id="' + file.id + '"><i class="fa-solid fa-pen-to-square"></i></button>' +
                    '<button class="btn_download"><i class="fa-solid fa-download"></i></button>' +
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
                            location.reload(); // 刷新頁面以顯示更新後的表格
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

                dialog02.style.display = 'block';
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
                        location.reload(); 
                    },
                    error: function(error) {
                        console.error("Error updating file: ", error);
                    }
                });

                dialog02.style.display = 'none';
                overlay.style.display = 'none';
            });

            // 綁定儲存修改按鈕的點擊事件

            $(".btn_previwe").click(function() {
                dialog.style.display = 'block';
                overlay.style.display = 'block';
            });
            $(".closeDialogBtn").click(function() {
                dialog.style.display = 'none';
                overlay.style.display = 'none';
            });
        },
        error: function(error) {
            console.error("Error fetching data: ", error);
        }
    });
});