package embrace.devops;

import org.mule.runtime.api.meta.ExpressionSupport;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.values.OfValues;

public class ADOParametersProject {
	@Parameter
	@Expression(ExpressionSupport.SUPPORTED)
	@Optional(defaultValue = "Project Name")
	private String project;

	@Parameter
	@Expression(ExpressionSupport.SUPPORTED)
	@Optional(defaultValue = "Project Description")
	private String desc;

	@Parameter
	@Expression(ExpressionSupport.SUPPORTED)
	@Optional(defaultValue = "Git")
	private String scm;

	@Parameter
	@Expression(ExpressionSupport.SUPPORTED)
	@Optional(defaultValue = "6b724908-ef14-45cf-84f8-768b5384da45")
	private String processTemplateId;

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getScm() {
		return scm;
	}

	public void setScm(String scm) {
		this.scm = scm;
	}

	public String getProcessTemplateId() {
		return processTemplateId;
	}

	public void setProcessTemplateId(String processTemplateId) {
		this.processTemplateId = processTemplateId;
	}

		
	
}
