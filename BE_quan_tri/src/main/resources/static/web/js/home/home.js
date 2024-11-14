$(document).ready(function () {
    
    if (username == null || username === "undefined") {
        backLogin();
    }else {
        $("#fullName").html(partnerName);
        $(".nav-user").click(function () {
            $(".box-infoUser").toggleClass("hien")
        });
        $("#btn-infoAccount").click(function () {
        
        });
        $("#viewDv").click(function () {
            $(this).parent().find("ul.ul-children").slideToggle();
        })
        $("#viewReport").click(function(){
            $(this).parent().find("ul.ul-children").slideToggle();
        });
        
    }
    console.log(sessionStorage.getItem("role"));
    if (role === "ADMIN"){
        customGetPage(`${localdomain}/admin/dashboard?idPart=`, "#content_box");
        $(".service").addClass("an");
    }else if(role === "PARTNER"){
        $("#logo-dv").attr("src", `data:image/png;base64,${logo}`);
        $(".qlService").addClass("an");
        $(".partner").addClass("an");
        $("#reportLink").attr("data-value", userId);
        $('a#reportLink').closest('li').addClass('an');
        $("#reportCategoryService").attr("data-value", userId);
        $("#reportService").attr("data-value", userId);
        $("#transactionLink").attr("data-value", userId);
        $("#serviceLink").attr("data-value", userId);
        customGetPage(`${localdomain}/admin/dashboardUser?userId=${userId}&startDate=${getFirstDayOfMonth()}&endDate=${getLastDayOfMonth()}`, "#content_box");
    }
    
    
});