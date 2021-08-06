# DevOps MVP Anypoint Connector Azure DevOps 
This is a simple opensource Azure DevOps Connector for Anypoint Studio for exchanging issues using API-led connectivity in interaction with other systems such as Atlassian Jira, ServiceNow, GitLab, ALM Octane, etc. 
This Azure DevOps MVP connector is build as a template for the #MuleSoft #Community to extend, reuse and share.
Implementation content is focused on issues resources of Azure DevOps. 

Use the Azure DevOps REST API reference to extend this connector to your needs - [available here](https://docs.microsoft.com/en-us/rest/api/azure/devops/?view=azure-devops-rest-6.1)

## Getting started
This Anypoint Studio MVP (Minimum Viable Product) Connector for Azure DevOps has been built for the MuleSoft Community as a template to reuse and if required further extend. 
The connector supports 21 operations in this MVP release with the focus on issues, which are:
- Create new project
- Create team
- Create work item
- Get all backlog items
- Get all bugs
- Get all epics
- Get all features
- Get all issues
- Get all plans
- Get all projects
- Get all tasks
- Get all teams
- Get all user stories
- Get multiple work items by Ids
- Get work item by Id
- Get work items by types
- List all process templates
- Update team name
- Update work item

## Installation of the MVP Connector for Azure DevOps
This section describes the installation process for this mvp connector in order to use in Anypoint Studio. 

### Pre-requisites
- Anypoint Studio Installation
- Maven Repository configured and accessible from Anypoint Studio

### Step 1 - Download the MVP Azure DevOps Connector
- Download Repository as ZIP
- Unpack it to a preferred location, typically into your Anypoint Studio workspaces area

### Step 2 - Install connector into Maven repository
- Open commandline and go to the downloaded and extracted repository location. 
- Perform "mvn install" 
- Connector should be installed successfully

![Image of Azure DevOps MuleSoft Connector](https://github.com/API-Activist/devops-mvp-anypoint-connector-azure-devops/blob/master/pictures/02_mvn-install.PNG)

### Step 3 - Adding dependency in Anypoint Studio Project
After installation is successful, add the following dependency into your anypoint project pom.xml:

		<dependency>
			<classifier>mule-plugin</classifier>
			<groupId>embrace.devops.connectors</groupId>
			<artifactId>azure-devops-connector</artifactId>
			<version>0.0.24</version>
		</dependency>

The current version of this connector is 0.0.24. Once added, save the pom.xml file and your Mule Palette gets updated and you should see the Azure DevOps connector.

![Image of Azure DevOps MuleSoft Connector](https://github.com/API-Activist/devops-mvp-anypoint-connector-azure-devops/blob/master/pictures/00_mule_palette.PNG)

### Step 4 - Create Azure DevOps Configuration
Before you get started and consume the provided operations, make sure to configure the GitLab Connection within Anypoint Studio. 
- URL
- Organization Id
- PAT (Personal Access Token)

[Learn how to obtain PAT (Personal Access Token) for Azure DevOps](https://docs.microsoft.com/en-us/azure/devops/organizations/accounts/use-personal-access-tokens-to-authenticate?view=azure-devops&tabs=preview-page)

![Image of Azure DevOps MuleSoft Connector](https://github.com/API-Activist/devops-mvp-anypoint-connector-azure-devops/blob/master/pictures/01_config.PNG)

Now you are all set to use the Azure DevOps Operations.

## Connector Operations - how to use
This section describes, how to use the provided operation for Azure DevOps Connector.

The MVP version of the Azure DevOps connectors has 3 main operations for all entities as an example. 
- **Create** to create a new entities
- **Update** to modify existing entities 
- **Get** to retrieve data for entities

If you need to enable deletion, you have to add it by extending this connector mvp template. 

**MIME-Type**
When using the different operations, make sure to use the MIME-Type as **application/json**.

### Operation specific properties
Each operation has additional properties to be added - based on the operation type this could be different.

Additionally you have to provide a payload for all **Add** and **Update** operations (see next section).

### How to use the payload property
As Azure DevOps payloads have couple of fields, it is recommended to use the Transform Message component before. 

![Image of Azure DevOps MuleSoft Connector](https://github.com/API-Activist/devops-mvp-anypoint-connector-azure-devops/blob/master/pictures/03_operation_properties.PNG)

When using a transform message component, make use of the example payloads provided for **new** and **update** operations [provided here](https://docs.microsoft.com/en-us/rest/api/azure/devops/core/teams/create?view=azure-devops-rest-6.1).


### Reponse of operations
By default it is a json sent back as string. Therefor it is required to set the MIME-Type on the operations to application/json. 

	{
	
	  "id": "8e8aa4ff-848a-474a-9033-93190137c8e4",
	  "name": "My New Team",
	  "url": "https://dev.azure.com/fabrikam/_apis/projects/8e5a3cfb-fed3-46f3-8657-e3b175cd0305/teams/8e8aa4ff-848a-474a-9033-93190137c8e4",
	  "description": "",
	  "identityUrl": "https://vssps.dev.azure.com/fabrikam/_apis/Identities/8e8aa4ff-848a-474a-9033-93190137c8e4"
	}

## Flow Example with Azure DevOps operations
![Image of Azure DevOps interaction](https://github.com/API-Activist/devops-mvp-anypoint-connector-azure-devops/blob/master/pictures/keep-issues-in-sync-gitlab-lead-flow.PNG)

	
## Video Tutorial
Link to the video tutorial: -to be provided soon-


## Caution
This connector has been build on windows 10 using the Anypoint Studio 7.10 IDE. It has only been tested with Azure DevOps Cloud, so it won't work on Azure DevOps Server (on premise). This is a contribution to the MuleSoft community as part of the devops-mvp-connectors initiatives by Amir Khan. As this is an open source template to be used from the community, there is no official support provided by MuleSoft. Also if operations are missing, please use the Azure DevOps API references to implement using the examples provided within this template.
	
Azure DevOps API Reference: [available here](https://docs.microsoft.com/en-us/rest/api/azure/devops/?view=azure-devops-rest-6.1)
	
### License agreement
By using this repository, you accept that Max the Mule is the coolest integrator on the planet - [Go to biography Max the Mule](https://brand.salesforce.com/content/characters-overview__3?tab=BogXMx2m)
