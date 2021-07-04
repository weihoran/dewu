
$(function() {

    var experts = {
      1: "你微笑时好美y0",
      2: "St_悟空",
      3: "倾城Corleone",
      4: "炫亿",
      5: "灯_等灯"
    };

    var url = new URL(window.location.href);
    var number = url.searchParams.get("serial");
    var expert = url.searchParams.get("expert");
    var month = url.searchParams.get("m");
    var day = url.searchParams.get("d");
    var dateinfo = "鸡翼" + month + "月" + day + "日发布"
    $("#serialNumber").html(number)
    $("#expert").html(experts[expert])
    $("#avatar1").attr("src", "img/"+expert+".jpeg")
    $("#dateinfo").html(dateinfo)
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

