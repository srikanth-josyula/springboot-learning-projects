### SpringBoot Messaging Sub Module Project

#### How to Create a Maven Sub Module Project

**Step 1**
* mvn archetype:generate with groupId and artifactId (this is Parent POM)

**Step-2**
* Update pom.xml to declare it as parent project 
* Open pom.xml of above created parent-project and change the packaging to ‘pom’. <packaging>pom</packaging>

**Step-3**
* Create sub-modules by moving into parent pom and run create project command with Ids
* F:\Workspace\Spring-Projects\springboot-messaging>mvn archetype:generate -DgroupId=com.sample -DartifactId=jms-messaging -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false

**Step-4**
* Now if you open the parent-project pom.xml, you will find all three modules being added in there.

	`<modules>
		<module>jms-messaging</module>
		<module>apache-kafka-messaging</module>
		<module>activeMQ-messaging</module>
		<module>rabbitMQ-messaging</module>
	</modules>`

**Step-5**
* Also, in each sub-module’s pom.xml, a parent section being added.

	`<parent>
    <groupId>com.sample</groupId>
    <artifactId>springboot-messaging</artifactId>
    <version>0.0.1-SNAPSHOT</version>
   </parent>`