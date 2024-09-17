# Prerequisites
This application is written using Java 21 and Maven. To run the executable, you will first need to install the following prerequisites:
1. JDK 21
2. Maven

# Building the Application
Run the following from `<REPO_ROOT>/directories`:

`mvn clean install`

# Executing the Application
There are two options for running the executable:
1. IDE (I used IntelliJ)
   - Open the pom.xml using IntelliJ (or your preferred IDE) and click the Run button.

2. Command line using Maven
   - Enter the following command from the directory in which you cloned the repo (make sure you're in the `directories` directory):

     `mvn exec:java -Dexec.mainClass="com.endpoint.directories.Main"`
