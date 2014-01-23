
$(function() {
	
	$('#dg').datagrid({
	    url:"../../options/queryOptions?random"+parseInt(Math.random()*100000),
	    method:"POST",
	    showFooter: true,
	    loadMsg:"请稍等.....",
	    rownumbers:true,
	    nowrap:true,
	    striped:true,
	    singleSelect:true,
	    checkOnSelect:true,
	    queryParams:{},
	    idField:'optionsid',
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
	    	 	  {field:'optionid',title:'考题ID',width:$(this).width()*0.15,hidden:true},
	    	 	  {field:'optionTitle',title:'考题标题',width:$(this).width()*0.15},
	    	 	  {field:'optionType',
	    	 		  title:'考题类型',
	    	 		  width:$(this).width()*0.15,
	    	 		  formatter:function(val,row){
			                if (val == '1'){
			                    return '单选';
			                }else if (val == '2'){
			                    return '多选';
			                }else if (val == '3'){
			                    return '填空';
			                }else if (val == '4'){
			                    return '简答';
			                }else if (val == '5'){
			                    return '论证';
			                }else {
			                    return '其他';
			                }
		            	}},
	    	 	  {field:'optionContents',title:'考题题干',width:$(this).width()*0.15,hidden:true},
	    	 	  {field:'optionAnswer',title:'考题答案',width:$(this).width()*0.15,hidden:true},
	    	 	  {field:'optionKnowledgePoint',title:'考题知识点',width:$(this).width()*0.15},
	    	 	  {field:'optionDate',title:'考题时间',width:$(this).width()*0.15},
	    	 	  {field:'optionLevelDifficult',title:'考题难度',width:$(this).width()*0.15}
	    ]],
	     onLoadError:function(){
                  alert('','加载数据失败！');
         },
         onLoadSuccess:function(){
        	 $('#dg').datagrid('options').pagination=true;
         },
         onRowContextMenu:function(e, rowIndex, rowData){
        	 e.preventDefault();
        	 $('#optionscontent').empty().html(rowData.optionContents+rowData.optionAnswer);
			 $('#myContentModal').modal({
  				keyboard: false
			});
         }
	});
	
	$("#query").click(
		function () {
			$('#dg').datagrid('reload',{
				optionsname: $('#optionsname').val()
			}).datagrid("clearSelections");
		});
	
	
	
	$("#add").click(
		function () {
			$('#myModal').modal({
  				keyboard: false
			});
			
			$("#dg").datagrid("clearSelections");
			$('#modal_form')[0].reset();
			$('#optionContentsModal').ckeditor();
			$('#optionAnswerModal').ckeditor();
			$('#optionTypeModal').chosen().trigger("chosen:updated");
	});
	
	
	$("#modify").click(
		function () {
			var row = $("#dg").datagrid("getSelected");
			
			if(row == null || row == "undefined"){
				$.messager.alert("操作提示", "请选择一行记录","info");
			}
			else{
		    	 	  $("#optionidModal").val(row.optionid);
		    	 	  $("#optionContentsModal").val(row.optionContents);
		    	 	  $("#optionAnswerModal").val(row.optionAnswer);
		    	 	  $("#optionTypeModal").val(row.optionType);
		    	 	  $("#optionTitleModal").val(row.optionTitle);
		    	 	  $("#optionKnowledgePointModal").val(row.optionKnowledgePoint);
		    	 	  $("#optionDateModal").val(row.optionDate);
		    	 	  $("#optionLevelDifficultModal").val(row.optionLevelDifficult);
				$('#myModal').modal({
	  				keyboard: false
				});
					var val = row.optionType;
					var tempVal = "";
				    if (val == '1'){
	                    tempVal= '单选';
	                }else if (val == '2'){
	                    tempVal= '多选';
	                }else if (val == '3'){
	                    tempVal= '填空';
	                }else if (val == '4'){
	                    tempVal= '简答';
	                }else if (val == '5'){
	                    tempVal= '论证';
	                }else {
	                    tempVal= '其他';
			                }
				
				$('#optionContentsModal').ckeditor();
				$('#optionAnswerModal').ckeditor();
				$('#optionTypeModal').empty().append("<option value='"+row.optionType+"'>"+tempVal
					  +"</option><option value='1'>单选</option><option value='2'>多选</option>" +
					  "<option value='3'>填空</option><option value='4'>简答</option><option value='5'>论证</option>")
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
				var optionid = $("#optionidModal").val();
				var optionContents = $("#optionContentsModal").val();
				var optionAnswer = $("#optionAnswerModal").val();
				var optionType = $("#optionTypeModal").val();
				var optionTitle = $("#optionTitleModal").val();
				var optionKnowledgePoint = $("#optionKnowledgePointModal").val();
				var optionDate = $("#optionDateModal").val();
				var optionLevelDifficult = $("#optionLevelDifficultModal").val();
				
				$.ajax({
				   type: "POST",
				   url: "../../options/addOptions?random"+parseInt(Math.random()*100000),
				   data: {
							optionid:optionid,
							optionContents:optionContents,
							optionAnswer:optionAnswer,
							optionType:optionType,
							optionTitle:optionTitle,
							optionKnowledgePoint:optionKnowledgePoint,
							optionDate:optionDate,
							optionLevelDifficult:optionLevelDifficult
				   },
				   success: function(msg){
				     $.messager.alert("操作提示", $.parseJSON(msg),"info");
				     $('#dg').datagrid('reload');
				     $("#dg").datagrid("clearSelections");
				   }
				});
			}
			else{
				var optionid = row.optionid;
				var optionContents = $("#optionContentsModal").val();
				var optionAnswer = $("#optionAnswerModal").val();
				var optionType = $("#optionTypeModal").val();
				var optionTitle = $("#optionTitleModal").val();
				var optionKnowledgePoint = $("#optionKnowledgePointModal").val();
				var optionDate = $("#optionDateModal").val();
				var optionLevelDifficult = $("#optionLevelDifficultModal").val();
				$.ajax({
				   type: "POST",
				   url: "../../options/modifyOptions?random"+parseInt(Math.random()*100000),
				   data: {
							optionid:optionid,
							optionContents:optionContents,
							optionAnswer:optionAnswer,
							optionType:optionType,
							optionTitle:optionTitle,
							optionKnowledgePoint:optionKnowledgePoint,
							optionDate:optionDate,
							optionLevelDifficult:optionLevelDifficult
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
						   url: "../../options/deleteOptions?random"+parseInt(Math.random()*100000),
						   data: {
								optionsid:row.optionid
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
	
	
	
	
	
	
	$('#exampaper_dg').datagrid({
	    url:"../../exampaper/queryExampaper?random"+parseInt(Math.random()*100000),
	    method:"POST",
	    showFooter: true,
	    loadMsg:"请稍等.....",
	    rownumbers:true,
	    singleSelect:true,
	    checkOnSelect:true,
	    queryParams:{},
	    idField:'examid',
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
	    	 	  {field:'examid',title:'试卷ID',width:$(this).width()*0.15,hidden:true},
	    	 	  {field:'examName',title:'试卷名称',width:$(this).width()*0.15},
	    	 	  {field:'examOperater',title:'试卷出题人',width:$(this).width()*0.15},
	    	 	  {field:'examTime',title:'出题时间',width:$(this).width()*0.15},
	    	 	  {field:'examSubject',title:'试卷科目',width:$(this).width()*0.15}
	    ]],
	     onLoadError:function(){
                  alert('','加载数据失败！');
         },
         onLoadSuccess:function(){
        	 $('#exampaper_dg').datagrid('options').pagination=true;
         }
	});
	
	$("#exampaper_query").click(
		function () {
			$('#exampaper_dg').datagrid('reload',{
				exampapername: $('#examname').val()
			}).datagrid("clearSelections");
		});
	
	
	
	
	$("#addToExamPaper").click(
		function () {
			var row = $("#exampaper_dg").datagrid("getSelected");
			var rowoption = $("#dg").datagrid("getSelected");
			if(row == null || row == "undefined" ){
				$.messager.alert("操作提示", "请选择试卷","info");
			}
			else if(rowoption == null || rowoption == "undefined"){
				$.messager.alert("操作提示", "请选择一条题目记录","info");
			}
			else{
				 $.messager.confirm("操作提示", "您确定要执行操作吗？", function (data) {
		         	if (data) {
		         		$.ajax({
						   type: "POST",
						   url: "../../exampaper/addOptionsToExam?random"+parseInt(Math.random()*100000),
						   data: {
								optionid:rowoption.optionid,
								examid:row.examid
						   },
						   success: function(msg){
						     $.messager.alert("操作提示", $.parseJSON(msg),"info");
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

