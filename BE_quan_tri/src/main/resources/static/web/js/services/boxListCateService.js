$(".box-icon-close").click(function () {
    $("#info-box-golive").removeClass("hien");
});
$(".btn-addServiceToCate").click(function () {
    let arr;
    let iPart= $(this).attr("data-idPart");
    getCheckedValues();
    
    function getCheckedValues() {
        const checkboxes = document.querySelectorAll('input[name="option"]:checked');
        let checkedValues = [];
        checkboxes.forEach((checkbox) => {
            checkedValues.push(checkbox.value);
        });
        arr = checkedValues;
    }
    var header = {
        "Content-type" : "application/json"
    }
    var bodyData = JSON.stringify({

        "idPartner": iPart,
        "data" : arr
    })
    fetch(`${localdomain}/category-service/addCategoryServiceToPart`, {
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
        console.log(data);
        if (data.success) {
            alertGloable(data.message, "success");
            $("#content_box").load(`/partners/infoUser?id=${iPart}&startDate=${getFirstDayOfMonth()}&endDate=${getLastDayOfMonth()}`);
        } else {
            alertGloable(data.message, "false");
        }
    })
        .catch(error => {
            console.error('There has been a problem with your fetch operation:', error);
        });
});