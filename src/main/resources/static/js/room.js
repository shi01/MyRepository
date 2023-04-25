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
	  elem: '#getTime', //指定元素
	  type: 'datetime'
	});
	var form = layui.form;
	//select + lay-search
	/**
    <select id="state" name="state"  lay-verify="required" class="layui-input">
        <option value>---请选择---</option>
    </select>	
	*/
	loadDict('room_type',form);
	loadDictByTable('id','floor_name','id','hotel_floor',form);
	loadDict('bed_type',form);
	loadDict('home_type',form);
});

var domain = "";
$(document).ready(function() {
	getMenus();
	search();
	initHead();
});

function initOther(){}

function loadDictAfter(result,form,type){
	if(type=='room_type'){
		$.each(result.data,function(index,item){
		    $('#state').append(new Option(item.label,item.val));//往下拉菜单里添加元素
		})
	}
	if(type=='home_type'){
		$.each(result.data,function(index,item){
		    $('#type').append(new Option(item.label,item.val));//往下拉菜单里添加元素
		})
	}
	if(type=='bed_type'){
		$.each(result.data,function(index,item){
		    $('#bed').append(new Option(item.label,item.val));//往下拉菜单里添加元素
		})
	}
	if(type=='hotel_floor'){
		$.each(result.data,function(index,item){
		    $('#floorId').append(new Option(item.label,item.val));//往下拉菜单里添加元素
		})
	}
	form.render(); 
}

function add(){
	layer.open({
		title: ['房间信息新建', 'font-size:16px;'],
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
		  title: ['房间信息编辑', 'font-size:16px;'],
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
		  title: ['房间信息详情', 'font-size:16px;'],
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
	var url = "/admin/room/list";
	findList(url,'searchForm');
}

function spellData(data){
	var str = "";
	var editOrView = getListViewPower();
	for (var i = 0; i < data.length; i++) {
		str += "<tr>"
			+"<td style='text-align: center;'>"+(i+1)+"</td>"
+"<td style='text-align: center;'><a style='color: blue;' href='#' onclick='"+editOrView+"("+data[i].id+")'>"+filterNull(data[i].roomNum)+"</a></td>"
+"<td style='text-align: center;'>"+trans('home_type',filterNull(data[i].type))+"</td>"
+"<td style='text-align: center;'>"+trans('bed_type',filterNull(data[i].bed))+"</td>"
+"<td style='text-align: center;'>"+filterNull(data[i].price)+"</td>"
+"<td style='text-align: center;'>"+filterNull(data[i].vipPrice)+"</td>"
+"<td style='text-align: center;'>"+trans('hotel_floor',filterNull(data[i].floorId))+"</td>"
+changeColor(filterNull(data[i].state))
+"<td style='text-align: center;'>"+getListBtnPower(data[i])+"</td>"

			+"</tr>";
	}
	return str;
}

function changeColor(state){
	if(state==1){
		return "<td style='text-align: center;color:red;'>"+trans('room_type',state)+"</td>"
	}
	return "<td style='text-align: center;color:green;'>"+trans('room_type',state)+"</td>"
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
		url : domain + "/admin/room/delete",
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
