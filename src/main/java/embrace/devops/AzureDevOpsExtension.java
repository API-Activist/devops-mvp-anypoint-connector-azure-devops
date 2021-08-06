package embrace.devops;

import org.mule.runtime.extension.api.annotation.Configurations;
import org.mule.runtime.extension.api.annotation.Extension;
import org.mule.runtime.extension.api.annotation.dsl.xml.Xml;


@Xml(prefix="ado")
@Extension(name="Azure DevOps")
@Configurations(AzureDevOpsConfiguration.class)
public class AzureDevOpsExtension {
	

}
