<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8"></meta>
  查询数据
    </title>
</head>
<body>

<div class="page-container" style="margin: 10px;">
	<div class="text-c"> 
	<form id="ff" style="margin: 10px;">
		<input type="text" style="width:200px" placeholder="" id="arraynumber" name="arraynumber">
	</form>
	<div id="btnsubmit" style="margin: 10px;">
		<button id="submitForm" onclick="submitForm()">提交查询</button>
		<button id="downloadnumber" onclick="downloadnumber()">数据导出excel</button>
		<button id="numberdelete" onclick="numberdelete()">删除单个组合</button>
		<button id="numberdeleteall" onclick="numberdeleteall()">删除所有组合</button>
	</div>
	
	</div>
	
<div class="mt-20" style="margin: 10px;">
		<table id="table1" class="table table-border table-bordered table-bg table-hover table-sort"
		  data-page=0>
			<thead>
				<tr class="text-c">
				<th width="25"><input type="checkbox" name="" value=""></th>
				<th width="50">ID</th>
				<th width="300">数字组合</th>
				<th width="100">创建时间</th>
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
		var arraynum=$("#arraynumber").val();
		
			var param={
					"arraynum":arraynum
			}
			
			var total=0;
			var count=10;
			var pageNum=0;
			var val=that.value;
			var pagenow=0;
			
			$.get("/number/list",param,function(data){
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
			        +'</td><td>'+data[i].id+'</td><td>'+data[i].arraynum
			        +'</td><td>'+data[i].creattime+'</td></tr>');
		    	}
				
				}
				else{
					alert("您搜索用户不存在");
				}
			})	

		
	});
})


function downloadnumber(){

	var arraynum = $("#arraynumber").val();
	window.location.href="/file/downloadnumber?arraynum="+arraynum;
	
	

}

function numberdelete(){
	var arraynum = $("#arraynumber").val();
	var param={
			"arraynum":arraynum
	}
	
	if(arraynum==''){
		alert("请输入要删除的数据");
		
	}else{
		$.get("/number/numberdelete", param, function(data) {
			alert(data);
		});
	}
	
}

function numberdeleteall(){
	var arraynum = $("#arraynumber").val();
	var param={
			"arraynum":arraynum
	}
	
	if(arraynum==''){
		alert("请输入要删除的数据");
		
	}else{
		$.get("/number/numberdeleteall", param, function(data) {
			alert(data);
		});
	}
	
}


function submitForm(){
	var arraynum = $("#arraynumber").val();
	
		var param={
				"arraynum":arraynum
		}
		
		$.ajax({
		    type: 'get',
		    url:"/number/list",
		    data: param,
		    dataType:"json",
		    success: function(data) {
		    	$("#total").html("共"+data.length+"条记录");
		    	
				if(data.length>0){
					var pageNum=Math.floor((data.length-1)/10)+1;
					$("#now").html("第"+1+"页");
					$("#pageNum").html("共"+pageNum+"页");
				}else{
					$("#now").html("第"+0+"页");
					$("#pageNum").html("共"+0+"页");
					alert("您搜索用户不存在");
				}
				

		    	//var jsonMsg=JSON.parse(data);
		    	$('#tbody').html("");
		    	for(var i=0;i<data.length&&i<10;i++)
		    	{  
			        $('#tbody').append('<tr><td>'+'<input type="checkbox">'
					        +'</td><td>'+data[i].id+'</td><td>'+data[i].arraynum
					        +'</td><td>'+data[i].creattime+'</td></tr>');
		    	}
		    }   
		}); 

	
}


</script>
</body>
</html>