function getMenus() {
	$("#meuns").find('span.layui-nav-bar').remove();
	$.ajax({
		type: 'get',
		url: "/admin/menus/list",
		data: { userId: userId , token:token},
		async: false,
		success: function(result) {
			if (result.code == 200) {
				appendMenuList(result);
			} else if(result.code == 401){
				errOut(result.message)
			} else {
				err(result.message);
			}
		},
		error: function(error) {
			err('请求错误,请稍后重试!');
		}
	})
}


function appendMenuList(result) {
	var fir = "";
	for (var i = 0; i < result.data.length; i++) {
		var sec = "";
		if (result.data[i].has == 0) {
			fir += "<li class='layui-nav-item' userpower='"+result.data[i].power+"'><a href='"+result.data[i].url+"'>"+result.data[i].describe+"</a></li>";
		} else {
			sec += "<li class='layui-nav-item'>"
				+ "<a id='ms_"+result.data[i].menuId+"' class='' href='"+result.data[i].url+"'>"+result.data[i].describe+"</a>"
				+ "<dl class='layui-nav-child'>";
			for (var j = 0; j < result.extra.length; j++) {
				if (result.extra[j].parentId == result.data[i].menuId) {
					sec += "<dd userpower='"+result.extra[j].power+"'><a href='"+result.extra[j].url+"'>"+result.extra[j].describe+"</a></dd>";
				}
			}
			sec += "</dl></li>";
			fir += sec;
		}
	}
	$("#menus").empty();
	$("#menus").append(fir);
	xuanran();
}

function xuanran() {
    layui.use('element', function () {
        var element = layui.element;
        var layFilter = $("#menus").attr('lay-filter');
        element.render('nav', layFilter);
        url = window.location.pathname;//菜单不能重复
        var level2 = $("a[href='" + url + "']");
    	level2.parent().addClass('layui-this');
    	var mbxFlag = level2.parent().is('li');
    	$("#userpower").val(level2.parent().attr('userpower'));//权限
    	loadPower();
    	var level1 = level2.parent().parent().parent();
    	level1.addClass(' layui-nav-itemed');
    	if(mbxFlag){
	    	$("#_body").html("<a href="+url+">"+level2.html()+"</a>");
    	}else{
	    	var _ddobj = $(level1.children()[0]).html();
	    	$("#_body").html("<a href='#'>"+_ddobj.substring(0,_ddobj.indexOf("<i"))+" / </a><a href="+url+">"+level2.html()+"</a>");
    	}
    })
}

function loadPower(){
	var arr = $(".userpower");
	for(var i=0; i<arr.length; i++){
		getPower(arr[i]);
	}
}