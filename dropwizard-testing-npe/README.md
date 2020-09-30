# dropwizard NPE problem

Reported as https://github.com/dropwizard/dropwizard/issues/3221 . 

This setup is based on https://www.dropwizard.io/en/latest/manual/testing.html 

However, running `mvn test` results in:
```
testHi(nl.feliksik.dropwizardproblem.serverapi.resources.RunsResourceTest)  Time elapsed: 0.906 sec  <<< ERROR!
java.lang.NullPointerException
	at java.base/java.util.Objects.requireNonNull(Objects.java:221)
	at io.dropwizard.testing.common.Resource.getJerseyTest(Resource.java:193)
	at io.dropwizard.testing.common.Resource.target(Resource.java:173)
	at io.dropwizard.testing.junit5.ResourceExtension.target(ResourceExtension.java:65)
	at nl.feliksik.dropwizardproblem.serverapi.resources.RunsResourceTest.testHi(RunsResourceTest.java:29)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:566)
	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:59)
	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:56)
	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
	at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
	at org.junit.runners.BlockJUnit4ClassRunner$1.evaluate(BlockJUnit4ClassRunner.java:100)
	at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:366)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:103)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:63)
	at org.junit.runners.ParentRunner$4.run(ParentRunner.java:331)
	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)
	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329)
	at org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)
	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293)
	at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
	at org.junit.runners.ParentRunner.run(ParentRunner.java:413)
	at org.apache.maven.surefire.junit4.JUnit4Provider.execute(JUnit4Provider.java:252)
	at org.apache.maven.surefire.junit4.JUnit4Provider.executeTestSet(JUnit4Provider.java:141)
	at org.apache.maven.surefire.junit4.JUnit4Provider.invoke(JUnit4Provider.java:112)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:566)
	at org.apache.maven.surefire.util.ReflectionUtils.invokeMethodWithArray(ReflectionUtils.java:189)
	at org.apache.maven.surefire.booter.ProviderFactory$ProviderProxy.invoke(ProviderFactory.java:165)
	at org.apache.maven.surefire.booter.ProviderFactory.invokeProvider(ProviderFactory.java:85)
	at org.apache.maven.surefire.booter.ForkedBooter.runSuitesInProcess(ForkedBooter.java:115)
	at org.apache.maven.surefire.booter.ForkedBooter.main(ForkedBooter.java:75)



```


