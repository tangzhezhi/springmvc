
$(function() {
	$('#dg').datagrid({
	    url:"../../video/queryVideo?random"+parseInt(Math.random()*100000),
	    method:"POST",
	    showFooter: true,
	    loadMsg:"请稍等.....",
	    rownumbers:true,
	    singleSelect:true,
	    checkOnSelect:true,
	    queryParams:{},
	    idField:'videoid',
		 width:'100%',    
		 height:'auto',
		 fitColumns:true,
		 pagination:true,
	    pageNumber:1,
	    pageSize:10,
	    pageList:[10,20,30,40,50],
	    beforePageText: '第',//页数文本框前显示的汉字  
        afterPageText: '页    共 {pages} 页',  
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
	    columns:[[
	    	 	  {field:'videoid',title:'视频ID',width:$(this).width()*0.15,hidden:true},
	    	 	  {field:'videoName',title:'视频名称',width:$(this).width()*0.15},
	    	 	  {field:'videoUrl',title:'视频URL',width:$(this).width()*0.15},
	    	 	  {field:'videoSubject',title:'视频类型',width:$(this).width()*0.15},
	    	 	  {field:'videoTime',title:'发行年份',width:$(this).width()*0.15},
	    	 	  {field:'userid',title:'用户ID',width:$(this).width()*0.15,hidden:true},
	    	 	  {field:'createtime',title:'创建时间',width:$(this).width()*0.15},
	    	 	  {field:'clickNum',title:'点击数',width:$(this).width()*0.15},
	    	 	  {field:'videoDuration',title:'视频时长',width:$(this).width()*0.15},
	    	 	  {field:'videoRecommend',title:'视频介绍',width:$(this).width()*0.15}
	    ]],
	     onLoadError:function(){
                  alert('','加载数据失败！');
         },
         onLoadSuccess:function(){
        	 $('#dg').datagrid('options').pagination=true;
         }
	});
	
	
		
		function chosenAjaxData(id,code){
			var contents = "";
			$.ajax({
			   type: "POST",
			   url:"../../common/queryDictory?random"+parseInt(Math.random()*100000),
	           dataType:'json',
	           async:false, 
	           data:{
					code:code,
					page:1,
					rows:99999
				},
			   success: function(data){
					var selectObj = $('#'+id); 
					for(var i=0;i<data.rows.length;i++){
						contents = contents +"<option value='"+data.rows[i].name+"'>"+data.rows[i].name+"</option>";
	                } 
					selectObj.append(contents);
			   }
			});
		}
	
	
	
	
	
	$("#query").click(
		function () {
			$('#dg').datagrid('reload',{
				videoname: $('#videoname').val()
			}).datagrid("clearSelections");
		});
	
	
	
	$("#add").click(
		function () {
			$('#myModal').modal({
  				keyboard: false
			});
			
			$("#dg").datagrid("clearSelections");
			$('#modal_form')[0].reset();
			
			$('#videoSubjectModal').empty();
			chosenAjaxData('videoSubjectModal','video_type');
			$('#videoSubjectModal').chosen().trigger("chosen:updated");
			$('#videoSubjectModal_chosen .chosen-single > span').empty().append("<span>请选择视频类型</span>");
			$('#videoSubjectModal_chosen').removeAttr("style"); 
			
	});
	
	
	$("#modify").click(
		function () {
			var row = $("#dg").datagrid("getSelected");
			
			if(row == null || row == "undefined"){
				$.messager.alert("操作提示", "请选择一行记录","info");
			}
			else{
		    	 	  $("#videoidModal").val(row.videoid);
		    	 	  $("#videoNameModal").val(row.videoName);
		    	 	  $("#videoUrlModal").val(row.videoUrl);
		    	 	  $("#useridModal").val(row.userid);
		    	 	  $("#createtimeModal").val(row.createtime);
		    	 	  $("#clickNumModal").val(row.clickNum);
		    	 	  $("#videoSubjectModal").val(row.videoSubject);
		    	 	  $("#videoTimeModal").val(row.videoTime);
		    	 	  $("#videoDurationModal").val(row.videoDuration);
		    	 	  $("#videoRecommendModal").val(row.videoRecommend);
				$('#myModal').modal({
	  				keyboard: false
				});
				
				 $('#videoSubjectModal').empty().append("<option value='"+row.videoSubject+"'>"+row.videoSubject+"</option>");
				 chosenAjaxData('videoSubjectModal','video_type');
				  $('#videoSubjectModal').chosen().trigger("chosen:updated");
				
			}
	});
	
	/**
	 * 新增或者更新
	 */
	$("#add_modify_modal").click(
		function (){
			var row = $("#dg").datagrid("getSelected");
			
			//新增
			if(row == null ||  row == "undefined"){
				var videoid = $("#videoidModal").val();
				var videoName = $("#videoNameModal").val();
				var videoUrl = $("#videoUrlModal").val();
				var userid = $("#useridModal").val();
				var createtime = $("#createtimeModal").val();
				var clickNum = $("#clickNumModal").val();
				var videoDuration = $("#videoDurationModal").val();
				var videoRecommend = $("#videoRecommendModal").val();
				var	videoSubject = $("#videoSubjectModal").val();
		    	var videoTime = $("#videoTimeModal").val();
				
				$.ajax({
				   type: "POST",
				   url: "../../video/addVideo?random"+parseInt(Math.random()*100000),
				   data: {
							videoid:videoid,
							videoName:videoName,
							videoUrl:videoUrl,
							userid:userid,
							createtime:createtime,
							clickNum:clickNum,
							videoDuration:videoDuration,
							videoRecommend:videoRecommend,
							videoSubject:videoSubject,
							videoTime:videoTime
				   },
				   success: function(msg){
				     $.messager.alert("操作提示", $.parseJSON(msg),"info");
				     $('#dg').datagrid('reload');
				     $("#dg").datagrid("clearSelections");
				   }
				});
			}
			else{
				var videoid = row.videoid;
				var videoid = $("#videoidModal").val();
				var videoName = $("#videoNameModal").val();
				var videoUrl = $("#videoUrlModal").val();
				var userid = $("#useridModal").val();
				var createtime = $("#createtimeModal").val();
				var clickNum = $("#clickNumModal").val();
				var videoDuration = $("#videoDurationModal").val();
				var videoRecommend = $("#videoRecommendModal").val();
				var	videoSubject = $("#videoSubjectModal").val();
		    	var videoTime = $("#videoTimeModal").val();
		    	
				$.ajax({
				   type: "POST",
				   url: "../../video/modifyVideo?random"+parseInt(Math.random()*100000),
				   data: {
							videoid:videoid,
							videoName:videoName,
							videoUrl:videoUrl,
							userid:userid,
							createtime:createtime,
							clickNum:clickNum,
							videoDuration:videoDuration,
							videoRecommend:videoRecommend,
							videoSubject:videoSubject,
							videoTime:videoTime
				   },
				   success: function(msg){
				     $('#dg').datagrid('reload');
				     $("#dg").datagrid("clearSelections");
				     $('#myModal').modal('hide');
				     $.messager.alert("操作提示", $.parseJSON(msg),"info");
				   }
				});
			}
		}
	);
	
	
	
	
	$("#delete").click(
		function () {
			var row = $("#dg").datagrid("getSelected");
			if(row == null || row == "undefined"){
				$.messager.alert("操作提示", "请选择一行记录","info");
			}
			else{
				 $.messager.confirm("操作提示", "您确定要执行操作吗？", function (data) {
		         	if (data) {
		         		$.ajax({
						   type: "POST",
						   url: "../../video/deleteVideo?random"+parseInt(Math.random()*100000),
						   data: {
								videoid:row.videoid
						   },
						   success: function(msg){
						     $.messager.alert("操作提示", $.parseJSON(msg),"info");
						     $('#dg').datagrid('reload');
						     $("#dg").datagrid("clearSelections");
						   }
						});
		            }
		            else {
		                return false;
		            }
		         });
			}
			
	});
	
	
});

