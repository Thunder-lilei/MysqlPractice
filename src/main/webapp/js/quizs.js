$(document).ready(function() {})

/*
 	如果此时cookie里不为空
 * */
if (getCookie("rlist") != "") {
	let randomList = getCookie("rlist");
	let quizstbody = $("#quizstbody");
	let quizstbodychildren = quizstbody.children();
	quizstbodychildren.filter("tr").remove();
	/*for (var i = quizstbodychildren.length - 1; i >= 0; i--) {
		quizstbody.remove(quizstbodychildren.item(i));
	}*/
	$.each($.parseJSON(randomList), function(index, item) {
		let newtr = $("<tr></tr>");
		let newtdid = $("<td></td>");
		newtdid.html(item);
		newtr.append(newtdid);
		let newtddo = $("<td></td>");
		let newa = $("<a></a>");
		newa.attr('href',"answer.html?id=" + item);
		newa.html("答题");
		newtddo.append(newa);
		newtr.append(newtddo);
		let newtdstatus = $("<td></td>");
		let newh5 = $("<h5></h5>");
		let newspan = $("<span></span>");

		switch (getCookie(item)) {
			case "1":
				newspan.attr("class", "badge badge-success");
				newspan.html("正确"); 
				break;
			case "2":
				newspan.attr("class", "badge badge-danger");
				newspan.html("错误"); 
				break;
			case "3":
			    newspan.attr("class", "badge badge-warning");
				newspan.html("语句有误"); 
				break;
			default:
				newspan.attr("class", "badge badge-secondary");
			 	newspan.html("未开始");
		}
		newh5.append(newspan);
		newtdstatus.append(newh5);
		newtr.append(newtdstatus);
		quizstbody.append(newtr);
	});


}

function randomquizs() {
	clearAllCookie();
	$.post('/answer/getRandomQuizs', function(data) {
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
