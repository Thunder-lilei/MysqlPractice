/*
 	这个js文件是为了进行后台传过来的时间戳进行过滤
 	
 * */
function dateFormatter (dateString) {
	let date = new Date(dateString); 
	let Y = date.getFullYear() + '/';
	let M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '/';
	let D = date.getDate();
	D = D<10?'0'+D+' ':D+' ';
	return (Y + M + D)
}