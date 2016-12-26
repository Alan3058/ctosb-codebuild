/**
 * 编辑
 * @param id
 * @author Alan
 * @date ${.now}
 */
function edit(id) {
	$.ajax({
		url : "subforum/get.shtml",
		// 数据发送方式
		type : "post",
		// 接受数据格式
		dataType : "json",
		// 要传递的数据
		data : {
			"id" : id
		},
		// 回调函数，接受服务器端返回给客户端的值，即result值
		success : function(result) {
			PopUtil.popForm('form',result);
//			for ( var key in result) {
//				// 文本框赋值
//				$("#" + key).val(result[key]);
//			}
		}
	});
}

/**
 * 复制
 * @param id
 * @author Alan
 * @date ${.now}
 */
function copy(id) {
	$.ajax({
		url : "subforum/get.shtml",
		// 数据发送方式
		type : "post",
		// 接受数据格式
		dataType : "json",
		// 要传递的数据
		data : {
			"id" : id
		},
		// 回调函数，接受服务器端返回给客户端的值，即result值
		success : function(result) {
			result["id"]="";
			PopUtil.popForm('form',result);
//			for ( var key in result) {
//				// 文本框赋值
//				$("#" + key).val(result[key]);
//				$("#id").val('');
//			}
		}
	});
}
/**
 * 删除
 * @param id
 * @param obj
 * @author Alan
 * @date ${.now}
 */
function del(id,obj) {
	$.ajax({
		url : "subforum/del.shtml",
		// 数据发送方式
		type : "post",
		// 接受数据格式
		dataType : "json",
		// 要传递的数据
		data : {
			"id" : id
		},
		// 回调函数，接受服务器端返回给客户端的值，即result值
		success : function(result) {
			for ( var key in result) {
				// 文本框赋值
				$(obj).parents('tr').remove();
				$('#form')[0].reset();
			}
		}
	});
}