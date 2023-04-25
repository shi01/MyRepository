//分页数
var pageNum = 1;
var limitCount = 10;

/*一次全部查出，前端静态分页*/
function appendList(data, obj) {
	$("#dataList").empty();
	//无数据直接返回
	if (obj.pages == 0) {
		return;
	}
	var str = "";
	var page_start = (obj.curr - 1) * obj.limit;
	var page_end = 0;

	if (obj.pages == 1) {
		//总数 <= 每页条数  取[0,count)
		page_end = obj.count;
	} else {
		if (obj.limit == 1) {
			//每页一条
			page_end = start + 1;
		} else {
			if (obj.count % obj.limit == 0) {
				//除尽
				page_end = page_start + obj.limit;
			} else {
				if (obj.curr == obj.pages) {
					//末页
					page_end = (obj.curr - 1) * obj.limit + obj.count % obj.limit;
				} else {
					page_end = (obj.curr - 1) * obj.limit + obj.curr * obj.limit;
				}
			}
		}
	}
	//console.log(page_start + "," + page_end)
	$("#dataList").append(spellData2(str, page_start, page_end, data));
}

/*首次查询*/
function findList(url, id) {
	var data = $("#" + id).serialize() + "&pageNum=1&pageSize=" + limitCount  + "&token=" + token;
	var laypage = layui.laypage;
	$.get(url, data, function(res) {
		if(res.code == 200){
			laypage.render({
				elem: 'layuipage',
				count: res.data.total, //数据总数，从服务端得到
				limit: limitCount,
				groups: 3,
				layout: ['prev', 'page', 'next', 'count'],
				jump: function(obj, first) {
					if (first) {
						fillList(res.data.list);
					} else {
						list(obj, url,id);
					}
				}
			});
		} else if(res.code == 401){
			errOut(res.message)
		} else{
			err(res.message);
		}
	});
}

/* 实时查询 */
function list(obj, url, id) {
	var data = $("#"+id).serialize() + "&pageNum=" + obj.curr + "&pageSize=" + obj.limit + "&token=" + token;
	$.ajax({
		type: 'get',
		url: url,
		data: data,
		async: false,
		success: function(result) {
			if (result.code == 200) {
				fillList(result.data.list);
			} else if(result.code == 401){
				errOut(result.message)
			} else {
				err(result.message, { icon: 0 });
			}
		},
		error: function(error) {
			err('请求错误,请稍后重试!');
		}
	})
}

/* 填充数据 */
function fillList(data) {
	$("#dataList").empty();
	$("#dataList").append(spellData(data));
//	initOther();
}