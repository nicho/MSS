package com.aus.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

import org.springframework.context.ApplicationContext;

import net.sourceforge.rtf.RTFTemplate;
import net.sourceforge.rtf.context.RTFContextFieldsReader;
import net.sourceforge.rtf.context.RTFContextUtil;
import net.sourceforge.rtf.context.RTFXmlFieldsReader;
import net.sourceforge.rtf.document.RTFDocument;
import net.sourceforge.rtf.document.transformer.config.DigesterTransformerConfig;
import net.sourceforge.rtf.document.transformer.config.TransformerConfig;
import net.sourceforge.rtf.helper.RTFTemplateBuilder;
import net.sourceforge.rtf.template.IContext;

public class RTFGenerator {

	private Map<String, Object> contextMap;

	private RTFTemplate rtfTemplate = null;
	private File transformerConfigFile = null;

	private ApplicationContext applicationContext;
	private String rtfTemplateImpl;

	private boolean saveTransformedDocument = false;

	/**
	 * This value allow to group by content when there is PageBreak in order to
	 * group by content.
	 */
	private int groupByPerPageBreak = -1;

	/**
	 * Run RTFTemplate for merging rtfSource with the context putted with the
	 * method putContext which be must implement. After execution of this
	 * method, files rtfSource + ".<rtfTemplateImpl>.rtf" (RTF template
	 * implementation (vmRTFtemplate,...) and rtfSource + ".out.rtf" (RTF final
	 * with values of the context) will be generate.
	 * 
	 * @param rtfSource
	 *            RTF source model.
	 * @throws Exception
	 */
	public final void run(String rtfSource, String rtfTarget) throws Exception {

		File rtfSourceFile = new File(rtfSource);

		String rtfTransformedDocumentOutput = rtfSource + "." + getRtfTemplateImpl() + ".rtf";

		/**
		 * 1. Get RTFtemplate builder
		 */
		RTFTemplateBuilder builder = null;
		if (applicationContext == null)
			builder = RTFTemplateBuilder.newRTFTemplateBuilder();
		else
			builder = RTFTemplateBuilder.newRTFTemplateBuilder(applicationContext);

		/**
		 * 2. Get RTFtemplate with Implementation
		 */
		this.rtfTemplate = builder.newRTFTemplate(rtfTemplateImpl);
		this.rtfTemplate.setGroupByPerPageBreak(groupByPerPageBreak);

		/**
		 * 3. Put default format
		 */
		putDefaultFormat(rtfTemplate);

		/**
		 * 4. Create a common inner context - not required but showing how
		 * common context values can be re-used
		 */
		IContext ctx = rtfTemplate.getTemplateEngine().newContext();
		putGlobalContext(ctx);

		/**
		 * 5. Set the template
		 */
		rtfTemplate.setTemplate(rtfSourceFile);

		/**
		 * 6. Set Global Context
		 */
		rtfTemplate.setGlobalContext(ctx);

		/**
		 * 7. Set Transformer Config
		 */
		if (transformerConfigFile != null) {
			TransformerConfig transformConfig = DigesterTransformerConfig.getTransformerConfig(new FileInputStream(transformerConfigFile));
			rtfTemplate.setTransformerConfig(transformConfig);
		}

		/**
		 * 8. Put Context
		 */
		putContext(rtfTemplate.getContext());
		if (saveTransformedDocument) {
			RTFDocument transformedDocument = rtfTemplate.transform();
			transformedDocument.save(new File(rtfTransformedDocumentOutput));
		}

		/**
		 * 9. Merge template and context
		 */
		rtfTemplate.merge(rtfTarget);
	}

