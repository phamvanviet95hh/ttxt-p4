
$(document).ready(function () {
   $("#request_number1").click(function () {
       router(`/partners/listPartner?size=${10}&page=${0}`);
   });

});