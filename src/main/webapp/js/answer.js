$(document).ready(function() {
	(function() {
		let id = GetQueryString("id");
		$.getJSON('/Mysql_practice/answer/gettitle', {
			id: id
		}, function(data) {
			let message = '';
			let answer = '';
			$.each(data, function(index, item) {
				if (index == "message") {
					message = item
				}
				if (index == "answer") {
					answer = JSON.stringify(item)
				}
			});
			switch (message) {
				case "success":
					break;
				default:
					alert(message);
			}
			$.each($.parseJSON(answer), function(index, item) {
				if (index === "question") {
					document.getElementById("question").innerHTML = item;
				}
			});
		});
	})()

})

function sql_format() {
	let btn = document.getElementById('btnformat')
	let input = document.getElementById('inputsql');
	let output = document.getElementById('inputsql');
	input.addEventListener('btn', format);

	function format() {
		console.time('formatting');
		output.value = sqlFormatter.format(input.value, "SQL");
		console.timeEnd('formatting');
	}
	format();
}

function preview() {
	reset();
	$.getJSON('/Mysql_practice/sql/preview', {
		sql: $("#inputsql").val()
	}, function(data) {
		let message = '';
		let previewlist = '';
		$.each(data, function(index, item) {
			if (index == "message") {
				message = item
			}
			if (index == "previewlist") {
				previewlist = JSON.stringify(item)
			}
		});
		switch (message) {
			case "success":
				$.each($.parseJSON(previewlist), function(index, item) {
					let previewdata = item;
					let newtr = document.createElement("tr");
					$.each(previewdata, function(index, item) {
						let newtd = document.createElement("td");
						newtd.innerHTML = item;
						newtr.appendChild(newtd);
					});
					tbody.appendChild(newtr);
				});
				break;
			default:
				//alert(message);
				$("#error").text("SQL语句有误");

		}

	});
}

function compare() {
	reset();
	let id = GetQueryString("id");
	$.getJSON('/Mysql_practice/sql/compare_sql', {
		sqlstring: $("#inputsql").val(),
		id: id
	}, function(data) {
		let message = '';
		let result = '';
		$.each(data, function(index, item) {
			if (index == "message") {
				message = item
			}
			if (index == "result") {
				result = JSON.stringify(item)
			}
		});
		switch (message) {
			case "success":
				break;
			default:
				//alert(message);
		}

		if (result == "\"Different\"") {
			setCookie(id, 2, 30);
			window.location.href = './quizs.html';
		} else if (result == "\"Same\"") {
			setCookie(id, 1, 30);
			window.location.href = './quizs.html';
		} else {
			setCookie(id, 3, 30);
			window.location.href = './quizs.html';
			//$("#error").text("SQL语句有误");
		}
	});
}

function page(num) {
	let id = parseInt(GetQueryString("id"));
	let rlist = JSON.parse(getCookie("rlist"));
	
	num = num+ rlist.indexOf(id);
	
	if(num>=0 && num<rlist.length){
		window.location.href='./answer.html?id='+rlist[num];
	}
}

function reset() {
	$("#error").text("");
	let tbody = document.getElementById("tbody")
	let tbodychildren = tbody.childNodes;
	for (var i = tbodychildren.length - 1; i >= 0; i--) {
		tbody.removeChild(tbodychildren.item(i));
	}
}

function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) return decodeURI(r[2]);
	return null;
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
