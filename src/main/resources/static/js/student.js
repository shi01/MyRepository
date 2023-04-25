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
	loadDict('sex',form);
});

var domain = "";
$(document).ready(function() {
	getMenus();
	search();
	initHead();
});

function initOther(){}

function loadDictAfter(result,form,type){
	if(type=='sex'){
		$.each(result.data,function(index,item){
		    $('#sex').append(new Option(item.label,item.val));//往下拉菜单里添加元素
		})
	}
	form.render(); 
}

function add(){
	layer.open({
		title: ['学生信息新建', 'font-size:16px;'],
		type:2,
		content: 'add.html',
		area: ['700px','250px'],
		closeBtn: 1,
		cancel: function(index, layero){ 
			window.parent.location.reload();
			layer.close(index);
		}
	});     
}

function edit(id){
	layer.open({
		  title: ['学生信息编辑', 'font-size:16px;'],
		  type:2,
		  content: 'edit.html?id='+id,
		  area: ['700px','250px'],
		  closeBtn: 1,
		  cancel: function(index, layero){ 
			  window.parent.location.reload();
			  layer.close(index);
		  } 
	});     
}

function view(id){
	layer.open({
		  title: ['学生信息详情', 'font-size:16px;'],
		  type:2,
		  content: 'view.html?id='+id,
		  area: ['700px','250px'],
		  closeBtn: 1,
		  cancel: function(index, layero){ 
			  window.parent.location.reload();
			  layer.close(index);
		  } 
	});     
}

function search(){
	var url = "/admin/student/list";
	findList(url,'searchForm');
}

function spellData(data){
	var str = "";
	var editOrView = getListViewPower();
	for (var i = 0; i < data.length; i++) {
		str += "<tr>"
			+"<td style='text-align: center;'>"+(i+1)+"</td>"
+"<td style='text-align: center;'><a style='color: blue;' href='#' onclick='"+editOrView+"("+data[i].id+")'>"+filterNull(data[i].studentName)+"</a></td>"
+"<td style='text-align: center;'>"+trans('sex',filterNull(data[i].sex))+"</td>"
+"<td style='text-align: center;'>"+filterNull(data[i].workNum)+"</td>"
+"<td style='text-align: center;'>"+getListBtnPower(data[i])+"</td>"

			+"</tr>";
	}
	return str;
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
		url : domain + "/admin/student/delete",
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
