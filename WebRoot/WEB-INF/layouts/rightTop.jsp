<%@ page language="java" pageEncoding="UTF-8" %>

<style>
.alert {
  padding: 0px 35px 0px 14px;
  margin-bottom: 20px;
  text-shadow: 0 1px 0 rgba(255, 255, 255, 0.5);
  background-color: #fcf8e3;
  border: 1px solid #fbeed5;
  -webkit-border-radius: 4px;
     -moz-border-radius: 4px;
          border-radius: 4px;position:absolute;z-index:1000;width:50%;top:0%;left:30%;
}

.alert,
.alert h4 {
  color: #c09853;
}

.alert h4 {
  margin: 0;
}

.alert .close {
  position: relative;
  top: -2px;
  right: -21px;
  line-height: 20px;
}

.alert-success {
  color: #468847;
  background-color: #dff0d8;
  border-color: #d6e9c6;
}

.alert-success h4 {
  color: #468847;
}

.alert-danger,
.alert-error {
  color: #b94a48;
  background-color: #f2dede;
  border-color: #eed3d7;
}

.alert-danger h4,
.alert-error h4 {
  color: #b94a48;
}

.alert-info {
  color: #3a87ad;
  background-color: #d9edf7;
  border-color: #bce8f1;
}

.alert-info h4 {
  color: #3a87ad;
}

.alert-block {
  padding-top: 14px;
  padding-bottom: 14px;
}

.alert-block > p,
.alert-block > ul {
  margin-bottom: 0;
}

.alert-block p + p {
  margin-top: 5px;
}

</style>

<div class="path">
        	<div class="path-title"><spring:message code='working_table'/></div>
            <div class="path-crumb"><spring:message code='current_location'/>：<span id="menuTop"><span><spring:message code='shouye'/></span> > <span><spring:message code='working_table'/></span></span> 
     
            </div>
       
		<div id="messageAlertSuccessHead" class="alert alert-error" style="display: none;">
			<button data-dismiss="alert" aria-hidden="true" class="close">×</button><div id="messageAlertSuccess" >审批成功</div></div>
</div>

