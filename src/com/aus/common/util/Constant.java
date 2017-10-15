package com.aus.common.util;

public class Constant {
	public static final String WORKFLOW_STATUS_START = "2";
	public static final String WORKFLOW_STATUS_DOCUMENT = "APPROVAL";
	public static final String WORKFLOW_STATUS_DIARY_ALLOW = "COMMITTED";
	public static final String DRAFT_STATUS_DOCUMENT = "DRAFT";
	public static final String DRAFT_STATUS_DIARY = "UNCOMMITTED";
	public static final String STATUS_Y = "Y";

	public static final String BUSINESSTRIP_BUSINESSTRIP = "BUSINESSTRIP"; // 出差-流程类型
	public static final String DCOUMENT_NOTICE = "NOTICE"; // 通知公告-流程类型
	public static final String OFFICE_SPLH = "CUX_FIN_OFFICESPL"; // 办公用品-流程类型
	public static final String MEM_COMPLAINT = "CUX_MEM_COMPLAINT"; // 投诉-流程类型
	public static final String DCOUMENT_SYSTEM = "BYLAW"; // 制度-流程类型
	public static final String DCOUMENT_COMPANY_DOCUMENT = "CERTIFICATE"; // 公司证件-流程类型
	public static final String DCOUMENT_KNOWLEDGE = "KNOWLEDGE"; // 知识库-流程类型 ,知识库-目录类型
	public static final String DOCUMENT = "DOCUMENT"; //文件类型-目录类型 
	public static final String DCOUMENT_MY_DOC = "MYDOC"; // VI设计-流程类型
	public static final String DCOUMENT_DESIGN = "DESIGN"; // VI设计-流程类型
	public static final String ASKFORLEAVE_TYPE_TIMEOFF = "CUX_TIMEOFF"; // 请假单-流程类型
	public static final String ASKFORLEAVE_TYPE_OVERTIME = "CUX_OVERTIME"; // 加班单-流程类型
	public static final String ASKFORLEAVE_TYPE_OPERATION = "COMMIT"; // 操作类型-请假加班单
	public static final String ASKFORLEAVE_TYPE_PROCNAME = "AUPORTAL.PROC_UPDATE_ASKFORLEAVE"; // 请假单-存储过程名称

	public static final String OPERATIONCODE_BUSINESSTRIP = "COMMIT"; // 出差-操作类型
	public static final String PROCNAME_BUSINESSTRIP = "AUPORTAL.PROC_UPDATE_BUSINESS_TRIP";// 出差-存储过程名
	public static final String PROCNAME_DOC = "AUPORTAL.PROC_UPDATE_DOCUMENT";// 文档-存储过程名

	public static final String PROCNAME_SPLH = "AUPORTAL.PROC_UPDATE_OFFICESPL";// 办公用品-存储过程名
	public static final String PROCNAME_COMPLAINT = "AUPORTAL.PROC_UPDATE_COMPLAINT";// 投诉-存储过程名

	public static final String IMPORT_IP = "112.124.22.126";
	public static final String IMPORT_IMG_IP ="img.ausnutria.com";
	public static final String IMPORT_PORT = "21";
	public static final String IMPORT_ADMIN_USER = "oa_appftp";
	public static final String IMPORT_IMG_ADMIN_USER = "img_web";
	public static final String IMPORT_ADMIN_PASSWORD = "oaftp392eke#$992";
	public static final String IMPORT_IMG_ADMIN_PASSWORD = "img_web9890";
	public static final String IMPORT_NORMAL_USER = "oa_userftp";
	public static final String IMPORT_NORMAL_PASSWORD = "oa_8302_ire$kd3";
	public static final String OPERATIONCODE_DOCUMENT = "COMMIT"; // 文档-操作类型
	public static final String PROCNAME_BORROW = "AUPORTAL.PROC_UPDATE_BORROW";// 借支管理工作流回调-存储过程名	
	public static final String PROCNAME_MEETING = "AUPORTAL.PROC_UPDATE_MEETING";// 会议管理工作流回调-存储过程名	

