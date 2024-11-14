$(document).ready(function(){
    if(role === "ADMIN"){

    }else if(role === "PARTNER"){
        $(".admin_role").addClass("an")
        $("#box-partnerName").addClass("an");
    }
    document.getElementById("startDateWeek").addEventListener("change", function() {
        const startOfWeek = new Date(this.value);
        const endOfWeek = new Date(startOfWeek);
        endOfWeek.setDate(startOfWeek.getDate() + 6);
        const endDate = endOfWeek.toISOString().split("T")[0];
        document.getElementById("endDateWeek").value = endDate;
    });
    const firstDay = getFirstDayOfMonth();
    const lastDay = getLastDayOfMonth();
    $("#startdate").attr("value", firstDay);
    $("#enddate").attr("value", lastDay);
    $("#findDate").attr("value", getCurrentLocalDateTime());
    $("#startDateWeek").attr("value", dautuanht());
    $("#endDateWeek").attr("value", cuoituanht());
    $(".nextPageTransaction").click(function(){
        let size = $(this).attr("data-size");
        let page = $(this).attr("data-page");
        let startDate = null;
        let endDate = null;
        let partnerCode = $("#partnerName").val();
        let statusPartner = $("#statusPartner").val();
        let findTypeTransaction = $("#findTypeTransaction").val();
        switch (findTypeTransaction){
            case "optional" :
                startDate = $("#startdate").val();
                endDate= $("#enddate").val();
                break;
            case "week" :
                startDate = $("#startDateWeek").val()+"T00:00:00";
                endDate= $("#endDateWeek").val()+"T00:00:00";
                break;

        }
        if(role === "ADMIN"){
            customGetPage(`${localdomain}/transactions/loadTransaction?id=&startDate=${startDate}&endDate=${endDate}&page=${page}&size=${size}&status=${statusPartner}&partnerCode=${partnerCode}`, "#loadTransaction")
        }else if (role === "PARTNER"){
            customGetPage(`${localdomain}/transactions/loadTransaction?id=${userId}&startDate=${startDate}&endDate=${endDate}&page=${page}&size=${size}&status=${statusPartner}&partnerCode=${partnerCode}`, "#loadTransaction")
        }
    })

    $(".prevPageTransaction").click(function(){
        let size = $(this).attr("data-size");
        let page = $(this).attr("data-page");
        let startDate = null;
        let endDate = null;
        let partnerCode = $("#partnerName").val();
        let statusPartner = $("#statusPartner").val();
        let findTypeTransaction = $("#findTypeTransaction").val();
        switch (findTypeTransaction){
            case "optional" :
                startDate = $("#startdate").val();
                endDate= $("#enddate").val();
                break;
            case "week" :
                startDate = $("#startDateWeek").val()+"T00:00:00";
                endDate= $("#endDateWeek").val()+"T00:00:00";
                break;

        }
        if(role === "ADMIN"){
            customGetPage(`${localdomain}/transactions/loadTransaction?id=&startDate=${startDate}&endDate=${endDate}&page=${page}&size=${size}&status=${statusPartner}&partnerCode=${partnerCode}`, "#loadTransaction")
        }else if (role === "PARTNER"){
            customGetPage(`${localdomain}/transactions/loadTransaction?id=${userId}&startDate=${startDate}&endDate=${endDate}&page=${page}&size=${size}&status=${statusPartner}&partnerCode=${partnerCode}`, "#loadTransaction")
        }

    })
    $(".btn-timkiemTransaction").click(function(){
        let startDate = null;
        let endDate = null ;
        let partnerCode = $("#partnerName").val();
        let statusPartner = $("#statusPartner").val();
        let findTypeTransaction = $("#findTypeTransaction").val();
        switch (findTypeTransaction){
            case "optional" :
                startDate = $("#startdate").val();
                endDate= $("#enddate").val();
                break;
            case "week" :
                startDate = $("#startDateWeek").val()+"T00:00:00";
                endDate= $("#endDateWeek").val()+"T00:00:00";
                break;

        }
        if(role === "ADMIN"){
            console.log(`${localdomain}/transactions/loadTransaction?startDate=${startDate}&endDate=${endDate}&page=0&size=10&status=${statusPartner}&partnerCode=${partnerCode}&id=`);
            customGetPage(`${localdomain}/transactions/loadTransaction?startDate=${startDate}&endDate=${endDate}&page=0&size=10&status=${statusPartner}&partnerCode=${partnerCode}&id=`, "#loadTransaction");

        }else if(role === "PARTNER"){
            customGetPage(`${localdomain}/transactions/loadTransaction?startDate=${startDate}&endDate=${endDate}&page=0&size=10&status=${statusPartner}&partnerCode=${partnerCode}&id=${userId}`, "#loadTransaction")
        }

    });

    $(".btn-exportData").click(function (){
        let startDate = null;
        let endDate = null ;
        let partnerCode = $("#partnerName").val();
        let statusPartner = $("#statusPartner").val();
        let findTypeTransaction = $("#findTypeTransaction").val();
        switch (findTypeTransaction){
            case "optional" :
                startDate = $("#startdate").val();
                endDate= $("#enddate").val();
                break;
            case "week" :
                startDate = $("#startDateWeek").val()+"T00:00:00";
                endDate= $("#endDateWeek").val()+"T00:00:00";
                break;

        }
        if(role === "ADMIN"){
            window.location.href = `${localdomain}/reports/exportDataTransaction?startDate=${startDate}&endDate=${endDate}&page=0&size=10&status=${statusPartner}&partnerCode=${partnerCode}&id=`;
        }else if(role === "PARTNER"){
            window.location.href = `${localdomain}/reports/exportDataTransaction?startDate=${startDate}&endDate=${endDate}&page=0&size=10&status=${statusPartner}&partnerCode=${partnerCode}&id=${userId}`;
        }

    });

    $(".view-transaction").click(function (){
        let transactionId = $(this).attr("data-id");
        $("#info-transaction-box").addClass("hien");
        customGetPage(`${localdomain}/transactions/load-info-transaction?transactionId=${transactionId}`, "#info-transaction-box");
    });

    $("#partnerName").keyup(function (){
        let valuePartnerName = $(this).val();
        customGetPage(`${localdomain}/partners/valuePartnerName?valuePartnerName=${valuePartnerName}`, "#box-searchPartner");
    });

    $("#findTypeTransaction").change(function (){
        let id = $(this).val();
        switch(id){

            case "week":
                $(`#week`).addClass("active");

                $(`#optional`).removeClass("active");
                break;
            case "optional":
                $(`#optional`).addClass("active");
                $(`#week`).removeClass("active");

                break;
            default:
                $(`#week`).removeClass("active");
                $(`#optional`).removeClass("active");
                break;
        }


    })
    $(".inputPage").change(function (){
        let page = $(this).val();
        console.log(page)
        let size = "10";
        let startDate = null;
        let endDate = null;
        let partnerCode = $("#partnerName").val();
        let statusPartner = $("#statusPartner").val();
        let findTypeTransaction = $("#findTypeTransaction").val();
        switch (findTypeTransaction){
            case "optional" :
                startDate = $("#startdate").val();
                endDate= $("#enddate").val();
                break;
            case "week" :
                startDate = $("#startDateWeek").val()+"T00:00:00";
                endDate= $("#endDateWeek").val()+"T00:00:00";
                break;

        }
        if(role === "ADMIN"){
            customGetPage(`${localdomain}/transactions/loadTransaction?id=&startDate=${startDate}&endDate=${endDate}&page=${page}&size=${size}&status=${statusPartner}&partnerCode=${partnerCode}`, "#loadTransaction")
        }else if (role === "PARTNER"){
            customGetPage(`${localdomain}/transactions/loadTransaction?id=${userId}&startDate=${startDate}&endDate=${endDate}&page=${page}&size=${size}&status=${statusPartner}&partnerCode=${partnerCode}`, "#loadTransaction")
        }
    });
});