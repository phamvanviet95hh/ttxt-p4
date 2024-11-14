$(document).ready(function () {
    let idCategory;
    $(".remove-category-service").click(function () {
        idCategory = $(this).attr("data-idCategory");
        let checkConfirm = confirm(" Bạn có thực sự muốn xóa dịch vụ này khỏi danh mục?");
        if (checkConfirm){
            let id = $(this).attr("data-id");
            customDelete(`${localdomain}/category-service/deleteCategoryServiceToService`, id)
            setTimeout(reloadListService, 1100);
        }

    });
    function reloadListService() {
        customGetPage(`/services/tableService?id=${idCategory}&size=${10}&page=${0}`, "#box-content-serviceChildren-content-body");
    }
});