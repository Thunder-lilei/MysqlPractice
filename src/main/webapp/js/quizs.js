$(document).ready(function() {
})

function randomquizs() {
			$.get('/sql_cerification/answer/getrandomquizs',function(data){
				let message = '';
				let randomlist = '';
				$.each(data, function(index,item) {
					if(index=="message")
					{message = item}
					if(index=="randomlist")
					{randomlist = JSON.stringify(item)}
				});
				switch(message)
				{
					case "success":
					break;
					default :
					alert(message);
				}
				let quizstbody = document.getElementById("quizstbody");
				let quizstbodychildren = quizstbody.childNodes;
					for(var i = quizstbodychildren.length - 1; i >= 0; i--) {
						quizstbody.removeChild(quizstbodychildren.item(i));
					}
				$.each($.parseJSON(randomlist), function(index, item) {
					let newtr = document.createElement("tr");
					let newtdid = document.createElement("td");
					newtdid.innerHTML = item;
					newtr.appendChild(newtdid);
					let newtddo = document.createElement("td");
					let newa = document.createElement("a");
					newa.href = "answer.html?id="+item;
					newa.innerHTML = "答题";
					newtddo.appendChild(newa);
					newtr.appendChild(newtddo);
					let newtdstatus = document.createElement("td");
					let newh5 = document.createElement("h5");
					let newspan = document.createElement("span");
					newspan.setAttribute("class","badge badge-secondary");
					newspan.innerHTML = "未开始";
					newh5.appendChild(newspan);
					newtdstatus.appendChild(newh5);
					newtr.appendChild(newtdstatus);
					quizstbody.appendChild(newtr);
				});
			});
		}