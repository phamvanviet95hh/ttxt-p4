$(document).ready(function () {
    $("#startdate").attr("value", getFirstDayOfMonth());
    $("#enddate").attr("value", getLastDayOfMonth());
    if (role === "ADMIN"){
    
    }else if(role === "PARTNER"){
        $("#partActionInfo").addClass("an");
        $("#partActionInfo-detail").addClass("an");
        $(".infor-service-used").addClass("an");
        $("#item2").addClass("an");
        $('#user-info-service-an').addClass("an");
        $("#box-categoryOfPart").addClass("an")
    }
    $(".nav-item").click(function () {
        let idMenu = $(this).find("button").attr("data-item");
        $(this).addClass("hien").siblings().removeClass("hien");
        if (idMenu === "item1") {
            $("." + idMenu).addClass("show")
            $(".item2").removeClass("show");
            
        } else {
            $("." + idMenu).addClass("show")
            $(".item1").removeClass("show");
        }
    });
    $(".btn-addQuotas").click(function () {
       $(".modal-addQuotas").addClass("show");
    });
    $(".btn-close-addQuota").click(function () {
        $(".modal-addQuotas").removeClass("show");
    })
    $(".modal-addQuotas").click(function () {
        $(this).removeClass("show");
    })
    $(".modal-content").click(function (e) {
        e.stopPropagation();
    })
    $(document).ready(function () {
        let checkLenghtPhone1 = 0;
        const input = document.getElementById("addQuotasService");
        input.addEventListener("input", function (event) {
            this.value = this.value.replace(/[^0-9]/g, ''); // Loại bỏ ký tự không phải số
            checkLenghtPhone1 = this.value.length;
        });
    });
    $(".editUserInfo").click(function () {
        let checkClass = $(this).find("i").hasClass("fa-pencil-square-o");
        if (checkClass) {
            let nameId = $(this).attr("id");
            let fullName = $(this).parent().find("b").attr("data-value");
            $(this).find("i").removeClass("fa-pencil-square-o");
            $(this).find("i").addClass("fa-floppy-o");
            $(this).parent().find("b").html(`<input type="text" placeholder="Edit ${fullName}..." id="input-${nameId}" data-key="" value="${fullName}" class="inputInfo"/>`)
        } else {
            $(this).find("i").addClass("fa-pencil-square-o");
            $(this).find("i").removeClass("fa-floppy-o")
            let keyData = $(this).parent().find("b").attr("data-key");
            let valueUpdate = $(this).parent().find("b").find("input").val();

        }
        
    });
    $("#info-topLeft-prev").click(function(){
        if (role==="ADMIN"){
            router(`/partners/listPartner?size=${20}&page=${0}`);
        }else if(role==="PARTNER"){
        
        }
        
    });
    $(".btn-findReport").click(function(){
        let startDate = $("#startdate").val();
        let endDate = $("#enddate").val();
        let idPartner = $(this).attr("data-value");
        customGetPage(`${localdomain}/partners/detailReport?id=${idPartner}&startDate=${startDate}&endDate=${endDate}`, "#loadReportDetail");
    });

    $(".btn-setGolive").click(function (){
        $("#info-box-golive").addClass("hien");
        let idCate = $(this).attr("data-value");
        let idPartner = $(this).attr("data-idPart");
        customGetPage(`${localdomain}/partners/setDateGolive?idCate=${idCate}&idPart=${idPartner}`, "#info-box-golive");
    });
    
    $(".btn-addService").click(function (){
        $("#info-box-golive").addClass("hien");
        let idCate = $(this).attr("data-value");
        let idPart = $(this).attr("data-id");
        customGetPage(`${localdomain}/services/loadService?id=${idCate}&size=${100}&page=${0}&idPart=${idPart}`, "#info-box-golive");
    })
    
    $(".btn-editInFor").click(function () {
        let idPartner = $(this).attr("data-idPart");
        router(`/partners/editPartner?id=${idPartner}`)
    })

    let count =1;
    $(".btn-addCateinPart").click(function (){
        $("#box-list-category-content").slideToggle();
       if ($(this).hasClass("btn-success")) {
           $(this).removeClass("btn-success");
           $(this).addClass("btn-warning");
           $(this).find("span.text").html("Đóng");
           $(this).find("i").removeClass("fa-angle-double-down");
           $(this).find("i").addClass("fa-angle-double-up");
       }else {
           $(this).addClass("btn-success");
           $(this).removeClass("btn-warning");
           $(this).find("span.text").html("Hiển thị danh sách danh mục");
           $(this).find("i").addClass("fa-angle-double-down");
           $(this).find("i").removeClass("fa-angle-double-up");
       }
        let idPart = $(this).attr("data-id");
        customGetPage(`${localdomain}/services/loadCategoryService?id=${idPart}&size=${20}&page=${0}`, "#box-list-category-content");

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

    $(".btn-changePassword").click(function (){
        $("#box-changePasswordNew").addClass("hien");
    })
    $(".box-icon-close").click(function (){
        $("#box-changePasswordNew").removeClass("hien");
    });

    function checkPass(password, rePassword){
        if(password === rePassword){
            return true;
        }else {
            $("#errorRepass").html("Mật khẩu không khớp")
            return false;
        }
    }

    function validatePassword(password) {
        let errors = [];

        if (password.length < 8) {
            errors.push("Mật khẩu phải có tối thiểu 8 ký tự.");
        }
        if (!/[a-z]/.test(password)) {
            errors.push("Mật khẩu phải chứa ít nhất một chữ thường.");
        }
        if (!/[A-Z]/.test(password)) {
            errors.push("Mật khẩu phải chứa ít nhất một chữ in hoa.");
        }
        if (!/\d/.test(password)) {
            errors.push("Mật khẩu phải chứa ít nhất một chữ số.");
        }
        if (!/[@$!%*?&]/.test(password)) {
            errors.push("Mật khẩu phải chứa ít nhất một ký tự đặc biệt (ví dụ: @$!%*?&).");
        }

        if (errors.length > 0) {
            console.log("Mật khẩu không hợp lệ:");
            $(".box-errorChangeParent").addClass("hien");
            const errorElement = document.getElementById("content-error");
            errorElement.innerHTML = errors.map(error => `<li>${error}</li>`).join("");
            return false;
        } else {
            console.log("Mật khẩu hợp lệ.");
            return true;
        }
    }
    $(".btn-sendChangePassword").click(function (){
        let checkEmpty = true;
        let idPart = $(this).attr("data-value");
        let passwordOld = $("#passwordOld").val();
        let passwordNew = $("#passwordNew").val();
        let passwordNewRe = $("#passwordNewReq").val();
        checkEmpty = checkPass(passwordNew, passwordNewRe);
        let checkValidate = validatePassword(passwordNew);
        if (checkValidate && checkEmpty){
            fetch(`${localdomain}/admin_service/partner/checkPassword`,{
                method: "POST",
                headers:{
                    "Content-Type": "application/json"
                },
                body : JSON.stringify({
                    partnerId : idPart,
                    password : passwordOld,
                    newPassword : passwordNew
                })
            }).then((res) => {
                if (!res.ok) {
                    if (res.status === 500) {
                        customLogout();
                    }
                    throw new Error('Network response was not ok');
                }
                return res.json();
            }).then(data =>{
                if(data.success){
                    alertGloable("Cập nhật mật khẩu thành công.", "success");
                    router(`/partners/infoUser?id=${idPart}&startDate=${getFirstDayOfMonth()}&endDate=${getLastDayOfMonth()}`);
                }else {
                    alertGloable("Cập nhật mật khẩu thất bại.", "false");
                    router(`/partners/infoUser?id=${idPart}&startDate=${getFirstDayOfMonth()}&endDate=${getLastDayOfMonth()}`);
                }
            }).catch(err => {
                console.log(err)
            })
        }

    });
    $(".btn-exitChangePassword").click(function (){
        $("#box-changePasswordNew").removeClass("hien");
    });
    $(".btn-removeService").click(function (){
        let idPart = $(this).attr("data-id");
        let idCate =  $(this).attr("data-value");
        fetch(`${localdomain}/admin_service/partner/removeCateOfPassword?idPart=${idPart}&idCate=${idCate}`,{
            method: "DELETE",
            headers:{
                "Content-Type": "application/json"
            }
        }).then((res) => {
            if (!res.ok) {
                if (res.status === 500) {
                    customLogout();
                }
                throw new Error('Network response was not ok');
            }
            return res.json();
        }).then(data =>{
            if(data.success){
                alertGloable(data.message, "success");
                router(`/partners/infoUser?id=${idPart}&startDate=${getFirstDayOfMonth()}&endDate=${getLastDayOfMonth()}`);
            }else {
                alertGloable(data.message, "false");
            }
        }).catch(err => {
            console.log(err)
        })

    })
})