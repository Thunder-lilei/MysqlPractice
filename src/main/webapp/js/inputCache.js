/*
	因为谷歌浏览器没有表单自动存储功能
	所以在此自己实现一个表单实现功能，并将其封装为js文件
	在此处决定使用原生js来编写，好处为：
		1.sesstionStorage为html5提供的新api
		2.需要使用此控件时不需要导入jquery就可以使用
	此控件的问题如下：
		1.必须放在类名为card-body的标签内
		2.select标签第一次未触发change事件，刷新的话数据未保存
 * */

$(document).ready(function () {
	
	//先获取form表单中所有不是submit按钮的元素
	let inputs = document.querySelectorAll('.card-body *:not([type="submit"])');
	let inputValues = [];
	
	//对当前的url进行解析，获取当前页面的名字
	let urlString = GetUrlFileName();
	
	//因为此时inputs是NodeList，为了让他能使用splice方法
	//对inputs进行解构赋值
	inputs = [...inputs];
	//对inputs进行过滤
	for(let i=0;i<inputs.length;i++){
		//判断节点类型
		if(inputs[i].nodeType==1){
			switch(inputs[i].nodeName) {
				case "SELECT": break;
				case "TEXTAREA": break;
				case "INPUT": break;
				default: 
					inputs.splice(i,1);
					//删除元素后索引需要前移
					i = i-1;
			}
		}
	}
	
	//页面加载开始判断之前是否存在sessionStorage(此处if为存在)
	if(window.sessionStorage.getItem('input'+urlString) != null){
			
		inputValues = window.sessionStorage.getItem('input'+urlString).split(',');
		
	}
	
	//判断inputValues是否从sessionStorage中获取到了值
	if(inputValues.length!=0){
		/*
		 * 	此时得到想要的inputs表单元素
		   	为所有的表单元素添加change事件并将里面的值存储在inputValues中  
		 * */
		for(let i in inputs){
			
			inputs[i].value = inputValues[i];
			
		}		
	}
		
	for(let i in inputs) {
		
		inputs[i].onchange = function (e) {
			
			inputValues[i] = inputs[i].value;
			window.sessionStorage.setItem('input'+urlString,inputValues);
			
		}
		
	}
		
	
	
	/*
	 	获取当前url文件名的函数
	 * */
	function GetUrlFileName() {
	　　	
		let urlString = window.location.href.split('/');
		urlString = urlString[urlString.length-1];
		let end = urlString.indexOf('.');
		urlString = urlString.substring(0,end);
		
	　　	return urlString;
	}

})
