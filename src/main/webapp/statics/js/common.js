function getIp(rootPath) {
    var ip = "";
    // $.ajax({
    //     url: rootPath + "/action/ip",
    //     dataType: 'json',
    //     crossDomain: true
    // });
    $.post(rootPath + "/action/ip",{suggest:""},function(result){
        ip = result;
    });
    // $.get(rootPath + "/action/ip", function(result){
    //     ip = result;
    // });
    return ip;
}

function sendAction(rootPath) {
    var output = new uaDevice(navigator.userAgent);
    var browserName = output.browser.name; //
    var browserVer = output.browser.version.original;
    var osName = output.os.name;
    var osVersion = output.os.version.original;
    var client = output.device.type;
    var manufacturer = output.device.manufacturer;
    var phone = output.device.model;

    var actionInfo = new Object();
    actionInfo.ip = getIp(rootPath);
    actionInfo.viewCode = "viewCode";
    actionInfo.nextViewCode = "nextViewCode";
    actionInfo.optType = 1;
    actionInfo.optCode = "optCode"
    actionInfo.optKey = "optKey";
    actionInfo.browserName = browserVer;
    actionInfo.browserVer = browserVer;
    actionInfo.osName = osName;
    actionInfo.osVersion = osVersion;
    actionInfo.client = client;
    actionInfo.manufacturer = manufacturer;
    actionInfo.phone = phone;

    /*$.post(rootPath+"/action/actionInfo",JSON.stringify(actionInfo),function(result){
        $("span").html(result);
    });*/

    $.ajax({
        type: 'POST',
        url: rootPath + "/action/actionInfo",
        data: JSON.stringify(actionInfo),
        dataType: 'json',
        crossDomain: true
    });
}