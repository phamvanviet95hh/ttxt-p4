$(document).ready(function(){
    $(".view-report").click(function(){
        let idPartner = $(this).attr("data-id");
        let startDate = $("#startdate").val();
        let endDate = $("#enddate").val();
        console.log(startDate)
        router(`${localdomain}/reports/listDetail?id=${idPartner}&startDate=${startDate}&endDate=${endDate}`);
    });
});