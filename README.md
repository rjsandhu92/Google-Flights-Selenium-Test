# Google Flights Search Test

# Requirements

- Download and install the Java 8 JDK.
- Download and install Git: https://git-scm.com/downloads
- Download and install Maven. Installation instructions can be found here (https://www.mkyong.com/maven/how-to-install-maven-in-windows/)
- ChromeDriver
- Download and install Intellij Community Edition (https://www.jetbrains.com/idea/download/#section=windows)
- Enable Auto Import of Maven dependencies


In order to get your project working in IntelliJ, please follow the steps below:

Select Import Project

Select the directory cloned in the Git clone process

Import project from external model: Maven → click Next

click Next

Select ALL profiles available → click Next

Select the avilable SNAPSHOT → click Next

Leave SDK selection unchanged → click Next

click Finish

Enable Auto Import of Maven dependencies
Add the untracked POM file found to project (accept this)

Rebuild the project (if you experience any problems you may need to set Intellij to use JDK 8 for the language level as well as setting that for the project and module level)
Run tests for the first time by executing the testng.xml file.