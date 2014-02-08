
$(function() {
	
	$.ajax({
	   type: "POST",
	   url:"../../public/showExamInformationTopFive?random"+parseInt(Math.random()*100000),
       dataType:'json',
       async:false, 
	   success: function(data){
			  var dataRow = data.data;
			  var content = "";
			  var option = "";
			  for(var i = 0 ; i < dataRow.length;i++){
				   option = option + "<p><a id='"+dataRow[i].examid+"' href='./web_content.html?examid="+dataRow[i].examid+"' title='"+dataRow[i].examName+"' target='_blank' >"+dataRow[i].examName+"</a><p>";
			  }
			  content = "<h2>考试信息</h2>" + option;
			  $("#gzdt").empty().append(content);
	   }
	});
	
	
	
});

