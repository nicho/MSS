 $(document).ready(function() { 
		jQuery.validator.addMethod("maxLength5", function(value, element) { 
			if(strlen1(value)>5){
			 return false;	
			}else{
			return true;	
			}
			
		}, "输入的字符串长度不能大于5个字符,中文占3个，其它占1个");
	 	jQuery.validator.addMethod("maxLength10", function(value, element) { 
			if(strlen1(value)>10){
			 return false;	
			}else{
			return true;	
			}
			
		}, "输入的字符串长度不能大于10个字符,中文占3个，其它占1个");
	 	jQuery.validator.addMethod("maxLength20", function(value, element) { 
			if(strlen1(value)>20){
			 return false;	
			}else{
			return true;	
			}
			
		}, "输入的字符串长度不能大于20个字符,中文占3个，其它占1个");
		jQuery.validator.addMethod("maxLength50", function(value, element) { 
			if(strlen1(value)>50){
			 return false;	
			}else{
			return true;	
			}
			
		}, "输入的字符串长度不能大于50个字符,中文占3个，其它占1个");
		jQuery.validator.addMethod("maxLength100", function(value, element) { 
			if(strlen1(value)>100){
			 return false;	
			}else{
			return true;	
			}
			
		}, "输入的字符串长度不能大于100个字符,中文占3个，其它占1个");
		jQuery.validator.addMethod("maxLength150", function(value, element) { 
			if(strlen1(value)>150){
			 return false;	
			}else{
			return true;	
			}
			
		}, "输入的字符串长度不能大于150个字符,中文占3个，其它占1个"); 
		jQuery.validator.addMethod("maxLength200", function(value, element) { 
			if(strlen1(value)>200){
			 return false;	
			}else{
			return true;	
			}
			
		}, "输入的字符串长度不能大于200个字符,中文占3个，其它占1个"); 
		jQuery.validator.addMethod("isMathFloor", function(value, element) { 
			if(!value) return false; 
			  try{ 
				  if(Math.floor(value) != value) {
				  	return false;
			  	  } 
			  } catch(ex) { 
			   	return false; 
			  } 
			  return true; 
		}, "请输入整数");
 }); 
 
//获取字符串长度
 function strlen1(str) {	
 	var len = 0;
 	for (var i=0; i<str.length; i++) { 
 		var c = str.charCodeAt(i); 
 		if ((c >= 0x0001 && c <= 0x007e) || (0xff60<=c && c<=0xff9f)) { 
 			len++; 
 		}else{
 			len+=3;
 		}
 	}
 	return len;
 }
