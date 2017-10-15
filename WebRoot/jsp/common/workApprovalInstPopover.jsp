<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="/oaTag" prefix="oaTag" %> 
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head> 
<title>流程图</title>
<link href="${ctx}/static/css/${sessionScope.initDomainCSS}" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/css/common.css" type="text/css" rel="stylesheet" />
<link type="image/x-icon" href="${ctx}/static/images/favicon.ico" rel="shortcut icon">
<link href="${ctx}/static/bootstrap/css/bootstrap.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/jquery-validation/1.11.1/validate.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/static/jquery/jquery-1.8.0.min.js" type="text/javascript"></script>
<script src="${ctx}/static/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery/json2.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery/jquery.collapse.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery/jquery.collapse_storage.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery/jquery.collapse_cookie_storage.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery/jquery.SuperSlide.js" type="text/javascript"></script>
<script language="javascript" type="text/javascript" src="${ctx}/static/My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript" src="${ctx}/static/js/pagination.js"></script>
 
<script src="${ctx}/static/js/highcharts.js"></script>  

<script type="text/javascript">
var indexfal = 0;
var x = 5;
var y = 5;
var fx = 1;
var i = 0;
var colors;
var ren;
var width;


$(document).ready(function() {
	
	
	var docId = '${docId}';
	var processCode = '${processCode}';
	var operationCode = '${operationCode}';

	
	$.ajax({
		type : 'POST',
		data : {
			docId : docId,
			processCode : processCode,
			operationCode : operationCode
		},
		url : '${ctx}/getApprovalInst.do',
		success : function(returnData) {
			if (returnData) {
				var products = eval("(" + returnData + ")");

				$('#container').highcharts({
					chart : {
						backgroundColor : 'white',
						events : {
							load : function() {

								// Draw the flow chart
								 ren = this.renderer;
								 colors = Highcharts.getOptions().colors;

								 width = $("#container").width();

								
								for (i = 0; i < products.length; i++) {

									commonAddNode(products);
									if (i < products.length - 1) {

										ren.path([ 'M', 0, 0, 'L', 45, 0, 'L', 40, 5, 'M', 45, 0, 'L', 40, -5 ]).attr({
											'stroke-width' : 2,
											stroke : colors[3]
										}).translate(x + 80, 23).add();

										x = x + 130;
									}
									if (i != products.length - 2 && (x + 200) > width) {
										i++;
										commonAddNode(products);

										fx = 2;
										break;
									}

								}
								
								commonAddLine(products)
						/*		
								if (fx == 2 && products.length > i + 1) {

									ren.path([ 'M', x + 40, y + 46, 'L', x + 40, 85, 'L', x + 40 - 5, 80, 'M', x + 40, 85, 'L', x + 40 + 5, 80 ]).attr({
										'stroke-width' : 2,
										stroke : colors[3]
									}).add();

									i++;
									y = y + 100;
									for (; i < products.length; i++) {

										commonAddNode(products);

										if (i < products.length - 1) {

											if (x - 130 < 0) {
												fx = 3;
												break;

											} else {
												ren.path([ 'M', 45, 0, 'L', 0, 0, 'L', 5, 5, 'M', 0, 0, 'L', 5, -5 ]).attr({
													'stroke-width' : 2,
													stroke : colors[3],
													width : 5,
												}).translate(x - 50, y + 20).add();

												x = x - 130;
											}

										}

									}
								}

								if (fx == 3 && products.length > i + 1) {

									ren.path([ 'M', x + 40, y + 50, 'L', x + 40, y + 95, 'L', x + 40 - 5, y + 90, 'M', x + 40, y + 95, 'L', x + 40 + 5, y + 90 ]).attr({
										'stroke-width' : 2,
										stroke : colors[3]
									}).add();

									i++;
									y = y + 100;
									for (; i < products.length; i++) {

										commonAddNode(products);
										if (i < products.length - 1) {
											ren.path([ 'M', 0, y, 'L', 45, y, 'L', 40, y + 5, 'M', 45, y, 'L', 40, y - 5 ]).attr({
												'stroke-width' : 2,
												stroke : colors[3],
												width : 5,
											}).translate(x + 80, 23).add();

											x = x + 130;
										}
										if (i != products.length - 2 && (x + 300) > width) {

											commonAddNode(products);

											fx = 4;
											break;
										}

									}
								}*/

							}
						}
					},
					title : {
						text : '',
						style : {
							color : 'black'
						}
					}

				});

			}
		}
	});
});


