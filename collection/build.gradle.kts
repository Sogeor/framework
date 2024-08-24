plugins {
    id("java")
}

group = rootProject.group
version = rootProject.version
description =
    "Представляет собой модуль для работы с неизменяемыми, унаследованными и изменяемыми коллекциями, а также с коллекциями только для чтения и только для записи."

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":throwable"))
    implementation(project(":validation"))

    implementation("org.jetbrains:annotations:24.1.0")
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21

    withJavadocJar()
    withSourcesJar()
}

tasks.javadoc {
    options.encoding = "UTF-8"
    val options = options as CoreJavadocOptions
    options.addStringOption("Xdoclint", "all")
    options.addStringOption("tag", "apiNote:a:\"API Note:\"")
    options.addStringOption("tag", "implSpec:a:\"Implementation Requirements:\"")
    options.addStringOption("tag", "implNote:a:\"Implementation Note:\"")
}

tasks.compileJava {
    options.encoding = "UTF-8"
}
