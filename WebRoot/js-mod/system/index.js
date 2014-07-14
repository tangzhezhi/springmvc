var _menus

$(function() {
	
	tabClose();
	tabCloseEven();
	
	$.ajax({
	   type: "POST",
	   url: "user/menu?random"+parseInt(Math.random()*100000),
	   success: function(msg){
			_menus = msg;
			
			var firstMenuName = $('#css3menu a:first').attr('name');
			
			if(_menus!=null && _menus != "undefined"){
				InitTopMenu(_menus.data[0].menus);
				addNav(_menus.data[0].menus);
				InitLeftMenu();
			}
	   }
	});


	// 导航菜单绑定初始化
	$("#menu").accordion( {
		animate : false
	});
});

function ChangeLeftMenu(topid)
{
		$('#css3menu a').removeClass('active');
		$.ajax({
			   type: "POST",
			   url: "user/menu?random"+parseInt(Math.random()*100000),
			   data:{
					topid:topid
			   },
			   success: function(msg){
					_menus = JSON.parse(msg);
					var d = _menus.data[0].menus;
					Clearnav();
					addNav(d);
					InitLeftMenu();
			   }
		});
}


function Clearnav() {
	var pp = $('#menu').accordion('panels');

	$.each(pp, function(i, n) {
		if (n) {
			var t = n.panel('options').title;
			$('#menu').accordion('remove', t);
		}
	});

	pp = $('#menu').accordion('getSelected');
	if (pp) {
		var title = pp.panel('options').title;
		$('#menu').accordion('remove', title);
	}
}

function addNav(data) {

	$.each(data, function(i, sm) {
		var menulist = "";
		menulist += '<ul>';
		$.each(sm.menus, function(j, o) {
			menulist += '<li><div><a rel="'+o.url+'" ref="'+o.menuid+'" href="#" icon="icon"><span class="icon ' + o.icon
					+ '" >&nbsp;</span><span class="mynav">' + o.menuname
					+ '</span></a></div></li> ';
		});
		menulist += '</ul>';
	
		$('#menu').accordion('add', {
			title : sm.menuname,
			content : menulist,
			iconCls : 'icon ' + sm.icon
		});
	});
	
	var pp = $('#menu').accordion('panels');
	var t = pp[0].panel('options').title;
	$('#menu').accordion('select', t);
}

function InitTopMenu(data){
	var topmenulist = "";
	$.each(data, function(i, sm) {
		topmenulist += '<li>';
		topmenulist += '<a class="topnav" title="'+sm.menuid+'" href="javascript:ChangeLeftMenu('+sm.menuid+');" name="'+sm.menuname+'">'+sm.menuname+'</a>';
		topmenulist += '</ll>';
	});
	$('#css3menu').html(topmenulist);
	$('#css3menu a:first').addClass("active");
}


// 初始化左侧
function InitLeftMenu() {
	hoverMenuItem();
	$('#menu li a').on('click', function() {
		var tabTitle = $(this).children('.mynav').text();
		var url = $(this).attr("rel");
		var menuid = $(this).attr("ref");
		var icon = $(this).attr("icon");
		addTab(tabTitle, url,icon);
		$('#menu li div').removeClass("selected");
		$(this).parent().addClass("selected");
	});

}

/**
 * 菜单项鼠标Hover
 */
function hoverMenuItem() {
	$(".easyui-accordion").find('a').hover(function() {
		$(this).parent().addClass("hover");
	}, function() {
		$(this).parent().removeClass("hover");
	});
}

function addTab(subtitle, url,icon) {
	if (!$('#tabs').tabs('exists', subtitle)) {
		$('#tabs').tabs('add', {
			title : subtitle,
			content : createFrame(url),
			closable : true,
			icon : icon
		});
		
		refreshTab({tabTitle:subtitle,url:url});
		
	} else {
		$('#tabs').tabs('select', subtitle);
		$('#mm-tabupdate').click();
	}
	tabClose();
}



