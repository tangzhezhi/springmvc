
$(function() {
	
		function getUrlParam(name)
		{
			var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
			var r = window.location.search.substr(1).match(reg);  //匹配目标参数
			if (r!=null) return unescape(r[2]); return null; //返回参数值
		} 
	
		$("#index_nav").attr("href","./../../welcome.html");
	$("#axx_nav > a").attr("href","./index_part.html?examType="+escape('爱学习'));
	$("#kgw_nav > a").attr("href","./index_part.html?examType="+escape('考公务'));
	$("#kjz_nav > a").attr("href","./index_part.html?examType="+escape('考驾照'));
	$("#qwt_nav > a").attr("href","./index_part.html?examType="+escape('趣味题'));
	$("#wyy_nav > a").attr("href","./index_part.html?examType="+escape('玩英语'));
		$("#mytest_nav > a").attr("href","./../../login.html");
		
		$.ajax({
		   type: "POST",
		   url: "../../public/previewExampaper?random"+parseInt(Math.random()*100000),
		   data: {
				examid:getUrlParam('examid')
		   },
		   success: function(data){
			    var dataRow = data.data
			   var content = "";
			   var option = "";
			   var temp_tools = "<p class='head_explain'>【<a href='#' onclick='window.print();'>打印本页</a>】【<a href='#' onclick='window.close();'>关闭窗口</a>】</p>";
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
			   $("#content").empty().append(temp_tools+content);
			   $("textarea").ckeditor();

		   }
		});
	
});

