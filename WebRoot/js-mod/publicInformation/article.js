
$(function() {
	$('#dg').datagrid({
	    url:"../../article/queryArticle?random"+parseInt(Math.random()*100000),
	    method:"POST",
	    showFooter: true,
	    loadMsg:"请稍等.....",
	    rownumbers:true,
	    singleSelect:true,
	    checkOnSelect:true,
	    queryParams:{},
	    idField:'articleid',
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
	    	 	  {field:'articleId',title:'文章ID',width:$(this).width()*0.15,hidden:true},
	    	 	  {field:'articleTitle',title:'标题',width:$(this).width()*0.15},
	    	 	  {field:'articleContent',title:'内容',width:$(this).width()*0.15,hidden:true},
	    	 	  {field:'articleType',title:'类型',width:$(this).width()*0.15},
	    	 	  {field:'articleOperater',title:'操作人',width:$(this).width()*0.15},
	    	 	  {field:'createTime',title:'创建时间',width:$(this).width()*0.15}
	    	 	  
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
				articlename: $('#articlename').val()
			}).datagrid("clearSelections");
		});
	
	
	function chosenAjaxData(id){
		var contents = "";
		$.ajax({
		   type: "POST",
		   url:"../../common/queryDictory?random"+parseInt(Math.random()*100000),
           dataType:'json',
           async:false, 
           data:{
				code:'government_subject',
				page:1,
				rows:99999
			},
		   success: function(data){
				var selectObj = $('#'+id); 
				for(var i=0;i<data.rows.length;i++){
					contents = contents +"<option value='"+data.rows[i].name+"'>"+data.rows[i].name+"</option>";
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
			
			$('#examSubjectModal').empty();
			chosenAjaxData('articleTypeModal');
			$('#articleTypeModal').chosen().trigger("chosen:updated");
			$('#articleTypeModal_chosen .chosen-single > span').empty().append("<span>请选择类型</span>");
			$('#articleTypeModal_chosen').removeAttr("style"); 
			
			$('#articleContentModal').ckeditor();
	});
	
	
	$("#modify").click(
		function () {
			var row = $("#dg").datagrid("getSelected");
			
			if(row == null || row == "undefined"){
				$.messager.alert("操作提示", "请选择一行记录","info");
			}
			else{
		    	 	  $("#articleIdModal").val(row.articleId);
		    	 	  $("#articleContentModal").val(row.articleContent);
		    	 	  $("#articleTypeModal").val(row.articleType);
		    	 	  $("#articleOperaterModal").val(row.articleOperater);
		    	 	  $("#createTimeModal").val(row.createTime);
		    	 	  $("#articleTitleModal").val(row.articleTitle);
				$('#myModal').modal({
	  				keyboard: false
				});
				
				 $('#articleTypeModal').empty().append("<option value='"+row.articleType+"'>"+row.articleType+"</option>");
				  chosenAjaxData('articleTypeModal');
				  $('#articleTypeModal').chosen().trigger("chosen:updated")
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
				var articleId = $("#articleIdModal").val();
				var articleContent = $("#articleContentModal").val();
				var articleType = $("#articleTypeModal").val();
				var articleOperater = $("#articleOperaterModal").val();
				var createTime = $("#createTimeModal").val();
				var articleTitle = $("#articleTitleModal").val();
				
				$.ajax({
				   type: "POST",
				   url: "../../article/addArticle?random"+parseInt(Math.random()*100000),
				   data: {
							articleId:articleId,
							articleContent:articleContent,
							articleType:articleType,
							articleOperater:articleOperater,
							createTime:createTime,
							articleTitle:articleTitle
				   },
				   success: function(msg){
				     $.messager.alert("操作提示", $.parseJSON(msg),"info");
				     $('#dg').datagrid('reload');
				     $("#dg").datagrid("clearSelections");
				   }
				});
			}
			else{
				var articleid = row.articleid;
				var articleId = $("#articleIdModal").val();
				var articleContent = $("#articleContentModal").val();
				var articleType = $("#articleTypeModal").val();
				var articleOperater = $("#articleOperaterModal").val();
				var createTime = $("#createTimeModal").val();
				var articleTitle = $("#articleTitleModal").val();
				$.ajax({
				   type: "POST",
				   url: "../../article/modifyArticle?random"+parseInt(Math.random()*100000),
				   data: {
							articleId:articleId,
							articleContent:articleContent,
							articleType:articleType,
							articleOperater:articleOperater,
							createTime:createTime,
							articleTitle:articleTitle
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
						   url: "../../article/deleteArticle?random"+parseInt(Math.random()*100000),
						   data: {
								articleid:row.articleId
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

