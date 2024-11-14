$(".btn-trolai").click(function(){
    if (role === "ADMIN"){
        router(`/reports/getAllServiceItem?startDate=${getFirstDayOfMonth()}&endDate=${getLastDayOfMonth()}&id=`);
    }else if(role === "PARTNER"){
        router(`/reports/getAllServiceItem?startDate=${getFirstDayOfMonth()}&endDate=${getLastDayOfMonth()}&id=${userId}`);
    }

})