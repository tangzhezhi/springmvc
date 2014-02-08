
$(function() {
	
		function getUrlParam(name)
		{
			var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
			var r = window.location.search.substr(1).match(reg);  //匹配目标参数
			if (r!=null) return unescape(r[2]); return null; //返回参数值
		} 
	
	
		$.ajax({
		   type: "POST",
		   url: "../../public/previewExampaper?random"+parseInt(Math.random()*100000),
		   data: {
				examid:getUrlParam('examid')
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
				  	content =  content +"<span>此题"+dataRow[i].optionScore+"分：</span>"+dataRow[i].optionContents+"<p><div>"+option+"</div></p></br> ";
			   }
			   $("#content").empty().append(content);
		   }
		});
	
});

