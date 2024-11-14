$(".view-report1").click(function(){
    let startDate = $("#startdate").val();
    let endDate = $("#enddate").val();
    let inData =   $(this).attr("data-id");
    if(role === "ADMIN"){
        router(`${localdomain}/reports/getAllPartnerToService?id=${inData}&startDate=${startDate}&endDate=${endDate}&idPartner=`);
    }else if(role === "PARTNER"){
        router(`${localdomain}/reports/getAllPartnerToService?id=${inData}&startDate=${startDate}&endDate=${endDate}&idPartner=`);
    }
    
});