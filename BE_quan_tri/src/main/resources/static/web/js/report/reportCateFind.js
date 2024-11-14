$(".view-report1").click(function () {
    let dataId = $(this).attr("data-id");
    let startDate = $("#startdate").val();
    let endDate = $("#enddate").val();
    if (role === "ADMIN"){
        router(`${localdomain}/reports/getAllPartnerToCate?id=${dataId}&startDate=${startDate}&endDate=${endDate}&idPartner=`);
    }else if(role === "PARTNER"){
        router(`${localdomain}/reports/getAllPartnerToCate?id=${dataId}&startDate=${startDate}&endDate=${endDate}&idPartner=${userId}`);
    }
    
});