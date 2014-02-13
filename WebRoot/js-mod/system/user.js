
$(function() {
	$('#dg').datagrid({
	    url:"../../user/queryUser?random"+parseInt(Math.random()*100000),
	    method:"POST",
	    showFooter: true,
	    loadMsg:"请稍等.....",
	    rownumbers:true,
	    singleSelect:true,
	    checkOnSelect:true,
	    queryParams:{},
	    idField:'userid',
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
	    	 	  {field:'userId',title:'用户ID',hidden:'true'},
	    	 	  {field:'userName',title:'用户名',width:$(this).width()*0.15},
	    	 	  {field:'orgId',title:'组织ID',hidden:'true'},
	    	 	  {field:'orgName',title:'组织名称',width:$(this).width()*0.15},
	    	 	  {field:'departmentId',title:'部门ID',hidden:'true'},
	    	 	  {field:'departmentName',title:'部门名称',width:$(this).width()*0.15},
	    	 	  {field:'roleId',title:'角色ID',hidden:'true'},
	    	 	  {field:'roleName',title:'角色名称',width:$(this).width()*0.15},
	    	 	  {field:'userPwd',title:'用户密码',width:$(this).width()*0.15},
	    	 	  {field:'userPhone',title:'用户手机',width:$(this).width()*0.15},
	    	 	  {field:'userEmail',title:'用户邮箱',width:$(this).width()*0.15},
	    	 	  {
	    	 		  field:'userType',
	    	 		  title:'用户类型', 
	    	 		  formatter:function(val,row){
			                if (val == '1'){
			                    return '管理员';
			                } else {
			                    return '普通用户';
			                }
		            	},
            		width:$(this).width()*0.15
            	   },
	    	 	  {field:'userLevel',title:'用户级别',width:$(this).width()*0.15},
	    	 	  {field:'userCredits',title:'用户积分',width:$(this).width()*0.15}
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
				userName: $('#username').val()
			});
		});
	
//	$('#myModal').on('hide.bs.modal', function () {
//		$('#orgidModal').empty().append("<option value=''>请选择用户组织</option>");
//		$('.chosen-single > span').text('请选择用户组织').trigger("chosen:updated");
//	});
	
