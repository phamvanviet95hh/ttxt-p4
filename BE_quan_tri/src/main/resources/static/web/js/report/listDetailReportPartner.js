    $(".btn-trolai").click(function () {
        if(role === "ADMIN"){
            router(`/reports/getAllService?id=&startDate=${getFirstDayOfMonth()}&endDate=${getLastDayOfMonth()}`)
        }else if(role === "PARTNER"){
            router(`/reports/getAllService?id=${userId}&startDate=${getFirstDayOfMonth()}&endDate=${getLastDayOfMonth()}`)
        }
    });



