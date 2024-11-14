$(document).ready(function(){
    $(".box-content-edit-input i").click(function(){
        let checkIcon = $(this).hasClass("fa-pencil-square-o");
        let checkIcon1 = $(this).hasClass("fa-floppy-o");
        if(checkIcon){
            $(this).removeClass("fa-pencil-square-o").addClass("fa-floppy-o");
            $(this).parent().find("input").removeAttr("readonly");
        }else if(checkIcon1){
            $(this).removeClass("fa-floppy-o").addClass("fa-pencil-square-o");
            $(this).parent().find("input").attr('readonly', true);
            let idService = $("#idService").val();
            let valueService = $(this).parent().find("input").val();
            let dataKey = $(this).attr("data-key");
            var header = {
                "Content-Type": "application/json"
            }
            var dataBody = JSON.stringify({
                id : idService,
                [dataKey] : valueService
            });

            customPost(`${localdomain}/services/updateService`, header, dataBody);
            setTimeout(reloadListService, 1100);
        }
    });
    $(".btn-close-addService").click(function (){
        $("#modalEditService").removeClass("show");
    });
    $(".btn-send-editService").click(function (){
        let check = true;
        let idService = $("#idService").val();
        let newServiceName = $("#addNameServiceEdit").val();
        let newServicePrice = $("#addPriceServiceEdit").val();
        if (newServiceName === "") {
            check = false;
            $("#box-content-edit-inputName").html("Không được để trống trường này");
        }else {
            check = true;
            $("#box-content-edit-inputName").html("");
        }
        if (newServicePrice === "") {
            check = false;
            $("#box-content-edit-inputPrice").html("Không được để trống trường nàyk");
        }else {
            if (newServicePrice === "0") {
                check = false;
                $("#box-content-edit-inputPrice").html("Giá phải lớn hơn 0");
            }
        }
        let checkO = isValidString(newServicePrice);
        var header = {
            "Content-Type": "application/json"
        }
        var dataBody = JSON.stringify({
            id : idService,
            serviceName : newServiceName,
            servicePrice : newServicePrice
        });
        
        console.log(newServiceName)
        console.log(newServicePrice)
        console.log(check)
        if (check && checkO){
            $("#box-content-edit-inputName").html("");
            $("#box-content-edit-inputPrice").html("");
            customPost(`${localdomain}/services/updateService`, header, dataBody);
            setTimeout(reloadListService, 1100);
        }
        
    });

    function isValidString(str) {
        $("#box-content-edit-inputPrice").html("Giá không được có số 0 ở đầu");
        return !/^0/.test(str);
    }
    function reloadListService() {
        router(`/services/listService?size=${10}&page=${0}`);
    }
    $(document).ready(function () {
        const input = document.getElementById("addPriceServiceEdit");
        input.addEventListener("input", function (event) {
            this.value = this.value.replace(/[^0-9]/g, ''); // Loại bỏ ký tự không phải số
        });
    });
});