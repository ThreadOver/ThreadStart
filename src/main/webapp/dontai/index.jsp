<%@ page  contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page  import="instance.*,java.util.*,JDBC.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=session.getAttribute("user")%><%="的主页" %></title>
<style>
*{margin:0;padding:0}
body{font-size:14px;font-family:"微软雅黑";background:url("images/2.jpg") top no-repeat;background-attachment:fixed;z-index:0;background-size:100%;}
.box{width:960px;height:2000px;margin:100px auto;position:relative;z-index:1;}

/*内容发布区域*/
.box .boxcenter{width:500px;height:200px;background:#fff;position:absolute;top:80px;left:180px;border:1px solid green;border-radius:6px;}
.box .boxcenter .boxc_t{height:30px;line-height:30px;}
.box .boxcenter .boxc_t h4{padding-left:20px;background:#3BBFB4;border-top-left-radius:6px;border-top-right-radius:6px;}
.box .boxcenter .boxc_c{width:460px;height:100px;border:1px solid #A6C8FF;margin:8px auto;text-indent:10px;box-shadow:0 0 4px #A6C8FF;}
.box .boxcenter .boxc_b{width:80px;height:30px;position:absolute;right:10px;bottom:8px;}
.box .boxcenter .boxc_b a{font-size:14px;color:#fff;line-height:30px;background:#3bbfb4;border-radius:6px;display:block;text-align:center;text-decoration:none;}
.box .boxcenter .boxc_b a:hover{background:#2C8E86;}
.box .timeline{width:60px;height:100%;position:absolute;top:100px;left:80px;border-right:2px solid #5d7895;}/**/
.timeline .timeline_t{width:50px;height:50px;border-radius:50%;border:1px solid #fff;background-size:100%;}
/* .timeline .nextbox{width:500px;height:500px;position:absolute;top:260px;left:60px;} */
.timeline .nextbox{width:300px;height:300px;position:absolute;top:260px;left:60px;}
.a{width:380px;height:80px;background:#fff;border-radius:6px;margin-top:30px;font-size:16px;line-height:20px;text-indent:20px;word-break:break-all;position:relative;left:6px;}
.a .b{width:6px;height:6px;border-radius:50%;border:2px solid #fff;background:#9DCFE1;position:absolute;top:35px;left:-10px;}
#time{font-size:20px;color:#ababab;}
#hour{font-size:12px;color:#92CADE;}
.file-box {
    position: relative;
    display: inline-block;
}
.file-box img {
    width: 50px;
    height: 50px;
    border-radius: 50%;
    position: absolute;
    top: -30px;
    left: 0;
    display: inline-block;
    border: none;
}
.file-box .txt,.file-box .file {
    width: 70px;
    height: 36px;
    position: absolute;
    top: -20px;
    left: 100px;
    text-align: center;
}
.img{
	width: 100%;
	height: 100%;
	}
</style>
</head>
<body>

<div class="box">

	<!--内容发布区域-->
	<div class="boxcenter">
		<div class="boxc_t"><h4>美好的回忆</h4></div>
		
		<div class="boxc_c"  id="aa">
		<img id="preview" />
		</div>
		<form action="../pic" method="post" enctype="multipart/form-data">
		<input type="file" name="file" id = "input_file" accept="image/gif,image/jpeg,image/jpg,image/png,image/svg" onchange="imgPreview(this)" >
		<input class="boxc_b" type="submit"  value="上传图片">
		
		</form>
		<!-- <div class="boxc_b"><a href="#">发表</a></div> -->
    <!-- <input type="text" id="imgfield" class="txt" placeholder="预览">  -->
    
	</div> 
	<!--时光轴线-->
	<div class="timeline" background:url("images/100.png") no-repeat>
		<div class="timeline_t">				
		</div>
		<!-- <div class="nextbox"> -->
		<% 
			Perform pf = new Perform();
			String use = (String)session.getAttribute("user");
			int top = 260;
			List<Road> mks = new ArrayList<Road>();
			mks = pf.readRord(use);
			for(Road road : mks){
				 String path = road.getRoad();%>
		
		<div style="width:700px;height:300px;position:absolute;top:<%=top%>px;left:60px;">
		<img class="img" alt="<%=road.getUsername() %>"  src="<%=path%>"></div>
				 	<%		
				 	top=top+320;
			}
		
		%>
		</div>
	</div>
	
<!-- </div> -->

<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript">	
function imgPreview(fileDom) {
    //判断是否支持FileReader
    if(window.FileReader) {
        var reader = new FileReader();
    } else {
        alert("您的设备不支持图片预览功能，如需该功能请升级您的设备！");
    }
    //获取文件
    var file = fileDom.files[0];
    var imageType = /^image\//;
    //是否是图片
    if(!imageType.test(file.type)) {
        alert("请选择图片！");
        return;
    }
    //读取完成
    reader.onload = function(e) {
        //获取图片dom
        var img = document.getElementById("preview");
        //图片路径设置为读取的图片
        img.src = e.target.result;
    };
    reader.readAsDataURL(file);
}
$(function(){
	$(".box").find(".boxc_b").click(function(){
		var center = $(".boxc_c").text();//.appendTo("nextbox");//修改路径
		if(center==""){
			alert("内容无法为空");
			return;
		}
		$(".nextbox").prepend("<div class='a'>"+
			"<div class='b'></div>"+
			"<span id='time'>"+year+"-"+
			month+"-"+
			day+"&nbsp;&nbsp;"+
			"<span id='hour'>"+hour+":"+min+"</span>"+
			"</span>"+
			"<br>"+
			"<p style='padding:4px'>"+center+"</p>"+
			"</div>");
		$(".boxc_c").text("");	
	});
	//alert(1);
	$(".boxc_c").keydown(function(event){					
		var len =$(".boxc_c").text().length;			
		if(len > 75){
		alert("超出字数限制");					
		}
	});

	var dateDom = new Date();
	//获取本地时间，年月日时分
	var year = dateDom.getFullYear();
	var month = dateDom.getMonth()+1;
	var day = dateDom.getDate();
	var hour = dateDom.getHours();
	var min = dateDom.getMinutes();
});
</script>

</body>
</html>