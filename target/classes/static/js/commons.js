var domain="";
var global_title = "酒店信息管理系统";
var token = "";
var userId = "";
$("#global_title").html(global_title);
valiadUrl();

function getParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}
//字段过滤
function filterNull(str){
	return (str==null||str==undefined)?'':str;
}

function subValid(str){
	if(str.length>10){
		return str.substring(0,10)+"...";
	}
	return str;
}

function valiadUrl(){
	token = getToken();
	if(token==undefined){
		alert("身份已过期，请重新登陆！");
		window.location.href="/";
	}
	userId = getUserId()
}

function getToken(){
	token = localStorage.getItem("token");
	return token;
}

function getUserId(){
	userId = localStorage.getItem("userId");
	return userId;
}

function getNickname(){
	return localStorage.getItem("_nickname");
}

function getLogo(){
	var logo = localStorage.getItem("localStorage_logo");
	if(logo==null || logo=="undefined"){
		logo = "../img/icon2.png";
	}
	return logo;
}

//定义Format方法  dateTime--后台传输过来的Date类型  fmt--你要转换的格式
//返回的是对应fmt时间格式的字符串
function Format(datetime,fmt) {
    if (parseInt(datetime)==datetime) {
        if (datetime.length==10) {
            datetime=parseInt(datetime)*1000;
        } else if(datetime.length==13) {
            datetime=parseInt(datetime);
        }
    }
    datetime=new Date(datetime);
    var o = {
        "M+" : datetime.getMonth()+1,                 //月份
        "d+" : datetime.getDate(),                    //日
        "h+" : datetime.getHours(),                   //小时
        "m+" : datetime.getMinutes(),                 //分
        "s+" : datetime.getSeconds(),                 //秒
        "q+" : Math.floor((datetime.getMonth()+3)/3), //季度
        "S"  : datetime.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt))
        fmt=fmt.replace(RegExp.$1, (datetime.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
        if(new RegExp("("+ k +")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
    return fmt;
}

function initHead(){
	$("#_head_title").html(global_title);
	$("#_head_user").html(getNickname());
}

function ok(msg){
	if(msg == undefined || msg==null)
		msg = "操作成功";
	layer.msg(msg, {icon: 1});
}

function okAndReload(msg){
	if(msg == undefined || msg==null)
		msg = "操作成功";
	layer.alert(msg, { icon: 1, closeBtn: 0 }, function (index) { 
		window.location.reload();
	});
}

function okAndClose(msg){
	if(msg == undefined || msg==null)
		msg = "操作成功";
	layer.alert(msg, { icon: 1, closeBtn: 0 }, function (index) { 
		parent.layer.close(index);
        parent.location.reload();
	});
}

function err(msg){
	layer.msg(msg, {icon: 2});
}

function errOut(msg){
	layer.confirm(msg,{
        btn:['确定'],
        cancel:function(index, layero){
        	logOut();
        }
    },
	function(){
		logOut();
	});
}

function _upload(id,url,type,sort){
	var formData = new FormData();
	var file = $("#"+id)[0].files[0];
	if(file==undefined){
		err("选择文件后才可以上传");
		return;
	}
    formData.append("multipartFile", file);
    formData.append("type", type);
    formData.append("sort", sort);
    formData.append("token", token);
	$.ajax({
		type : 'post',
		url : url,
		data : formData,
		contentType:false,
        processData:false,
        dataType:"json",
        mimeType:"multipart/form-data",
		success : function(result) {
			if (result.code == 200) {
				uploadAfter(result);
			} else {
				uploadFail(result.message)
//				err(result.message);
			}
		},
		error : function(error) {
			err('请求错误,请稍后重试!');
			layer.close(subLoding);
		}
	})
}

function logOut(){
	localStorage.removeItem("token");
	localStorage.removeItem("userId");
	localStorage.removeItem("_nickname");
	window.location.href="/";
}
function personal(){
	window.location.href="/html/person/list.html";
}

function getPower(obj){
	var userpower = $("#userpower").val();
	if(userpower!='all'){
		var jqObj = $(obj);
		if(contains(userpower,jqObj.attr('userpower'))){
			jqObj.show();
		}else{
			jqObj.hide();
		}
	}
}

function getListViewPower(){
	var str = "";
	var userpower = $("#userpower").val();
	if(userpower!='all'){
		if(contains(userpower,'edit')){
			str += "edit";
		}else{
			str += "view";
		}
	}else{
		str = "edit";
	}
	return str;
}

function getListBtnPower(obj){
	var str = "";
	var userpower = $("#userpower").val();
	if(userpower!='all'){
		if(contains(userpower,'delete')){
			str += "<button class='layui-btn layui-btn-sm layui-btn-danger userpower' userpower='delete'  href='#' onclick='del("+obj.id+")'><i class='layui-icon'>&#xe640;</i>删除</button>";
		}
	}else{
		str += "<button class='layui-btn layui-btn-sm layui-btn-danger userpower' userpower='delete'  href='#' onclick='del("+obj.id+")'><i class='layui-icon'>&#xe640;</i>删除</button>";
	}
	return str;
}


function loadDict(type,form){
	$.ajax({
		type : 'get',
		url : domain + "/admin/dict/list",
		data : {
			type : type,
			token : token
		},
		async: false,
		success : function(result) {
			if (result.code == 200) {
			    setDict("local_"+type,result.data)
				loadDictAfter(result,form,type); 
			} else {
				err(result.message);
			}
		},
		error : function(error) {
			err('请求错误,请稍后重试!');
		}
	})
}

function loadDictByTable(p1,p2,p3,p4,form){
	$.ajax({
		type : 'get',
		url : domain + "/admin/common/list",
		data : {
			c1 : p1,
			c2 : p2,
			c3 : p3,
			t : p4,
			token : token
		},
		async: false,
		success : function(result) {
			if (result.code == 200) {
			    setDict("local_"+p4,result.data)
				loadDictAfter(result,form,p4); 
			} else {
				err(result.message);
			}
		},
		error : function(error) {
			err('请求错误,请稍后重试!');
		}
	})
}

function loadDictBySearch(p1,p2,p3,p4,form,p5){
	$.ajax({
		type : 'get',
		url : domain + "/admin/common/list2",
		data : {
			c1 : p1,
			c2 : p2,
			c3 : p3,
			t : p4,
			w : p5,
			token : token
		},
		async: false,
		success : function(result) {
			if (result.code == 200) {
			    setDict("local_"+p4,result.data)
				loadDictAfter(result,form,p4); 
			} else {
				err(result.message);
			}
		},
		error : function(error) {
			err('请求错误,请稍后重试!');
		}
	})
}

function showLoading(){
	var index = layer.load(1,{
		shade:[0.5,'#fff']
	});
	return index;
}

function contains(str,target){
	return str.indexOf(target) != -1;
}

//列表汉化
function trans(type,val){
	var data = getDict(type);
	for(var i=0;i<data.length;i++){
		if(data[i].val == val){
		  return data[i].label;
		}
	}
	return "";
}

function setDict(name,value){
	localStorage.setItem(name,JSON.stringify(value));
}

function getDict(name){
   return JSON.parse(localStorage.getItem("local_"+name));
}