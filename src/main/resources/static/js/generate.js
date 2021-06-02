$(document).ready(function(){
    $(".button").click(function(){
        var times = $("#time").val().trim();
        var pw = $("#password").val().trim();

        $.ajax({
            url:'/api/create/' + times,
            type:'post',
            data: pw,
            contentType : 'text/plain',
            success:function(response){
               $("#message").html(response.code);
            },
            error:function(e){
               $("#message").html("密码错误");
            }
        });

    });
});