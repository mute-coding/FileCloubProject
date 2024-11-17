let RegisterDialog = document.querySelector('.RegisterDialog');
let overlay = document.querySelector('.overlay');
$(".openRegisterDialog").click(function() {
    RegisterDialog.style.display = 'block';
    overlay.style.display = 'block';
});
$(".xmarkBTN").click(function() {
    RegisterDialog.style.display = 'none';
    overlay.style.display = 'none';
});
$(document).ready(function () {
    
    // 註冊按鈕點擊事件
    $("#submitBtn").on("click", function () {
        // 取得輸入的註冊資料
        const username = $("#userNameInput").val();
        const password = $("#passwordInput").val();
        const userphone = $("#phoneInput").val();
        const useremail = $("#emailInput").val();

        // 驗證資料是否完整
        if (!username || !password || !userphone || !useremail) {
            alert("請完整填寫所有欄位！");
            return;
        }

        // 發送 AJAX 請求到 `/createuser`
        $.ajax({
            url: "http://localhost:8080/login/createuser",
            type: "POST",
            data: {
                username: username,
                password: password,
                userphone: userphone,
                useremile: useremail,
            },
            success: function (response) {
                alert(response); // 顯示成功訊息
                // 可選：清空表單
                $("#userNameInput").val("");
                $("#passwordInput").val("");
                $("#phoneInput").val("");
                $("#emailInput").val("");
            },
            error: function (xhr, status, error) {
                alert("註冊失敗：" + xhr.responseText);
            },
        });
    });
    
    $("#LoginsubmitBtn").on("click", function () {
        // 取得輸入的登入資料
        const username = $("input[placeholder='請輸入使用者名稱']").val();
        const password = $("input[placeholder='請輸入密碼']").val();

        // 驗證資料是否完整
        if (!username || !password) {
            alert("請輸入使用者名稱和密碼！");
            return;
        }

        // 發送 AJAX 請求到 `/userlogin`
        $.ajax({
            url: "http://localhost:8080/login/userlogin",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify({ user_name: username, password: password }),
            success: function (userList) {
                if (userList && userList.length > 0) {
                    alert("登入成功！歡迎，" + userList[0].user_name);
                    // 可選：跳轉到其他頁面
                     window.location.href = "/list.html";
                } else {
                    alert("登入失敗，請檢查帳號或密碼！");
                }
            },
            error: function (xhr, status, error) {
                alert("登入失敗：" + xhr.responseText);
            },
        });
    });
});