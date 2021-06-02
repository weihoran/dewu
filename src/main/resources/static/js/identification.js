
$(function() {

    var url = new URL(window.location.href);
    var number = url.searchParams.get("serial");
    $("#serialNumber").html(number)

    $.get("/api/get/identification/" + number, function(data, status){
        $("#productName").html(data.productName)
        $("title").html(data.productName + "鉴定报告")
        list = data.imagelist
        for (i = 0; i < list.length; i++) {
              $.get("/api/get/file/" + list[i], function(data, status){
                $(".column").append('<img src="data:image/png;base64,' + data + '" />')
              })
            }
      });

})

