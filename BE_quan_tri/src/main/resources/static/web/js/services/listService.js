$(document).ready(function () {
    
    $(".btn-addNewService").click(function () {
        $(".modal-addService").addClass("show");
    });
    $(".btn-close-addService").click(function () {
        $(".modal-addService").removeClass("show");
    })
    $(".modal-addService").click(function () {
        $(this).removeClass("show");
    })
    $("#modal-content1").click(function (e) {
        e.stopPropagation();
    })
    $(".view-service").click(function () {
        $(".modal-editService").addClass("show");
       let idService = $(this).attr("data-id");
        customGetPage(`/services/editService?id=${idService}`, "#modal-content");
    });
    $(".btn-send-addService").click(function () {
        let nameService = $("#addNameService").val();
        let check = true;
        let priceService = $("#addPriceService").val();
        var header = {
            "Content-Type": "application/json"
        }
        var dataBody = JSON.stringify({
            serviceName : nameService,
            servicePrice: priceService
        });
        
        if (nameService === "") {
            check = false;
            $("#loiCategorySVName").html("Không được để trống trường này");
        }else {
            check = true;
            $("#loiCategorySVName").html("");
        }
        if (priceService === "") {
            check = false;
            $("#loiCategorySVPrice").html("Không được để trống trường này");
        }else {
            if (priceService === "0") {
                check = false;
                $("#loiCategorySVPrice").html("Giá phải lớn hơn 0");
            }
        }
        let checkO = isValidString(priceService);

        if (check && checkO){
            $("#loiCategorySVPrice").html("");
            $("#loiCategorySVName").html("");
            customPost(`${localdomain}/services/addService`, header, dataBody);
            setTimeout(reloadListService, 1100);
        }
        
    });

    function isValidString(str) {
        $("#loiCategorySVPrice").html("Giá không được có số 0 ở đầu");
        return !/^0/.test(str);
    }
    function reloadListService() {
        router(`/services/listService?size=${10}&page=${0}`);
    }
    $(".remove-partner-service i").click(function () {
        let checkConfirm = confirm("Bạn có chắc chắn muốn xóa dịch vụ này???");
        if(checkConfirm){
            let idService = $(this).parent().attr("data-id");
            customDelete(`${localdomain}/services/deleteService`, idService);
            setTimeout(reloadListService, 1100);
        }
    });
    $(document).ready(function () {
        const input = document.getElementById("addPriceService");
        input.addEventListener("input", function (event) {
            this.value = this.value.replace(/[^0-9]/g, ''); // Loại bỏ ký tự không phải số
        });
    });
    $("#info-topLeft-prev").click(function (){
        customGetPage(`${localdomain}/admin/dashboard?idPart=`, "#content_box");
    });
});