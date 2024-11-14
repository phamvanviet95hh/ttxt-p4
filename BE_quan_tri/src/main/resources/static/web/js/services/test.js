$(document).ready(function(){
    $(".box-icon-close i.fa-times-circle-o").click(function (){
        $("#box-content-serviceChildren").removeClass("hien").html("");
    });
    $(".select-box").click(function (){
       let ckeckBtn = $(this).hasClass("btn-showBox");
       if(ckeckBtn){
           $(this).html(
               `
                    <i class="fa fa-floppy-o" aria-hidden="true"></i> Lưu thay đổi
               `

           ).removeClass("btn-showBox").addClass("btn-SaveCategoryService");

           $("#box-setlect-service").addClass("hien");
       }else if($(this).hasClass("btn-SaveCategoryService")){
           $(this).html(
               `
                    <i class="fa fa-plus" aria-hidden="true"></i> Thêm dịch vụ 
               `
           ).removeClass("btn-SaveCategoryService").addClass("btn-showBox");
           $("#box-setlect-service").removeClass("hien");
           let arr;
           getCheckedValues();
           function getCheckedValues() {
               const checkboxes = document.querySelectorAll('input[name="option"]:checked');
               let checkedValues = [];
               checkboxes.forEach((checkbox) => {
                   checkedValues.push(checkbox.value);
               });

               arr = checkedValues;
           }

          let idddd = $("#idCategoryServices").val();
           var header = {
               "Content-type" : "application/json"
           }
           var bodyData = JSON.stringify({
               "id" : idddd,
               "data" : arr
           })
           fetch(`${localdomain}/category-service/add`, {
               method: "POST",
               headers: header,
               body: bodyData // Dữ liệu gửi đi
           }).then(response => {
               if (!response.ok) {
                   if (response.status === 500) {
                       customLogout();
                   }
                   throw new Error('Network response was not ok');
               }
               return response.json();
           }).then(data => {
               if (data.success) {
                   alertGloable(data.message, "success");
                   customGetPage(`/services/tableService?id=${idddd}&size=${10}&page=${0}`, "#box-content-serviceChildren-content-body");
                   customGetPage(`/services/loadService?id=${idCategoryServices}&size=${100}&page=${0}`, "#box-setlect-service");
               } else {
                   alertGloable(data.message, "false");
               }
           })
               .catch(error => {
                   console.error('There has been a problem with your fetch operation:', error);
               });
       }
    });
    $(document).ready(function(){
        idCategoryServices = $("#idCategoryServices").val();
        customGetPage(`/services/tableService?id=${idCategoryServices}&size=${10}&page=${0}`, "#box-content-serviceChildren-content-body");
        console.log($("#box-content-serviceChildren-content"));
    });
    $(document).ready(function(){
        customGetPage(`/services/loadService?id=${idCategoryServices}&size=${100}&page=${0}`, "#box-setlect-service");
    });
    

})