$(document).ready(function(){
    console.log("ạdgashudvasghdvasghd")
    $("#startdate").attr("value", getFirstDayOfMonth());
    $("#enddate").attr("value", getLastDayOfMonth());
    if(role === "ADMIN"){

    }else if(role === "PARTNER"){
        $("#partnerName").addClass("an");
    }


    $(".view-report").click(function(){
        let idPartner = $(this).attr("data-id");
        router(`${localdomain}/reports/listDetail?id=${idPartner}&startDate=${getFirstDayOfMonth()}&endDate=${getLastDayOfMonth()}`);
    });
    $(".btn-findReport").click(function(){
        let startDate = $("#startdate").val();
        let endDate = $("#enddate").val();
        let idPartner = $("#partnerName").val();
        if (role === "ADMIN"){
            customGetPage(`/reports/findListReport?id=${idPartner}&startDate=${startDate}&endDate=${endDate}`, "#loadReport")
        }else if(role === "PARTNER"){
            customGetPage(`/reports/findListReport?id=${userId}&startDate=${startDate}&endDate=${endDate}`, "#loadReport")
        }

    });
    $(".btn-findReportService").click(function(){
        let startDate = $("#startdate").val();
        let endDate = $("#enddate").val();
        customGetPage(`/reports/getAllService?startDate=${startDate}&endDate=${endDate}`, "#loadReport")
    });
    
    $(".btn-findReportServiceItem").click(function(){
        let startDate = $("#startdate").val();
        let endDate = $("#enddate").val();
        customGetPage(`/reports/getAllServiceItem?startDate=${startDate}&endDate=${endDate}`, "#loadReport")
    });

    $(".btn-exportData").click(function(){
        let startDate = $("#startdate").val();
        let endDate = $("#enddate").val();
        let idPartner = $("#partnerName").val();
        if (role === "ADMIN"){
            window.location.href = `${localdomain}/reports/exportDataListPartnerReport?id=${idPartner}&startDate=${startDate}&endDate=${endDate}`;
        }else if(role === "PARTNER"){
            window.location.href = `${localdomain}/reports/exportDataListPartnerReport?id=${userId}&startDate=${startDate}&endDate=${endDate}`;
        }

    });
    
});