$(document).ready(function() {
    $('#submitBtn').click(function() {
        var userKey = $('#userKey').val();
        var userPassword = $('#userPassword').val();

        $.ajax({
            url: 'http://localhost:8080/login/signin',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({ userKey: userKey, userPassword: userPassword }),
            success: function(response) {
                alert('登入成功！');
                window.location.href = '/FileCloudProject/frontend/index.html';
            },
            error: function(xhr, status, error) {
                alert('登入失敗：' + xhr.responseText);
            }
        });
    });
});