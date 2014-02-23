$(function() {
    
	$('#dg').datagrid({
	    url:"../../resource/queryResource?random"+parseInt(Math.random()*100000),
	    method:"POST",
	    showFooter: true,
	    loadMsg:"请稍等.....",
	    rownumbers:true,
	    singleSelect:true,
	    checkOnSelect:true,
	    queryParams:{},
	    idField:'resourceId',
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
	        {field:'resourceId',title:'资源ID',width:$(this).width()*0.15,hidden:true},
	        {field:'resourceName',title:'资源名' ,align:'left',width:$(this).width()*0.15},
	       	{field:'resourceCode',title:'资源代码' ,width:$(this).width()*0.15},
	        {field:'resourceUrl',title:'URL' ,width:$(this).width()*0.15},
	        {field:'resourceParentId',title:'资源父ID' ,align:'right',width:$(this).width()*0.15},
	        {field:'resourceSort',title:'资源排序',width:$(this).width()*0.15 }
	    ]],
	     onLoadError:function(){
                  alert('','加载数据失败！');
         },
         onLoadSuccess:function(){
        	 $('#dg').datagrid('options').pagination=true;
         }
	});
	
	
	
	function chosenAjaxData(id){
			var contents = "";
			$.ajax({
			   type: "POST",
			   url:"../../resource/queryResource?random"+parseInt(Math.random()*100000),
	           dataType:'json',
	           async:false, 
	           data:{
					page:1,
					rows:99999
				},
			   success: function(data){
					var selectObj = $('#'+id); 
					for(var i=0;i<data.rows.length;i++){
						contents = contents +"<option value='"+data.rows[i].resourceId+"'>"+data.rows[i].resourceName+"</option>";
	                } 
					selectObj.append(contents);
			   }
			});
	}
	
	
	
	$("#query").click(
		function () {
			$('#dg').datagrid('reload',{
				resourceName: $('#resourceName').val()
			});
		});
	
	$("#add").click(
		function () {
			$('#myModal').modal({
  				show:true,
  				 backdrop: 'static',
				keyboard: false
			});
			
			$("#dg").datagrid("getSelected") == null;
			$('#modal_form')[0].reset();
			
			$('#resourcetypeModal').chosen().trigger("chosen:updated");
			$('#resourceurlparentidModal').empty();
			chosenAjaxData('resourceurlparentidModal');
			$('#resourceurlparentidModal').chosen().trigger("chosen:updated");
			$('#resourceurlparentidModal_chosen .chosen-single > span').empty().append("<span>请选择上级资源</span>");
			$('#resourceurlparentidModal_chosen').removeAttr("style"); 
	});
	
	
	$("#modify").click(
		function () {
			var row = $("#dg").datagrid("getSelected");
			
			if(row == null || row == "undefined"){
				$.messager.alert("操作提示", "请选择一行记录","info");
			}
			else{
				$("#resourcenameModal").val(row.resourceName);
				$("#resourcecodeModal").val(row.resourceCode);
				$("#resourceurlModal").val(row.resourceUrl);
				$("#resourceurlparentidModal").val(row.resourceParentId);
				
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
				var name = $("#resourcenameModal").val();
				var code = $("#resourcecodeModal").val();
				var url = $("#resourceurlModal").val();
				var pid = $("#resourceurlparentidModal").val();
				var type = $("#resourcetypeModal").val();
				$.ajax({
				   type: "POST",
				   url: "../../resource/addResource?random"+parseInt(Math.random()*100000),
				   data: {
						resourceName:name,
						resourceCode:code,
						resourceParentId:pid,
						resourceSort:"",
						resourceType:type,
						resourceUrl:url,
				   },
				   success: function(msg){
				     $.messager.alert("操作提示", $.parseJSON(msg),"info");
				     $('#dg').datagrid('reload');
				   }
				});
			}
			else{
				var resourceId = row.resourceId;
				var name = $("#resourcenameModal").val();
				var code = $("#resourcecodeModal").val();
				var url = $("#resourceurlModal").val();
				var pid = $("#resourceurlparentidModal").val();
				var type = $("#resourcetypeModal").val();
				$.ajax({
				   type: "POST",
				   url: "../../resource/modifyResource?random"+parseInt(Math.random()*100000),
				   data: {
						resourceId:resourceId,
						resourceName:name,
						resourceCode:code,
						resourceParentId:pid,
						resourceSort:"",
						resourceType:type,
						resourceUrl:url,
				   },
				   success: function(msg){
				     $.messager.alert("操作提示", $.parseJSON(msg),"info");
				     $('#dg').datagrid('reload');
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
						   url: "../../resource/deleteResource?random"+parseInt(Math.random()*100000),
						   data: {
								resourceId:row.resourceId
						   },
						   success: function(msg){
						     $.messager.alert("操作提示", $.parseJSON(msg),"info");
						     $('#dg').datagrid('reload');
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

