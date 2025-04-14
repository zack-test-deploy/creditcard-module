plugins {
    `java-library`
    `maven-publish`
}

group = "com.hotsauce.payments.creditcard"
version = "1.0-SNAPSHOT"


val nexusRepoUrl = uri("http://hotsaucepos.ddns.net:8081/repository/maven-snapshots/")
val nexusUsername: String = project.findProperty("nexusUser") as? String ?: "hotsauce-developer"
val nexusPassword: String = project.findProperty("nexusPassword") as? String ?: "hotsauce987!"

repositories {
    mavenCentral()
    maven {
        url = nexusRepoUrl
        credentials {
            username = nexusUsername
        }
    }
}

java {
    withSourcesJar()
    withJavadocJar()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
    repositories {
        maven {
            name = "nexus"
            url = nexusRepoUrl
            credentials {
                username = nexusUsername
                password = nexusPassword
            }
            isAllowInsecureProtocol = true
        }
    }
}

