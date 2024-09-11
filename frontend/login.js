$(document).ready(function() {
    $('#submitBtn').click(function() {
        var userKey = $('#userKey').val();
        var userPassword = $('#userPassword').val();

        $.ajax({
            type: "POST",
            url: "http://localhost:8080/perform_login",
            contentType: "application/json",
            data: JSON.stringify({
                userName: userKey,
                userPassword: userPassword
            }),
            success: function(response) {
                window.location.href = "/FileCloudProject/frontend/index.html";
            },
            error: function(xhr) {
                alert("Login failed: " + xhr.responseText);
            }
        });
    });
});