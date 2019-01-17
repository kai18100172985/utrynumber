<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8"></meta>
    <title>
        生成数据
    </title>
</head>
<body>

<div class="page-container" style="margin: 10px;">
	<div class="text-c"> 
	<form id="ff" style="margin: 10px;">
		<input type="text" style="width:300px" placeholder="" id="number" name="number">
	</form>
	<div id="btnsubmit" style="margin: 10px;">
		<button id="submitForm" onclick="submitForm()">生成数据</button>
		<button id="getone" onclick="getone()">生成一条数据</button>
		<button id="getfive" onclick="getfive()">生成五条数据</button>
		<button id="downloadnumbertxt" onclick="downloadnumbertxt()">数据导出txt</button>
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
	</div>
</div>


<script  type="text/javascript">
function submitForm(){
		$.get("/number/get", {}, function(data) {
		$("#number").val(data);
		
	});
}

function getone(){
	
	var num = $("#number").val();
	var param={
			"num" : num
	}
	
	if(num==null){
		alert("请先生成数据");
	}else{
		$.get("/number/getnum",param , function(data) {
			
	    	$('#tbody').html("");
	    	for(var i=0;i<1;i++)
	    	{  
		        $('#tbody').append('<tr><td>'+'<input type="checkbox">'
		        +'</td><td>'+data[i].id+'</td><td>'+data[i].arraynum
		        +'</td><td>'+data[i].creattime+'</td></tr>');
	    	}
			
		});
	}
	

}


function getfive(){
	
	var num = $("#number").val();
	var param={
			"num" : num
	}
	
	if(num==null){
		alert("请先生成数据");
	}else{
		$.get("/number/getnum",param, function(data) {
			
	    	$('#tbody').html("");
	    	for(var i=0;i<5;i++)
	    	{  
		        $('#tbody').append('<tr><td>'+'<input type="checkbox">'
		        +'</td><td>'+data[i].id+'</td><td>'+data[i].arraynum
		        +'</td><td>'+data[i].creattime+'</td></tr>');
	    	}
			
		});
	}
	

}

function downloadnumbertxt(){
	var num = $("#number").val();
	if(num==null){
		alert("请先生成数据");
	}else{
	window.location.href="/file/downloadnumbertxt?num="+num;
	}
}

</script>
</body>
</html>