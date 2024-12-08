plugins {
    id("java")
}

description = "Предоставляет фундаментальные коллекции."

dependencies {
    implementation(project(":annotation"))
    implementation(project(":common"))
    implementation(project(":function"))
    implementation(project(":throwable"))
    implementation(project(":validation"))
}