	public static final String PROCNAME_PROMT = "AUPORTAL.PROC_UPDATE_PROMT";// 积分促销单工作流回调-存储过程名
	public static final String PROMTWORKFLOW = "CUX_MEM_PROMT";// 积分促销单工作流

	public static final String PROCNAME_BNFTPAY = "OAHR.PROC_UPDATE_BNFTPAY";// 工资发放工作流回调-存储过程名
	public static final String BNFTPAYWORKFLOW = "CUX_OA_BNFT_PAY";// 工资发放工作流
	
	public static final String PROCNAME_BFM = "AUPORTAL.PROC_UPDATE_BFM";// 转正申请工作流回调-存储过程名
	public static final String BFMWORKFLOW = "CUX_OA_HR_BFM";// 转正申请工作流
	
	public static final String PROCNAME_SALARY = "AUPORTAL.PROC_UPDATE_SALARY";// 薪资调整申请工作流回调-存储过程名
	public static final String SALARYWORKFLOW = "CUX_OA_HR_SALARY_CHG";// 薪资调整申请单工作流
	
	public static final String PROCNAME_LEAVE = "AUPORTAL.PROC_UPDATE_LEAVE";// 离职申请工作流回调-存储过程名
	public static final String LEAVEWORKFLOW = "CUX_OA_HR_LEAVE_TRN";// 离职申请单工作流

	public static final String PROCNAME_JOB_CHG = "AUPORTAL.PROC_UPDATE_JOB_CHG";// 岗位调动工作流回调-存储过程名
	public static final String JOB_CHG_WORKFLOW = "CUX_OA_HR_JOB_CHG";// 岗位调动工作流
	
	public static final String PROCNAME_CAR = "AUPORTAL.PROC_UPDATE_CAR";// 自驾车标准工作流回调-存储过程名
	public static final String CARWORKFLOW = "CUX_OA_HR_CAR";// 自驾车标准工作流
	
	public static final String PROCNAME_LTRANSFER = "AUPORTAL.PROC_UPDATE_LTRANSFER";// 离职移交申请工作流回调-存储过程名
	public static final String LTRANSFERWORKFLOW = "CUX_OA_HR_LTRANSFER_TRN";// 离职移交申请单工作流
	
	public static final String PROCNAME_SDH = "AUPORTAL.PROC_UPDATE_SALEDEALERHOVER";//经销商交接单工作流回调-存储过程名
	public static final String SDHWORKFLOW = "CUX_SALE_DEALER_HOVER";// 经销商交接单工作流
	
	public static final String PROCNAME_MKPRM = "AUPORTAL.PROC_UPDATE_MKPRM";//促销品申请工作流回调-存储过程名
	public static final String MKPRMWORKFLOW = "CUX_MK_PRM";// 促销品申请工作流
	
	public static final String PROCNAME_SCC = "AUPORTAL.PROC_UPDATE_SALECHANNELCHG";//经销商及门店变更工作流回调-存储过程名
	public static final String SCCWORKFLOW = "CUX_SALE_DS_CHG";// 经销商及门店变更工作流
	
	public static final String PROCNAME_SDP = "AUPORTAL.PROC_UPDATE_SALEDEALER";//经销商新增单工作流回调-存储过程名
	public static final String SDPWORKFLOW = "CUX_SALE_DEALER_P";//经销商新增单工作流
	
	public static final String PROCNAME_EMPTRAIN = "AUPORTAL.PROC_UPDATE_EMPTRAIN";//培训申请工作流回调-存储过程名
	public static final String EMPTRAINWORKFLOW = "CUX_EMP_TRAIN_P";// 培训申请工作流

