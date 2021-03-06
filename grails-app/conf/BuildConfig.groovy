grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.war.file = "target/${appName}.war"
grails.project.dependency.resolution = {
   // inherit Grails' default dependencies
   inherits("global") {
      // uncomment to disable ehcache
      // excludes 'ehcache'
   }
   log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
   repositories {
      grailsPlugins()
      grailsHome()
      grailsCentral()

      // uncomment the below to enable remote dependency resolution
      // from public Maven repositories
      //mavenLocal()
      //mavenCentral()
      //mavenRepo "http://snapshots.repository.codehaus.org"
      //mavenRepo "http://repository.codehaus.org"
      //mavenRepo "http://download.java.net/maven/2/"
      //mavenRepo "http://repository.jboss.com/maven2/"
   }
   dependencies {
      test "org.codehaus.geb:geb-spock:0.6.0"
   }
   plugins {
      build ":tomcat:${grailsVersion}"

      compile ":hibernate:${grailsVersion}"
      compile ":joda-time:1.2-SNAPSHOT"
      compile ":resources:1.0.2"

      runtime ":jquery:1.7.1"
      runtime ":jquery-ui:1.8.15"

      test ":geb:0.6.2"
      test ":spock:0.5-groovy-1.7"
   }
}
