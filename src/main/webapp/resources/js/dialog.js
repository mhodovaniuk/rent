function showMessage(){
    alert("Hello World!");
}
function handleRequest(xhr, status, args) {
    if(args.validationFailed || !args.addedStatus) {
        PF('dlg').jq.effect("shake", { times:5 }, 100);
    }
    else {
        PF('dlg').hide();
    }
}

