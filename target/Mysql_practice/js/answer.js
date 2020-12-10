$(document).ready(function() {
	(function() {
			setCookie('time', 10, 30);
			let id = GetQueryString("id");
			$.getJSON('/Mysql_practice/answer/getTitle', {
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
						$("#question").html(item);
					}
				});
			});

			// var countdown = document.getElementById("countdown");
			// var time = getCookie('time'); //30分钟换算成1800秒
			// setInterval(function() {
			// 	time = time - 1;
			// 	setCookie('time', time, 30);
			// 	var minute = parseInt(time / 60);
			// 	var second = parseInt(time % 60);
			// 	countdown.innerHTML = '还剩' + minute + '分' + second + '秒';
			// }, 1000);

		}

	)()
})

function sql_format() {
	let btn = $('#btnformat');
	let inputEle = $('#inputsql');
	let output = $('#inputsql');
	inputEle.on('btn', format);

	function format() {
		console.time('formatting');
		output.val(sqlFormatter.format(inputEle.val(), "SQL"));
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
		let previewlist = [];
		$.each(data, function(index, item) {
			if (index == "message") {
				message = item
			}
			if (index == "previewlist") {
				previewlist = item
			}
		});
		switch (message) {
			case "success":
				$.each(previewlist, function(index, item) {
					let previewdata = item;
					let newtr = $("<tr></tr>");
					$.each(previewdata, function(indexTwo, itemTwo) {
						let newtd = $("<td></td>");
						newtd.html(itemTwo);
						newtr.append(newtd);
					});
					$("#tbody").append(newtr);
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
	$.getJSON('/Mysql_practice/sql/compareSqlAddHistory', {
		sqlString: $("#inputsql").val(),
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

	num = num + rlist.indexOf(id);

	if (num >= 0 && num < rlist.length) {
		window.location.href = './answer.html?id=' + rlist[num];
	}
}

function reset() {
	$("#error").text("");
	let tbody = $("#tbody");
	let tbodychildren = tbody.children();
	tbodychildren.filter("tr").remove();
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
