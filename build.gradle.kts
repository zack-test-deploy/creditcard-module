plugins {
    `java-library`
    `maven-publish`
}

group = "com.hotsauce.payments.creditcard"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    withSourcesJar()
    withJavadocJar()
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            val org = System.getenv("GITHUB_ORG")
            val repo = System.getenv("GITHUB_REPO")
            url = uri("https://maven.pkg.github.com/$org/$repo")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}

