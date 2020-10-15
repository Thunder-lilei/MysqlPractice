$(document).ready(function() {
		(function(){
			let id = GetQueryString("id");
			$.getJSON('/Mysql_practice/answer/gettitle',{id:id},function(data){
				let message = '';
				let answer = '';
				$.each(data, function(index,item) {
					if(index=="message")
					{message = item}
					if(index=="answer")
					{answer = JSON.stringify(item)}
				});
				switch(message)
				{
					case "success":
					break;
					default :
					alert(message);
				}
				$.each($.parseJSON(answer), function(index, item) {
					if(index==="question"){document.getElementById("question").innerHTML = item;}
				});
			});
		})()
		
})

function GetQueryString(name) {
			var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
			var r = window.location.search.substr(1).match(reg);
			if(r != null) return decodeURI(r[2]);
			return null;
		}