	public static final String PROCNAME_LGT = "AUPORTAL.PROC_UPDATE_LGT";//物流破损退货工作流回调-存储过程名
	public static final String LGTWORKFLOW = "CUX_OA_LGT_RETURN";// 物流破损退货工作流
	public static final String PROCESSCODE_CONTRACT = "CUX_FIN_AGMT";// 合同管理 流程类型
	public static final String OPERATIONCODE_CONTRACT = "COMMIT";// 合同管理 操作类型
	public static final String PROCNAME_CONTRACT = "AUPORTAL.PROC_UPDATE_CONTRACT";// 合同管理工作流回调-存储过程名	
	
	public static final String PROCESSCODE_AUTHORIZEDBOOK = "CUX_SALE_CERTAUTH";// 授权单 流程类型
	public static final String OPERATIONCODE_AUTHORIZEDBOOK = "COMMIT";// 授权单 操作类型
	public static final String PROCNAME_AUTHORIZEDBOOK = "AUPORTAL.PROC_UPDATE_AUTHORIZEDBOOK";// 授权单工作流回调-存储过程名	

	public static final String PROCESSCODE_CUXLOGISTICSRULE = "CUX_LOGISTICS_RULE";//运费计价规则配置 流程类型 
	public static final String OPERATIONCODE_CUXLOGISTICSRULE = "COMMIT";// 运费计价规则配置 操作类型
	public static final String PROCNAME_CUXLOGISTICSRULE = "AUPORTAL.PROC_UPDATE_CUXLOGISTICSRULE";// 运费计价规则配置工作流回调-存储过程名
	
	public static final String PROCESSCODE_CUXCDMTRANSFERHEADER = "INV_TRANSFER";// 调拨单 流程类型
	public static final String OPERATIONCODE_CUXCDMTRANSFERHEADER = "COMMIT";// 调拨单 操作类型
	public static final String PROCNAME_CUXCDMTRANSFERHEADER = "AUPORTAL.PROC_UPDATE_CUXTRANSFERHEADER";// 调拨单工作流回调-存储过程名	
	
	public static final String PROCESSCODE_PRICECATALOGUE = "PRICECATALOGUE";// 价目表批次 流程类型
	public static final String OPERATIONCODE_PRICECATALOGUE = "COMMIT";//  价目表批次 操作类型
	public static final String PROCNAME_PRICECATALOGUE = "AUPORTAL.PROC_UPDATE_PRICECATALOGUE";//  价目表批次 工作流回调-存储过程名	
	
	
	public static final String PROCESSCODE_SAMPLING = "OA_SAMPLING";// 抽样 流程类型
	public static final String OPERATIONCODE_SAMPLING = "COMMIT";//  抽样 操作类型
	public static final String PROCNAME_SAMPLING = "AUPORTAL.PROC_UPDATE_SAMPLING";//  抽样 工作流回调-存储过程名	
	
	
	public static final String OA_INV_MISC = "CUX_INV_MISC"; // 库存杂项-流程类型
	public static final String INV_MISC_OPERATION = "commit"; // 操作类型-库存杂项
	
	public static final String MESSAGE_TYPE_CODE_EMAIL = "MAIL";// 消息类型
	public static final String MESSAGE_TYPE_CODE_SMS = "SMS";// 消息类型
	public static final String MESSAGE_TYPE_CODE_WEIXIN = "WEIXIN";// 消息类型
	public static final long MSGINTERFACEID_SMS = 9320;// 消息接口ID_短信_ 机构81
	public static final long MSGINTERFACEID_EMAIL = 9340;// 消息接口ID_邮件_ 机构81
	public static final long MSGINTERFACEID_SOURCEFUNCID = 1025;// 功能ID 出差
	public static final long MSGINTERFACEID_SOURCEFUNCID_MEETING = 1048;// 功能ID 会议
	public static final long MSGINTERFACEID_SOURCEFUNCID_MESSAGE = 1031;// 功能ID   消息发送
	public static final long MSGINTERFACEID_SOURCEFUNCID_ORDERLIST = 1523;// 功能ID    订单列表
	public static final String SUCCESS = "SUCCESS";
	public static final String ERROR = "ERROR";
	
