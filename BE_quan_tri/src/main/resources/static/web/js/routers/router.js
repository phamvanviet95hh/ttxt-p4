$(document).ready(function () {


    //Dashboard Router
    $("#dashboard").click(function () {
        if (role === "ADMIN"){
            customGetPage(`${localdomain}/admin/dashboard?idPart=`, "#content_box");
        }else if(role === "PARTNER"){
            customGetPage(`${localdomain}/admin/dashboardUser?userId=${userId}&startDate=${getFirstDayOfMonth()}&endDate=${getLastDayOfMonth()}`, "#content_box");
        } 
    
    });
    //Partner Router
    $(".partner").click(function () {
        router(`/partners/listPartner?size=${10}&page=${0}`);
    });


    //Report Router

    if (role === "ADMIN") {
        $(".report").click(function () {
            router(`/reports/listReport?id=&startDate=${getFirstDayOfMonth()}&endDate=${getLastDayOfMonth()}`);
        });
        $("#reportCategoryService").click(function () {
            router(`/reports/getAllService?id=&startDate=${getFirstDayOfMonth()}&endDate=${getLastDayOfMonth()}`)
        });
        $("#reportService").click(function () {
            router(`/reports/getAllServiceItem?id=&startDate=${getFirstDayOfMonth()}&endDate=${getLastDayOfMonth()}`)
        });

    } else if (role === "PARTNER") {
        $(".report").click(function () {
            let partnerId = $(this).attr("data-value");
            router(`/reports/listReport?id=${partnerId}&startDate=${getFirstDayOfMonth()}&endDate=${getLastDayOfMonth()}`);
        });
        $("#reportCategoryService").click(function () {
            let partnerId = $(this).attr("data-value");
            router(`/reports/getAllService?id=${partnerId}&startDate=${getFirstDayOfMonth()}&endDate=${getLastDayOfMonth()}`)
        });
        $("#reportService").click(function () {
            let partnerId = $(this).attr("data-value");
            router(`/reports/getAllServiceItem?id=${partnerId}&startDate=${getFirstDayOfMonth()}&endDate=${getLastDayOfMonth()}`)
        });
        $("#serviceLink111").click(function () {
            router(`/services/loadServiceForPartner?id=${userId}&startDate=${getFirstDayOfMonth()}&endDate=${getLastDayOfMonth()}`);
        });
    }



    //Service Router
    $("#serviceLink").click(function () {
        router(`/services/listCategoryService?size=${10}&page=${0}`);
    });

    $(".listService").click(function (e) {
        e.stopPropagation();
        router(`/services/listService?size=${10}&page=${0}`);
    });


    //Transaction Router
    $(".transaction").click(function () {
        if (role === "ADMIN") {
            router(`/transactions/listTransaction?startDate=${getFirstDayOfMonth()}&endDate=${getLastDayOfMonth()}&page=0&size=10&partnerCode=&status=&id=`);
        } else if (role === "PARTNER") {
            router(`/transactions/listTransaction?startDate=${getFirstDayOfMonth()}&endDate=${getLastDayOfMonth()}&page=0&size=10&partnerCode=&status=&id=${userId}`);
        }
    });

    //System Router
    $(".system").click(function () {
        router("/system/notification");
    });



})