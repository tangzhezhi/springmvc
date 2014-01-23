
$(function() {
	$('#dg').datagrid({
	    url:"../../role/queryRole?random"+parseInt(Math.random()*100000),
	    method:"POST",
	    showFooter: true,
	    loadMsg:"请稍等.....",
	    rownumbers:true,
	    singleSelect:true,
	    checkOnSelect:true,
	    queryParams:{},
	    idField:'roleId',
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
	    	 	  {field:'roleId',title:'角色ID',width:$(this).width()*0.15,hidden:true},
	    	 	  {field:'orgId',title:'组织ID',width:$(this).width()*0.15,hidden:true},
	    	 	  {field:'orgName',title:'组织名称',width:$(this).width()*0.15},
	    	 	  {field:'roleName',title:'角色名称',width:$(this).width()*0.15}
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
				rolename: $('#rolename').val()
			}).datagrid("clearSelections");
		});
	
	
//	$('#myModal').on('hide.bs.modal', function () {
//		$('#orgidModal').empty().append("<option value=''>请选择组织名称</option>");
//		$('.chosen-single > span').text('请选择组织名称').trigger("chosen:updated");
//	});
	
	
	function chosenAjaxData(id){
			var contents = "";
			$.ajax({
			   type: "POST",
			   url:"../../organization/queryOrganization?random"+parseInt(Math.random()*100000),
	           dataType:'json',
	           async:false, 
	           data:{
					page:1,
					rows:99999
				},
			   success: function(data){
					var selectObj = $('#'+id); 
					for(var i=0;i<data.rows.length;i++){
						contents = contents +"<option value='"+data.rows[i].orgid+"'>"+data.rows[i].orgname+"</option>";
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
			$('#orgidModal_chosen .chosen-single > span').empty().append("<span>请选择组织</span>");
			
	});
	
	
	$("#modify").click(
		function () {
			var row = $("#dg").datagrid("getSelected");
			
			if(row == null || row == "undefined"){
				$.messager.alert("操作提示", "请选择一行记录","info");
			}
			else{
	    	 	  $("#roleidModal").val(row.roleId);
	    	 	  $("#orgidModal").val(row.orgId);
	    	 	  $("#rolenameModal").val(row.roleName);
				  $('#myModal').modal({
		  				keyboard: false
				  });
				  $('#orgidModal').empty().append("<option value='"+row.orgId+"'>"+row.orgName+"</option>");
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
				var roleid = $("#roleidModal").val();
				var orgid = $("#orgidModal").val();
				var rolename = $("#rolenameModal").val();
				
				$.ajax({
				   type: "POST",
				   url: "../../role/addRole?random"+parseInt(Math.random()*100000),
				   data: {
							orgid:orgid,
							rolename:rolename
				   },
				   success: function(msg){
				     $.messager.alert("操作提示", $.parseJSON(msg),"info");
				     $('#dg').datagrid('reload');
				     $("#dg").datagrid("clearSelections");
				   }
				});
			}
			else{
				var roleid = row.roleId;
				var orgid = $("#orgidModal").val();
				var rolename = $("#rolenameModal").val();
				$.ajax({
				   type: "POST",
				   url: "../../role/modifyRole?random"+parseInt(Math.random()*100000),
				   data: {
							roleid:roleid,
							orgid:orgid,
							rolename:rolename
				   },
				   success: function(msg){
				     $.messager.alert("操作提示", $.parseJSON(msg),"info");
				     $('#dg').datagrid('reload');
				     $("#dg").datagrid("clearSelections");
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
						   url: "../../role/deleteRole?random"+parseInt(Math.random()*100000),
						   data: {
								roleid:row.roleId
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
	
	
	
	$("#roleAuth").click(
		function () {
			var row = $("#dg").datagrid("getSelected");
			if(row == null || row == "undefined"){
				$.messager.alert("操作提示", "请选择一行记录","info");
			}
			else{
					$('#myModalTree').modal({
		  				keyboard: false
					});
					
					var setting = {
						check: {
							enable: true
						},
						data: {
							simpleData: {
							 	idKey:"id",
		                    	pIdKey:"pid",
								enable: true
							}
						}
					};
					
					
					var zNodes = "";
					$.ajax({
					   type: "POST",
					   url:"../../role/queryRoleTree?random"+parseInt(Math.random()*100000),
					   data:{appointroleid:row.roleId},
			           dataType:'json',
			           async:false, 
					   success: function(data){
							zNodes = data.tree;
							$.fn.zTree.init($("#treeDemo"), setting, zNodes);
					   }
					});
			}
	});
	
	
	
	$("#add_modify_modaltree").click(
		function () {
			var row = $("#dg").datagrid("getSelected");
			var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
			var nodes = treeObj.getCheckedNodes(true);
			
			var checkedid = "";
			for(var i = 0 ; i < nodes.length;i++){
				checkedid =checkedid+nodes[i].id+",";
			}
			checkedid = checkedid.substr(0,checkedid.length-1);
			
			$.ajax({
			   type: "POST",
			   url: "../../role/saveRoleAuth?random"+parseInt(Math.random()*100000),
			   data: {
						roleid:row.roleId,
						resources:checkedid
			   },
			   success: function(msg){
			     $.messager.alert("操作提示", $.parseJSON(msg),"info");
			   }
			});
			
			
			
	});
	
});

