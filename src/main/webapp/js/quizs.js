$(document).ready(function() {})

if (getCookie("rlist") != "") {
	let randomList = getCookie("rlist");
	let quizstbody = document.getElementById("quizstbody");
	let quizstbodychildren = quizstbody.childNodes;
	for (var i = quizstbodychildren.length - 1; i >= 0; i--) {
		quizstbody.removeChild(quizstbodychildren.item(i));
	}
	$.each($.parseJSON(randomList), function(index, item) {
		let newtr = document.createElement("tr");
		let newtdid = document.createElement("td");
		newtdid.innerHTML = item;
		newtr.appendChild(newtdid);
		let newtddo = document.createElement("td");
		let newa = document.createElement("a");
		newa.href = "answer.html?id=" + item;
		newa.innerHTML = "答题";
		newtddo.appendChild(newa);
		newtr.appendChild(newtddo);
		let newtdstatus = document.createElement("td");
		let newh5 = document.createElement("h5");
		let newspan = document.createElement("span");

		switch (getCookie(item)) {
			case "1":
				newspan.setAttribute("class", "badge badge-success");
				newspan.innerHTML = "正确"
				break;
			case "2":
				newspan.setAttribute("class", "badge badge-danger");
				newspan.innerHTML = "错误"
				break;
			case "3":
			    newspan.setAttribute("class", "badge badge-warning");
				newspan.innerHTML = "语句有误"
				break;
			default:
				newspan.setAttribute("class", "badge badge-secondary");
			 	newspan.innerHTML = "未开始";
		}
		newh5.appendChild(newspan);
		newtdstatus.appendChild(newh5);
		newtr.appendChild(newtdstatus);
		quizstbody.appendChild(newtr);
	});


}

function randomquizs() {
	clearAllCookie();
	$.get('/Mysql_practice/answer/getRandomQuizs', function(data) {
		let message = '';
		let randomList = getCookie("rlist");
		$.each(data, function(index, item) {
			if (index == "message") {
				message = item;
			}
			if (index == "randomList") {
				randomList = JSON.stringify(item);
			}
		});
		switch (message) {
			case "success":
				break;
			default:
				alert(message);
		}

		console.log(randomList);
		setCookie("rlist", randomList, 30);
		location.reload();
		$.each($.parseJSON(randomList), function(index, item) {
			setCookie(item, 0, 30);
		})



	});
}

function setCookie(cname, cvalue, exdays) {
	var d = new Date();
	d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
	var expires = "expires=" + d.toGMTString();
	document.cookie = cname + "=" + cvalue + "; " + expires;
}

function getCookie(cname) {
	var name = cname + "=";
	var ca = document.cookie.split(';');
	for (var i = 0; i < ca.length; i++) {
		var c = ca[i].trim();
		if (c.indexOf(name) == 0) {
			return c.substring(name.length, c.length);
		}
	}
	return "";
}

function clearAllCookie() {
	var keys = document.cookie.match(/[^ =;]+(?=\=)/g);
	if (keys) {
		for (var i = keys.length; i--;)
			document.cookie = keys[i] + '=0;expires=' + new Date(0).toUTCString()
	}
}
