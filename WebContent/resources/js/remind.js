// 参数：区块id,区块名称,提醒框位置x,提醒框位置y
function remind_start(block_id, block_name, x, y) {
	x += 10;
	y += 10;
	var html = "<div class='remind' style='top:"+ y + "px;left:"+ x + "px;position:fixed;'>"
				+ "<div class='remind-title'>" 
					+ block_name + "<span onclick='remind_close()'>x</span>" 
		        + "</div>" 
		        + "<div class='remind_content'>"
					+ "<div class='remind_content_add'><a>添加结点</a></div>" 
					+ "<div class='remind_content_del'>" 
					   + "<div class='remind_content_del_title'>下一子节点</div>"
					   + "<ul class='remind_content_del_content'>"
					   		+ "<li></li>"
					   + "</ul>"
					+ "</div>" 
				+ "</div>"
			+ "</div>";
	$(".remind").remove();
	$("body").append(html);
}
function remind_close(){
	$(".remind").remove();
}