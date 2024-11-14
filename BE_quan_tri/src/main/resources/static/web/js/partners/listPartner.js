$(document).ready(function(){

    $(".remove-partner-service").click(function(){
        let checkConfirm = confirm("Bạn có chắc chắn muốn xóa đơn vị này? ");
        if (checkConfirm) {
            let idParner = $(this).attr("data-id");
            customDelete(`${localdomain}/admin_service/partner/deletePatner`, idParner);
            setTimeout(reloadListService, 1100);
        }
    });
    function reloadListService() {
        router(`/partners/listPartner?size=${10}&page=${0}`);
    }
    $(".view-bank").click(function(){
        let partnerId = $(this).attr("data-id");
        router(`/partners/infoUser?id=${partnerId}&startDate=${getFirstDayOfMonth()}&endDate=${getLastDayOfMonth()}`)
    })
    $(".edit-bank").click(function(){
        let partnerId = $(this).attr("data-id");
        router(`/partners/editPartner?id=${partnerId}`)
    });
    $(".btn-addnewpartner").click(function(){
        router("/partners/addPartner");
    });
    $('#info-topLeft-prev').click(function () {
        customGetPage(`${localdomain}/admin/dashboard`, "#content_box");
    });
});
