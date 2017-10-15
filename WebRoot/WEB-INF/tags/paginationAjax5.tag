<%@tag pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  


<input type="hidden" id="ajaxPage5_ajaxQuery">
<input type="hidden" id="ajaxPage5_init">
<input type="hidden" id="ajaxPage5_page_size" value="10">
<input type="hidden" id="ajaxPage5_total">
<script language="javascript" type="text/javascript"> 
function onChangePageSizeAjax5(obj)
{ 
	$('#ajaxPage5_init').val("N");
	$('#ajaxPage5_page_size').val(obj.value);
	createPagination5();
}

function pageselectCallback5(page_index, jq){ 
 	getDataList5(page_index);
}
function createPagination5()
{
	 $("#Pagination5").pagination($('#ajaxPage5_total').val(), {
            callback: pageselectCallback5,//PageCallback() 为翻页调用次函数。
            prev_text: " 上一页",
            next_text: "下一页 ",
            items_per_page: $('#ajaxPage5_page_size').val(), //每页的数据个数
            num_display_entries: 3, //两侧首尾分页条目数
            current_page: 0,   //当前页码
            num_edge_entries: 1, //连续分页主体部分分页条目数 
        });
}

</script>
 <div style="float: right;">
<div id="Pagination5" class="pagination" style="margin: 3px 0 !important;float: left;"><!-- 这里显示分页 --></div> 
<select style="display: none;" class="select4 ajaxpagesizetagclass"  name="pageSize" id="ajaxPage5SizeTag" onchange="onChangePageSizeAjax5(this)"><option id="10" value="10">10</option><option id="20" value="20">20</option><option id="30" value="30">30</option></select>
</div> 