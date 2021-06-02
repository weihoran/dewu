$(document).ready(function(){
    $(".button").click(function(){
        var code = $(".code").val().trim();
        var reg=/^\d{13}$/;
        if(!reg.exec(code)){
            $("#message").html("激活码错误或已失效");
            return
        }

        $.ajax({
            url:'/api/login',
            type:'post',
            data: code,
            contentType : 'text/plain',
            success:function(response){
               if(response){
                   localStorage.setItem("code",code);
                   window.location = "../";
               }

               else
                   $("#message").html("激活码错误或已失效");
            },
            error:function(e){
               $("#message").html("请稍后重试");
            }
        });





    });
});