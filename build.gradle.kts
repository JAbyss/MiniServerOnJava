plugins {
	java
	id("org.springframework.boot") version "3.1.0"
	id("io.spring.dependency-management") version "1.1.0"
}

group = "com.notmorron"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
//	implementation("org.eclipse.paho:org.eclipse.paho.client.mqttv3:1.2.5")

//	implementation("org.postgresql.postgresql:42.2.12")
//	implementation("org.springframework.spring-jdbc")
	implementation("com.google.code.gson:gson:2.8.6")
//	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
//	implementation(project(mapOf("path" to "NoParentModule:NoParentModule.main")))

	implementation(files("libs/mqttclient-lib.jar"))

	compileOnly("org.projectlombok:lombok")
	runtimeOnly("org.postgresql:postgresql")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	implementation("org.testcontainers:testcontainers:1.18.3") //import bom
	testImplementation("org.testcontainers:postgresql")
	testImplementation("org.testcontainers:testcontainers:1.18.3")
	testImplementation("org.testcontainers:junit-jupiter:1.18.3")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
