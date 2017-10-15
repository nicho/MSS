//通用删除一行记录
function delLine(line){
	$(line).parents(".meetingFacility").remove();
}

//学习教育经历
function addNewEduInfo(){
	var trLength = $("#table_eduinfoList tr").length;
	var line="<tr class='meetingFacility'>"+
	"<td><input type='text' id='startDate'  name='startDate' class='input3 input-large required' onClick='WdatePicker()' /><span>*</span></td>"+
	"<td><input type='text' id='endDate'  name='endDate' class='input3 input-large required' onClick='WdatePicker()' /><span>*</span></td>"+
	"<td><input type='text' id='graduationSchool' onkeyup='limitLength(this,20);' name='graduationSchool' class='input3 required' /><span>*</span></td>"+
	"<td><input type='text' id='major' onkeyup='limitLength(this,20);' name='major' class='input3 required' /><span>*</span></td>"+
	
	"<td>"+
	"<select name='eduDegree' id='eduDegree' style='width: 120px;' class='select3'>"+
    "<option value='0'>请选择</option>"+
	"<option value='1'>小学</option>"+
	"<option value='2'>初中</option>"+
	"<option value='3'>高中</option>"+
	"<option value='4'>专科</option>"+		
	"<option value='5'>本科</option>"+	
	"<option value='6'>硕士</option>"+	
	"<option value='7'>博士</option>"+	
	"<option value='8'>其它</option>	"+			 		    
	"</select>"+
	"</td>"+
	
	
	"<td><input type='text' id='eduStyle' onkeyup='limitLength(this,20);' name='eduStyle' class='input3 required' /></td><span>*</span>";
	if(trLength==1){
		line += "<td></td></tr>";
	}else{
		line += "<td><a onclick='delLine(this)'>删除</a></td></tr>";
	}
	
	$("#table_eduinfoList").append(line);
}
//职业经历
function addNewOccupationexp(){
	var line="<tr class='meetingFacility'>"+
	"<td><input type='text' id='ocstartDate'  name='ocstartDate' class='input3 input-large required' onClick='WdatePicker()' /><span>*</span></td>"+
	"<td><input type='text' id='ocendDate'  name='ocendDate' class='input3 input-large required' onClick='WdatePicker()' /><span>*</span></td>"+
	"<td><input type='text' id='company' onkeyup='limitLength(this,20);' name='company' class='input3 required' /><span>*</span></td>"+
	"<td><input type='text' id='position' onkeyup='limitLength(this,20);' name='position' class='input3 required' /><span>*</span></td>"+
	"<td><input type='text' id='income' onkeyup='limitLength(this,6);' name='income' class='input3 number' /></td>"+
	"<td><input type='text' id='leaveReason' onkeyup='limitLength(this,20);' name='leaveReason' class='input3' /></td>"+
	"<td><a onclick='delLine(this)'>删除</a></td></tr>";
	$("#table_occupationexp").append(line);
}

//培训经历
function addNewTrainexp(){
	var line="<tr class='meetingFacility'>"+
	"<td><input type='text' id='startDate'  name='startDate' class='input3 input-large required' onClick='WdatePicker()' /><span>*</span></td>"+
	"<td><input type='text' id='endDate'  name='endDate' class='input3 input-large required' onClick='WdatePicker()' /><span>*</span></td>"+
	"<td><input type='text' id='rainOrg' onkeyup='limitLength(this,20);' name='rainOrg' class='input3 required' /><span>*</span></td>"+
	"<td><input type='text' id='address' onkeyup='limitLength(this,20);' name='address' class='input3 required' /><span>*</span></td>"+
	"<td><input type='text' id='rainContent' onkeyup='limitLength(this,20);' name='rainContent' class='input3 required' /><span>*</span></td>"+
	"<td><a onclick='delLine(this)'>删除</a></td></tr>";
	$("#table_trainexp").append(line);
}
//荣誉/成就
function addNewAchieve(){
	var line="<tr class='meetingFacility'>"+
	"<td><input type='text' id='gainDate'  name='gainDate' class='input3 input-large required' onClick='WdatePicker()' /><span>*</span></td>"+
	"<td><input type='text' id='gainThing' onkeyup='limitLength(this,40);' name='gainThing' class='input3 required' /><span>*</span></td>"+
	"<td><input type='text' id='awardOrg' onkeyup='limitLength(this,40);' name='awardOrg' class='input3 required' /><span>*</span></td>"+
	"<td><a onclick='delLine(this)'>删除</a></td></tr>";
	$("#table_achieve").append(line);
}
//证明人
function addNewWitness(){
	var trLength = $("#table_witness tr").length;
	var line="<tr class='meetingFacility'>"+
	"<td><input type='text' id='witnessName' onkeyup='limitLength(this,20);' name='witnessName' class='input3 required' /><span>*</span></td>"+
	"<td><input type='text' id='witnessPosition' onkeyup='limitLength(this,20);' name='witnessPosition' class='input3 required' /><span>*</span></td>"+
	"<td><input type='text' id='witnessCompany' onkeyup='limitLength(this,20);' name='witnessCompany' class='input3 required' /><span>*</span></td>"+
	"<td><input type='text' id='witnessPhone' onkeyup='limitLength(this,11);' name='witnessPhone' class='input3  required dhhm' /><span>*</span></td>";
	if(trLength==1){
		line += "<td></tr>";
	}else{
		line += "<td><a onclick='delLine(this)'>删除</a></td></tr>";
	}
	
	$("#table_witness").append(line);
}
//家庭关系
function addNewFamilyrel(){
	var trLength = $("#table_familyrel tr").length;
	var line="<tr class='meetingFacility'>"+
	"<td><input type='text' id='name'  name='name' onkeyup='limitLength(this,20);' class='input3 required' /><span>*</span></td>"+
	"<td><input type='text' id='rel' onkeyup='limitLength(this,20);' name='rel' class='input3 required' /><span>*</span></td>"+
	"<td><input type='text' id='company' onkeyup='limitLength(this,20);' name='company' class='input3 required' /><span>*</span></td>"+
	"<td><input type='text' id='address' onkeyup='limitLength(this,20);' name='address' class='input3 required' /><span>*</span></td>"+
	"<td><input type='text' id='mobile' onkeyup='limitLength(this,11);' name='mobile' class='input3 dhhm required' /><span>*</span></td>";
	if(trLength==1){
		line+="<td></td></tr>";
	}else{
		line+="<td><a onclick='delLine(this)'>删除</a></td></tr>";
	}
	
	$("#table_familyrel").append(line);
}

function addNewRecommend(){
	var line="<tr class='meetingFacility'>"+
	"<td><input type='text' id='name' onkeyup='limitLength(this,20);' name='name' class='input3 required' /><span>*</span></td>"+
	"<td><input type='text' id='rel' onkeyup='limitLength(this,20);' name='rel' class='input3 required' /><span>*</span></td>"+
	"<td><input type='text' id='position' onkeyup='limitLength(this,20);' name='position' class='input3 required' /><span>*</span></td>"+
	"<td><input type='text' id='tel' onkeyup='limitLength(this,11);' name='tel' class='input3 dhhm required' /><span>*</span></td>"+
	"<td><a onclick='delLine(this)'>删除</a></td></tr>";
	$("#table_recommend").append(line);
}