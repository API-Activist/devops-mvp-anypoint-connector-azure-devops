package embrace.devops;

import org.mule.runtime.api.meta.ExpressionSupport;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.values.OfValues;

public class ADOParameterWorkItemTypes {
	@Parameter
	@Expression(ExpressionSupport.SUPPORTED)
	@Optional(defaultValue = "Issue")
	private String workitemtype;
	
	public String getWorkitemtype() {
		return workitemtype;
	}

	public void setWorkitemtype(String workitemtype) {
		this.workitemtype = workitemtype;
	}


}
