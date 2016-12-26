/**
 * @Title 运单申报管理
 * @Description
 * @author
 * 修改记录
 * 修改后版本      	修改人           	修改时间            	修改内容
 * 2015-10-22     	Alan.Li      	2016-01-25        	create
 */
var ${tableInfo.upperCamelTabName}Query = function() {
	
	var mmGrid = null;
	
	return {
		/**
		 * 初始化方法
		 */
		init: function() {
			
			//初始化表单
			PUI.plugin.init({}, $("#${tableInfo.camelTabName}SearchForm"));
			//grid数据模型
			var cols = [
			             { title:'客户名称', name:'distributionOrderName' ,width:100, align:'center', sortable: true, type: 'String'},
			             { title:'运单号', name:'distributionOrderNo' ,width:150, align:'center', sortable: true, type: 'String'},
			             { title:'运单状态', name:'distributionOrderCode' ,width:100, align:'center', sortable: true, type: 'String'},
			             { title:'收件人', name:'telephone' ,width:100, align:'center', sortable: true, type: 'String'},
			             { title:'证件类型', name:'address' ,width:100, align:'center', sortable: true, type: 'String'},
			             { title:'收件人证件号码', name:'industryTypeName' ,width:100, align:'center', sortable: true, type: 'String'},
			             { title:'收件地址', name:'distributionOrderStatusName' ,width:70, align:'center', sortable: true, type: 'String'},
			             { title:'毛重', name:'creator' ,width:70, align:'center', sortable: true, type: 'String',hidden:true},
			             { title:'净重', name:'creator' ,width:70, align:'center', sortable: true, type: 'String',hidden:true},
			             { title:'进出口标识', name:'creator' ,width:70, align:'center', sortable: true, type: 'String',hidden:true},
			             { title:'创建时间', name:'createTime' ,width:120, align:'center', sortable: true, type: 'String',hidden:true,
			            	 renderer:function(val,item,rowIndex){
			            		 return date2format(val,"yyyy-MM-dd hh:mm:ss");
			             	 }}
			         ];
			
			mmGrid = $("#mmg").mmGrid({
				height : getAutoHeightByMmGrid($(".page-content")),
				width : 'auto', // 表格宽度。参数为'auto'和以'%'结尾,表格可在调整窗口尺寸时根据父元素调整尺寸。
				cols : cols, //数据模型,用于表头分组，目前只支持两层。
				url : 'cdci/${tableInfo.camelTabName}/page.shtml', // AJAX请求数据的地址。
		     	params : $("#distributionOrderSearchForm").serialize(), // AJAX请求的参数。
				method : 'post',
				nowrap : true, // 表格显示的数据超出列宽时是否换行。
				checkCol : true, // 表格显示checkbox
				autoLoad : false, // 是否表格准备好时加载数据。
				sortStatus : 'desc',
				fullWidthRows : true, // true:表格第一次加载数据时列伸展,自动充满表格。
				cache : false,
				multiSelect : true,
				plugins : [$('#pg').mmPaginator({})]
			});
		},
		
		/**
		 * 根据查询条件，进行查询
		 */
		queryHandler: function() {
			//校验表单
			if(!${tableInfo.upperCamelTabName}Query.validateForm()){
				return;
			}
			
			mmGrid.load($("#${tableInfo.camelTabName}SearchForm").serialize());
		},
		
		/**
		 * 校验表单
		 */
		validateForm : function() {
			
			var isValid = true;
			//
			
			return isValid;
		},
		
		
		/**
		 * 清空查询表单form
		 */
		resetHandler: function(){
			PUI.util.resetForm($("#${tableInfo.camelTabName}SearchForm"));
		},
		
		/**
		 * 新增
		 */
		addHandler: function() {
			$("#${tableInfo.camelTabName}_add_content").html(PUI.String.format($("#user-detail-template").html(), {}));
			${tableInfo.upperCamelTabName}Details.init(null);
			$("#tabwin_add").popup();
		},
		
		/**
		 * 修改
		 */
		updateHandler: function() {
			var selectedRowCount = mmGrid.selectedRowsIndex().length;
			if (selectedRowCount < 1){
				PUI.MessageBox.alert("请先选中一条记录");
				return;
			}
			if (selectedRowCount > 1){
				PUI.MessageBox.alert("每次只能选择一条记录进行修改");
				return;
			}
			var selectedItem = mmGrid.selectedRows()[0];
			var rowIndex = mmGrid.selectedRowsIndex;
			// 获取模板
			var templateHtml = PUI.String.format($("#user-detail-template").html(),$.extend(selectedItem, {rowIndex: rowIndex}));
			$("#${tableInfo.camelTabName}_add_content").html(templateHtml);
			${tableInfo.upperCamelTabName}Details.init(selectedItem);
			$("#tabwin_add").popup();
		},
		
		/**
		 * 删除
		 */
		deleteHandler: function() {
			
			if (mmGrid.selectedRowsIndex().length < 1){
				PUI.MessageBox.alert("请至少选中一条记录");
				return;
			}
			
			var params = [];
			var items = mmGrid.selectedRows();
			for (var i = 0;i < items.length; i++) {
				params.push({name: "ids", value: items[i].${tableInfo.camelTabName}Id});
			}
			
			PUI.MessageBox.show({
			    title: "删除信息",
			    content: "是否删除？",
			    type: "confirm",
			    buttons: [{ value: "是" },{ value: "否" }],
			    success: function (result) {
			        if (result === "是") {
			        	$.post("cdci/${tableInfo.camelTabName}/deleteByIds.shtml", params, function(data) {
							if(data['result'] == true){
								PUI.MessageBox.info("操作成功！", null, {autoClose: true, timeOut: 997, afterClose: function() {
									${tableInfo.upperCamelTabName}Query.queryHandler();
								}});
							} else{
								PUI.MessageBox.error("操作失败");
							}
			        	}, "json");
			        }
			    }
			});
		}
	};
}();

$().ready(function() {
	${tableInfo.upperCamelTabName}Query.init();
});
