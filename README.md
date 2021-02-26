# # DatNT Workspace!

This repository store my works

Within it I have **StandardMicroService**. If you want to have a standard structure of spring-boot microservices project, with Mybatis integration, and log4j ready, then you can clone this project and make some modification as per your requirement.

Project **MicroSrvAdapter** is a template for implementing an Adapter for Microservices. It is implemented with Log4j, properties files separated to different environments, dev, test, staging, production. It has JUnit within it to be ready for TDD. Endpoints for external API are stored within another property file to centralize a single source for all URLs. Messages is stored within another property file to centralize messages used for this project.  

## Below is note on execution of these 2 projects above:
- Using spring tool suite IDE  
- Execute installation from pom.xml  
- If having problem with install dependency from maven, remeber to check $HOME/.m2/settings.xml. Refer to a sample which was worked at Apr 2019.
- To start: Right click on pom.xml , in goal, enter: spring-boot:run  


Project **XamarinGoogleAuthIOS** is a guideline, and sample code for Xamarin to have OAuth with Google on iOS. I had not integrate with Android but the idea is generally the same.

## 