$(function(){
    $("a").each(function(){
        if ($(this).attr("href") == window.location.pathname){
            $(this).addClass("selected");
        }
    });
});