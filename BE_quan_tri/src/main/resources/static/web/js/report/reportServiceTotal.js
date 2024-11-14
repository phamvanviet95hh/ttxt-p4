$(document).ready(function () {
    const firstDay = getFirstDayOfMonth();
    const lastDay = getLastDayOfMonth();
    $("#startdate").attr("value", firstDay);
    $("#enddate").attr("value", lastDay);

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
   $(".btn-findReport").click(function () {
       let startDate = $("#startdate").val();
       let endDate = $("#enddate").val();
       if (role === "ADMIN"){
           customGetPage(`${localdomain}/reports/loadReportCategory?startDate=${startDate}&endDate=${endDate}&id=`, "#loadReport");
       }else if(role === "PARTNER"){
           customGetPage(`${localdomain}/reports/loadReportCategory?startDate=${startDate}&endDate=${endDate}&id=${userId}`, "#loadReport");
       }

   });

   $(".btn-exportData").click(function () {
       let startDate = $("#startdate").val();
       let endDate = $("#enddate").val();
       if (role === "ADMIN"){
           window.location.href = `${localdomain}/reports/exportDataListCategory?startDate=${startDate}&endDate=${endDate}&id=`;
       }else if(role === "PARTNER"){
           window.location.href = `${localdomain}/reports/exportDataListCategory?startDate=${startDate}&endDate=${endDate}&id=${userId}`;
       }

   });

});