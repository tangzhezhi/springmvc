
$(function() {
	
		function getUrlParam(name)
		{
			var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
			var r = window.location.search.substr(1).match(reg);  //匹配目标参数
			if (r!=null) return unescape(r[2]); return null; //返回参数值
		} 
	
	
		$.ajax({
		   type: "POST",
		   url: "../../public/selectArticleAll?random"+parseInt(Math.random()*100000),
		   data: {
				pageNo:1,
				articleType:getUrlParam('articleType')
		   },
		   success: function(data){
			   var dataRow = $.parseJSON(data).rows
			   var total = $.parseJSON(data).total;
			   var content = "<table>";
			   var option = "";
			   var date="";
			   for(var i = 0 ; i < dataRow.length;i++){
				   var temp_date = dataRow[i].createTime;
				   date = "<span class='text-right text-muted'>["+temp_date.substring(0,4)+"-"+temp_date.substring(4,6)+"-"+temp_date.substring(6,8)+"]</span>";
				   option = option + "<tr><td><a id='"+dataRow[i].articleId+"' href='./web_content.html?articleId="+dataRow[i].articleId+"' title='"+dataRow[i].articleTitle+"' target='_blank' >"+dataRow[i].articleTitle+"</a></td><td>"+date+"</td></tr>";
			   }
			   content = content +option +"</table>";
			   $("#dg").empty().append(content);
		   }
		});
	
});