```
$ mvn dependency:tree
[MVNVM] Using maven: 3.5.2
[INFO] Scanning for projects...
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] Building minimalexample 1.0-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO]
[INFO] --- maven-dependency-plugin:2.8:tree (default-cli) @ minimalexample ---
[INFO] nl.feliksik.dropwizardproblem:minimalexample:jar:1.0-SNAPSHOT
[INFO] +- io.dropwizard:dropwizard-forms:pom:2.0.13:compile
[INFO] |  +- io.dropwizard:dropwizard-jersey:jar:2.0.13:compile
[INFO] |  |  +- org.glassfish.jersey.ext:jersey-metainf-services:jar:2.31:runtime
[INFO] |  |  +- org.glassfish.jersey.inject:jersey-hk2:jar:2.31:runtime
[INFO] |  |  |  \- org.glassfish.hk2:hk2-locator:jar:2.6.1:runtime
[INFO] |  |  +- org.javassist:javassist:jar:3.27.0-GA:compile
[INFO] |  |  +- io.dropwizard.metrics:metrics-jersey2:jar:4.1.12.1:compile
[INFO] |  |  +- com.fasterxml.jackson.core:jackson-core:jar:2.10.5:compile
[INFO] |  |  +- joda-time:joda-time:jar:2.10.6:compile
[INFO] |  |  +- org.glassfish.hk2:hk2-api:jar:2.6.1:compile
[INFO] |  |  |  +- org.glassfish.hk2:hk2-utils:jar:2.6.1:compile
[INFO] |  |  |  \- org.glassfish.hk2.external:aopalliance-repackaged:jar:2.6.1:compile
[INFO] |  |  +- org.glassfish.jersey.containers:jersey-container-servlet:jar:2.31:runtime
[INFO] |  |  \- org.apache.commons:commons-lang3:jar:3.11:compile
[INFO] |  +- io.dropwizard:dropwizard-logging:jar:2.0.13:compile
[INFO] |  |  +- io.dropwizard.metrics:metrics-logback:jar:4.1.12.1:compile
[INFO] |  |  +- org.slf4j:jul-to-slf4j:jar:1.7.30:compile
[INFO] |  |  +- ch.qos.logback:logback-core:jar:1.2.3:compile
[INFO] |  |  +- io.dropwizard.logback:logback-throttling-appender:jar:1.1.0:compile
[INFO] |  |  +- org.slf4j:log4j-over-slf4j:jar:1.7.30:runtime
[INFO] |  |  \- org.slf4j:jcl-over-slf4j:jar:1.7.30:runtime
[INFO] |  \- org.glassfish.jersey.media:jersey-media-multipart:jar:2.31:compile
[INFO] |     \- org.jvnet.mimepull:mimepull:jar:1.9.13:compile
[INFO] +- io.dropwizard:dropwizard-auth:jar:2.0.13:compile
[INFO] |  +- io.dropwizard:dropwizard-util:jar:2.0.13:compile
[INFO] |  +- io.dropwizard.metrics:metrics-core:jar:4.1.12.1:compile
[INFO] |  +- io.dropwizard.metrics:metrics-caffeine:jar:4.1.12.1:compile
[INFO] |  +- com.github.ben-manes.caffeine:caffeine:jar:2.8.5:compile
[INFO] |  |  +- org.checkerframework:checker-qual:jar:3.6.0:compile
[INFO] |  |  \- com.google.errorprone:error_prone_annotations:jar:2.4.0:compile
[INFO] |  +- com.google.code.findbugs:jsr305:jar:3.0.2:compile
[INFO] |  +- com.google.guava:guava:jar:29.0-jre:compile
[INFO] |  |  +- com.google.guava:failureaccess:jar:1.0.1:compile
[INFO] |  |  +- com.google.guava:listenablefuture:jar:9999.0-empty-to-avoid-conflict-with-guava:compile
[INFO] |  |  \- com.google.j2objc:j2objc-annotations:jar:1.3:compile
[INFO] |  +- jakarta.annotation:jakarta.annotation-api:jar:1.3.5:compile
[INFO] |  +- jakarta.ws.rs:jakarta.ws.rs-api:jar:2.1.6:compile
[INFO] |  +- org.glassfish.hk2.external:jakarta.inject:jar:2.6.1:compile
[INFO] |  +- org.glassfish.jersey.containers:jersey-container-servlet-core:jar:2.31:compile
[INFO] |  +- org.glassfish.jersey.core:jersey-common:jar:2.31:compile
[INFO] |  |  +- org.glassfish.hk2:osgi-resource-locator:jar:1.0.3:compile
[INFO] |  |  \- com.sun.activation:jakarta.activation:jar:1.2.2:compile
[INFO] |  +- org.glassfish.jersey.core:jersey-server:jar:2.31:compile
[INFO] |  |  \- org.glassfish.jersey.media:jersey-media-jaxb:jar:2.31:compile
[INFO] |  \- org.slf4j:slf4j-api:jar:1.7.30:compile
[INFO] +- io.dropwizard:dropwizard-core:jar:2.0.13:compile
[INFO] |  +- io.dropwizard:dropwizard-jackson:jar:2.0.13:compile
[INFO] |  |  +- com.fasterxml.jackson.datatype:jackson-datatype-jsr310:jar:2.10.5:compile
[INFO] |  |  +- com.fasterxml.jackson.datatype:jackson-datatype-jdk8:jar:2.10.5:compile
[INFO] |  |  +- com.fasterxml.jackson.module:jackson-module-parameter-names:jar:2.10.5:compile
[INFO] |  |  +- com.fasterxml.jackson.module:jackson-module-afterburner:jar:2.10.5:compile
[INFO] |  |  \- com.fasterxml.jackson.datatype:jackson-datatype-joda:jar:2.10.5:compile
[INFO] |  +- io.dropwizard:dropwizard-validation:jar:2.0.13:compile
[INFO] |  |  \- org.glassfish:jakarta.el:jar:3.0.3:compile
[INFO] |  +- io.dropwizard:dropwizard-configuration:jar:2.0.13:compile
[INFO] |  |  +- com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:jar:2.10.5:compile
[INFO] |  |  |  \- org.yaml:snakeyaml:jar:1.26:compile
[INFO] |  |  \- org.apache.commons:commons-text:jar:1.9:compile
[INFO] |  +- io.dropwizard:dropwizard-metrics:jar:2.0.13:compile
[INFO] |  +- io.dropwizard:dropwizard-servlets:jar:2.0.13:compile
[INFO] |  +- io.dropwizard:dropwizard-jetty:jar:2.0.13:compile
[INFO] |  |  +- org.eclipse.jetty:jetty-servlets:jar:9.4.31.v20200723:compile
[INFO] |  |  |  \- org.eclipse.jetty:jetty-continuation:jar:9.4.31.v20200723:compile
[INFO] |  |  \- org.eclipse.jetty:jetty-http:jar:9.4.31.v20200723:compile
[INFO] |  +- io.dropwizard:dropwizard-lifecycle:jar:2.0.13:compile
[INFO] |  +- io.dropwizard.metrics:metrics-jetty9:jar:4.1.12.1:compile
[INFO] |  +- io.dropwizard.metrics:metrics-jvm:jar:4.1.12.1:compile
[INFO] |  +- io.dropwizard.metrics:metrics-jmx:jar:4.1.12.1:compile
[INFO] |  +- io.dropwizard.metrics:metrics-servlets:jar:4.1.12.1:compile
[INFO] |  |  +- io.dropwizard.metrics:metrics-json:jar:4.1.12.1:compile
[INFO] |  |  \- com.helger:profiler:jar:1.1.1:compile
[INFO] |  +- io.dropwizard.metrics:metrics-healthchecks:jar:4.1.12.1:compile
[INFO] |  +- io.dropwizard:dropwizard-request-logging:jar:2.0.13:compile
[INFO] |  |  \- ch.qos.logback:logback-access:jar:1.2.3:compile
[INFO] |  +- ch.qos.logback:logback-classic:jar:1.2.3:compile
[INFO] |  +- com.fasterxml.jackson.core:jackson-databind:jar:2.10.5:compile
[INFO] |  +- jakarta.servlet:jakarta.servlet-api:jar:4.0.4:compile
[INFO] |  +- net.sourceforge.argparse4j:argparse4j:jar:0.8.1:compile
[INFO] |  +- org.eclipse.jetty:jetty-security:jar:9.4.31.v20200723:compile
[INFO] |  +- org.eclipse.jetty:jetty-server:jar:9.4.31.v20200723:compile
[INFO] |  +- org.eclipse.jetty:jetty-servlet:jar:9.4.31.v20200723:compile
[INFO] |  +- org.eclipse.jetty:jetty-util:jar:9.4.31.v20200723:compile
[INFO] |  +- org.eclipse.jetty.toolchain.setuid:jetty-setuid-java:jar:1.0.4:compile
[INFO] |  \- org.glassfish.jersey.ext:jersey-bean-validation:jar:2.31:compile
[INFO] +- io.dropwizard:dropwizard-db:jar:2.0.13:compile
[INFO] |  \- org.apache.tomcat:tomcat-jdbc:jar:9.0.37:compile
[INFO] |     \- org.apache.tomcat:tomcat-juli:jar:9.0.37:compile
[INFO] +- com.fasterxml.jackson.core:jackson-annotations:jar:2.10.5:compile
[INFO] +- jakarta.validation:jakarta.validation-api:jar:2.0.2:compile
[INFO] +- org.hibernate.validator:hibernate-validator:jar:6.1.5.Final:compile
[INFO] |  +- org.jboss.logging:jboss-logging:jar:3.3.2.Final:compile
[INFO] |  \- com.fasterxml:classmate:jar:1.5.1:compile
[INFO] +- io.dropwizard:dropwizard-testing:jar:2.0.13:test
[INFO] |  +- io.dropwizard.metrics:metrics-annotation:jar:4.1.12.1:compile
[INFO] |  +- com.fasterxml.jackson.datatype:jackson-datatype-guava:jar:2.10.5:compile
[INFO] |  +- com.fasterxml.jackson.jaxrs:jackson-jaxrs-json-provider:jar:2.10.5:compile
[INFO] |  |  +- com.fasterxml.jackson.jaxrs:jackson-jaxrs-base:jar:2.10.5:compile
[INFO] |  |  \- com.fasterxml.jackson.module:jackson-module-jaxb-annotations:jar:2.10.5:compile
[INFO] |  +- org.eclipse.jetty:jetty-io:jar:9.4.31.v20200723:compile
[INFO] |  +- org.glassfish.jersey.core:jersey-client:jar:2.31:compile
[INFO] |  +- org.glassfish.jersey.test-framework:jersey-test-framework-core:jar:2.31:test
[INFO] |  |  \- junit:junit:jar:4.13:test
[INFO] |  |     \- org.hamcrest:hamcrest-core:jar:1.3:test
[INFO] |  +- org.glassfish.jersey.test-framework.providers:jersey-test-framework-provider-inmemory:jar:2.31:test
[INFO] |  +- jakarta.activation:jakarta.activation-api:jar:1.2.2:runtime
[INFO] |  \- jakarta.xml.bind:jakarta.xml.bind-api:jar:2.3.3:runtime
[INFO] +- org.junit.jupiter:junit-jupiter-api:jar:5.7.0:test
[INFO] |  +- org.apiguardian:apiguardian-api:jar:1.1.0:test
[INFO] |  +- org.opentest4j:opentest4j:jar:1.2.0:test
[INFO] |  \- org.junit.platform:junit-platform-commons:jar:1.6.2:test
[INFO] \- org.junit.jupiter:junit-jupiter-engine:jar:5.7.0:test
[INFO]    \- org.junit.platform:junit-platform-engine:jar:1.6.2:test
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 1.683 s
[INFO] Finished at: 2020-09-30T13:01:08+02:00
[INFO] Final Memory: 18M/64M
[INFO] ------------------------------------------------------------------------
```