function commonAddNode(products) {

	var name = products[i].act_role_name;
	if (name == null)
		name = "　结束";
	else if (name.substring(name.length - 1) == ',')
		name = name.substring(0, name.length - 1);

	var vw = 55;
	if (name.length > 6) {
		vw = 10 * name.length;
		if (fx / 2 == 1) {
			x=x-(vw-55);
		}
	}

	if ("Y" == products[i].is_current) {
		indexfal = i;

		var colorNode = 'rgb(255, 188, 24)';

		if (products[i].node_statu == 'B') {
			colorNode = 'rgb(80, 198, 37)';
		}
		if(name == "　结束")
		{
			colorNode = 'rgb(244, 168, 189)';
		}

		ren.label(name, x, y).attr({
			fill : colorNode,
			stroke : '#9b9b9b',
			'stroke-width' : 2,
			width : vw,
			padding : 10,
			r : 5
		}).css({
			color : 'black'
		}).add().shadow(true);
	} else {

		if (indexfal != 0 && i > indexfal) {
			var backfill= 'rgb(215, 215, 215)';
			if ("Y" == products[i].is_back_node) {
				backfill='#0066ff';
			} 
			ren.label(name, x, y).attr({
				fill : backfill,
				stroke : '#9b9b9b',
				'stroke-width' : 2,
				width : vw,
				padding : 10,
				r : 5
			}).css({
				color : 'black'
			}).add().shadow(true);
		} else if ("Y" == products[i].is_back_node) {
			indexfal = i;
			ren.label(name, x, y).attr({
				fill : '#0066ff',
				stroke : '#9b9b9b',
				'stroke-width' : 2,
				width : vw,
				padding : 10,
				r : 5
			}).css({
				color : 'black'
			}).add().shadow(true);
		} else {

			ren.label(name, x, y).attr({
				fill : 'rgb(244, 168, 189)',
				stroke : '#9b9b9b',
				'stroke-width' : 2,
				width : vw,
				padding : 10,
				r : 5
			}).css({
				color : 'black'
			}).add().shadow(true);

		}

	}

	if (name.length > 6) {
		if (fx / 2 == 1) {
			x = x - (10 * name.length - 90);
		} else {
			x = x + (10 * name.length - 55);
		}

	}

	if (fx > 1) {
		$('#container').css("height", fx * 90 + "px");
	}

}


function commonAddLine(products)
{
	
	while(products.length > i + 1)
	{
		if(fx%2==0)
		{
			ren.path([ 'M', x + 40, y + 46, 'L', x + 40, y +85, 'L', x + 40 - 5, y +80, 'M', x + 40, y +85, 'L', x + 40 + 5, y +80 ]).attr({
				'stroke-width' : 2,
				stroke : colors[3]
			}).add();

			i++;
			y = y + 100;
			for (; i < products.length; i++) {

				commonAddNode(products);

				if (i < products.length - 1) {

					if (x - 130 < 0) {
						fx++;
						break;

					} else {
						ren.path([ 'M', 45, 0, 'L', 0, 0, 'L', 5, 5, 'M', 0, 0, 'L', 5, -5 ]).attr({
							'stroke-width' : 2,
							stroke : colors[3]
						}).translate(x - 50, y + 20).add();

						x = x - 130;
					}

				}

			}
		}else{

			ren.path([ 'M', x + 40, y + 50, 'L', x + 40, y + 95, 'L', x + 40 - 5, y + 90, 'M', x + 40, y + 95, 'L', x + 40 + 5, y + 90 ]).attr({
				'stroke-width' : 2,
				stroke : colors[3]
			}).add();

			i++;
			y = y + 100;
			for (; i < products.length; i++) {

				commonAddNode(products);
				if (i < products.length - 1) {
					ren.path([ 'M', 0, y, 'L', 45, y, 'L', 40, y + 5, 'M', 45, y, 'L', 40, y - 5 ]).attr({
						'stroke-width' : 2,
						stroke : colors[3]
					}).translate(x + 80, 23).add();

					x = x + 130;
				}
				if (i != products.length - 2 && (x + 300) > width) {

					commonAddNode(products);

					fx++;
					break;
				}

			}
		}
	}
 
}
</script> 
</head>
<body style="border:0px;overflow:auto;height:300px;">
	<form action="#">
	
 <div class="list_box mar_2">
	<div class="aulist_3">
		<b></b>审批流程图
		<div class="list_an"></div>
	</div>
	<div></div>
	<div style="width: 100%; height: 0px; background: #fff;"></div>
	<div>
		颜色标识说明：<span style="color: rgb(255, 188, 24);">■</span>未接收 &nbsp;&nbsp;<span style="color: rgb(80, 198, 37);">■</span>办理中 &nbsp;&nbsp;<span style="color: rgb(244, 168, 189);">■</span>办理完毕
		&nbsp;&nbsp;<span style="color: rgb(215, 215, 215);">■</span>预设步骤 &nbsp;&nbsp;<span style="color: #0066ff;">■</span>驳回或驳回重审&nbsp;&nbsp;
	</div>

	<div id="container" style="max-width: 1200px; margin-top: 10px; height: 230px; text-align: center;"></div>
</div>
</form>
</body>
</html>