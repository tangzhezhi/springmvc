
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
	var stream =  {
				poster: "http://www.jplayer.org/video/poster/Big_Buck_Bunny_Trailer_480x270.png"
			};
	var setting = {
		view: {
			selectedMulti: false
		},
		check: {
			enable: true,
			chkStyle: "radio",
			radioType: "level"
		},
		data: {
			simpleData: {
			 	idKey:"id",
            	pIdKey:"pid",
				enable: true
			}
		},
		callback: {
			onCheck: onCheck
		}
	};
//	
//	
	function beforeCheck(treeId, treeNode){
		var contents = " ";
		 $("#content").empty().append(contents);
	}
	
	
	function onCheck(e, treeId, treeNode) {
		stream = {
				m4v: treeNode.url,
				ogv: treeNode.url,
				webmv:treeNode.url,
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
		
	}

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
	
	
	
	
//	var stream = {
//		  title: "流音乐测试",
//		  webm:"http://www.jplayer.org/video/webm/Big_Buck_Bunny_Trailer.webm",
//		  m4a:"http://www.jplayer.org/audio/m4a/TSP-01-Cro_magnon_man.m4a",
//		  oga:"http://www.jplayer.org/audio/ogg/TSP-01-Cro_magnon_man.ogg"
//	 	};
		
		
//		$("#jquery_jplayer_1").jPlayer({
//			ready: function () {
//				$(this).jPlayer("setMedia", stream);
//			},
//			swfPath: "../../resources/js/jPlay/js/",
//			supplied: "webmv, ogv, m4v",
//			size: {
//				width: "640px",
//				height: "360px",
//				cssClass: "jp-video-360p"
//			},
//			smoothPlayBar: true,
//			keyEnabled: true
//		});
		
		
//		$("#jquery_jplayer_1").jPlayer({
//		   ready: function () {
//		    $(this).jPlayer("setMedia", {
//		     webm: "http://www.jplayer.org/video/webm/Big_Buck_Bunny_Trailer.webm"
//		    });
//		   },
//		   swfPath: "../../resources/js/jPlay/",
//		   supplied: "webm,m4a, oga"
//  		});
		
//		var stream = {
//		  title: "流音乐测试",
////		  mp3: "http://listen.radionomy.com/abc-jazz"
//		  webm:"http://www.jplayer.org/video/webm/Big_Buck_Bunny_Trailer.webm",
//		  m4a:"http://www.jplayer.org/audio/m4a/TSP-01-Cro_magnon_man.m4a",
//		  oga:"http://www.jplayer.org/audio/ogg/TSP-01-Cro_magnon_man.ogg"
//		 },
//		 ready = false;
////		
//		 $("#jquery_jplayer_1").jPlayer({
//			  ready: function (event) {
//			   ready = true;
//			   $(this).jPlayer("setMedia", stream);
//			  },
//			  pause: function() {
//			   $(this).jPlayer("clearMedia");
//			  },
//			  error: function(event) {
//			   if(ready && event.jPlayer.error.type === $.jPlayer.error.URL_NOT_SET) {
//			    // Setup the media stream again and play it.
//			    $(this).jPlayer("setMedia", stream).jPlayer("play");
//			   }
//			  },
//			  swfPath: "../../resources/js/jPlay/",
//			  supplied: "webm,mp3",
//			  preload: "none"
// 		});
		
		
		
		
		
//	$("#jquery_jplayer_1").jPlayer({
//		ready: function (event) {
//			$(this).jPlayer("setMedia", {
//				m4a:"http://www.jplayer.org/audio/m4a/TSP-01-Cro_magnon_man.m4a",
//				oga:"http://www.jplayer.org/audio/ogg/TSP-01-Cro_magnon_man.ogg"
//			});
//		},
//		swfPath: "js",
//		supplied: "m4a, oga",
//		wmode: "window",
//		smoothPlayBar: true,
//		keyEnabled: true
//	});
	
	
		
});

