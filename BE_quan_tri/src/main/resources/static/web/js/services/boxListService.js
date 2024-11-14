$(".btn-addServiceToCate").click(function () {
    let arr;
    let idddd = $(this).attr("data-id");
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
        "id" : idddd,
        "idPart": iPart,
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
        console.log(data);
        if (data.success) {
            alertGloable(data.message, "success");
            router(`/partners/infoUser?id=${iPart}&startDate=${getFirstDayOfMonth()}&endDate=${getLastDayOfMonth()}`)
            $("#info-box-golive").removeClass("hien");
        } else {
            alertGloable(data.message, "false");
        }
    })
        .catch(error => {
            console.error('There has been a problem with your fetch operation:', error);
        });
});
$(".box-icon-close").click(function () {
    $("#info-box-golive").removeClass("hien");
});