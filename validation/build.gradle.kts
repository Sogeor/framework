plugins {
    id("java")
}

description = "Предоставляет инструменты для валидации объектов и значений."

dependencies {
    implementation(project(":annotation"))
    implementation(project(":throwable"))
}
