<%@tag pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<script language="javascript" type="text/javascript">
$(document).ready(function() {
	var pagesize = '${page.pageSize}';  
	 $("#pageSizeTag").find("option[value='"+pagesize+"']").attr("selected",true);
	 
	 
     $('#pageNumTag').bind('keypress',function(event){ 

         if(event.keyCode == "13")    
         { 
             if(isDigit($('#pageNumTag').val())==false || $('#pageNumTag').val()<0 || parseInt($('#pageNumTag').val())>parseInt('${page.pages}'))
            {
            	 alert('你输入的页码不正确');
            	 return false;
            }       
             else
            {   
               location.href ='?pageNum='+$('#pageNumTag').val()+'&pageSize='+$('#pageSizeTag').val()+'${searchParams}';
           }
         }
     });
}); 

function isDigit(s) {
	 var patrn=/^\d*$/;
	   if (patrn.test(s)) {   
	           return true;
	   } else {
	           return false;
	   }
	   return false;
}
function onChangePageSize(obj)
{ 
	showMenuPage3('?pageNum=1&pageSize='+obj.value+'${searchParams}');
}
</script>
 <input type="hidden" name="querySearchParamsCondition" id="querySearchParamsCondition" value="${querySearchParamsCondition}">
    <!-- 页签 -->
<div class="pages_box">
      <ul style="float:right;">
       <li class="pages_lf" onmouseover="this.className='pages_lfover'" onmouseout="this.className='pages_lf'"  onclick="showMenuPage3('?pageNum=1&isFromPage=Y&pageSize=${page.pageSize}${searchParams}')"></li>
                
       				<c:if test="${page.hasPreviousPage}">
       				   <li class="pages_lf2" onmouseover="this.className='pages_lf2over'" onmouseout="this.className='pages_lf2'" onclick="showMenuPage3('?pageNum=${page.prePage}&isFromPage=Y&pageSize=${page.pageSize}${searchParams}')"></li>       
       				</c:if>
       
       
     
         <li class="pages_1"> <input type="text" id="pageNumTag"  class="input7" name="pageNum" value="${page.pageNum}" /></li>
         
          <li class="pages_1">共${page.pages}页</li>
          
                   <c:if test="${page.hasNextPage}">
                     <li class="pages_lf3" onmouseover="this.className='pages_lf3over'" onmouseout="this.className='pages_lf3'" onclick="showMenuPage3('?pageNum=${page.nextPage}&isFromPage=Y&pageSize=${page.pageSize}${searchParams}')"></li>
                         
                    </c:if>
           
          <li class="pages_lf4" onmouseover="this.className='pages_lf4over'" onmouseout="this.className='pages_lf4'" onclick="showMenuPage3('?pageNum=${page.pages}&isFromPage=Y&pageSize=${page.pageSize}${searchParams}')"></li>
          
           <li class="pages_1"> <select class="select4" name="pageSize" id="pageSizeTag" onchange="onChangePageSize(this)"><option id="10" value="10">10</option><option id="20" value="20">20</option><option id="30" value="30">30</option></select> </li>
      </ul>
</div>   
      <!-- 页签 -->
