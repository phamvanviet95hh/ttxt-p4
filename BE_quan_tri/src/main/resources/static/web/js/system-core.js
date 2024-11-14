let localdomain = window.location.protocol + "//" + window.location.hostname + ":" + window.location.port;
let token = localStorage.getItem("token") !== null ? localStorage.getItem("token") : sessionStorage.getItem("token");
let fullName = localStorage.getItem("fullName") !== null ? localStorage.getItem("fullName") : sessionStorage.getItem("fullName");
let username = localStorage.getItem("userName") !== null ? localStorage.getItem("userName") : sessionStorage.getItem("userName");
let userId = localStorage.getItem("userId") !== null ? localStorage.getItem("userId") : sessionStorage.getItem("userId");
let role = localStorage.getItem("role") !== null ? localStorage.getItem("role") : sessionStorage.getItem("role");
let phone = localStorage.getItem("phone") !== null ? localStorage.getItem("phone") : sessionStorage.getItem("phone");
let email = localStorage.getItem("email") !== null ? localStorage.getItem("email") : sessionStorage.getItem("email");
let address = localStorage.getItem("address") !== null ? localStorage.getItem("address") : sessionStorage.getItem("address");
let partnerName = localStorage.getItem("partnerName") !== null ? localStorage.getItem("partnerName") : sessionStorage.getItem("partnerName");
let logo = localStorage.getItem("logo") !== null ? localStorage.getItem("logo") : sessionStorage.getItem("logo");
let idCategoryServices = 0;
let checkFromaAddPartner = true;
let checkPass=true;

let selectedFile = null;

function alertGloable(message, type) {
    if (type === "success") {
        $(".alert-success").addClass("opa");
        $('#myAlert').fadeIn();
        $("#contentAlert").html(message);
    }else if (type === "false") {
        $('#myAlertError').fadeIn();
        $("#contentAlertError").html(message);
        $(".alert-danger").addClass("opa");
    }
    setTimeout(function () {
        if (type === "success") {
            $('#myAlert').fadeOut();
            $(".alert-success").removeClass("opa");
        }else if (type === "false") {
            $('#myAlertError').fadeOut();
            $(".alert-danger").removeClass("opa");
        }
    }, 3000);
}

function backLogin(){
    window.location.href = `${localdomain}`;
}

function router(path){
    $("#content_box").load(path);
}

function customDelete(url, id) {
    fetch(`${url}?id=${id}`, {
        method: "DELETE",
        headers: {
            "Content-Type": "application/json"
        }
    }).then(res => {
        if (!res.ok) {
            if (res.status === 500) {
                customLogout();
            }
            throw new Error('Network response was not ok')
        }
        return res.json();
    }).then(data => {
        if (data.success) {
            alertGloable(data.message, "success");
        }else {
            alertGloable(data.message, "false");
        }
    }).catch(err => {
        console.error('There has been a problem with your fetch operation:', err);
    })
}
function customLogout(){
    sessionStorage.removeItem("userName");
    sessionStorage.removeItem("userId");
    sessionStorage.removeItem("token");
    sessionStorage.removeItem("partnerName");
    sessionStorage.removeItem("role");
    sessionStorage.removeItem("email");
    sessionStorage.removeItem("address");
    sessionStorage.removeItem("phone");
    sessionStorage.removeItem("fullName");
    localStorage.removeItem("userName");
    localStorage.removeItem("userId");
    localStorage.removeItem("token");
    localStorage.removeItem("partnerName");
    localStorage.removeItem("role");
    localStorage.removeItem("email");
    localStorage.removeItem("address");
    localStorage.removeItem("phone");
    localStorage.removeItem("fullName");
    backLogin();
}

function customPost(url, header = {}, bodyData = {}, type) {
    fetch(url, {
        method: "POST",
        headers: header,
        body: bodyData // Dá»¯ liá»‡u gá»­i Ä‘i
    }).then(response => {
        if (!response.ok) {
            if (response.status === 500) {
                customLogout();
            }
            throw new Error('Network response was not ok');
        }
        return response.json();
    }).then(data => {
        if (data.success) {
            alertGloable(data.message, "success");
            if (type === "login") {
                sessionStorage.setItem("userName", data.data.userName);
                sessionStorage.setItem("userId", data.data.id);
                sessionStorage.setItem("token", data.token);
                sessionStorage.setItem("partnerName", data.data.partnerName);
                sessionStorage.setItem("role", data.data.role);
                sessionStorage.setItem("email", data.data.partnerEmail);
                sessionStorage.setItem("address", data.data.partnerAddress);
                sessionStorage.setItem("phone", data.data.partnerPhone);
                sessionStorage.setItem("fullName", data.data.partnerUser);
                sessionStorage.setItem("logo", data.data.partnerLogo);

                window.location.href = `${localdomain}/dashboard?idPart=`;
            }
        } else {
            alertGloable(data.message, "false");
        }
    })
        .catch(error => {
            console.error('There has been a problem with your fetch operation:', error);
        });
}