	public static final String MSGUSERNAME = "admin";
	public static final String MSGUSERPWD = "xxxxxxx";
	

	public static final String STORE_NEW     = "STORENEW";      // 新开门店
	public static final String STORE_INVALID = "STOREINVALID";  // 门店失效
	public static final String STORE_UPDATE  = "STOREUPDATE";   // 门店变更
	

	public static final String MESSAGE_SEND_STATUS_WAIT = "PENDING";// 1.待发送
	public static final String MESSAGE_SEND_STATUS_RUNING = "RUNING";// 正在发生
	public static final String MESSAGE_SEND_STATUS_SUCCESS = "SUCCEED";// 2.已发送 
	public static final String MESSAGE_SEND_STATUS_ERROR = "FAIL";// 3.发送错误
	
	//profile字典
	public static final String ORG_ID = "ORG_ID";
	public static final String CHANNEL = "CHANNEL";
	public static final String USER_TYPE = "USER_TYPE";
	//profile字典
	
	public static final String BORROWAPPLY = "BORROWAPPLY";    //借支申请工作流类型
	
	/**会议工作流类型**/
	public static final String MEETINGAPPLYWORKFLOW = "MEETING";
	//通知公告lis
	public static final String DOCUMENT_LIST = "docApplyList.do?docType=1";
	//通知公告update
	public static final String DOCUMENT_UPDATE = "initUpdateDoc.do";
	//通知公告view
	public static final String DOCUMENT_VIEW = "docApplyView.do";
	
	//规章制度list
	public static final String APPROVAL_LIST = "approvalList.do";
	//规章制度update
	public static final String APPROVAL_UPDATE = "initUpdateApproval.do";
	//规章制度view
	public static final String APPROVAL_VIEW = "approvalView.do";
	
	//公司证件list
	public static final String CERTIFICATE_LIST = "certificateList.do";
	//公司证件update
	public static final String CERTIFICATE_UPDATE = "initUpdateCertificate.do";
	//公司证件view
	public static final String CERTIFICATE_VIEW = "certificateView.do";
	
	
	//我的文档list
	public static final String MYDOCUMENT_LIST = "myDocumentList.do";
	//我的文档update
	public static final String MYDOCUMENT_UPDATE = "initUpdateMyDocument.do";
	//我的文档view
	public static final String MYDOCUMENT_VIEW = "myDocumentView.do";
	
	//知识库list
	public static final String KNOWLEDGE_LIST = "knowledgeList.do";
	//知识库update
	public static final String KNOWLEDGE_UPDATE = "initUpdateKnowledge.do";
	//知识库view
	public static final String KNOWLEDGE_VIEW = "knowledgeView.do";
	
	
	//微信功能ID(澳优)
	public static final String MSGINTERFACEID_WEIXINFUNCID_TRIP = "61";//   行政
	public static final String MSGINTERFACEID_WEIXINFUNCID_MEETING = "63";//   会议
	public static final String MSGINTERFACEID_WEIXINFUNCID_TODO = "62";//   待办 
	
	//微信url(澳优)
	public static final String WEIXINURL_TRIP = "http://www.iseeau.cn/ess/mobile/search_travel.do";//   行政
	public static final String WEIXINURL_MEETING = "http://www.iseeau.cn/ess/mobile/search_meeting.do";//   会议
	public static final String WEIXINURL_TODO = "http://www.iseeau.cn/ess/mobile/todo.do";//   待办 
	
	
	public static final String MD5_KEY = "liang!@#$%";//   密码加密KEY
	
	public static final String CorpID = "wxbaa80479dd64524f";
	public static final String Secret = "FbyhVFaYoWdtXAMItzRPuexSANAaGD8SH0CuljHwjJWjGZbwmQCxcx2NoZJ74vRO";
	
