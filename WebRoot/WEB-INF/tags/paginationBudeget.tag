<%@tag pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<script language="javascript" type="text/javascript">
$(document).ready(function() {
	var pagesize = '${page.pageSize}';
	 $("#pageSizeTag").find("option[value='"+pagesize+"']").attr("selected",true);
	 
	 
   /*  $('#pageNumTag').bind('keypress',function(event){
         if(event.keyCode == "13")    
         {
           
             if($('#pageNumTag').val()<0 || $('#pageNumTag').val()>'${page.pages}')
            {
            	 alert('你输入的页码不正确');
            }  
         }
     });*/
});

var hasChanged = false;

function onChangePageSizeClick(url,searchParams) {
	if(hasChanged) {
		$.messager.confirm("操作提示","页面数据有修改，是否保存？",function(data){
			//url +="&menuName="+encodeURI('${param.menuName}')+"&h1_id="+'${param.h1_id}'+"&h1_index="+'${param.h1_index}'+"&h2_id="+'${param.h2_id}'+"&h2_index="+'${param.h2_index}';			
			saveForm(url);
			return;
		});
	} else {
		$("#inputForm").attr('action','${ctx}/viewExpenseAccountOrgList.do');
		showMenuPage3(url + searchParams);
	}
	
	
	
}

function onChangePageSize(obj) {
	if(hasChanged) {
		$.messager.confirm("操作提示","页面数据有修改，是否保存？",function(data){
			var url = '?pageNum=1&pageSize='+obj.value;//+ "&menuName="+encodeURI('${param.menuName}')+"&h1_id="+'${param.h1_id}'+"&h1_index="+'${param.h1_index}'+"&h2_id="+'${param.h2_id}'+"&h2_index="+'${param.h2_index}';
			saveForm(url);
			return;
		});
	} else {
		$("#inputForm").attr('action','${ctx}/viewExpenseAccountOrgList.do');
		showMenuPage3('?pageNum=1&pageSize='+obj.value+'${searchParams}');
	}
}
</script>
 
    <!-- 页签 -->
<div class="pages_box">
      <ul style="float:right;">
       <li class="pages_lf" onmouseover="this.className='pages_lfover'" onmouseout="this.className='pages_lf'" <c:if test="${page.pageSize!=null}"> onclick="onChangePageSizeClick('?pageNum=1&isFromPage=Y&pageSize=${page.pageSize}','${searchParams}')"</c:if>></li>
                
       				<c:if test="${page.hasPreviousPage}">
       				   <li class="pages_lf2" onmouseover="this.className='pages_lf2over'" onmouseout="this.className='pages_lf2'" onclick="onChangePageSizeClick('?pageNum=${page.prePage}&isFromPage=Y&pageSize=${page.pageSize}','${searchParams}')"></li>       
       				</c:if>
       
       
     
         <li class="pages_1"> <input type="text" id="pageNumTag"  class="input7" name="pageNum" value="${page.pageNum}" onkeypress="keypress()" /></li>
         
          <li class="pages_1">共${page.pages}页</li>
          
                   <c:if test="${page.hasNextPage}">
                     <li class="pages_lf3" onmouseover="this.className='pages_lf3over'" onmouseout="this.className='pages_lf3'" onclick="onChangePageSizeClick('?pageNum=${page.nextPage}&isFromPage=Y&pageSize=${page.pageSize}','${searchParams}')"></li>                        
                    </c:if>
          
          <li class="pages_lf4" onmouseover="this.className='pages_lf4over'" onmouseout="this.className='pages_lf4'" <c:if test="${page.pageSize!=null}"> onclick="onChangePageSizeClick('?pageNum=${page.pages}&isFromPage=Y&pageSize=${page.pageSize}','${searchParams}')"</c:if>></li>
          
           <li class="pages_1"> <select class="select4" name="pageSize" id="pageSizeTag" onchange="onChangePageSize(this)"><c:if test="${page.pageSize!=null}"><option id="10" value="10">10</option><option id="20" value="20">20</option><option id="30" value="30">30</option></c:if></select> </li>
      </ul>
</div>
      <!-- 页签 -->
