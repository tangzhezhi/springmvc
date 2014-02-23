
$(function() {
	
	$("#index_nav").attr("href","./welcome.html");
	$("#axx_nav > a").attr("href","./html/publicInformation/index_part.html?examType="+escape('爱学习'));
	$("#kgw_nav > a").attr("href","./html/publicInformation/index_part.html?examType="+escape('考公务'));
	$("#kjz_nav > a").attr("href","./html/publicInformation/index_part.html?examType="+escape('考驾照'));
	$("#qwt_nav > a").attr("href","./html/publicInformation/index_part.html?examType="+escape('趣味题'));
	$("#wyy_nav > a").attr("href","./html/publicInformation/index_part.html?examType="+escape('玩英语'));
	$("#gxsp_nav > a").attr("href","./html/publicInformation/index_part.html?examType="+escape('搞笑视频'));
	
	
	$.ajax({
	   type: "POST",
	   url:"./public/showPicInformationTopFive?random"+parseInt(Math.random()*100000),
       dataType:'json',
       async:true, 
	   success: function(data){
			  var dataRow = data.data;
			  var content = "";
			  var option = "";
			  
			  for(var i = 0 ; i < dataRow.length;i++){
				  if(i==0){
					    option = option + "<div class='item active'>"+dataRow[i].picUrl+"<div class='container'><div class='carousel-caption'><p>"+dataRow[i].picName+"</p></div></div></div>";
				  }
				  else{
					  option = option + "<div class='item'>"+dataRow[i].picUrl+"<div class='container'><div class='carousel-caption'><p>"+dataRow[i].picName+"</p></div></div></div>";
				  }
			  }
			  content =  option;
			  $("#pic_content").empty().append(content);
	   }
	});
	
	
	
	$.ajax({
	   type: "POST",
	   url:"./public/showExamInformationTopFive?random"+parseInt(Math.random()*100000),
       dataType:'json',
       async:true, 
   	   data: {
			examType:'爱学习'
	   },
	   success: function(data){
			  var dataRow = data.data;
			  var content = "";
			  var message_content=""
			  var option = "<table class='table'>";
			  var message_option="";
			  var date = "<p  height='29'>&nbsp;&nbsp;</p>";
			  var param = escape("爱学习");
			  for(var i = 0 ; i < dataRow.length;i++){
				  
				   var temp_date = dataRow[i].createTime;
				   date = "<span class='text-right text-muted'>["+temp_date.substring(2,4)+"-"+temp_date.substring(4,6)+"-"+temp_date.substring(6,8)+"]</span>";
			  	   option = option +"<tr><td><img src='./resources/img/arrow.gif' width='5' height='8' border='0'/><a id='"+dataRow[i].examid+"' href='./html/publicInformation/web_content.html?examid="+dataRow[i].examid+"' title='"+dataRow[i].examName+"' target='_blank' >"+dataRow[i].examName+"</a></td><td class='text-right'>"+date+"</td></tr>";
			  }
//			  content = "<div class = 'index_title_src'>爱学习</div><div><a  href='./html/publicInformation/index_part.html?examType="+param+"' target='_blank' ><img src='./resources/img/public_01.gif' width='355' height='29' border='0'></a>" +
			  content = "<div class = 'index_title_src'>爱学习</div><div><a  href='./html/publicInformation/index_part.html?examType="+param+"' target='_blank' ><button class='btn btn-info  col-md-12 col-sm-12 text-justify'>爱学习</button></a>" +
			  "</div>" + option+"</table>";
			  $("#axx").empty().append(content);
			  
	   }
	});
	
	
	$.ajax({
	   type: "POST",
	   url:"./public/showExamInformationTopFive?random"+parseInt(Math.random()*100000),
       dataType:'json',
       async:true, 
   	   data: {
			examType:'考公务'
	   },
	   success: function(data){
			  var dataRow = data.data;
			    var date = "<p  height='29'>&nbsp;&nbsp;</p>";
			  var content = "";
			  var option = "";
			   var param = escape("考公务");
			  for(var i = 0 ; i < dataRow.length;i++){
			option = option + "<p><img src='./resources/img/arrow.gif' width='5' height='8' border='0'/><a id='"+dataRow[i].examid+"' href='./html/publicInformation/web_content.html?examid="+dataRow[i].examid+"' title='"+dataRow[i].examName+"' target='_blank' >"+dataRow[i].examName+"</a>"+date+"</p>";			  }
//			  content = "<div class = 'index_title_src'>考公务</div><div><a href='./html/publicInformation/index_part.html?examType="+param+"' target='_blank' ><img src='./resources/img/public_01.gif' width='383' height='29' border='0'></a></div>" + option;
			  content = "<div class = 'index_title_src'>考公务</div><div><a href='./html/publicInformation/index_part.html?examType="+param+"' target='_blank' ><button class='btn btn-info  col-md-12 col-sm-12'>考公务</button></a></div>" + option;
			  $("#kgw").empty().append(content);
	   }
	});
	

	$.ajax({
	   type: "POST",
	   url:"./public/showExamInformationTopFive?random"+parseInt(Math.random()*100000),
       dataType:'json',
       async:true, 
   	   data: {
			examType:'考驾照'
	   },
	   success: function(data){
			  var dataRow = data.data;
			  var content = "";
			    var date = "<p  height='29'>&nbsp;&nbsp;</p>";
			  var option = "";
			    var param = escape("考驾照");
			  for(var i = 0 ; i < dataRow.length;i++){
			  option = option + "<p><img src='./resources/img/arrow.gif' width='5' height='8' border='0'/><a id='"+dataRow[i].examid+"' href='./html/publicInformation/web_content.html?examid="+dataRow[i].examid+"' title='"+dataRow[i].examName+"' target='_blank' >"+dataRow[i].examName+"</a>"+date+"</p>";			  }
			  content = "<div class = 'index_title_src'>考驾照</div><div><a href='./html/publicInformation/index_part.html?examType="+param+"' target='_blank' ><button class='btn btn-info  col-md-12 col-sm-12'>考驾照</button></a></div>" + option;
			  $("#kjz").empty().append(content);
	   }
	});
	
	
	$.ajax({
	   type: "POST",
	   url:"./public/showExamInformationTopFive?random"+parseInt(Math.random()*100000),
       dataType:'json',
       async:true, 
   	   data: {
			examType:'趣味题'
	   },
	   success: function(data){
			  var dataRow = data.data;
			  var index_zxta="";
			    var date = "<p  height='29'>&nbsp;&nbsp;</p>";
			  var content = "";
			  var option = "";
			      var param = escape("趣味题");
			  var subject_option="";
			  for(var i = 0 ; i < dataRow.length;i++){
				option = option + "<p><img src='./resources/img/arrow.gif' width='5' height='8' border='0'/><a id='"+dataRow[i].examid+"' href='./html/publicInformation/web_content.html?examid="+dataRow[i].examid+"' title='"+dataRow[i].examName+"' target='_blank' >"+dataRow[i].examName+"</a>"+date+"</p>";			  	   subject_option = subject_option + "<p class='col-lg-12 col-md-12 text-overflow' ><img src='./resources/img/arrow.gif' width='5' height='8' border='0'/><a  id='"+dataRow[i].articleId+"' href='./html/publicInformation/web_content.html?articleId="+dataRow[i].articleId+"' title='"+dataRow[i].articleTitle+"' target='_blank' >"+dataRow[i].articleTitle+"</a><p>";
			  }
			 content = "<div class = 'index_title_src'>趣味题</div><div><a href='./html/publicInformation/index_part.html?examType="+param+"' target='_blank' ><button class='btn btn-info  col-md-12 col-sm-12'>趣味题</button></a></div>" + option;
			  $("#qwt").empty().append(content);
	   }
	});
	
	
	$.ajax({
	   type: "POST",
	   url:"./public/showExamInformationTopFive?random"+parseInt(Math.random()*100000),
       dataType:'json',
       async:true, 
   	   data: {
			examType:'玩英语'
	   },
	   success: function(data){
			  var dataRow = data.data;
			  var content = "";
			    var date = "<p  height='29'>&nbsp;&nbsp;</p>";
			  var option = "";
			     var param = escape("玩英语");
			  for(var i = 0 ; i < dataRow.length;i++){
option = option + "<p><img src='./resources/img/arrow.gif' width='5' height='8' border='0'/><a id='"+dataRow[i].examid+"' href='./html/publicInformation/web_content.html?examid="+dataRow[i].examid+"' title='"+dataRow[i].examName+"' target='_blank' >"+dataRow[i].examName+"</a>"+date+"</p>";			  }
			  content = "<div class = 'index_title_src'>玩英语</div><div><a href='./html/publicInformation/index_part.html?examType="+param+"' target='_blank' ><button class='btn btn-info  col-md-12 col-sm-12'>玩英语</button></a></div>" + option;
			  $("#wyy").empty().append(content);
	   }
	});
		
	
});

