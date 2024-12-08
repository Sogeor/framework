plugins {
    id("java")
}

description = "Предоставляет функциональные инструменты."

dependencies {
    implementation(project(":annotation"))
    implementation(project(":throwable"))
    implementation(project(":validation"))
}
