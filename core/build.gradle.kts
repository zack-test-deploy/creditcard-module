plugins {
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.reflections:reflections:0.10.2")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.mockito:mockito-core:3.8.0")
}

tasks.test {
    useJUnitPlatform()
}

java {
    withSourcesJar()
}