
$(function() {
	$('#dg').datagrid({
	    url:"../../notice/queryNotice?random"+parseInt(Math.random()*100000),
	    method:"POST",
	    showFooter: true,
	    loadMsg:"请稍等.....",
	    rownumbers:true,
	    singleSelect:true,
	    checkOnSelect:true,
	    queryParams:{},
	    idField:'noticeid',
		 width:'100%',    
		 height:'auto',
		 fitColumns:true,
		 pagination:true,
	    pageNumber:1,
	    pageSize:10,
	    pageList:[10,20,30,40,50],
	    beforePageText: '第',//页数文本框前显示的汉字  
        afterPageText: '页    共 {pages} 页',  
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
	    columns:[[
	    	 	  {field:'id',title:'主键',width:$(this).width()*0.15},
	    	 	  {field:'authorid',title:'发布人ID',width:$(this).width()*0.15},
	    	 	  {field:'authorname',title:'发布人姓名',width:$(this).width()*0.15},
	    	 	  {field:'type',title:'类型',width:$(this).width()*0.15},
	    	 	  {field:'title',title:'标题',width:$(this).width()*0.15},
	    	 	  {field:'content',title:'内容',width:$(this).width()*0.15},
	    	 	  {field:'createtime',title:'创建时间',width:$(this).width()*0.15}
	    ]],
	     onLoadError:function(){
                  alert('','加载数据失败！');
         },
         onLoadSuccess:function(){
        	 $('#dg').datagrid('options').pagination=true;
         }
	});
	
	$("#query").click(
		function () {
			$('#dg').datagrid('reload',{
				noticename: $('#noticename').val()
			}).datagrid("clearSelections");
		});
	
	
	
	$("#add").click(
		function () {
			$('#myModal').modal({
  				keyboard: false
			});
			
			$("#dg").datagrid("clearSelections");
			$('#modal_form')[0].reset();
			
	});
	
	
	$("#modify").click(
		function () {
			var row = $("#dg").datagrid("getSelected");
			
			if(row == null || row == "undefined"){
				$.messager.alert("操作提示", "请选择一行记录","info");
			}
			else{
		    	 	  $("#idModal").val(row.id);
		    	 	  $("#authoridModal").val(row.authorid);
		    	 	  $("#authornameModal").val(row.authorname);
		    	 	  $("#typeModal").val(row.type);
		    	 	  $("#titleModal").val(row.title);
		    	 	  $("#contentModal").val(row.content);
		    	 	  $("#createtimeModal").val(row.createtime);
				$('#myModal').modal({
	  				keyboard: false
				});
			}
	});
	
	/**
	 * 新增或者更新
	 */
	$("#add_modify_modal").click(
		function (){
			var row = $("#dg").datagrid("getSelected");
			
			//新增
			if(row == null ||  row == "undefined"){
				var id = $("#idModal").val();
				var authorid = $("#authoridModal").val();
				var authorname = $("#authornameModal").val();
				var type = $("#typeModal").val();
				var title = $("#titleModal").val();
				var content = $("#contentModal").val();
				var createtime = $("#createtimeModal").val();
				
				$.ajax({
				   type: "POST",
				   url: "../../notice/addNotice?random"+parseInt(Math.random()*100000),
				   data: {
							id:id,
							authorid:authorid,
							authorname:authorname,
							type:type,
							title:title,
							content:content,
							createtime:createtime
				   },
				   success: function(msg){
				     $.messager.alert("操作提示", $.parseJSON(msg),"info");
				     $('#dg').datagrid('reload');
				     $("#dg").datagrid("clearSelections");
				   }
				});
			}
			else{
				var noticeid = row.noticeid;
				var id = $("#idModal").val();
				var authorid = $("#authoridModal").val();
				var authorname = $("#authornameModal").val();
				var type = $("#typeModal").val();
				var title = $("#titleModal").val();
				var content = $("#contentModal").val();
				var createtime = $("#createtimeModal").val();
				$.ajax({
				   type: "POST",
				   url: "../../notice/modifyNotice?random"+parseInt(Math.random()*100000),
				   data: {
							id:id,
							authorid:authorid,
							authorname:authorname,
							type:type,
							title:title,
							content:content,
							createtime:createtime
				   },
				   success: function(msg){
				     $('#dg').datagrid('reload');
				     $("#dg").datagrid("clearSelections");
				     $('#myModal').modal('hide');
				     $.messager.alert("操作提示", $.parseJSON(msg),"info");
				   }
				});
			}
		}
	);
	
	
	
	
	$("#delete").click(
		function () {
			var row = $("#dg").datagrid("getSelected");
			if(row == null || row == "undefined"){
				$.messager.alert("操作提示", "请选择一行记录","info");
			}
			else{
				 $.messager.confirm("操作提示", "您确定要执行操作吗？", function (data) {
		         	if (data) {
		         		$.ajax({
						   type: "POST",
						   url: "../../notice/deleteNotice?random"+parseInt(Math.random()*100000),
						   data: {
								noticeid:row.noticeid
						   },
						   success: function(msg){
						     $.messager.alert("操作提示", $.parseJSON(msg),"info");
						     $('#dg').datagrid('reload');
						     $("#dg").datagrid("clearSelections");
						   }
						});
		            }
		            else {
		                return false;
		            }
		         });
			}
			
	});
	
	
});

