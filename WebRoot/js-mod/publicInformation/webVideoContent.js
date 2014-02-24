
$(function() {
	
		function getUrlParam(name)
		{
			var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
			var r = window.location.search.substr(1).match(reg);  //匹配目标参数
			if (r!=null) return unescape(r[2]); return null; //返回参数值
		} 
	
		$("#index_nav").attr("href","./../../welcome.html");
		$("#axx_nav > a").attr("href","./index_part.html?examType="+escape('爱学习'));
		$("#kgw_nav > a").attr("href","./index_part.html?examType="+escape('考公务'));
		$("#kjz_nav > a").attr("href","./index_part.html?examType="+escape('考驾照'));
		$("#qwt_nav > a").attr("href","./index_part.html?examType="+escape('趣味题'));
		$("#wyy_nav > a").attr("href","./index_part.html?examType="+escape('玩英语'));
		$("#mytest_nav > a").attr("href","./../../login.html");
		
		
		
	var zNodes = "";
	var curMenu = null, zTree_Menu = null;
	
	var setting = {
		view: {
			showLine : true,
			selectedMulti: false,
			dblClickExpand: true
		},
//		check: {
//			enable: true,
//			chkStyle: "radio",
//			radioType: "all"
//		},
		data: {
			simpleData: {
			 	idKey:"id",
            	pIdKey:"pid",
				enable: true
			}
		},
		callback: {
			onClick:onClickTree
		}
	};
	
	
	
	function onClickTree(e, treeId, treeNode) {
			stream = {
				m4v: treeNode.videoUrl,
				ogv: treeNode.videoUrl,
				webmv:treeNode.videoUrl,
				poster: "http://www.jplayer.org/video/poster/Big_Buck_Bunny_Trailer_480x270.png"
			};
			$("#jquery_jplayer_1").jPlayer("destroy");
			
			$("#jquery_jplayer_1").jPlayer({
				ready: function () {
					$(this).jPlayer("setMedia",
						stream
					);
				},
				swfPath: "../../resources/js/jPlay/js/",
				supplied: "webmv, ogv, m4v",
				size: {
					width: "640px",
					height: "360px",
					cssClass: "jp-video-360p"
				},
				smoothPlayBar: true,
				keyEnabled: true
			});
			
			$("#jp-title_li").empty().append(treeNode.bak);
			
	}
	
	
	
	var stream =  {
				poster: "http://www.jplayer.org/video/poster/Big_Buck_Bunny_Trailer_480x270.png"
			};


	$.ajax({
	   type: "POST",
	   url:"../../public/queryVideoTree?random"+parseInt(Math.random()*100000),
       dataType:'json',
       async:false, 
	   success: function(data){
			zNodes = data.tree;
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	   }
	});
	
	
	zTree_Menu = $.fn.zTree.getZTreeObj("treeDemo");
	curMenu = zTree_Menu.getNodes()[0].children[0].children[0];
	zTree_Menu.selectNode(curMenu);
	var anode = $("#" + zTree_Menu.getNodes()[0].tId + "_a");
	anode.addClass("cur");
	
	
	$("#jquery_jplayer_1").jPlayer({
		ready: function () {
			$(this).jPlayer("setMedia",
				stream
			);
		},
		swfPath: "../../resources/js/jPlay/js/",
		supplied: "webmv, ogv, m4v",
		size: {
			width: "640px",
			height: "360px",
			cssClass: "jp-video-360p"
		},
		smoothPlayBar: true,
		keyEnabled: true
	});
	
});

