
if(role === "ADMIN"){

}else if(role === "PARTNER"){
    $(".admin_role").addClass("an");
}

$(".btn-removeService").click(function(){
   
        let idService = $(this).attr("data-service");
        let idPartner = $(this).attr("data-id");
        let idCategory = $(this).attr("data-value");
        console.log(idService, idPartner, idCategory)
        var header = {
            "Content-type" : "application/json"
        }
        fetch(`${localdomain}/category-service/delete?idService=${idService}&idCate=${idCategory}&idPart=${idPartner}`, {
            method: "DELETE",
            headers: header,
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
                customGetPage(`/partners/load-CateOfPart?id=${idPartner}&idCategory=${idCategory}`, "#load-CateOfPart");
            } else {
                alertGloable(data.message, "false");
            }
        })
            .catch(error => {
                console.error('There has been a problem with your fetch operation:', error);
            });
});