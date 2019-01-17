<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8"></meta>
    <title>
        添加/修改信息
    </title>
</head>
<body>

<div class="page-container" style="margin: 10px;">
	<div class="text-c"> 
	<form id="ff" style="margin: 10px;">
		<input type="text" style="width:150px" placeholder="用户名" id="name" name="name">
		<input type="text" style="width:180px" placeholder="电话" id="telephone1" name="telephone">
	</form>
	<div id="btnsubmit" style="margin: 10px;">
		<button id="submitForm" onclick="submitForm()">提交查询</button>
		<button id="downloadfile" onclick="downloadfile()">数据导出excel</button>
		<button id="userdelete" onclick="userdelete()">数据删除</button>
		<button id="downloadfiletxt" onclick="downloadfiletxt()">数据导出txt</button>
	</div>
	
	</div>
	
<div class="mt-20" style="margin: 10px;">
		<table id="table1" class="table table-border table-bordered table-bg table-hover table-sort"
		  data-page=0>
			<thead>
				<tr class="text-c">
				<th width="25"><input type="checkbox" name="" value=""></th>
				<th width="50">ID</th>
				<th width="80">姓名</th>
				<th width="80">昵称</th>
				<th width="150">电话</th>
				<th width="150">邮箱</th>
				</tr>
			</thead>
			<tbody id="tbody">
				
			</tbody>
		</table>
		<div class="page" style="margin: 10px;">
			<button id='total' value='1'>共0条记录</button>
			<button id='first' value='2'>首页</button>
			<button id='pre' value='3'>上一页</button>
			<button id='now' value='4'>第0页</button>
			<button id='next' value='5'>下一页</button>
			<button id='last' value='6'>尾页</button>
			<button id='pageNum' value='7'>共0页</button>
		</div>
	</div>
</div>


<script  type="text/javascript">
$(function(){
	$("#first,#pre,#next,#last").on("click",function(){
		var that=this;
		var name=$("#name").val();
		var telephone=$("#telephone1").val();
		
		var param={
				"name":name,
				"telephone":telephone
		}
		
		var total=0;
		var count=5;
		var pageNum=0;
		var val=that.value;
		var pagenow=0;
		
		$.get("/page/user/userfind",param,function(data){
			total=data.length;
			if(total>0){
				pageNum=Math.floor((total-1)/count)+1;
			
			
			var page=$("#table1").data("page");
			
			$("#total").html("共"+total+"条记录");
			
			if(val=='2'){
				$("#table1").data("page",1);
			}if(val=='3'){
				if($("#table1").data("page")>1){
					$("#table1").data("page",page-1);
				}else{
					$("#table1").data("page",1);
				}
			}if(val=='5'){
				
				if($("#table1").data("page")<pageNum){
					$("#table1").data("page",page+1);
				}else{
					
					$("#table1").data("page",pageNum);
				}
			}if(val=='6'){
				$("#table1").data("page",pageNum);
			}
			
			pagenow=$("#table1").data("page");
			
			$("#now").html("第"+pagenow+"页");
			$("#pageNum").html("共"+pageNum+"页");
			
	    	$('#tbody').html("");
	    	for(var i=(pagenow-1)*count;i<pagenow*count;i++)
	    	{  
		        $('#tbody').append('<tr><td>'+'<input type="checkbox">'
		        +'</td><td>'+data[i].id+'</td><td>'+data[i].name
		        +'</td><td>'+data[i].nikeName
		        +'</td><td>'+data[i].telephone
		        +'</td><td>'+data[i].email+'</td></tr>');
	    	}
			
			}
			else{
				alert("您搜索用户不存在");
			}
		})	

	});
})

function downloadfiletxt(){
	var name=$("#name").val();
	var telephone=$("#telephone1").val();
	
	window.location.href="/file/userdownloadfiletxt?name="+name
			+"&telephone="+telephone;
}
    
function downloadfile(){

	var name=$("#name").val();
	var telephone=$("#telephone1").val();
	
	window.location.href="/file/userdownloadfile?name="+name
			+"&telephone="+telephone;
}

function userdelete(){
	var name=$("#name").val();
	var telephone=$("#telephone1").val();
	
	var param={
			"name":name,
			"telephone":telephone
	}
	
	$.get("/page/user/userdelete", param, function(data) {
		alert(data);
	
	});
	
}
function submitForm(){
	var name=$("#name").val();
	var telephone=$("#telephone1").val();
	
	var param={
			"name":name,
			"telephone":telephone
	}
	
	$.ajax({
	    type: 'get',
	    url:"/page/user/userfind",
	    data: param,
	    dataType:"json",
	    success: function(data) {
	    	$("#total").html("共"+data.length+"条记录");
	    	
			if(data.length>0){
				var pageNum=Math.floor((data.length-1)/5)+1;
				$("#now").html("第"+1+"页");
				$("#pageNum").html("共"+pageNum+"页");
			}else{
				$("#now").html("第"+0+"页");
				$("#pageNum").html("共"+0+"页");
				alert("您搜索用户不存在");
			}
			

	    	//var jsonMsg=JSON.parse(data);
	    	$('#tbody').html("");
	    	for(var i=0;i<data.length&&i<5;i++)
	    	{  
		        $('#tbody').append('<tr><td>'+'<input type="checkbox">'
		        +'</td><td>'+data[i].id+'</td><td>'+data[i].name
		        +'</td><td>'+data[i].nikeName
		        +'</td><td>'+data[i].telephone
		        +'</td><td>'+data[i].email+'</td></tr>');
	    	}
	    }   
	}); 
	
}


</script>
</body>
</html>