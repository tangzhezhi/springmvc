
$(function() {
	
		function getUrlParam(name)
		{
			var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
			var r = window.location.search.substr(1).match(reg);  //匹配目标参数
			if (r!=null) return unescape(r[2]); return null; //返回参数值
		} 
	
		$("#index_nav").attr("href","./../../welcome.html");
		$("#zxgk_nav > a").attr("href","./index_part.html?articleType="+escape('政协概况'));
		$("#zxgz_nav > a").attr("href","./index_part.html?articleType="+escape('政协工作'));
		$("#zxhy_nav > a").attr("href","./index_part.html?articleType="+escape('政协会议'));
		$("#jyxc_nav > a").attr("href","./index_part.html?articleType="+escape('建言献策'));
		$("#zxta_nav > a").attr("href","./index_part.html?articleType="+escape('政协提案'));
		$("#gzzd_nav > a").attr("href","./index_part.html?articleType="+escape('规章制度'));
		$("#xxyd_nav > a").attr("href","./index_part.html?articleType="+escape('学习园地'));
		$("#wszl_nav > a").attr("href","./index_part.html?articleType="+escape('文史资料'));
		$("#mtbd_nav > a").attr("href","./index_part.html?articleType="+escape('媒体报道'));
		$("#zxwy_nav > a").attr("href","./index_part.html?articleType="+escape('政协委员'));
		
		
		$.ajax({
		   type: "POST",
		   url: "../../public/previewArticle?random"+parseInt(Math.random()*100000),
		   data: {
				articleId:getUrlParam('articleId')
		   },
		   success: function(data){
			   var dataRow = $.parseJSON(data).data
			   var content = "";
			   var option = "";
			   var date="";
			   for(var i = 0 ; i < dataRow.length;i++){
				    var temp_date = dataRow[i].createTime;
				    date = "<p class='head_explain text-left'>发布日期:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+temp_date.substring(0,4)+"-"+temp_date.substring(4,6)+"-"+temp_date.substring(6,8)+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;作者:</p>";
				    var temp_tools = "<p class='head_explain'>【<a href='#' onclick='window.print();'>打印本页</a>】【<a href='#' onclick='window.close();'>关闭窗口</a>】</p>";
				    content = "<div><p class='text-center h3 head_title' >"+dataRow[i].articleTitle+"</p>"+date+temp_tools+"</div>";
				  	content =  content +"<p class='text-center h2'>"+dataRow[i].articleTitle+"</p><div class='content'>"+dataRow[i].articleContent+"</div></br> ";
			   }
			   $("#content").empty().append(content);
		   }
		});
	
});