	public static final String CorpID_HP = "wx98aaf1e2d2a6dd20";
	public static final String Secret_HP = "o4kn90z_lAYL_uwtbdOe7QDIPkll0Ei3oSvJlS7sfjfF_HBRF_hCqM6xT6CPvY3Y";
	
	public static final String CorpID_1897 = "wx066844a6a8ebb6bd";
	public static final String Secret_1897 = "FKyiVTUBVwS9A2F1JIg7fvIb2vIjyb09rSI4uE0htlR-48EJATc16J3PiMGEUpS7";
	
	public static final String CorpID_GN = "wxadebb5bedca9afb3";
	public static final String Secret_GN = "Hr97TxCUDXM22mk41nMjbtz2jXOaw32pZBpwGlFf0KnC_tlreddk9w0Ku5mD9Q3m";
	
	public static final String CorpID_PRD = "wxd677da1f8ff44979";
	public static final String Secret_PRD = "GMx8hpExLVnA2w9U35pKrJLqAwOwoHc4FImWrXeMFtcH8UmD6Pzw85okxH7UklcG";
	
	//获取Ip对应真实地址的API地址
	public static final String TAOBAO_API = "http://ip.taobao.com/service/getIpInfo.php?ip=";

    public static final String MONEY_TYPE_CNY ="CNY"; 

    
    public static final String ODM_QUOTADETAIL_UPDATE  = "CUX_CSS_ODM_QUOTADETAIL";   //配额审批 流程类型
    public static final String OPERATIONCODE_ODM_QUOTADETAIL = "COMMIT"; // 配额审批-操作类型
    
    public static final String ODM_PMTCONFIG_UPDATE  = "AUCSS_ODM_PMT_CFG";   //促销规则 流程类型
    public static final String OPERATIONCODE_ODM_PMTCONFIG = "COMMIT"; // 促销规则-操作类型
    public static final String WEBSERVICETARGETNAMESPACE = "http://tws.aus.com/"; // webservice命名空间
    public static final String CUX_CSS_ODM_ORDER  = "CUX_CSS_ODM_ORDER";   //订单审批 流程类型
    public static final String OPERATIONCODE_ODM_ORDER = "COMMIT"; // 订单审批 -操作类型
    
    public static final String CUX_CSS_ODM_ORDERRETURN  = "CUX_CSS_ODM_ORDERRETURN";   //退货订单审批 流程类型
    public static final String OPERATIONCODE_ODM_ORDERRETURN = "COMMIT"; // 退货订单审批 -操作类型
    
    public static final String OPERATIONCODE_SALE_SHOP = "COMMIT";//门店新增单 操作类型
	public static final String SALE_SHOP_UPDATE = "CUX_SALE_SHOP_P";//门店新增单  流程类型
	
    public static final String CUX_CSS_DAHEDBACK  = "CUX_CSS_DAHEDBACK";   //冲红审批 流程类型
    public static final String OPERATIONCODE_ODM_DAHEDBACK= "COMMIT"; // 冲红审批 -操作类型
    
    public static final String OPERATIONCODE_SALE_PDA = "COMMIT";//经销商PDA申领 操作类型
	public static final String SALE_PDA_UPDATE = "CUX_SALE_PDA_P";//经销商PDA申领  流程类型
	
	public static final String OPERATIONCODE_SALE_LGTMFREE = "COMMIT";//经销商物流扣款免除申请 操作类型
	public static final String SALE_LGTMFREE_UPDATE = "CUX_SALE_LGTMFREE_P";//经销商物流扣款免除申请  流程类型
	
	public static final String OPERATIONCODE_SALE_ABNORMAL = "COMMIT";//物流异常返账处理申请 操作类型
	public static final String SALE_ABNORMAL_UPDATE = "CUX_SALE_ABNORMAL_P";//物流异常返账处理申请  流程类型
	
