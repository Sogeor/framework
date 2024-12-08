plugins {
    id("java")
}

group = "com.sogeor"
version = "1.0.0-SNAPSHOT"

subprojects {
    project.afterEvaluate {
        group = rootProject.group
        version = rootProject.version

        java {
            sourceCompatibility = JavaVersion.VERSION_21
            targetCompatibility = JavaVersion.VERSION_21

            withJavadocJar()
            withSourcesJar()
        }

        repositories {
            mavenCentral()
        }

        tasks.compileJava {
            options.encoding = "UTF-8"
        }

        tasks.javadoc {
            options.encoding = "UTF-8"
            val options = options as CoreJavadocOptions
            options.addStringOption("Xdoclint", "all")
            options.addStringOption("tag", "apiNote:a:\"API Note:\"")
            options.addStringOption("tag", "implSpec:a:\"Implementation Requirements:\"")
            options.addStringOption("tag", "implNote:a:\"Implementation Note:\"")
        }

        if (name != "annotation") {
            dependencies {
                testImplementation("org.junit.jupiter:junit-jupiter-api:5.11.3")
                testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.11.3")
            }

            tasks.compileTestJava {
                options.encoding = "UTF-8"
            }

            tasks.test {
                useJUnitPlatform()
            }
        }
    }
}
