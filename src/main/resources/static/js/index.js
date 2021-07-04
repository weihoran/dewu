$(function() {
    var code = localStorage.getItem("code");
    if (code == '' || code == undefined || code == null)
        window.location = "/login"

    $.ajax({
        url:'/api/get/times',
        type:'post',
        data: code,
        contentType : 'text/plain',
        success:function(response){
            if(response == 0){
                localStorage.removeItem("code");
                alert("激活次数已用完")
                window.location = "/login"
            }
            else
                $("#times").html(response);
        }
    });
	var img = [];
	var formData = new FormData(); //创建一个formdata用来post
	$("#inputs").change(function() {
        var fil = this.files;

        for(var i = 0; i < fil.length; i++) {
            if(fil[i].size > 1024 * 1024){
                alert("图片不能大于1M")
                this.value = ""
                return
            }
            reads(fil[i]);
            img.push(fil[i]); //将传入的图片push到空对象中
            formData.append("files",fil[i])

        }
        $('.uploadDIv').show();
        console.log(img);
    });

	function reads(fil) {
        var reader = new FileReader();
        reader.readAsDataURL(fil);
        reader.onload = function() {
        document.getElementById("uploadBox").innerHTML += "<div class='divImg' id='uploadImg'><img src='" + reader.result + "' class='imgBiMG'></div>";
        }
	}

    $("#upload").click(function(){
        var productName = $(".name").val().trim();
        var expert = $("#expert").val()
        if (!expert){
            alert("请选择鉴别师")
            return
        }
        if (productName == '' || productName == undefined || productName == null){
            alert("请输入产品名称！")
            return
        }
        if (img.length == 0){
            alert("请上传图片！")
            return
        }
        formData.append("productName",productName)
        formData.append("userCode",code)
        formData.append("expert", expert)
        $.ajax({
            "url": "/api/identify",
            "data" : formData,
            "dataType":"json",
            "type": "post",
            "contentType" : false, //上传文件一定要是false
            "processData":false,
            "success" : function(response) {
                window.location = "/identification?serial=" + response + "&expert=" + expert
            }
        });

    })

})
