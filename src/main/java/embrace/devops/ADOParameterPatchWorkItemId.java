package embrace.devops;

import org.mule.runtime.api.meta.ExpressionSupport;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.values.OfValues;

public class ADOParameterPatchWorkItemId {
	@Parameter
	@Expression(ExpressionSupport.SUPPORTED)
	@Optional(defaultValue = "1")
	private String workitemid;
	
	public String getWorkitemid() {
		return workitemid;
	}

	public void setWorkitemid(String workitemid) {
		this.workitemid = workitemid;
	}


}
