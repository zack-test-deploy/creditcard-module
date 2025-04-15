plugins {
    `java-library`
    `maven-publish`
}

group = "com.hotsauce.payments.creditcard"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

allprojects{
    plugins.apply("maven-publish")
    plugins.apply("java-library")
    publishing {
        publications {
            create<MavenPublication>("mavenJava") {
                from(components["java"])
            }
        }
        repositories {
            maven {
                name = "GitHubPackages"

                val org = System.getenv("GITHUB_ORG")
                val repo = System.getenv("GITHUB_REPO")
                val actor = System.getenv("GITHUB_ACTOR")
                val tokenSet = System.getenv("GITHUB_TOKEN") != null

                url = uri("https://maven.pkg.github.com/$org/$repo")

                logger.lifecycle("Publishing to GitHub Packages:")
                logger.lifecycle(" - url: $url")
                logger.lifecycle(" - actor: $actor")
                logger.lifecycle(" - token is set: $tokenSet")

                credentials {
                    username = actor
                    password = System.getenv("GITHUB_TOKEN")
                }
            }
        }
    }
    java {
        withSourcesJar()
        withJavadocJar()
    }
}


