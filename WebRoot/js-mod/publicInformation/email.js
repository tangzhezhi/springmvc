
$(function() {
	$('#dg').datagrid({
	    url:"../../email/queryEmail?random"+parseInt(Math.random()*100000),
	    method:"POST",
	    showFooter: true,
	    loadMsg:"请稍等.....",
	    rownumbers:true,
	    singleSelect:true,
	    checkOnSelect:true,
	    queryParams:{},
	    idField:'emailid',
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
	    	 	  {field:'emailid',title:'EmailID',width:$(this).width()*0.15},
	    	 	  {field:'fromadd',title:'发件人地址',width:$(this).width()*0.15},
	    	 	  {field:'subject',title:'主题',width:$(this).width()*0.15},
	    	 	  {field:'content',title:'邮件内容',width:$(this).width()*0.15},
	    	 	  {field:'status',title:'状态',width:$(this).width()*0.15},
	    	 	  {field:'toadd',title:'收件人地址',width:$(this).width()*0.15},
	    	 	  {field:'attchfileurl',title:'附件地址',width:$(this).width()*0.15}
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
				emailname: $('#emailname').val()
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
		    	 	  $("#emailidModal").val(row.emailid);
		    	 	  $("#fromaddModal").val(row.fromadd);
		    	 	  $("#subjectModal").val(row.subject);
		    	 	  $("#contentModal").val(row.content);
		    	 	  $("#statusModal").val(row.status);
		    	 	  $("#toaddModal").val(row.toadd);
		    	 	  $("#attchfileurlModal").val(row.attchfileurl);
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
				var emailid = $("#emailidModal").val();
				var fromadd = $("#fromaddModal").val();
				var subject = $("#subjectModal").val();
				var content = $("#contentModal").val();
				var status = $("#statusModal").val();
				var toadd = $("#toaddModal").val();
				var attchfileurl = $("#attchfileurlModal").val();
				
				$.ajax({
				   type: "POST",
				   url: "../../email/addEmail?random"+parseInt(Math.random()*100000),
				   data: {
							emailid:emailid,
							fromadd:fromadd,
							subject:subject,
							content:content,
							status:status,
							toadd:toadd,
							attchfileurl:attchfileurl
				   },
				   success: function(msg){
				     $.messager.alert("操作提示", $.parseJSON(msg),"info");
				     $('#dg').datagrid('reload');
				     $("#dg").datagrid("clearSelections");
				   }
				});
			}
			else{
				var emailid = row.emailid;
				var emailid = $("#emailidModal").val();
				var fromadd = $("#fromaddModal").val();
				var subject = $("#subjectModal").val();
				var content = $("#contentModal").val();
				var status = $("#statusModal").val();
				var toadd = $("#toaddModal").val();
				var attchfileurl = $("#attchfileurlModal").val();
				$.ajax({
				   type: "POST",
				   url: "../../email/modifyEmail?random"+parseInt(Math.random()*100000),
				   data: {
							emailid:emailid,
							fromadd:fromadd,
							subject:subject,
							content:content,
							status:status,
							toadd:toadd,
							attchfileurl:attchfileurl
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
						   url: "../../email/deleteEmail?random"+parseInt(Math.random()*100000),
						   data: {
								emailid:row.emailid
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

