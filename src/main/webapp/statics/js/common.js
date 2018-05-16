function getIp(rootPath) {
    var ip = "";
    $.ajax({
        type: 'POST',
        url: rootPath + "/action/ip",
        async:false,
        dataType: 'json',
        crossDomain: true,
        success:function(result) {
            ip = result.data.ip;
        }
    });
    return ip;
}

function sendAction(rootPath,url) {
    var output = new uaDevice(navigator.userAgent);
    var browserName = output.browser.name; //
    var browserVer = output.browser.version.original;
    var osName = output.os.name;
    var osVersion = output.os.version.original;
    var client = output.device.type;
    var manufacturer = output.device.manufacturer;
    var phone = output.device.model;

    var actionInfo = {
        url:url,
        ip:getIp(rootPath),
        viewCode:"viewCode",
        nextViewCode:"nextViewCode",
        optType:1,
        optCode:"optCode",
        optKey:"optKey",
        browserName:browserName,
        browserVer:browserVer,
        osName:osName,
        osVersion:osVersion,
        client:client,
        manufacturer:manufacturer,
        phone:phone
    }


    /*$.post(rootPath+"/action/actionInfo",JSON.stringify(actionInfo),function(result){
        $("span").html(result);
    });*/

    var actionStr = JSON.stringify(actionInfo);

    $.ajax({
        type: 'POST',
        url: rootPath + "/action/actionInfo",
        async:false,
        dataType: 'json',
        crossDomain: true,
        data: {'actionStr':actionStr}
    });
}