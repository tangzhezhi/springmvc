
$(function() {
			
	
	
		var total = "";
		function getUrlParam(name)
		{
			var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
			var r = window.location.search.substr(1).match(reg);  //匹配目标参数
			if (r!=null) return unescape(r[2]); return null; //返回参数值
		} 
		
		var articleType = getUrlParam('articleType');
		
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
		   url: "../../public/selectArticleAll?random"+parseInt(Math.random()*100000),
		   data: {
				pageNo:1,
				articleType:articleType
		   },
		   success: function(data){
			   var dataRow = $.parseJSON(data).rows
			   total = $.parseJSON(data).total;
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
						   page_content = page_content + "<li id=page_li_"+i+" class='active page_li'><a href='#'  onclick=pageClick("+i+",'"+articleType+"')>"+i+"<span class='sr-only'>(current)</span></a></li>";
					   }
					   else if(i==10){
						   $("#next").removeClass("disabled");
						   break;
					   }
					   else{
						   page_content = page_content + "<li id=page_li_"+i+"><a href='#' class='page_li' onclick=pageClick("+i+",'"+articleType+"')>"+i+"</a></li>";
					   }
				   }
				   $("#currentMaxPage").val(10);
			   }
			   
			   $("#preview").after(page_content);
		   }
		});
		
		
		$("#next").click(function(){
			var beforeClick_currentMaxPage = parseInt($("#currentMaxPage").val());
			var temp_currentMaxPage = parseInt($("#currentMaxPage").val())+10;
			var page_content="";
			if(total>(beforeClick_currentMaxPage*10)){
				$(".page_li").detach(); 
				  for(var i = 0 ; i <= parseInt((total-(beforeClick_currentMaxPage*10))/10)+1; i++){
						 page_content = page_content + "<li id=page_li_"+parseInt(beforeClick_currentMaxPage/1+i/1)+"><a href='#' class='page_li' onclick=pageClick("+parseInt(beforeClick_currentMaxPage/1+i/1)+",'"+articleType+"')>"+parseInt(beforeClick_currentMaxPage/1+i/1)+"</a></li>";
				   }
				
			}
			  $("#preview").after(page_content);
			  $("#currentMaxPage").val(temp_currentMaxPage);
		});
		
		
		$("#preview").click(function(){
			var beforeClick_currentMaxPage = parseInt($("#currentMaxPage").val());
			var temp_currentMaxPage = parseInt($("#currentMaxPage").val())-10;
			var page_content="";
			if(total>(beforeClick_currentMaxPage*10)){
				$(".page_li").detach(); 
				  for(var i = temp_currentMaxPage-10 ; i < temp_currentMaxPage ; i++){
						 page_content = page_content + "<li id=page_li_"+i+"><a href='#' class='page_li' onclick=pageClick("+parseInt(i)+",'"+articleType+"')>"+parseInt(i)+"</a></li>";
				   }
				
			}
			  $("#preview").after(page_content);
			  $("#currentMaxPage").val(temp_currentMaxPage);
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

