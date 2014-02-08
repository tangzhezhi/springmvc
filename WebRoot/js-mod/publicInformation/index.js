
$(function() {
	
	$.ajax({
	   type: "POST",
	   url:"../../public/showArticleInformationTopFive?random"+parseInt(Math.random()*100000),
       dataType:'json',
       async:false, 
	   success: function(data){
			  var dataRow = data.data;
			  var content = "";
			  var option = "";
			  for(var i = 0 ; i < dataRow.length;i++){
				   option = option + "<p><a id='"+dataRow[i].articleId+"' href='./web_content.html?articleId="+dataRow[i].articleId+"' title='"+dataRow[i].t.articleContent+"' target='_blank' >"+dataRow[i].t.articleContent+"</a><p>";
			  }
			  content = "<h2>工作动态</h2>" + option;
			  $("#gzdt").empty().append(content);
	   }
	});
	
	
	
});

