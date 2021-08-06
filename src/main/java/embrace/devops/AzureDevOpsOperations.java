package embrace.devops;

import org.mule.runtime.extension.api.annotation.Alias;
import org.mule.runtime.extension.api.annotation.param.Config;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;

import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;

import javax.net.ssl.HttpsURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AzureDevOpsOperations {

	private static final Logger LOGGER = LoggerFactory.getLogger(AzureDevOpsOperations.class);
	
	@MediaType(value = ANY, strict = false)
	@Alias("Get-all-projects")
	public String getProjects(@Config AzureDevOpsConfiguration ADOConfig) throws IOException, InterruptedException {
    	LOGGER.info("Azure DevOps getProjects entered");  		

		String responseBody;
		String url_in = ADOConfig.getUrl();
		String orgId = ADOConfig.getOrganizationId();
    	String url = url_in + "/" + orgId + "/_apis/projects";
		
		responseBody = getRequest(ADOConfig, url);

		return responseBody;
		
		
	}	

	@MediaType(value = ANY, strict = false)
	@Alias("List-all-process-templates")
	public String getProcessTemplates(@Config AzureDevOpsConfiguration ADOConfig) throws IOException, InterruptedException {
    	LOGGER.info("Azure DevOps getProcessTemplates entered");  		

		String responseBody;
		String url_in = ADOConfig.getUrl();
		String orgId = ADOConfig.getOrganizationId();
    	String url = url_in + "/" + orgId + "/_apis/process/processes";
		
		responseBody = getRequest(ADOConfig, url);

		return responseBody;
		
		
	}	


	
	@MediaType(value = ANY, strict = false)
	@Alias("Create-new-project")
	public String createProject(@Config AzureDevOpsConfiguration ADOConfig,  @ ParameterGroup(name="Project") ADOParametersProject ADOProject) throws IOException, InterruptedException {
    	LOGGER.info("Azure DevOps getBacklogItems entered");  		

    	String payloadBLIs = "{\r\n"
    			+ "  \"name\": \"" + ADOProject.getProject() + "\",\r\n"
    			+ "  \"description\": \"" + ADOProject.getDesc() + "\",\r\n"
    			+ "  \"capabilities\": {\r\n"
    			+ "    \"versioncontrol\": {\r\n"
    			+ "      \"sourceControlType\": \"" + ADOProject.getScm() + "\"\r\n"
    			+ "    },\r\n"
    			+ "    \"processTemplate\": {\r\n"
    			+ "      \"templateTypeId\": \"" + ADOProject.getProcessTemplateId() + "\"\r\n"
    			+ "    }\r\n"
    			+ "  }\r\n"
    			+ "}";
    	
		LOGGER.info("Payload: " + payloadBLIs);
		String responseBody;
		String url_in = ADOConfig.getUrl();
		String orgId = ADOConfig.getOrganizationId();
    	String url = url_in + "/" + orgId + "/_apis/projects?api-version=6.1-preview.4";
		

		responseBody = sendRequest(ADOConfig, url, payloadBLIs);

		return responseBody;

		
	}	


	
	
	@MediaType(value = ANY, strict = false)
	@Alias("Get-all-teams")
	public String getTeams(@Config AzureDevOpsConfiguration ADOConfig) throws IOException, InterruptedException {
    	LOGGER.info("Azure DevOps getTeams entered");  		

		String responseBody;
		String url_in = ADOConfig.getUrl();
		String orgId = ADOConfig.getOrganizationId();
    	String url = url_in + "/" + orgId + "/_apis/teams";
		
		responseBody = getRequest(ADOConfig, url);

		return responseBody;
		
		
	}	

	@MediaType(value = ANY, strict = false)
	@Alias("Get-all-plans")
	public String getPlans(@Config AzureDevOpsConfiguration ADOConfig,  @ParameterGroup(name="Project") ADOParameterProject ADOProject) throws IOException, InterruptedException {
    	LOGGER.info("Azure DevOps getPlans entered");  		

		String responseBody;
		String url_in = ADOConfig.getUrl();
		String orgId = ADOConfig.getOrganizationId();
    	String url = url_in + "/" + orgId + "/" + ADOProject.getProject() + "/_apis/work/plans";
		
		responseBody = getRequest(ADOConfig, url);

		return responseBody;
		
		
	}	
	@MediaType(value = ANY, strict = false)
	@Alias("Create-work-item")
	public String createWorkItem(@Config AzureDevOpsConfiguration ADOConfig, @ ParameterGroup(name="Project") ADOParameterProject ADOProject, @ParameterGroup(name="Work Item Type") ADOParameterWorkItemTypes ADOWorkItemTypes, @ParameterGroup(name = "Payload") AzureDevOpsPayload ADOPayload) throws IOException, InterruptedException {
    	LOGGER.info("Azure DevOps createWorkItem entered");  		

		LOGGER.info("Payload: " + ADOPayload.getPayload());
		String responseBody;
		String url_in = ADOConfig.getUrl();
		String orgId = ADOConfig.getOrganizationId();
    	String url = url_in + "/" + orgId + "/" + ADOProject.getProject() + "/_apis/wit/workitems/$" + ADOWorkItemTypes.getWorkitemtype() + "?api-version=6.1-preview.3";
		

		responseBody = sendPatchRequest(ADOConfig, url, ADOPayload.getPayload(), "POST");

		return responseBody;

		
	}	

	@MediaType(value = ANY, strict = false)
	@Alias("Update-work-item")
	public String updateWorkItem(@Config AzureDevOpsConfiguration ADOConfig, @ ParameterGroup(name="Project") ADOParameterProject ADOProject, @ParameterGroup(name="Work Item Id") ADOParameterPatchWorkItemId ADOWorkItemId, @ParameterGroup(name = "Payload") AzureDevOpsPayload ADOPayload) throws IOException, InterruptedException {
    	LOGGER.info("Azure DevOps updateWorkItem entered");  		

		LOGGER.info("Payload: " + ADOPayload.getPayload());
		String responseBody;
		String url_in = ADOConfig.getUrl();
		String orgId = ADOConfig.getOrganizationId();
    	String url = url_in + "/" + orgId + "/" + ADOProject.getProject() + "/_apis/wit/workitems/" + ADOWorkItemId.getWorkitemid() + "?api-version=6.1-preview.3";
		

		responseBody = sendPatchRequest(ADOConfig, url, ADOPayload.getPayload(), "PATCH");

		return responseBody;

		
	}	
	
	@MediaType(value = ANY, strict = false)
	@Alias("Create-team")
	public String createTeam(@Config AzureDevOpsConfiguration ADOConfig, @ ParameterGroup(name="Project") ADOParameterProject ADOProject, @ParameterGroup(name = "Payload") AzureDevOpsPayload ADOPayload) throws IOException, InterruptedException {
    	LOGGER.info("Azure DevOps createTeam entered");  		

		LOGGER.info("Payload: " + ADOPayload.getPayload());
		String responseBody;
		String url_in = ADOConfig.getUrl();
		String orgId = ADOConfig.getOrganizationId();
    	String url = url_in + "/" + orgId + "/_apis/projects/" + ADOProject.getProject() + "/teams?api-version=6.1-preview.3";
		

		responseBody = sendRequest(ADOConfig, url, ADOPayload.getPayload());

		return responseBody;

		
	}	
	
	@MediaType(value = ANY, strict = false)
	@Alias("Get-work-item-types")
	public String getWorkItemTypes(@Config AzureDevOpsConfiguration ADOConfig, @ ParameterGroup(name="Project") ADOParameterProject ADOProject, @ParameterGroup(name = "Payload") AzureDevOpsPayload ADOPayload) throws IOException, InterruptedException {
    	LOGGER.info("Azure DevOps getWorkItemTypes entered");  		

		LOGGER.info("Payload: " + ADOPayload.getPayload());
		String responseBody;
		String url_in = ADOConfig.getUrl();
		String orgId = ADOConfig.getOrganizationId();
    	String url = url_in + "/" + orgId + "/_apis/projects/" + ADOProject.getProject() + "/_apis/wit/workitemtypes?api-version=6.1-preview.2";
		

		responseBody = sendRequest(ADOConfig, url, ADOPayload.getPayload());

		return responseBody;

		
	}	
	
	@MediaType(value = ANY, strict = false)
	@Alias("Get-all-backlog-items")
	public String getBacklogItems(@Config AzureDevOpsConfiguration ADOConfig) throws IOException, InterruptedException {
    	LOGGER.info("Azure DevOps getBacklogItems entered");  		

    	String payloadBLIs = "{\"query\": \"Select [System.Id], [System.Title], [System.State] From WorkItems\"}";
    	
		LOGGER.info("Payload: " + payloadBLIs);
		String responseBody;
		String url_in = ADOConfig.getUrl();
		String orgId = ADOConfig.getOrganizationId();
    	String url = url_in + "/" + orgId + "/_apis/wit/wiql?api-version=6.0";
		

		responseBody = sendRequest(ADOConfig, url, payloadBLIs);

		return responseBody;

		
	}	

	@MediaType(value = ANY, strict = false)
	@Alias("Get-all-issues")
	public String getIssues(@Config AzureDevOpsConfiguration ADOConfig) throws IOException, InterruptedException {
    	LOGGER.info("Azure DevOps getIssues entered");  		

    	String payloadBLIs = "{\"query\": \"Select [System.Title], [System.State] From WorkItems WHERE [System.WorkItemType] = 'Issue' \"}";
    	
		LOGGER.info("Payload: " + payloadBLIs);
		String responseBody;
		String url_in = ADOConfig.getUrl();
		String orgId = ADOConfig.getOrganizationId();
    	String url = url_in + "/" + orgId + "/_apis/wit/wiql?api-version=6.0";
		

		responseBody = sendRequest(ADOConfig, url, payloadBLIs);

		return responseBody;

		
	}	

	@MediaType(value = ANY, strict = false)
	@Alias("Get-all-epics")
	public String getEpics(@Config AzureDevOpsConfiguration ADOConfig) throws IOException, InterruptedException {
    	LOGGER.info("Azure DevOps getEpics entered");  		

    	String payloadBLIs = "{\"query\": \"Select [System.Title], [System.State] From WorkItems WHERE [System.WorkItemType] = 'Epic' \"}";
    	
		LOGGER.info("Payload: " + payloadBLIs);
		String responseBody;
		String url_in = ADOConfig.getUrl();
		String orgId = ADOConfig.getOrganizationId();
    	String url = url_in + "/" + orgId + "/_apis/wit/wiql?api-version=6.0";
		

		responseBody = sendRequest(ADOConfig, url, payloadBLIs);

		return responseBody;

		
	}	

	@MediaType(value = ANY, strict = false)
	@Alias("Get-all-tasks")
	public String getTasks(@Config AzureDevOpsConfiguration ADOConfig) throws IOException, InterruptedException {
    	LOGGER.info("Azure DevOps getTasks entered");  		

    	String payloadBLIs = "{\"query\": \"Select [System.Title], [System.State] From WorkItems WHERE [System.WorkItemType] = 'Epic' \"}";
    	
		LOGGER.info("Payload: " + payloadBLIs);
		String responseBody;
		String url_in = ADOConfig.getUrl();
		String orgId = ADOConfig.getOrganizationId();
    	String url = url_in + "/" + orgId + "/_apis/wit/wiql?api-version=6.0";
		

		responseBody = sendRequest(ADOConfig, url, payloadBLIs);

		return responseBody;

		
	}	


	@MediaType(value = ANY, strict = false)
	@Alias("Get-all-features")
	public String getFeatures(@Config AzureDevOpsConfiguration ADOConfig) throws IOException, InterruptedException {
    	LOGGER.info("Azure DevOps getFeatures entered");  		

    	String payloadBLIs = "{\"query\": \"Select [System.Title], [System.State] From WorkItems WHERE [System.WorkItemType] = 'Feature' \"}";
    	
		LOGGER.info("Payload: " + payloadBLIs);
		String responseBody;
		String url_in = ADOConfig.getUrl();
		String orgId = ADOConfig.getOrganizationId();
    	String url = url_in + "/" + orgId + "/_apis/wit/wiql?api-version=6.0";
		

		responseBody = sendRequest(ADOConfig, url, payloadBLIs);

		return responseBody;

		
	}	
	
	@MediaType(value = ANY, strict = false)
	@Alias("Get-all-bug")
	public String getBugs(@Config AzureDevOpsConfiguration ADOConfig) throws IOException, InterruptedException {
    	LOGGER.info("Azure DevOps getFeatures entered");  		

    	String payloadBLIs = "{\"query\": \"Select [System.Title], [System.State] From WorkItems WHERE [System.WorkItemType] = 'Bug' \"}";
    	
		LOGGER.info("Payload: " + payloadBLIs);
		String responseBody;
		String url_in = ADOConfig.getUrl();
		String orgId = ADOConfig.getOrganizationId();
    	String url = url_in + "/" + orgId + "/_apis/wit/wiql?api-version=6.0";
		

		responseBody = sendRequest(ADOConfig, url, payloadBLIs);

		return responseBody;

		
	}	

	@MediaType(value = ANY, strict = false)
	@Alias("Get-all-user-stories")
	public String getUserStories(@Config AzureDevOpsConfiguration ADOConfig) throws IOException, InterruptedException {
    	LOGGER.info("Azure DevOps getUserStories entered");  		

    	String payloadBLIs = "{\"query\": \"Select [System.Title], [System.State] From WorkItems WHERE [System.WorkItemType] = 'User Story' \"}";
    	
		LOGGER.info("Payload: " + payloadBLIs);
		String responseBody;
		String url_in = ADOConfig.getUrl();
		String orgId = ADOConfig.getOrganizationId();
    	String url = url_in + "/" + orgId + "/_apis/wit/wiql?api-version=6.0";
		

		responseBody = sendRequest(ADOConfig, url, payloadBLIs);

		return responseBody;

		
	}	

	@MediaType(value = ANY, strict = false)
	@Alias("Get-all-test-cases")
	public String getTestCases(@Config AzureDevOpsConfiguration ADOConfig) throws IOException, InterruptedException {
    	LOGGER.info("Azure DevOps getTestCases entered");  		

    	String payloadBLIs = "{\"query\": \"Select [System.Title], [System.State] From WorkItems WHERE [System.WorkItemType] = 'Test Case' \"}";
    	
		LOGGER.info("Payload: " + payloadBLIs);
		String responseBody;
		String url_in = ADOConfig.getUrl();
		String orgId = ADOConfig.getOrganizationId();
    	String url = url_in + "/" + orgId + "/_apis/wit/wiql?api-version=6.0";
		

		responseBody = sendRequest(ADOConfig, url, payloadBLIs);

		return responseBody;

		
	}	

	
	@MediaType(value = ANY, strict = false)
	@Alias("Update-team-name")
	public String updateTeam(@Config AzureDevOpsConfiguration ADOConfig, @ ParameterGroup(name="Team") ADOParameterTeam ADOProject, @ParameterGroup(name = "Payload") AzureDevOpsPayload ADOPayload) throws IOException, InterruptedException {
    	LOGGER.info("Azure DevOps createTeam entered");  		

		LOGGER.info("Payload: " + ADOPayload.getPayload());
		String responseBody;
		String url_in = ADOConfig.getUrl();
		String orgId = ADOConfig.getOrganizationId();
    	String url = url_in + "/" + orgId + "/_apis/projects/" + ADOProject.getProject() + "/teams?api-version=6.1-preview.3";
		

		responseBody = sendRequest(ADOConfig, url, ADOPayload.getPayload());

		return responseBody;

		
	}	
	
	
	@MediaType(value = ANY, strict = false)
	@Alias("Get-work-item-by-id")
	public String getWorkItemById(@Config AzureDevOpsConfiguration ADOConfig, @ParameterGroup(name="Project") ADOParameterProject ADOProject, @ParameterGroup(name="Work Item Id") ADOParamterWorkItemId ADOId ) throws IOException, InterruptedException {
    	LOGGER.info("Azure DevOps getWorkItemById entered");  		

		String responseBody;
		String url_in = ADOConfig.getUrl();
		String orgId = ADOConfig.getOrganizationId();
    	String url = url_in + "/" + orgId + "/" + ADOProject.getProject() +  "/_apis/wit/workitems/" + ADOId.getIds() ;
		
		responseBody = getRequest(ADOConfig, url);

		return responseBody;
		
		
	}	


	
	@MediaType(value = ANY, strict = false)
	@Alias("Get-multiple-work-items-by-ids")
	public String getWorkItemsByIds(@Config AzureDevOpsConfiguration ADOConfig, @ParameterGroup(name="Project") ADOParameterProject ADOProject, @ParameterGroup(name="Work Item Ids") ADOParamterWorkItemId ADOId ) throws IOException, InterruptedException {
    	LOGGER.info("Azure DevOps getWorkItemById entered");  		

		String responseBody;
		String url_in = ADOConfig.getUrl();
		String orgId = ADOConfig.getOrganizationId();
    	String url = url_in + "/" + orgId + "/" + ADOProject.getProject() +  "/_apis/wit/workitems?ids=" + ADOId.getIds() ;
		
		responseBody = getRequest(ADOConfig, url);

		return responseBody;
		
		
	}	


	private String getRequest(@Config AzureDevOpsConfiguration ADOConfig, String ADOURL) throws IOException, InterruptedException {

		StringBuilder responseBodyReturn;
		int responseCode = 0;
		
		String pat = ADOConfig.getPAT();

		
    	URL url = new URL(ADOURL);
    	LOGGER.info("Azure DevOps Request-url: " + url);  		
    	
    	URLConnection conn = url.openConnection();

    	if(conn instanceof HttpsURLConnection){
			LOGGER.info("Processing HTTPS request");
			HttpsURLConnection https = (HttpsURLConnection) conn;
	    	https.setRequestMethod("GET");
	    	https.addRequestProperty("Accept", "*/*");
	    	
	    	String userCredentials = ":" + pat;
	    	String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userCredentials.getBytes()));

	    	https.setRequestProperty ("Authorization", basicAuth);
	    	
        	responseCode = https.getResponseCode();
            InputStream inputStream;
            if (200 <= responseCode && responseCode <= 299) {
                inputStream = https.getInputStream();
            } else {
                inputStream = https.getErrorStream();
            }

            BufferedReader in = new BufferedReader(
                new InputStreamReader(
                    inputStream));

            StringBuilder responseBody = new StringBuilder();
            String currentLine;

            while ((currentLine = in.readLine()) != null) 
            	responseBody.append(currentLine);

            in.close();
            
            responseBodyReturn = responseBody;

    	} 
    	else {
    		LOGGER.info("Processing HTTP request");
        	HttpURLConnection http = (HttpURLConnection) conn;
        	http.setRequestMethod("GET");
        	http.addRequestProperty("Accept", "*/*");
        	
        	String userCredentials = pat;
        	String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userCredentials.getBytes()));

        	http.setRequestProperty ("Authorization", basicAuth);
        	
    	
        	responseCode = http.getResponseCode();
            InputStream inputStream;
            if (200 <= responseCode && responseCode <= 299) {
                inputStream = http.getInputStream();
            } else {
                inputStream = http.getErrorStream();
            }

            BufferedReader in = new BufferedReader(
                new InputStreamReader(
                    inputStream));

            StringBuilder responseBody = new StringBuilder();
            String currentLine;

            while ((currentLine = in.readLine()) != null) 
            	responseBody.append(currentLine);

            in.close();

            responseBodyReturn = responseBody;
    	}

    	

    	
    	LOGGER.info("Azure DevOps ReturnCode: " + String.valueOf(responseCode));
    	

		return responseBodyReturn.toString();
		
		
	}	

	private String sendRequest(@Config AzureDevOpsConfiguration ADOConfig, String ADOURL, String ADOPayload) throws IOException, InterruptedException {

		StringBuilder responseBodyReturn;
		int responseCode = 0;
		
		String pat = ADOConfig.getPAT();
    	String payload = ADOPayload;    	
		
    	URL url = new URL(ADOURL);
    	LOGGER.info("Azure DevOps Request-url: " + url);  		
    	
    	URLConnection conn = url.openConnection();

    	try {
			
	    	if(conn instanceof HttpsURLConnection){
				LOGGER.info("Processing HTTPS request");
		    	HttpsURLConnection https = (HttpsURLConnection) conn;
	        	String userCredentials = ":" + pat;
	        	String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userCredentials.getBytes()));

	        	https.setDoOutput(true);
		    	https.setDoInput(true);
		    	https.setRequestMethod("POST");
		    	https.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		    	https.setRequestProperty("Accept", "application/json");    	

	        	https.setRequestProperty ("Authorization", basicAuth);
	        	try(OutputStream os = conn.getOutputStream()){
					byte[] input = payload.getBytes("utf-8");
					os.write(input,0,input.length);
				}
				
		    	LOGGER.info("Response Code sendGETRequest: " + https.getResponseCode());
	    	}
	    	else {
				LOGGER.info("Processing HTTP request");
		    	HttpURLConnection http = (HttpURLConnection) conn;
	        	String userCredentials = ":" + pat;
	        	String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userCredentials.getBytes()));

		    	http.setDoOutput(true);
		    	http.setDoInput(true);
		    	http.setRequestMethod("POST");
		    	http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		    	http.setRequestProperty("Accept", "application/json");    	

	        	http.setRequestProperty ("Authorization", basicAuth);
		    	try(OutputStream os = conn.getOutputStream()){
					byte[] input = payload.getBytes("utf-8");
					os.write(input,0,input.length);
				}
				
		    	LOGGER.info("Response Code sendRequest: " + http.getResponseCode());

	    	}
	    	
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = br.readLine()) != null) {
	            sb.append(line+"\n");
	        }
	        br.close();

	        responseBodyReturn = sb;

	    	
		} catch(IOException e) {
	    	LOGGER.error("Error occurred while POSTing resource: " + url);
	    	e.printStackTrace();
	    	responseBodyReturn = null;
		}
		
		
	
		return responseBodyReturn.toString();
		
		
	}	


	
	private String sendPatchRequest(@Config AzureDevOpsConfiguration ADOConfig, String ADOURL, String ADOPayload, String httpMethod) throws IOException, InterruptedException {

		StringBuilder responseBodyReturn;
		int responseCode = 0;
		
		String pat = ADOConfig.getPAT();
    	String payload = ADOPayload;    	
		
    	URL url = new URL(ADOURL);
    	LOGGER.info("Azure DevOps Request-url: " + url);  		
    	
    	URLConnection conn = url.openConnection();

    	try {
			
	    	if(conn instanceof HttpsURLConnection){
				LOGGER.info("Processing HTTPS request");
		    	HttpsURLConnection https = (HttpsURLConnection) conn;
	        	String userCredentials = ":" + pat;
	        	String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userCredentials.getBytes()));

	        	https.setDoOutput(true);
		    	https.setDoInput(true);
		    	if (httpMethod == "PATCH") {
			    	https.setRequestProperty("X-HTTP-Method-Override", "PATCH");
			    	httpMethod = "POST";
		    	}
		    	https.setRequestMethod(httpMethod);
		    	https.setRequestProperty("Content-Type", "application/json-patch+json; charset=UTF-8");
		    	https.setRequestProperty("Accept", "application/json");    	

	        	https.setRequestProperty ("Authorization", basicAuth);
	        	try(OutputStream os = conn.getOutputStream()){
					byte[] input = payload.getBytes("utf-8");
					os.write(input,0,input.length);
				}
				
		    	LOGGER.info("Response Code sendGETRequest: " + https.getResponseCode());
	    	}
	    	else {
				LOGGER.info("Processing HTTP request");
		    	HttpURLConnection http = (HttpURLConnection) conn;
	        	String userCredentials = ":" + pat;
	        	String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userCredentials.getBytes()));

		    	http.setDoOutput(true);
		    	http.setDoInput(true);
		    	if (httpMethod == "PATCH") {
			    	http.setRequestProperty("X-HTTP-Method-Override", "PATCH");
			    	httpMethod = "POST";
		    	}
		    	http.setRequestMethod(httpMethod);
		    	http.setRequestProperty("Content-Type", "application/json-patch+json; charset=UTF-8");
		    	http.setRequestProperty("Accept", "application/json");    	

	        	http.setRequestProperty ("Authorization", basicAuth);
		    	try(OutputStream os = conn.getOutputStream()){
					byte[] input = payload.getBytes("utf-8");
					os.write(input,0,input.length);
				}
				
		    	LOGGER.info("Response Code sendRequest: " + http.getResponseCode());

	    	}
	    	
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = br.readLine()) != null) {
	            sb.append(line+"\n");
	        }
	        br.close();

	        responseBodyReturn = sb;

	    	
		} catch(IOException e) {
	    	LOGGER.error("Error occurred while POSTing resource: " + url);
	    	e.printStackTrace();
	    	responseBodyReturn = null;
		}
		
		
	
		return responseBodyReturn.toString();
		
		
	}	

	
	
	private String sendUpdateRequest(@Config AzureDevOpsConfiguration ADOConfig, String ADOURL, String ADOPayload) throws IOException, InterruptedException {

		StringBuilder responseBodyReturn;
		int responseCode = 0;
		
		String pat = ADOConfig.getPAT();
    	String payload = ADOPayload;    	
		
    	URL url = new URL(ADOURL);
    	LOGGER.info("Azure DevOps Request-url: " + url);  		
    	
    	URLConnection conn = url.openConnection();

    	try {
			
	    	if(conn instanceof HttpsURLConnection){
				LOGGER.info("Processing HTTPS request");
		    	HttpsURLConnection https = (HttpsURLConnection) conn;
	        	String userCredentials = ":" + pat;
	        	String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userCredentials.getBytes()));

	        	https.setDoOutput(true);
		    	https.setDoInput(true);
		    	https.setRequestMethod("PATCH");
		    	https.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		    	https.setRequestProperty("Accept", "application/json");    	

	        	https.setRequestProperty ("Authorization", basicAuth);
	        	try(OutputStream os = conn.getOutputStream()){
					byte[] input = payload.getBytes("utf-8");
					os.write(input,0,input.length);
				}
				
		    	LOGGER.info("Response Code sendGETRequest: " + https.getResponseCode());
	    	}
	    	else {
				LOGGER.info("Processing HTTP request");
		    	HttpURLConnection http = (HttpURLConnection) conn;
	        	String userCredentials = ":" + pat;
	        	String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userCredentials.getBytes()));

		    	http.setDoOutput(true);
		    	http.setDoInput(true);
		    	http.setRequestMethod("PATCH");
		    	http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		    	http.setRequestProperty("Accept", "application/json");    	

	        	http.setRequestProperty ("Authorization", basicAuth);
		    	try(OutputStream os = conn.getOutputStream()){
					byte[] input = payload.getBytes("utf-8");
					os.write(input,0,input.length);
				}
				
		    	LOGGER.info("Response Code sendRequest: " + http.getResponseCode());

	    	}
	    	
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = br.readLine()) != null) {
	            sb.append(line+"\n");
	        }
	        br.close();

	        responseBodyReturn = sb;

	    	
		} catch(IOException e) {
	    	LOGGER.error("Error occurred while POSTing resource: " + url);
	    	e.printStackTrace();
	    	responseBodyReturn = null;
		}
		
		
	
		return responseBodyReturn.toString();
		
		
	}	

	
}