function refreshTab(cfg){  
    var refresh_tab = cfg.tabTitle?$('#tabs').tabs('getTab',cfg.tabTitle):$('#tabs').tabs('getSelected');  
    if(refresh_tab && refresh_tab.find('iframe').length > 0){  
    var _refresh_ifram = refresh_tab.find('iframe')[0];  
    var refresh_url = cfg.url?cfg.url:_refresh_ifram.src;  
    _refresh_ifram.contentWindow.location.href=refresh_url;  
    }  
}  



function createFrame(url) {
	var heightpx = $(document.body).height()-100+"px";
	var s = '<iframe scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:'+heightpx+'"></iframe>';
	return s;
}

function tabClose() {
	/* 双击关闭TAB选项卡 */
	$(".tabs-inner").dblclick(function() {
		var subtitle = $(this).children(".tabs-closable").text();
		$('#tabs').tabs('close', subtitle);
	});
	/* 为选项卡绑定右键 */
	$(".tabs-inner").bind('contextmenu', function(e) {
		$('#mm').menu('show', {
			left : e.pageX,
			top : e.pageY
		});

		var subtitle = $(this).children(".tabs-closable").text();

		$('#mm').data("currtab", subtitle);
		$('#tabs').tabs('select', subtitle);
		return false;
	});
}
// 绑定右键菜单事件
function tabCloseEven() {
	// 刷新
	$('#mm-tabupdate').click(function() {
		var currTab = $('#tabs').tabs('getSelected');
		var url = $(currTab.panel('options').content).attr('src');
		$('#tabs').tabs('update', {
			tab : currTab,
			options : {
				content : createFrame(url)
			}
		});
	});
	// 关闭当前
	$('#mm-tabclose').click(function() {
		var currtab_title = $('#mm').data("currtab");
		$('#tabs').tabs('close', currtab_title);
	});
	// 全部关闭
	$('#mm-tabcloseall').click(function() {
		$('.tabs-inner span').each(function(i, n) {
			var t = $(n).text();
			$('#tabs').tabs('close', t);
		});
	});
	// 关闭除当前之外的TAB
	$('#mm-tabcloseother').click(function() {
		$('#mm-tabcloseright').click();
		$('#mm-tabcloseleft').click();
	});
	// 关闭当前右侧的TAB
	$('#mm-tabcloseright').click(function() {
		var nextall = $('.tabs-selected').nextAll();
		if (nextall.length == 0) {
			// msgShow('系统提示','后边没有啦~~','error');
			alert('后边没有啦~~');
			return false;
		}
		nextall.each(function(i, n) {
			var t = $('a:eq(0) span', $(n)).text();
			$('#tabs').tabs('close', t);
		});
		return false;
	});
	// 关闭当前左侧的TAB
	$('#mm-tabcloseleft').click(function() {
		var prevall = $('.tabs-selected').prevAll();
		if (prevall.length == 0) {
			alert('到头了，前边没有啦~~');
			return false;
		}
		prevall.each(function(i, n) {
			var t = $('a:eq(0) span', $(n)).text();
			$('#tabs').tabs('close', t);
		});
		return false;
	});

	// 退出
	$("#mm-exit").click(function() {
		$('#mm').menu('hide');
	});
}

// 弹出信息窗口 title:标题 msgString:提示信息 msgType:信息类型 [error,info,question,warning]
function msgShow(title, msgString, msgType) {
	$.messager.alert(title, msgString, msgType);
}

// 本地时钟
function clockon() {
	var now = new Date();
	var year = now.getFullYear(); // getFullYear getYear
	var month = now.getMonth();
	var date = now.getDate();
	var day = now.getDay();
	var hour = now.getHours();
	var minu = now.getMinutes();
	var sec = now.getSeconds();
	var week;
	month = month + 1;
	if (month < 10)
		month = "0" + month;
	if (date < 10)
		date = "0" + date;
	if (hour < 10)
		hour = "0" + hour;
	if (minu < 10)
		minu = "0" + minu;
	if (sec < 10)
		sec = "0" + sec;
	var arr_week = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六");
	week = arr_week[day];
	var time = "";
	time = year + "年" + month + "月" + date + "日" + " " + hour + ":" + minu
			+ ":" + sec + " " + week;

	$("#bgclock").html(time);

	var timer = setTimeout("clockon()", 200);
}
