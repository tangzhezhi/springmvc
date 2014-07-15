
$(function() {
	$('#dg').datagrid({
	    url:"../../organization/queryOrganization?random"+parseInt(Math.random()*100000),
	    method:"POST",
	    showFooter: true,
	    loadMsg:"请稍等.....",
	    rownumbers:true,
	    singleSelect:true,
	    checkOnSelect:true,
	    queryParams:{},
	    idField:'organizationid',
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
	    	 	  {field:'orgid',title:'组织ID',width:$(this).width()*0.15,hidden:true},
	    	 	  {field:'orgname',title:'名称',width:$(this).width()*0.15},
	    	 	  {field:'orgaddress',title:'地址',width:$(this).width()*0.15},
	    	 	  {field:'orgcontact',title:'联系人',width:$(this).width()*0.15},
	    	 	  {field:'orgphone',title:'电话',width:$(this).width()*0.15},
	    	 	  {field:'orgemails',title:'电邮地址',width:$(this).width()*0.15}
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
				organizationName: $('#organizationName').val()
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
		    	 	  $("#orgidModal").val(row.orgid);
		    	 	  $("#orgnameModal").val(row.orgname);
		    	 	  $("#orgaddressModal").val(row.orgaddress);
		    	 	  $("#orgcontactModal").val(row.orgcontact);
		    	 	  $("#orgphoneModal").val(row.orgphone);
		    	 	  $("#orgemailsModal").val(row.orgemails);
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
				var orgid = $("#orgidModal").val();
				var orgname = $("#orgnameModal").val();
				var orgaddress = $("#orgaddressModal").val();
				var orgcontact = $("#orgcontactModal").val();
				var orgphone = $("#orgphoneModal").val();
				var orgemails = $("#orgemailsModal").val();
				
				$.ajax({
				   type: "POST",
				   url: "../../organization/addOrganization?random"+parseInt(Math.random()*100000),
				   data: {
							orgid:orgid,
							orgname:orgname,
							orgaddress:orgaddress,
							orgcontact:orgcontact,
							orgphone:orgphone,
							orgemails:orgemails
				   },
				   success: function(msg){
				     $.messager.alert("操作提示", $.parseJSON(msg),"info");
				     $('#dg').datagrid('reload');
				     $("#dg").datagrid("clearSelections");
				   },
				   error : function (x){
					  if(x.status==401){
						   $.messager.alert("提示", "你没有权限","info");
					  }
				   }
				});
			}
			else{
				var organizationid = row.organizationid;
				var orgid = $("#orgidModal").val();
				var orgname = $("#orgnameModal").val();
				var orgaddress = $("#orgaddressModal").val();
				var orgcontact = $("#orgcontactModal").val();
				var orgphone = $("#orgphoneModal").val();
				var orgemails = $("#orgemailsModal").val();
				$.ajax({
				   type: "POST",
				   url: "../../organization/modifyOrganization?random"+parseInt(Math.random()*100000),
				   data: {
							orgid:orgid,
							orgname:orgname,
							orgaddress:orgaddress,
							orgcontact:orgcontact,
							orgphone:orgphone,
							orgemails:orgemails
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
						   url: "../../organization/deleteOrganization?random"+parseInt(Math.random()*100000),
						   data: {
								organizationid:row.orgid
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

