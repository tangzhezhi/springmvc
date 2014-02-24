
$(function() {
	
	var zNodes = "";
	
	var setting = {
		view: {
			selectedMulti: false
		},
		check: {
			enable: true,
			chkStyle: "radio",
			radioType: "all"
		},
		data: {
			simpleData: {
			 	idKey:"id",
            	pIdKey:"pid",
				enable: true
			}
		},
		callback: {
			onCheck: onCheck
		}
	};
	
	$.ajax({
	   type: "POST",
	   url:"../../exam/queryExamTree?random"+parseInt(Math.random()*100000),
       dataType:'json',
       async:false, 
	   success: function(data){
			zNodes = data.tree;
			$.fn.zTree.init($("#exampaper_tree"), setting, zNodes);
	   }
	});
	
	
	function beforeCheck(treeId, treeNode){
		var contents = " <div id = 'exam_progress' class='progress progress-striped active'> " +
		"<div class='progress-bar'  role='progressbar' aria-valuenow='45' aria-valuemin='0' aria-valuemax='100' style='width: 45%'>" +
		"<span class='sr-only'>45% Complete</span></div></div>";
		 $("#exam_home").empty().append(contents);
		 
				
			$('#note').empty().countdown({
			  image: '../../resources/img/digits.png',
	          startTime: '02:00:00',
	          timerEnd: function(){ alert('end!'); },
	          format: 'hh:mm:ss'
			});
		 
	}
	
	
	function onCheck(e, treeId, treeNode) {
		 $("#appointExam").val(treeNode.bak);
		$.ajax({
		   type: "POST",
		   url: "../../exampaper/previewExampaper?random"+parseInt(Math.random()*100000),
		   data: {
				examid:treeNode.bak
		   },
		   beforeSend:function(){
			   beforeCheck();
		   },
		   complete:function(){
			    $.messager.alert("操作提示", "考卷生成完毕,请开始做题","info");
		   },
		   success: function(data){
			   var dataRow = $.parseJSON(data).data
			   var content = "";
			   var option = "";
			   for(var i = 0 ; i < dataRow.length;i++){
				   if("1"==dataRow[i].optionType){
					    option = "<input type='radio' id='"+dataRow[i].optionid+"' name='"+dataRow[i].optionid+"' value='A'>A</input>" +
					    "<span/>&nbsp;&nbsp;<span/><input type='radio' id='"+dataRow[i].optionid+"' name='"+dataRow[i].optionid+"' value='B'>B</input>" +
					    "<span/>&nbsp;&nbsp;<span/><input type='radio' id='"+dataRow[i].optionid+"' name='"+dataRow[i].optionid+"' value='C'>C</input>" +
					    "<span/>&nbsp;&nbsp;<span/><input type='radio' id='"+dataRow[i].optionid+"' name='"+dataRow[i].optionid+"' value='D'>D</input>";
				   }
				   else if("2"==dataRow[i].optionType){
					     option = "<textarea id='"+dataRow[i].optionid+"' cols='80'  name='"+dataRow[i].optionid+"' rows='5'></textarea>";
				   }
				  	content =  content +"<span>此题"+dataRow[i].optionScore+"分：</span>"+dataRow[i].optionContents+"<p><div>"+option+"</div></p></br> ";
			   }
			   $("#exam_home").empty().append(content);
			   $("textarea").ckeditor();
		   },
		   error:function(){
			   $.messager.alert("操作提示", "服务器异常,无法生成考卷","info");
		   }
		});
		
	}	
	
	
	$("#save_exam").click(
		function () {
			var answercontent = "";
			$('input[type="radio"]').each(function(){  
				if($(this).is(":checked")){
					answercontent = answercontent + "optionid="+$(this)[0].id+"=useranswer="+$(this).val()+"#";
				}
			});  
			 alert(answercontent);
			 
		$.ajax({
		   type: "POST",
		   url: "../../exam/saveUserExampaper?random"+parseInt(Math.random()*100000),
		   data: {
				examid: $("#appointExam").val(),
				answercontent:answercontent
		   },
		   beforeSend:function(){
		   },
		   complete:function(){
			    $.messager.alert("操作提示", "考试结束","info");
		   },
		   success: function(data){

		   },
		   error:function(){
			   $.messager.alert("操作提示", "服务器异常,无法交卷","info");
		   }
		});
			 
			 
	});
	
	
	
	
	
});

