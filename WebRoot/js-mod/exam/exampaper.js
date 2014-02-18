
$(function() {
	$('#dg').datagrid({
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
	    	 	   {field:'examType',title:'试卷类型',width:$(this).width()*0.15},
	    	 	  {field:'examSubject',title:'试卷科目',width:$(this).width()*0.15}
	    ]],
	    onSelect:function(rowIndex, rowData){
	    	var examid = rowData.examid;
	    	$("#exampaperdetails_dg").datagrid({
	    		queryParams: {
					examid: examid
				}
	    	});
	    	
			$.ajax({
			   type: "POST",
			   url: "../../exampaper/findExamInfoByExamId?random"+parseInt(Math.random()*100000),
			   data: {
						examid:examid
			   },
			   success: function(msg){
				   var data = $.parseJSON(msg);
				   $("#totalscore").empty().append("当前总分值："+data.totalScore);
				   $("#totalitems").empty().append("当前总题数："+data.totalItems);
			   }
			});
	    	
	    },
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
				exampapername: $('#exampapername').val()
			}).datagrid("clearSelections");
		});
	
	
		function chosenAjaxData(id,code){
			var contents = "";
			$.ajax({
			   type: "POST",
			   url:"../../common/queryDictory?random"+parseInt(Math.random()*100000),
	           dataType:'json',
	           async:false, 
	           data:{
					code:code,
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
			$('#examTypeModal').empty();
			chosenAjaxData('examSubjectModal','student_subject');
			chosenAjaxData('examTypeModal','exam_type');
			$('#examSubjectModal').chosen().trigger("chosen:updated");
			$('#examTypeModal').chosen().trigger("chosen:updated");
			$('#examSubjectModal_chosen .chosen-single > span').empty().append("<span>请选择试卷科目</span>");
			$('#examSubjectModal_chosen').removeAttr("style"); 
			
			$('#examTypeModal_chosen .chosen-single > span').empty().append("<span>请选择试卷类型</span>");
			$('#examTypeModal_chosen').removeAttr("style"); 
	});
	
	
	$("#modify").click(
		function () {
			var row = $("#dg").datagrid("getSelected");
			
			if(row == null || row == "undefined"){
				$.messager.alert("操作提示", "请选择一行记录","info");
			}
			else{
		    	 	  $("#examidModal").val(row.examid);
		    	 	  $("#examNameModal").val(row.examName);
		    	 	  $("#examOperaterModal").val(row.examOperater);
		    	 	  $("#examTimeModal").val(row.examTime);
		    	 	  $("#examSubjectModal").val(row.examSubject);
				$('#myModal').modal({
	  				keyboard: false
				});
				
				 $('#examSubjectModal').empty().append("<option value='"+row.examSubject+"'>"+row.examSubject+"</option>");
				  $('#examTypeModal').empty().append("<option value='"+row.examType+"'>"+row.examType+"</option>");
				 chosenAjaxData('examSubjectModal','student_subject');
				 chosenAjaxData('examTypeModal','exam_type');
				  $('#examSubjectModal').chosen().trigger("chosen:updated");
				  $('#examTypeModal').chosen().trigger("chosen:updated");
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
				var examid = $("#examidModal").val();
				var examName = $("#examNameModal").val();
				var examOperater = $("#examOperaterModal").val();
				var examTime = $("#examTimeModal").val();
				var examSubject = $("#examSubjectModal").val();
				
				$.ajax({
				   type: "POST",
				   url: "../../exampaper/addExampaper?random"+parseInt(Math.random()*100000),
				   data: {
							examid:examid,
							examName:examName,
							examOperater:examOperater,
							examTime:examTime,
							examSubject:examSubject
				   },
				   success: function(msg){
				     $.messager.alert("操作提示", $.parseJSON(msg),"info");
				     $('#dg').datagrid('reload');
				     $("#dg").datagrid("clearSelections");
				   }
				});
			}
			else{
				var examid = row.examid;
				var examName = $("#examNameModal").val();
				var examOperater = $("#examOperaterModal").val();
				var examTime = $("#examTimeModal").val();
				var examSubject = $("#examSubjectModal").val();
				$.ajax({
				   type: "POST",
				   url: "../../exampaper/modifyExampaper?random"+parseInt(Math.random()*100000),
				   data: {
							examid:examid,
							examName:examName,
							examOperater:examOperater,
							examTime:examTime,
							examSubject:examSubject
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
						   url: "../../exampaper/deleteExampaper?random"+parseInt(Math.random()*100000),
						   data: {
								examid:row.examid
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
	
	
	
	
	
	
	$('#exampaperdetails_dg').datagrid({
	    url:"../../exampaper/queryOptionsOfExam?random"+parseInt(Math.random()*100000),
	    method:"POST",
	    showFooter: true,
	    loadMsg:"请稍等.....",
	    rownumbers:true,
	    singleSelect:true,
	    checkOnSelect:true,
	    queryParams:{},
	    idField:'exampaperdetailsid',
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
	    	 	  {field:'exampaperdetailsid',title:'试卷ID',width:$(this).width()*0.15,hidden:true},
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
	    	 	  {
		            		field:'optionScore',
		            		title:'分值',
		            		width:$(this).width()*0.15,
		            		editor:{
			                    type:'text',
			                    options:{
			                        required:true
			                    }
			                }
		           },
		           {
		            		field:'optionOrder',
		            		title:'题号',
		            		width:$(this).width()*0.15,
		            		editor:{
			                    type:'text',
			                    options:{
			                        required:true
			                    }
			                }
		           }
	    ]],
		onDblClickRow:function(rowIndex,rowData){
           lastIndex=rowIndex;
           
           var options = $("#exampaperdetails_dg").datagrid('options');
           
           $("#exampaperdetails_dg").datagrid('endEdit',rowIndex);
           $("#exampaperdetails_dg").datagrid('beginEdit',rowIndex);
           
            $("input.datagrid-editable-input").bind("keypress",function(evt){
            	 if(evt.keyCode==13){
            		  $("#exampaperdetails_dg").datagrid('endEdit',lastIndex);
            	 }
            });
        },
        onAfterEdit:function(rowIndex,rowData,changes){
        	 modifyOptionScore(rowData,eval("rowData.optionScore"),eval("rowData.optionOrder"));
        },
	     onLoadError:function(){
                  alert('','加载数据失败！');
         },
         onLoadSuccess:function(){
        	 $('#exampaperdetails_dg').datagrid('options').pagination=true;
         }
	});
	
	
	
	$("#delete_option").click(
		function () {
			var row = $("#dg").datagrid("getSelected");
			var rowoption = $("#exampaperdetails_dg").datagrid("getSelected");
			if(row == null || row == "undefined"){
				$.messager.alert("操作提示", "请选择一行记录","info");
			}
			else if(rowoption == null || rowoption == "undefined"){
				$.messager.alert("操作提示", "请选择一行记录","info");
			}
			else{
				 $.messager.confirm("操作提示", "您确定要执行操作吗？", function (data) {
		         	if (data) {
		         		$.ajax({
						   type: "POST",
						   url: "../../exampaper/deleteOneOptionsOfExampaper?random"+parseInt(Math.random()*100000),
						   data: {
								exampaperdetailsid:rowoption.exampaperdetailsid
						   },
						   success: function(msg){
						     $.messager.alert("操作提示", $.parseJSON(msg),"info");
						     $('#exampaperdetails_dg').datagrid('reload');
						     $("#exampaperdetails_dg").datagrid("clearSelections");
						   }
						});
		            }
		            else {
		                return false;
		            }
		         });
			}
	});
	
	
	$('#myExamPreviewModal').on('show.bs.modal', function () {
			var row = $("#dg").datagrid("getSelected");
			$.ajax({
			   type: "POST",
			   url: "../../exampaper/previewExampaper?random"+parseInt(Math.random()*100000),
			   data: {
					examid:row.examid
			   },
			   success: function(data){
				   var dataRow = $.parseJSON(data).data
				   var content = "";
				   for(var i = 0 ; i < dataRow.length;i++){
					  	content =  content +dataRow[i].optionContents+"</br>";
				   }
				   $("#exam_content").empty().append(content);
			   }
			});
	});
	
	
	
	$("#exam_preview").click(
		function () {
			var row = $("#dg").datagrid("getSelected");
			if(row == null || row == "undefined"){
				$.messager.alert("操作提示", "请选择一行记录","info");
			}
			else{
				$('#myExamPreviewModal').modal({
	  				keyboard: false
				});
			}
	});
	
	
	function modifyOptionScore(rowData,value1,value2,e){
         $.ajax({
		   type: "POST",
		   url: "../../exampaper/modifyOneOptionsOfExampaper?random"+parseInt(Math.random()*100000),
		   data: {
				exampaperdetailsid:rowData.exampaperdetailsid,
				optionScore:value1,
				optionOrder:value2
		   },
		   success: function(msg){
			   var data = $.parseJSON(msg);
			   $("#totalscore").empty().append("当前总分值："+data.totalScore);
			   $("#totalitems").empty().append("当前总题数："+data.totalItems);
		   }
		});
	}
	
	
});

