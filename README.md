# embedmongo-offline-maven-plugin

Maven plugin wrapper for the [flapdoodle.de embedded MongoDB API](http://github.com/flapdoodle-oss/embedmongo.flapdoodle.de).

This plug-in allows you to start and stop an instance of MongoDB from the binary available in the mongo-repo folder(`\src\main\resources\mongo-repo`) during a Maven build, e.g. for integration testing. The Mongo instance isn't strictly embedded (it's not running within the JVM of your application), but it _is_ a managed instance that exists only for the lifetime of your build.

## Required

* Java 8 and later
* Lombok

## Usage

```xml
<build>
		<plugins>
			<plugin>
				<groupId>com.github.joelittlejohn.embedmongo</groupId>
				<artifactId>embedmongo-offline-maven-plugin</artifactId>
				<version>0.5.0</version>
				<executions>
					<execution>
						<?m2e ignore?>
						<id>start</id>
						<goals>
							<goal>start</goal>
						</goals>
						<configuration>
							<port>27017</port>
							<version>2.7.1</version>
							<databaseDirectory>/tmp/mongotest</databaseDirectory>
							<logging>console</logging>
							<bindIp>127.0.0.1</bindIp>
							<skip>false</skip>
							<!-- optional, skips this plugin entirely, use on the command line 
								like -Dmongodb.skip -->
						</configuration>
					</execution>
					<execution>
						<id>stop</id>
						<goals>
							<goal>stop</goal>
						</goals>
					</execution>
				</executions>
			</plugin>
		</plugins>
	</build>
```

## Notes

* The version defined in your pom.xml, Example: `<version>2.7.1</version>` should be identical to the version of the MongoDB binary file available at: embedmongo-offline-maven-plugin `\src\main\resources\mongo-repo`. Find the MongoDB binary based on your operating system.
	- Windows https://www.mongodb.org/dl/win32/x86 
	- Linux   https://www.mongodb.org/dl/linux/x86
	- MacOs   https://www.mongodb.org/dl/osx/x86

* By default, the `start` goal is bound to `test-compile`, the `stop` goal is bound to `post-integration-test`. You can of course bind to different phases if required.
* If you omit/forget the `stop` goal, any Mongo process spawned by the `start` goal will be stopped when the JVM terminates.
* If you want to run Maven builds in parallel you can use `randomPort` to avoid port conflicts, the value allocated will be available to other plugins in the project as a property `embedmongo.port`.
  If you're using Jenkins, you can also try the [Port Allocator Plugin](https://wiki.jenkins-ci.org/display/JENKINS/Port+Allocator+Plugin).
* If you're having trouble with Windows firewall rules, try setting the _bindIp_ config property to `127.0.0.1`.
* If you'd like the start goal to start mongodb and wait, you can add `-Dembedmongo.wait` to your Maven command line arguments or `-Dembedmongo.import.wait` if you want the imports
* If you are using a charset encoding to load scripts, refer to the [IANA Charset Registry](http://www.iana.org/assignments/character-sets/character-sets.xhtml).  Accepted charsets are found in the __Preferred MIME Name__ column.

### Custon version off Rian Vasconcelos based on  Joe Littlejohn
