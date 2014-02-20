/**
 * JMEditor 0.9 beta
 * http://www.jmeditor.com/
 */
var JMEditor_BasePath = "";
var scriptEles=document.getElementsByTagName("script");
for(var i=0;i<scriptEles.length;i++){
	var m = scriptEles[i].src.match(/^(.*)JMEditor.js$/i);
	if(m){
		JMEditor_BasePath=m[1];
		break
	}
}

document.write(
"<link href=\"" + JMEditor_BasePath + "mathquill-0.9.1/mathquill.css\" rel=\"stylesheet\" type=\"text/css\" />" + 
"<script type=\"text/javascript\" src=\"" + JMEditor_BasePath + "/ckeditor.js\"></script>" +
"<script type=\"text/javascript\" src=\"" + JMEditor_BasePath + "mathquill-0.9.1/mathquill.min.js\"></script>"
);

var JMEditor = {};
$(document).ready(function(){
	JMEditor = {
		versionCode : 1,
		versionName : "V0.9",
		ckEditor : CKEDITOR,
		jmeBasePath : JMEditor_BasePath,
		defaultFontSize : "20px",
		isEmpty : function(elementId){
			return ($("#" + elementId).html()+"").replace(/(<[^>]*>|\s|&nbsp;)/ig,"").length < 1;
		},
		html : function(elementId){
			return $("#" + elementId).html();
		}
	};
});


