$(function(){
    $("a").each(function(){
        if ($(this).attr("href") == window.location.pathname && window.location.pathname!="/rent/login.xhtml" && window.location.pathname!="/rent/register.xhtml"){
            $(this).addClass("selected");
        }
        if ((window.location.pathname=="/rent/" || window.location.pathname=="/rent") && $(this).attr("href") == "/rent/index.xhtml"){
            $(this).addClass("selected")
        }
    });
});