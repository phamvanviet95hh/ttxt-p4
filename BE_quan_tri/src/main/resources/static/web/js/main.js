$(document).ready(function () {
    $(".info").click(function (){
        
        $("#content_box").load(`/partners/infoUser?id=${userId}&startDate=${getFirstDayOfMonth()}&endDate=${getLastDayOfMonth()}`);
        // sessionStorage.setItem("urlLoadInfo", `${sessionStorage.getItem("domain")}/eid-suite/users/infoUser.php?id=${userId}`);
    })
    $(".logout").click(function () {
        customLogout();
    });
    $(".box-infoUser").click(function (){
       $(this).removeClass("hien");
    });
});