	public static final String OPERATIONCODE_OA_MK_DON_HEAD = "COMMIT";//优爱申请单 操作类型
	public static final String PROCESSCODE_OA_MK_DON_HEAD = "OA_MK_DON_HEAD";//优爱申请单  流程类型
	public static final String PROCNAME_OA_MK_DON_HEAD = "AUPORTAL.PROC_UPDATE_OA_MK_DON_HEAD";// /优爱申请单-存储过程名	
	
	public static final String OPERATIONCODE_OA_MK_DONCAV_HEAD = "COMMIT";//优爱核销单 操作类型
	public static final String PROCESSCODE_OA_MK_DONCAV_HEAD = "OA_MK_DONCAV_HEAD";//优爱核销单  流程类型
	public static final String PROCNAME_OA_MK_DONCAV_HEAD = "AUPORTAL.PROC_UPDATE_OA_MK_DONCAV_HEAD";// /优爱核销-存储过程名
	public static final String INTIPASS = "123456";
	
	public static final String PROCNAME_SALEAGMINFO = "AUPORTAL.PROC_UPDATE_SALEAGMINFO";//经销商合同工作流回调-存储过程名

	public static final String PROCNAME_STOREAGMINFO = "AUPORTAL.PROC_UPDATE_STOREAGMINFO";//门店合同工作流回调-存储过程名
	
	public static final String PROCNAME_SALEPDAINFO = "AUPORTAL.PROC_UPDATE_SALEPDAINFO";//PDA信息管理-存储过程名
	
	public static final String SALEAGMINFOFLOW = "CUX_SALE_AGM";// 经销商合同流程
	
	public static final String STOREAGMINFOFLOW = "CUX_STORE_AGM";// 门店合同流程
	
	public static final String SALEPDAINFOFLOW = "CUX_SALE_PDAIFM";// PDA信息管理

	public static final String PROCNAME_PAY_CHECK_IN = "PAY_CHECKIN";//考勤-存储过程名
	
	public static final String PROCNAME_PAY_PSN = "PAY_PSN";//薪酬-存储过程名
	
	public static final String PROCNAME_EBREFUND = "AUPORTAL.PROC_UPDATE_EBREFUND";//未发货退款工作流回调-存储过程名

	public static final String EBREFUNDFLOW = "CUX_EB_REFUND";//未发货退款流程
	
	public static final String RETURN_REP = "CUX_RETURN_REP"; // 退货换货-流程类型
	
	public static final String REFUND = "CUX_REFUND"; // 退款-流程类型
	
	public static final String  AP_DETRV= "CUX_AP_DETRV"; // 经销商转款-流程类型
	
	public static final String  AP_TRV= "CUX_AP_TRV"; // 差旅费用报账单-流程类型
	
	public static final String  PRMT_ACT_RESE= "PRMT_ACT_RESE"; // 物料申领提报
	
	public static final String HR_RECRUIT = "CUX_HR_RECRUIT"; // 招聘需求申请-流程类型
	
	public static final String HR_TRY_EVAL = "CUX_HR_TRY_EVAL"; // 人员转正评价-流程类型
	
	public static final String TRANSFER = "CUX_TRANSFER"; // 银行间资金划转-流程类型
	
	public static final String DISPH = "CUX_DISPH"; // 调货-流程类型
	public static final String PROCNAME_RETURN_REP = "AUPORTAL.PROC_UPDATE_RETURN_REP";// 退换补货-存储过程名
	
	public static final String PROCNAME_REFUND = "AUPORTAL.PROC_UPDATE_REFUND";// 退款-存储过程名
	
	public static final String PROCNAME_AP_DETRV = "AUPORTAL.PROC_UPDATE_AP_DETRV";// 经销商转款-存储过程名
	
	public static final String PROCNAME_AP_TRV = "AUPORTAL.PROC_UPDATE_AP_TRV";// 差旅费用报账单-存储过程名
	
	public static final String PROCNAME_PRMT_ACT_RESE = "AUPORTAL.PROC_UPDATE_PRMT_ACT_RESE";// 物料申领提报-存储过程名
	
