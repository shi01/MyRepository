layui.use(['element', 'layer', 'util', 'laypage', 'laydate', 'form'], function(){
	var element = layui.element
			,layer = layui.layer
			,util = layui.util
			,$ = layui.$;

	/* 分页 */
	var laypage = layui.laypage;
	var laydate = layui.laydate;
	//执行一个laydate实例
	laydate.render({
	  elem: '#startDate', //指定元素
	  type: 'date'
	});
	laydate.render({
	  elem: '#endDate', //指定元素
	  type: 'date'
	});
	var form = layui.form;
	//select + lay-search
	/**
    <select id="state" name="state"  lay-verify="required" class="layui-input">
        <option value>---请选择---</option>
    </select>	
	*/
	loadDict('order_type',form);
	loadDict('use_state',form);
	loadDictByTable('id','room_num','id','hotel_room',form);
});

var domain = "";
$(document).ready(function() {
	getMenus();
	search();
	initHead();
});

function initOther(){}

function loadDictAfter(result,form,type){
	if(type=='order_type'){
		$.each(result.data,function(index,item){
		    $('#orderType').append(new Option(item.label,item.val));//往下拉菜单里添加元素
		})
	}
	if(type=='use_state'){
		$.each(result.data,function(index,item){
		    $('#state').append(new Option(item.label,item.val));//往下拉菜单里添加元素
		})
	}
	form.render(); 
}

function add(){
	layer.open({
		title: ['订单信息新建', 'font-size:16px;'],
		type:2,
		content: 'add.html',
		area: ['700px','350px'],
		closeBtn: 1,
		cancel: function(index, layero){ 
			window.parent.location.reload();
			layer.close(index);
		}
	});     
}

function edit(id){
	layer.open({
		  title: ['订单信息编辑', 'font-size:16px;'],
		  type:2,
		  content: 'edit.html?id='+id,
		  area: ['700px','350px'],
		  closeBtn: 1,
		  cancel: function(index, layero){ 
			  window.parent.location.reload();
			  layer.close(index);
		  } 
	});     
}

function view(id){
	layer.open({
		  title: ['订单信息详情', 'font-size:16px;'],
		  type:2,
		  content: 'view.html?id='+id,
		  area: ['700px','350px'],
		  closeBtn: 1,
		  cancel: function(index, layero){ 
			  window.parent.location.reload();
			  layer.close(index);
		  } 
	});     
}

function search(){
	var url = "/admin/order/list";
	findList(url,'searchForm');
}

function spellData(data){
	var str = "";
	var editOrView = getListViewPower();
	for (var i = 0; i < data.length; i++) {
		str += "<tr>"
			+"<td style='text-align: center;'>"+(i+1)+"</td>"
+"<td style='text-align: center;'><a style='color: blue;' href='#' onclick='"+editOrView+"("+data[i].id+")'>"+subValid(filterNull(data[i].orderNum))+"</a></td>"
+"<td style='text-align: center;'>"+filterNull(data[i].startDate)+"</td>"
+"<td style='text-align: center;'>"+filterNull(data[i].endDate)+"</td>"
+"<td style='text-align: center;'>"+filterNull(data[i].customerName)+"</td>"
+"<td style='text-align: center;'>"+filterNull(data[i].phone)+"</td>"
+"<td style='text-align: center;'>"+trans('hotel_room',filterNull(data[i].roomId))+"</td>"
+"<td style='text-align: center;'>"+trans('order_type',filterNull(data[i].orderType))+"</td>"
+"<td style='text-align: center;'>"+trans('use_state',filterNull(data[i].state))+"</td>"
+"<td style='text-align: center;'>"+getSignOutBtn(data[i])+getListBtnPower(data[i])+"</td>"

			+"</tr>";
	}
	return str;
}

function getSignOutBtn(obj){
	if(obj.state==2){
		return "";
	}
	return "<button class=\"layui-btn layui-btn-sm userpower\" userpower=\"edit\" onclick=\"signOut("+obj.id+")\">→退房</button>";
}

function signOut(id){
	layer.confirm('确定要退房吗？', {
		btn : [ '确定', '关闭' ]
	}, function(index, layero) {
		$.ajax({
			type : 'post',
			url : domain + "/admin/order/state",
			data : {
				id : id,
				token : token
			},
			success : function(result) {
				if (result.code == 200) {
					layer.alert('退房成功', { icon: 1, closeBtn: 0 }, function (index) { 
						window.location.reload();
					});
				} else {
					err(result.message);
				}
			},
			error : function(error) {
				err('请求错误,请稍后重试!');
			}
		})
	}, function(index) {
	});
	return;
}

function del(id) {
	layer.confirm('确定要删除吗？', {
		btn : [ '确定', '关闭' ]
	}, function(index, layero) {
		delDate(id);
	}, function(index) {
	});
	return;
}

function delDate(id){
	$.ajax({
		type : 'post',
		url : domain + "/admin/order/delete",
		data : {
			id : id,
			token : token
		},
		success : function(result) {
			if (result.code == 200) {
				layer.alert('删除成功', { icon: 1, closeBtn: 0 }, function (index) { 
					window.location.reload();
				});
			} else {
				err(result.message);
			}
		},
		error : function(error) {
			err('请求错误,请稍后重试!');
		}
	})
}
