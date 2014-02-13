
$(function() {
	$('#dg').datagrid({
	    url:"../../department/queryDepartment?random"+parseInt(Math.random()*100000),
	    method:"POST",
	    showFooter: true,
	    loadMsg:"请稍等.....",
	    rownumbers:true,
	    singleSelect:true,
	    checkOnSelect:true,
	    queryParams:{},
	    idField:'departmentid',
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
	    	 	  {field:'departmentid',title:'部门ID',width:$(this).width()*0.15,hidden:true},
	    	 	  {field:'orgid',title:'组织ID',width:$(this).width()*0.15,hidden:true},
	    	 	  {field:'pdepartmentid',title:'上级部门ID',width:$(this).width()*0.15,hidden:true},
	    	 	  {field:'departmentname',title:'部门名称',width:$(this).width()*0.15},
	    	 	  {field:'orgname',title:'公司名称',width:$(this).width()*0.15},
	    	 	  {field:'pdepartmentname',title:'上级机关名称',width:$(this).width()*0.15}
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
				departmentname: $('#departmentname').val()
			}).datagrid("clearSelections");
		});
	
	function chosenAjaxData(id){
			var contents = "";
			$.ajax({
			   type: "POST",
			   url:"../../department/queryUpperDepartment?random"+parseInt(Math.random()*100000),
	           dataType:'json',
	           async:false, 
	           data:{
					page:1,
					rows:99999
				},
			   success: function(data){
					var selectObj = $('#'+id); 
					for(var i=0;i<data.rows.length;i++){
						contents = contents +"<option value='"+data.rows[i].departmentid+"'>"+data.rows[i].departmentname+"</option>";
	                } 
					selectObj.append(contents);
			   }
			});
	}
	
	
	
	$("#add").click(
		function () {
			$('#myModal').modal({
  				keyboard: false
			});
			
			$("#dg").datagrid("clearSelections");
			$('#modal_form')[0].reset();
			
			$('#orgidModal').empty();
			chosenAjaxData('orgidModal');
			$('#orgidModal').chosen().trigger("chosen:updated");
			$('#orgidModal_chosen .chosen-single > span').empty().append("<span>请选择部门上级组织</span>");
			
			
	});
	
	
	$("#modify").click(
		function () {
			var row = $("#dg").datagrid("getSelected");
			
			if(row == null || row == "undefined"){
				$.messager.alert("操作提示", "请选择一行记录","info");
			}
			else{
		    	 	  $("#departmentidModal").val(row.departmentid);
		    	 	  $("#orgidModal").val(row.orgid);
		    	 	  $("#departmentnameModal").val(row.departmentname);
				$('#myModal').modal({
	  				keyboard: false
				});
				
				  $('#orgidModal').empty().append("<option value='"+row.departmentid+"'>"+row.departmentname+"</option>");
				  chosenAjaxData('orgidModal');
				  $('#orgidModal').chosen().trigger("chosen:updated")
				
				
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
				var departmentid = $("#departmentidModal").val();
				var upperdepartmentid = $("#orgidModal").val();
				var departmentname = $("#departmentnameModal").val();
				
				$.ajax({
				   type: "POST",
				   url: "../../department/addDepartment?random"+parseInt(Math.random()*100000),
				   data: {
							departmentid:departmentid,
							upperdepartmentid:upperdepartmentid,
							departmentname:departmentname
				   },
				   success: function(msg){
				     $.messager.alert("操作提示", $.parseJSON(msg),"info");
				     $('#dg').datagrid('reload');
				     $("#dg").datagrid("clearSelections");
				   }
				});
			}
			else{
				var departmentid = row.departmentid;
				var departmentid = $("#departmentidModal").val();
				var upperdepartmentid = $("#orgidModal").val();
				var departmentname = $("#departmentnameModal").val();
				$.ajax({
				   type: "POST",
				   url: "../../department/modifyDepartment?random"+parseInt(Math.random()*100000),
				   data: {
							departmentid:departmentid,
							upperdepartmentid:upperdepartmentid,
							departmentname:departmentname
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
						   url: "../../department/deleteDepartment?random"+parseInt(Math.random()*100000),
						   data: {
								departmentid:row.departmentid
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