	public static final String PROCNAME_TRANSFER = "AUPORTAL.PROC_UPDATE_TRANSFER";// 银行间资金划转-存储过程名
	
	public static final String PROCNAME_DISPH = "AUPORTAL.PROC_UPDATE_DISPH";// 调货-存储过程名
	
	public static final String PROCNAME_HRHSUBSIDY = "AUPORTAL.PROC_UPDATE_HRHSUBSIDY";//住房补贴工作流回调-存储过程名

	public static final String HRHSUBSIDYFLOW = "CUX_HR_HSUBSIDY";//住房补贴
	
	public static final String PROCNAME_HRWMIKE = "AUPORTAL.PROC_UPDATE_HRWMIKE";//奶粉福利申请工作流回调-存储过程名
	
	public static final String PROCNAME_HR_RECRUIT = "AUPORTAL.PROC_UPDATE_HR_RECRUIT";//奶粉福利申请工作流回调-存储过程名

	public static final String PROCNAME_HR_EVL = "AUPORTAL.PROC_UPDATE_HR_TRY_EVL";//人员转正评价工作流回调-存储过程名
	
	public static final String HRWMIKEFLOW = "CUX_HR_WMIKE";//奶粉福利申请
	
	public static final String PROCNAME_HRGUIDER = "AUPORTAL.PROC_UPDATE_HRGUIDER";//专导编制工作流回调-存储过程名

	public static final String HRGUIDERFLOW = "CUX_HR_GUIDER";//专导编制
	
	public static final String HRMEETFLOW = "CUX_HR_MEET";//省区会议
	
	public static final String PROCNAME_HRMEET = "AUPORTAL.PROC_UPDATE_HRMEET";//省区会议工作流回调-存储过程名
	
	public static final String XLS = "xls";
	public static final String XLSX = "xlsx";
	
	public static final String PROCNAME_MKPRMCZP = "AUPORTAL.PROC_UPDATE_MKPRM";//产品申请工作流回调-存储过程名
	public static final String MKPRMWORKFLOWCZP = "CUX_MK_PRM_CZP";// 产品申请工作流
	
	public static final String RECRUIT_INTERVIEW = "CUX_OA_RECRUIT_INTERVIEW"; // 入职录用-流程类型
	public static final String PROCNAME_RECRUIT_INTERVIEW = "AUPORTAL.PROC_UPDATE_RECRUIT_INTERVIEW";//入职录用-存储过程名
	
	public static final String RECRUIT_ENTRYWQEEK = "CUX_OA_RECRUIT_ENTRYWQEEK"; // 入职一周反馈-流程类型
	public static final String PROCNAME_RECRUIT_ENTRYWQEEK = "AUPORTAL.PROC_UPDATE_RECRUIT_ENTRYWQEEK";//入职一周反馈-存储过程名
	
	public static final String RECRUIT_ENTRYONEDAY = "CUX_OA_RECRUIT_ENTRYONEDAY"; // 入职一天反馈-流程类型
	public static final String PROCNAME_RECRUIT_ENTRYONEDAY = "AUPORTAL.PROC_UPDATE_RECRUIT_CONONEDAY";//入职一天反馈-存储过程名

	public static final String RECRUIT_POSITION = "CUX_OA_RECRUIT_POSITION"; // 职位需求申请-流程类型
	public static final String PROCNAME_RECRUIT_POSITION = "AUPORTAL.PROC_UPDATE_RECRUIT_POSITION";//职位需求申请工作流回调-存储过程名

	//人事项目手机端应聘登记表正式环境地址
	public static final String APPLY_FORM_URL = "http://www.iseeau.cn/ess/mobile/initEditApplyForm.do?resumeId=";

	public static final String INFOAUCSS_DZD_RETURNBACK = "INFOAUCSS_DZD_RETURNBACK"; // 积分对账单财务审核回复通知
}
