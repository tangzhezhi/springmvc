
$(function() {
			
	
	
	
		function getUrlParam(name)
		{
			var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
			var r = window.location.search.substr(1).match(reg);  //匹配目标参数
			if (r!=null) return unescape(r[2]); return null; //返回参数值
		} 
		
		var articleType = getUrlParam('articleType');
		
		
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
		   url: "../../public/selectArticleAll?random"+parseInt(Math.random()*100000),
		   data: {
				pageNo:1,
				articleType:articleType
		   },
		   success: function(data){
			   var dataRow = $.parseJSON(data).rows
			   var total = $.parseJSON(data).total;
			   var content = "<table class='table'><thead><tr><th>标　题</th><th>日期</th></tr></thead>";
			   var option = "";
			   var date="";
			   for(var i = 0 ; i < dataRow.length;i++){
				   var temp_date = dataRow[i].createTime;
				   date = "<span class='text-right text-muted'>["+temp_date.substring(0,4)+"-"+temp_date.substring(4,6)+"-"+temp_date.substring(6,8)+"]</span>";
				   option = option + "<tr><td><img src='../../resources/img/arrow02.gif' width='10' height='10'>&nbsp;&nbsp;&nbsp;&nbsp;<a id='"+dataRow[i].articleId+"' href='./web_content.html?articleId="+dataRow[i].articleId+"' title='"+dataRow[i].articleTitle+"' target='_blank' >"+dataRow[i].articleTitle+"</a></td><td>"+date+"</td></tr>";
			   }
			   content = content +option +"</table>";
			   $("#title_content_top_a").append(articleType);
			   $("#dg").empty().append(content);
			   $("#totalnum").append("<a href='#'>总共:"+total+"条记录&nbsp;&nbsp;&nbsp;&nbsp;共"+parseInt(total/10+1)+"页</a>");
			   
			   var page_content="";
			   if(total>0){
				   for(var i = 1 ; i < (total/10)+1; i++){
					   if(i==1){
						   page_content = page_content + "<li id=page_li_"+i+" class='active'><a href='#'  onclick=pageClick("+i+",'"+articleType+"')>"+i+"<span class='sr-only'>(current)</span></a></li>";
					   }
					   else{
						   page_content = page_content + "<li id=page_li_"+i+"><a href='#' onclick=pageClick("+i+",'"+articleType+"')>"+i+"</a></li>";
					   }
				   }
			   }
			   
			   $("#preview").after(page_content);
		   }
		});
	
});


function pageClick(pageNo,articleType){
				$.ajax({
				   type: "POST",
				   url: "../../public/selectArticleAll?random"+parseInt(Math.random()*100000),
				   data: {
						pageNo:pageNo,
						articleType:articleType
				   },
				   success: function(data){
					   var dataRow = $.parseJSON(data).rows
					   var total = $.parseJSON(data).total;
					   var content = "<table class='table'><thead><tr><th>标　题</th><th>日期</th></tr></thead>";
					   var option = "";
					   var date="";
					   for(var i = 0 ; i < dataRow.length;i++){
						   var temp_date = dataRow[i].createTime;
						   date = "<span class='text-right text-muted'>["+temp_date.substring(0,4)+"-"+temp_date.substring(4,6)+"-"+temp_date.substring(6,8)+"]</span>";
						   option = option + "<tr><td><img src='../../resources/img/arrow02.gif' width='10' height='10'>&nbsp;&nbsp;&nbsp;&nbsp;<a id='"+dataRow[i].articleId+"' href='./web_content.html?articleId="+dataRow[i].articleId+"' title='"+dataRow[i].articleTitle+"' target='_blank' >"+dataRow[i].articleTitle+"</a></td><td>"+date+"</td></tr>";
					   }
					   content = content +option +"</table>";
					   $("#dg").empty().append(content);
					   $("#dg_page_ul > li").removeClass("active");
					   $("#page_li_"+pageNo).addClass("active");
				   }
				});
		}

