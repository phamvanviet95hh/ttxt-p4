$(document).ready(function () {
    $(".btn-addNewService").click(function () {
        $(".modal-addService").addClass("show");
    });
    $(".btn-close-addService").click(function () {
        $(".modal-addService").removeClass("show");
    });
    $("#modalAddService").click(function () {
        $(".modal-addService").removeClass("show");
    });
    $(".modal-content").click(function (e) {
        e.stopPropagation();
    })
    
    $(".btn-send-addService").click(function () {
        let checkAddGroupService = true;
        let addNameService = $("#addNameService").val();
        let addCodeService = $("#addCodeService").val();
        if (addNameService === "") {
            checkAddGroupService = false;
            $("#loiCategorySVName").html("This field cannot be left blank");
        }else {
            $("#loiCategorySVName").html("");
        }
        if (addCodeService === "") {
            checkAddGroupService = false;
            $("#loiCategorySVCode").html("This field cannot be left blank");
        }else {
            $("#loiCategorySVCode").html("");
        }
        if (checkAddGroupService) {
            $("#loiCategorySVName").html("");
            $("#loiCategorySVCode").html("");
            let categoryName = $("#addNameService").val();
            let categoryCode = $("#addCodeService").val();
            var header = {
                "Content-Type": "application/json"
            }
            var dataBody = JSON.stringify({
                serviceName : categoryName,
                serviceDetail : categoryCode
            });
            customPost(`${localdomain}/category-service/addCategoryService`, header, dataBody);
            setTimeout(reloadListService, 1100);
        }
    });
    let categoryServiceId;

    $(".view-categoryService").click(function (){
        categoryServiceId = $(this).attr("data-id");
        customGetPage(`/services/listServiceOfCategory?id=${categoryServiceId}&size=${10}&page=${0}`, "#box-content-serviceChildren");
        $("#box-content-serviceChildren").addClass("hien");
    });
    function reloadListService() {
        router(`/services/listCategoryService?size=${10}&page=${0}`);
    }
    $(".remove-partner-service").click(function (){
        let checkConfirm = confirm(" Bạn có thực sự muốn xóa danh mục này?");
        if (checkConfirm){
            let idCategory = $(this).attr("data-id");
            customDelete(`${localdomain}/category-service/deleteCategoryService`, idCategory);
            setTimeout(reloadListService, 1100);
        }
    });

    $(".updateStatus").click(function (){
        let idCategory = $(this).attr("data-id");
        let status = $(this).attr("data-status");
        let statusUpdate;
        if(status === "1"){
            statusUpdate = "0";
        }else {
            statusUpdate = "1";
        }

        fetch(`${localdomain}/category-service/updateStatusService?id=${idCategory}&status=${statusUpdate}`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }
        }).then(res => res.json())
            .then((data) => {
                if (data.success) {
                    router(`/services/listCategoryService?size=${10}&page=${0}`);
                }
                }
            )
            .catch(err => console.log(err));
    });
    $("#info-topLeft-prev").click(function (){
        customGetPage(`${localdomain}/admin/dashboard?idPart=`, "#content_box");
    });

});