function customGetPage(url, idElement){
    $.ajax(url,{
        method : "GET",
        dataType : "html",
        success:(res)=>{
            alertGloable("Xử lý thành công!", "success");
            $(idElement).html(res)
        },
        error : (err) => {
            alertGloable("Xử lý thất bại!", "fail");
            console.log(err);
        }
    })
};

function customGet(url, header = {}, bodyData = {}, type) {
    fetch(url, {
        method: "GET",
        headers: header,
        body: bodyData // Dá»¯ liá»‡u gá»­i Ä‘i
    }).then(response => {
        if (!response.ok) {
            if (response.status === 500) {
                customLogout();
            }
            throw new Error('Network response was not ok');
        }
        return response.json();
    }).then(data => {
        console.log(data);
        if (data.success) {
            checkFromaAddPartner =true;
            alertGloable(data.message, "success");
            switch (type) {
                case "username":
                    $("#errorUsername").html("");
                    break;
                case "code":
                    $("#errorCodePartner").html("");
                    break;
            }
        } else {
            checkFromaAddPartner = false;
            alertGloable(data.message, "false");
            switch (type) {
                case "username":
                    $("#errorUsername").html(data.message);
                    break;
                case "code":
                    $("#errorCodePartner").html(data.message);
                    break;
            }
        }
    })
        .catch(error => {
            console.error('There has been a problem with your fetch operation:', error);
        });
}


function getFirstDayOfMonth() {
    const now = new Date();
    const year = now.getFullYear();
    const month = now.getMonth();


    const firstDay = new Date(year, month, 1);


    const yearString = firstDay.getFullYear();
    const monthString = String(firstDay.getMonth() + 1).padStart(2, '0');
    const dayString = String(firstDay.getDate()).padStart(2, '0');
    const hoursString = String(firstDay.getHours()).padStart(2, '0');
    const minutesString = String(firstDay.getMinutes()).padStart(2, '0');
    const secondsString = String(firstDay.getSeconds()).padStart(2, '0');


    const formattedDate = `${yearString}-${monthString}-${dayString}T${hoursString}:${minutesString}:${secondsString}`;

    return formattedDate;
}

function getLastDayOfMonth() {
    const now = new Date();
    const year = now.getFullYear();
    const month = now.getMonth();


    const firstDayNextMonth = new Date(year, month + 1, 1);


    const lastDay = new Date(firstDayNextMonth - 1);

    const yearString = lastDay.getFullYear();
    const monthString = String(lastDay.getMonth() + 1).padStart(2, '0');
    const dayString = String(lastDay.getDate()).padStart(2, '0');
    const hoursString = String(lastDay.getHours()).padStart(2, '0');
    const minutesString = String(lastDay.getMinutes()).padStart(2, '0');
    const secondsString = String(lastDay.getSeconds()).padStart(2, '0');

    const formattedDate = `${yearString}-${monthString}-${dayString}T${hoursString}:${minutesString}:${secondsString}`;

    return formattedDate;
}
$(document).ready(function (){
    function updateClock() {
        const now = new Date();
        const hours = String(now.getHours()).padStart(2, '0');
        const minutes = String(now.getMinutes()).padStart(2, '0');
        const seconds = String(now.getSeconds()).padStart(2, '0');
        document.getElementById('clock').textContent = `${hours}:${minutes}:${seconds}`;
    }

    setInterval(updateClock, 1000);
    updateClock(); // Cáº­p nháº­t ngay khi trang vá»«a táº£i
    const today = new Date();

    const day = String(today.getDate()).padStart(2, '0'); // Láº¥y ngÃ y, Ä‘áº£m báº£o cÃ³ 2 chá»¯ sá»‘
    const month = String(today.getMonth() + 1).padStart(2, '0'); // Láº¥y thÃ¡ng, cáº§n cá»™ng thÃªm 1 vÃ¬ thÃ¡ng báº¯t Ä‘áº§u tá»« 0
    const year = today.getFullYear(); // Láº¥y nÄƒm

    const currentDate = `${day}/${month}/${year}`;
    document.getElementById('dateTime').textContent = `${currentDate}`;
    const daysOfWeek = ["Chủ nhật", "Thứ 2", "Thứ 3", "Thứ 4", "Thứ 5", "Thứ 6", "Thứ 7"];

    const currentDay = daysOfWeek[today.getDay()]; // Láº¥y thá»© hiá»‡n táº¡i
    document.getElementById('dateThu').textContent = `${currentDay}`;
});

function dautuanht() {
    const today = new Date();
    // NgÃ y báº¯t Ä‘áº§u lÃ  hÃ´m nay
    var startDateWeek = today.toISOString().split("T")[0];
    return startDateWeek;
}

function cuoituanht() {
    const today = new Date();
    const endOfWeek = new Date(today);
    endOfWeek.setDate(today.getDate() + 6);
    const endDateWeek = endOfWeek.toISOString().split("T")[0];

    return endDateWeek;
}

function getCurrentLocalDateTime() {
    const date = new Date();
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
}