	/**
	 * This method must be implement by class wich manage your RTF model. Put
	 * the context of your model (eg : context("date", new Date()); )
	 * 
	 * @param context
	 *            IContext
	 */
	protected void putContext(IContext context) {
		for (String key : contextMap.keySet()) {

			if (contextMap.get(key) instanceof java.lang.String) {
				context.put(key, GBKEncoded.getEncodedRTFString(contextMap.get(key) + ""));
			}
//			else if (contextMap.get(key) instanceof List<?>) {
//
//				List<?> list = (List<?>) contextMap.get(key);
//
//				List<Object> newlist = new ArrayList<Object>();
//				for (Object object : list) {
//					if (object instanceof ReimburseLDto) {
//						ReimburseLDto dto = (ReimburseLDto) object;
//						dto.setAttribute1(GBKEncoded.getEncodedRTFString(dto.getAttribute1()));
//						dto.setCity(GBKEncoded.getEncodedRTFString(dto.getCity()));
//						dto.setExpenseItem(GBKEncoded.getEncodedRTFString(dto.getExpenseItem()));
//						dto.setReimburseAmount(GBKEncoded.getEncodedRTFString(dto.getReimburseAmount()));
//						dto.setStartDate(GBKEncoded.getEncodedRTFString(dto.getStartDate()));
//						dto.setEndDate(GBKEncoded.getEncodedRTFString(dto.getEndDate()));
//						dto.setEventDesc(GBKEncoded.getEncodedRTFString(dto.getEventDesc()));
//						newlist.add(dto);
//					} else if (object instanceof ApprovalHisDto) {
//						ApprovalHisDto dto = (ApprovalHisDto) object;
//						dto.setApprovalUserName(GBKEncoded.getEncodedRTFString(dto.getApprovalUserName()));
//						dto.setActionName(GBKEncoded.getEncodedRTFString(dto.getActionName()));
//						dto.setStatusName(GBKEncoded.getEncodedRTFString(dto.getStatusName()));
//						dto.setApprovalDate(GBKEncoded.getEncodedRTFString(dto.getApprovalDate()));
//						dto.setApprovalComment(GBKEncoded.getEncodedRTFString(dto.getApprovalComment()));
//						newlist.add(dto);
//					} else {
//						newlist.add(object);
//					}
//				}
//				context.put(key, newlist);
//
//			}
			else {
				context.put(key, contextMap.get(key));
			}

		}
	}

	/**
	 * Return String XML Mergefields used in your context and Bookmarks (for
	 * start and end loop)
	 * 
	 * @return
	 */
	public String getXMLFields() {
		// XML
		RTFXmlFieldsReader reader = new RTFXmlFieldsReader();
		reader.readContext(rtfTemplate.getContext(), rtfTemplate.getTransformerConfig());
		return reader.getXMLFields();
	}

	protected void putDefaultFormat(RTFTemplate template) {
	}

	protected void putGlobalContext(IContext context) {
	}

	/**
	 * Save XML fields available into file. If force parameter is false, the
	 * file is updated with new context (by keeping just description) otherwise
	 * the file is crushed with new context.
	 * 
	 * @param filename
	 * @throws Exception
	 */
	public void saveXmlFields(String filename, boolean force) throws Exception {
		RTFContextFieldsReader reader = new RTFContextFieldsReader();
		reader.readContext(rtfTemplate.getContext(), rtfTemplate.getTransformerConfig());
		RTFContextUtil.saveXmlFields(filename, reader.getContextFields(), force);
	}

	public void setTransformerConfigFile(String transformerConfig) {
		setTransformerConfigFile(new File(transformerConfig));
	}

	public void setTransformerConfigFile(File transformerConfigFile) {
		this.transformerConfigFile = transformerConfigFile;
	}

	/**
	 * set true if RTF with (velocity, freemarker,... macro) file must be
	 * generated and false otherwise.
	 * 
	 * @param saveTransformedDocument
	 */
	public void saveTransformedDocument(boolean saveTransformedDocument) {
		this.saveTransformedDocument = saveTransformedDocument;
	}

	public String getRtfTemplateImpl() {
		if (rtfTemplateImpl == null) {
			this.rtfTemplateImpl = RTFTemplateBuilder.DEFAULT_VELOCITY_RTFTEMPLATE;
		}
		return rtfTemplateImpl;
	}

	public void setRtfTemplateImpl(String rtfTemplateImpl) {
		this.rtfTemplateImpl = rtfTemplateImpl;
	}

	protected int getGroupByPerPageBreak() {
		return groupByPerPageBreak;
	}

	/**
	 * This value allow to group by content when there is PageBreak in order to
	 * group by content.
	 * 
	 * @param groupByPerPageBreak
	 */
	protected void setGroupByPerPageBreak(int groupByPerPageBreak) {
		this.groupByPerPageBreak = groupByPerPageBreak;
	}

	protected RTFTemplate getRtfTemplate() {
		return rtfTemplate;
	}

	protected void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public void setContextMap(Map<String, Object> contextMap) {
		this.contextMap = contextMap;
	}

}
