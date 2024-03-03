import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.10"
    application
}

group = "com.lignting"
version = "1.0.0"

repositories {
    mavenLocal()
    maven { url =uri("https://maven.aliyun.com/nexus/content/groups/public/")}
    mavenCentral()
    maven { url =uri("https://repo.spring.io/snapshot") }
    maven { url =uri("https://repo.spring.io/milestone") }
    maven { url =uri("https://oss.jfrog.org/artifactory/oss-snapshot-local/") }
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.1.1")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}