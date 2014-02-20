
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
	    	 	  {field:'videoid',title:'视频ID',width:$(this).width()*0.15},
	    	 	  {field:'videoName',title:'视频名称',width:$(this).width()*0.15},
	    	 	  {field:'videoUrl',title:'视频URL',width:$(this).width()*0.15},
	    	 	  {field:'userid',title:'用户ID',width:$(this).width()*0.15},
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
		    	 	  $("#videoDurationModal").val(row.videoDuration);
		    	 	  $("#videoRecommendModal").val(row.videoRecommend);
				$('#myModal').modal({
	  				keyboard: false
				});
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
							videoRecommend:videoRecommend
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
							videoRecommend:videoRecommend
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

