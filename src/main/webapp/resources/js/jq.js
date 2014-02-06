$(function(){
    $("a").each(function(){
        if ($(this).attr("href") == window.location.pathname && window.location.pathname!="/rent/login.xhtml" && window.location.pathname!="/rent/register.xhtml"){
            $(this).addClass("selected");
        }
    });
});