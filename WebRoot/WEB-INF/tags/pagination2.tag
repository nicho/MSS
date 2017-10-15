<%@tag pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<script language="javascript" type="text/javascript">
$(document).ready(function() {
	var pagesize2 = '${page2.pageSize}';  
	 $("#pageSizeTag").find("option[value='"+pagesize2+"']").attr("selected",true);
	 
	 
   /*  $('#pageNumTag').bind('keypress',function(event){
         if(event.keyCode == "13")    
         {
           
             if($('#pageNumTag').val()<0 || $('#pageNumTag').val()>'${page2.pages}')
            {
            	 alert('你输入的页码不正确');
            }  
         }
     });*/
}); 
function onChangePageSize(obj)
{ 
	showMenuPage3('?pageNum2=1&pageSize2='+obj.value+'${searchParams}');
}
</script>
 
    <!-- 页签 -->
<div class="pages_box">
      <ul style="float:right;">
       <li class="pages_lf" onmouseover="this.className='pages_lfover'" onmouseout="this.className='pages_lf'"  onclick="showMenuPage3('?pageNum2=1&isFromPage=Y&pageSize2=${page2.pageSize}${searchParams}')"></li>
                
       				<c:if test="${page2.hasPreviousPage}">
       				   <li class="pages_lf2" onmouseover="this.className='pages_lf2over'" onmouseout="this.className='pages_lf2'" onclick="showMenuPage3('?pageNum2=${page2.prePage}&isFromPage=Y&pageSize2=${page2.pageSize}${searchParams}')"></li>       
       				</c:if>
       
       
     
         <li class="pages_1"> <input type="text" id="pageNumTag"  class="input7" name="pageNum2" value="${page2.pageNum}" onkeypress="keypress()" /></li>
         
          <li class="pages_1">共${page2.pages}页</li>
          
                   <c:if test="${page2.hasNextPage}">
                     <li class="pages_lf3" onmouseover="this.className='pages_lf3over'" onmouseout="this.className='pages_lf3'" onclick="showMenuPage3('?pageNum2=${page2.nextPage}&isFromPage=Y&pageSize2=${page2.pageSize}${searchParams}')"></li>
                         
                    </c:if>
          
          <li class="pages_lf4" onmouseover="this.className='pages_lf4over'" onmouseout="this.className='pages_lf4'" onclick="showMenuPage3('?pageNum2=${page2.pages}&isFromPage=Y&pageSize2=${page2.pageSize}${searchParams}')"></li>
          
           <li class="pages_1"> <select class="select4" name="pageSize2" id="pageSizeTag" onchange="onChangePageSize(this)"><option id="10" value="10">10</option><option id="20" value="20">20</option><option id="30" value="30">30</option></select> </li>
      </ul>
</div>
      <!-- 页签 -->
