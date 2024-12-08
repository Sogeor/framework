plugins {
    id("java")
}

description = "Предоставляет фундаментальные инструменты."

dependencies {
    implementation(project(":annotation"))
    implementation(project(":function"))
    implementation(project(":throwable"))
    implementation(project(":validation"))
}