//	$('#myModal').on('show.bs.modal', function () {
//		var contents = "";
//			$.ajax({
//			   type: "POST",
//			   url:"../../organization/queryOrganization?random"+parseInt(Math.random()*100000),
//	           dataType:'json',
//	           async:false, 
//	           data:{
//					page:1,
//					rows:99999
//				},
//			   success: function(data){
//					var selectObj = $("#orgidModal"); 
//					for(var i=0;i<data.rows.length;i++){
//						contents = contents +"<option value='"+data.rows[i].orgid+"'>"+data.rows[i].orgname+"</option>";
//	                } 
//					selectObj.append(contents);
//			   }
//			});
//			$('#orgidModal').chosen();
//			$('#usertypeModal').chosen();
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
	
	
	function chosenRoleAjaxData(id){
			var contents = "";
			$.ajax({
			   type: "POST",
			   url:"../../role/queryRole?random"+parseInt(Math.random()*100000),
	           dataType:'json',
	           async:false, 
	           data:{
					page:1,
					rows:99999
				},
			   success: function(data){
					var selectObj = $('#'+id); 
					for(var i=0;i<data.rows.length;i++){
						contents = contents +"<option value='"+data.rows[i].roleId+"'>"+data.rows[i].roleName+"</option>";
	                } 
					selectObj.append(contents);
			   }
			});
	}
	
	
	function chosenDeptAjaxData(id){
			var contents = "";
			$.ajax({
			   type: "POST",
			   url:"../../department/queryDepartment?random"+parseInt(Math.random()*100000),
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
				backdrop: 'static',
				show:true,
  				keyboard: false
			});
			$("#dg").datagrid("clearSelections");
			$('#modal_form')[0].reset();
			
			$('#orgidModal').empty();
			chosenAjaxData('orgidModal');
			$('#usertypeModal').chosen().trigger("chosen:updated");
			$('#orgidModal').chosen().trigger("chosen:updated");
			
			$('#roleidModal').empty();
			chosenRoleAjaxData('roleidModal');
			$('#roleidModal').chosen().trigger("chosen:updated");
			
			$('#departmentidModal').empty();
			chosenDeptAjaxData('departmentidModal');
			$('#departmentidModal').chosen().trigger("chosen:updated");
			
			$('#orgidModal_chosen .chosen-single > span').empty().append("<span>请选择组织</span>");
			$('#roleidModal_chosen .chosen-single > span').empty().append("<span>请选择角色</span>");
			$('#departmentidModal_chosen .chosen-single > span').empty().append("<span>请选择用户部门</span>");
			
			$('#orgidModal_chosen').removeAttr("style"); 
			$('#roleidModal_chosen').removeAttr("style"); 
			$('#departmentidModal_chosen').removeAttr("style"); 
	});
	
	$("#modify").click(
		function () {
			var row = $("#dg").datagrid("getSelected");
			
			if(row == null || row == "undefined"){
				$.messager.alert("操作提示", "请选择一行记录","info");
			}
			else{
		    	 	  $("#useridModal").val(row.userId);
		    	 	  $("#orgidModal").val(row.orgId);
		    	 	  $("#departmentidModal").val(row.departmentId);
		    	 	  $("#usernameModal").val(row.userName);
		    	 	  $("#userpwdModal").val(row.userPwd);
		    	 	  $("#userphoneModal").val(row.userPhone);
		    	 	  $("#useremailModal").val(row.userEmail);
		    	 	  $("#usertypeModal").val(row.userType);
		    	 	  $("#userlevelModal").val(row.userLevel);
		    	 	  $("#usercreditsModal").val(row.userCredits);
				$('#myModal').modal({
	  				keyboard: false
				});
				
				  $('#orgidModal').empty().append("<option value='"+row.orgId+"'>"+row.orgName+"</option>");
				  chosenAjaxData('orgidModal');
				  $('#orgidModal').chosen().trigger("chosen:updated")
				  
				  $('#roleidModal').empty().append("<option value='"+row.roleId+"'>"+row.roleName+"</option>");
				  chosenRoleAjaxData('roleidModal');
				  $('#roleidModal').chosen().trigger("chosen:updated");
				  
				  $('#usertypeModal').empty().
				  append("<option value='"+row.userType+"'>"+(row.userType=='0'?'普通用户':'管理员')+"</option><option value='0'>普通用户</option><option value='1'>管理员</option>")
				  .chosen().trigger("chosen:updated");
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
					var userid = $("#useridModal").val();
					var orgid = $("#orgidModal").val();
					var roleid = $("#roleidModal").val();
					var departmentid = $("#departmentidModal").val();
					var username = $("#usernameModal").val();
					var userpwd = $("#userpwdModal").val();
					var userphone = $("#userphoneModal").val();
					var useremail = $("#useremailModal").val();
					var usertype = $("#usertypeModal").val();
					var userlevel = $("#userlevelModal").val();
					var usercredits = $("#usercreditsModal").val();
					
					$.ajax({
					   type: "POST",
					   url: "../../user/addUser?random"+parseInt(Math.random()*100000),
					   data: {
								userid:userid,
								orgid:orgid,
								departmentid:departmentid,
								roleid:roleid,
								username:username,
								userpwd:userpwd,
								userphone:userphone,
								useremail:useremail,
								usertype:usertype,
								userlevel:userlevel,
								usercredits:usercredits
					   },
					   success: function(msg){
					     $.messager.alert("操作提示", $.parseJSON(msg),"info");
					     $('#dg').datagrid('reload');
					     $("#dg").datagrid("clearSelections");
					   }
					});
				}
				else{
					var userid = row.userid;
					var userid = $("#useridModal").val();
					var orgid = $("#orgidModal").val();
					var roleid = $("#roleidModal").val();
					var departmentid = $("#departmentidModal").val();
					var username = $("#usernameModal").val();
					var userpwd = $("#userpwdModal").val();
					var userphone = $("#userphoneModal").val();
					var useremail = $("#useremailModal").val();
					var usertype = $("#usertypeModal").val();
					var userlevel = $("#userlevelModal").val();
					var usercredits = $("#usercreditsModal").val();
					
					$.ajax({
					   type: "POST",
					   url: "../../user/modifyUser?random"+parseInt(Math.random()*100000),
					   data: {
								userid:userid,
								orgid:orgid,
								departmentid:departmentid,
								roleid:roleid,
								username:username,
								userpwd:userpwd,
								userphone:userphone,
								useremail:useremail,
								usertype:usertype,
								userlevel:userlevel,
								usercredits:usercredits
					   },
					   success: function(msg){
						 $('#myModal').modal('hide');
						 $('#dg').datagrid('reload');
					     $("#dg").datagrid("clearSelections");
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
						   url: "../../user/deleteUser?random"+parseInt(Math.random()*100000),
						   data: {
								userid:row.userId
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

