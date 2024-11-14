
$("#startdate").attr("value", getFirstDayOfMonth());
$("#enddate").attr("value", getLastDayOfMonth());
$(".box-icon-close").click(function(){
    $("#box-content-report-children").removeClass("hien");
})

if (role === "ADMIN"){
    $(".btn-trolai").click(function(){
        router(`/reports/listReport?id=&startDate=${getFirstDayOfMonth()}&endDate=${getLastDayOfMonth()}`);
    });
    $(".view-report1").click(function(){
        let startDate = $("#startdate").val();
        let endDate = $("#enddate").val();
        let inData =   $(this).attr("data-id");
        router(`${localdomain}/reports/getAllPartnerToService?id=${inData}&startDate=${startDate}&endDate=${endDate}&idPartner=`);
    });
    $(".btn-findReport").click(function(){
        let startDate = $("#startdate").val();
        let endDate = $("#enddate").val();
        customGetPage(`${localdomain}/reports/loadAllService?id=&startDate=${startDate}&endDate=${endDate}`, "#loadReport");
    });
    $(".btn-exportData").click(function(){
        let startDate = $("#startdate").val();
        let endDate = $("#enddate").val();
        window.location.href = `${localdomain}/reports/exportDataListAllService?id=&startDate=${startDate}&endDate=${endDate}`;
    });
}else if (role === "PARTNER"){
    $(".btn-trolai").click(function(){
        router(`/reports/listReport?id=${userId}&startDate=${getFirstDayOfMonth()}&endDate=${getLastDayOfMonth()}`);
    });
    $(".view-report1").click(function(){
        let startDate = $("#startdate").val();
        let endDate = $("#enddate").val();
        let inData =   $(this).attr("data-id");
        let idPartner = $(this).attr("data-idPartner");
        router(`${localdomain}/reports/getAllPartnerToService?id=${inData}&startDate=${startDate}&endDate=${endDate}&idPartner=${idPartner}`);
    });
    $(".btn-findReport").click(function(){
        let startDate = $("#startdate").val();
        let endDate = $("#enddate").val();
        let idPartner = $(this).attr("data-idPartner");
        customGetPage(`${localdomain}/reports/loadAllService?id=${idPartner}&startDate=${startDate}&endDate=${endDate}`, "#loadReport");
    });
    $(".btn-exportData").click(function(){
        let startDate = $("#startdate").val();
        let endDate = $("#enddate").val();
        let idPartner = $(this).attr("data-idPartner");
        window.location.href = `${localdomain}/reports/exportDataListAllService?id=${idPartner}&startDate=${startDate}&endDate=${endDate}`;
    });
}


