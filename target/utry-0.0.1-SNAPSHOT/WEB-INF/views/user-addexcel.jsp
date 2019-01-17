<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8"></meta>
    <title>
      导入表格
    </title>
</head>
<body>
    <form method="post" enctype="multipart/form-data" action="" style="margin:10px;">
     
		<div>
		<input id="file" name="file" type="file" style="margin:10px;"/>
		</div>

	<input type="button" onclick="psubmit()" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
   </form>
<script  type="text/javascript">
function psubmit(){
	/* var fileObj = document.getElementById("file").files[0];
	var formFile = new FormData(); 
    formFile.append("file", fileObj); //加入文件对象 */
    
	var file = $("#file").val();
	if(file==''){
		alert('请先选择文件');
	}else{
	    /* $.ajax({ 
	    	url: '/page/user/upload', 
	    	data: formFile,
            type: "Post",
            dataType: "json",
            cache: false,//上传文件无需缓存
            processData: false,//用于对data参数进行序列化处理 这里必须false
            contentType: false, //必须
            success: function (result) {
                alert("上传完成!");
            }
	    }); */
	    $.ajaxFileUpload({
	    	url: 'http://localhost:8071/page/user/upload', 
	        secureuri: false, //一般设置为false
	        fileElementId: 'file', // 上传文件的id、name属性名
	        data: {},
	        dataType: 'json', //返回值类型，一般设置为json、application/json
	        //elementIds: elementIds, //传递参数到服务器
	        success: function(data, status){  
	        	alert(data);
	        	
	        },
	        error: function(data, status, e){ 
	        	//alert(data);
	        }
	    });
	}
}

</script>
</body>
</html>