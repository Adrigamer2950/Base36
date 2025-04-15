plugins {
    kotlin("jvm")
    id("maven-publish")
}

group = "me.devadri"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(11)
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.register("sourcesJar", Jar::class) {
    from(sourceSets.main.get().kotlin)
    archiveClassifier.set("sources")

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

publishing {
    repositories {
        maven {
            url = uri("https://repo.devadri.es/repository/releases")
            credentials {
                username = project.findProperty("NEXUS_USERNAME") as String?
                password = project.findProperty("NEXUS_PASSWORD") as String?
            }
        }
    }
    publications {
        create<MavenPublication>("maven") {
            groupId = rootProject.group as String
            artifactId = rootProject.name
            version = rootProject.version as String

            artifact(tasks["sourcesJar"])
            artifact(tasks["jar"])

            pom {
                name = rootProject.name
                description = "Base36 implementation"
                url = "https://github.com/Adrigamer2950/Base36"

                licenses {
                    license {
                        name = "GPL-3.0"
                        url = "https://www.gnu.org/licenses/gpl-3.0.html"
                    }
                }

                developers {
                    developer {
                        id = "devadri"
                        name = "Adri"
                    }
                }

                scm {
                    url = "https://github.com/Adrigamer2950/Base36"
                }

                issueManagement {
                    system = "GitHub"
                    url = "https://github.com/Adrigamer2950/Base36/issues"
                }
            }
        }
    }
}
