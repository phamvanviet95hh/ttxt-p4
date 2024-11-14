$(document).ready(function(){
    if (username){
        console.log(username)
        window.location.href = `${localdomain}/dashboard?idPart=`;
    }
    $("#form-login").on("submit", function(event){
        event.preventDefault();
        let username = $("#username").val();
        let password = $("#password").val();
        var headers = {
            'Content-Type': 'application/json',
        };
        var bodyData = JSON.stringify({
            "username": username,
            "password": password
        });
        customPost(`${localdomain}/login`, headers, bodyData, "login");
    })
});