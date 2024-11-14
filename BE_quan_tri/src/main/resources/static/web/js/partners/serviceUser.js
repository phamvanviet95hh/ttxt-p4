$(document).ready(function(){
    const firstDay = getFirstDayOfMonth();
    const lastDay = getLastDayOfMonth();
    $("#startdate").attr("value", firstDay);
    $("#enddate").attr("value", lastDay);

    if(role === "ADMIN"){

    }else if(role === "PARTNER"){
        $(".admin_role").addClass("an");
        $(".boxActionCateInPart").addClass("an")
    }

    $(".btn-findReport").click(function(){
        let startDate = $("#startdate").val();
        let endDate = $("#enddate").val();
        let idPartner = $(this).attr("data-value");
        customGetPage(`${localdomain}/partners/detailReport?id=${idPartner}&startDate=${startDate}&endDate=${endDate}`, "#loadReportDetail");
    });
    $(".btn-viewService-Cate").click(function (){
        if($(this).hasClass("fa-chevron-circle-down")){
            $(this).removeClass("fa-chevron-circle-down");
            $(this).addClass("fa-chevron-circle-up");
            let idPart = $(this).attr("data-idPart");
            let idCate = $(this).attr("data-value");
            $.ajax(`${localdomain}/partners/loadDataServiceInPart?idPart=${idPart}&idCate=${idCate}`,{
                method : "GET",
                dataType : "html",
                success:(res)=>{
                    alertGloable("Xử lý tác vụ thành công !", "success");
                    $(this).parent().parent().parent().find("div.box-serviceInCate").html(res);
                },
                error : (err) => {
                    console.log(err);
                }
            });
        }else if($(this).hasClass("fa-chevron-circle-up")){
            $(this).removeClass("fa-chevron-circle-up");
            $(this).addClass("fa-chevron-circle-down");
            $(this).parent().parent().parent().find("div.box-serviceInCate").html("");
        }
    });
})