lib_destory_element = function(elements){
	for(var i=0;i<elements.length;i++){
		$("#"+elements[i]).html("");
	}
}