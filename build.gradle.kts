plugins {
    `java-library`
    id("com.jfrog.artifactory") version "5.1.11"
    `maven-publish`
}

allprojects {
    apply(plugin = "java")
    apply(plugin = "maven-publish")

    group = "net.bytemc"
    version = "1.3.7-SNAPSHOT"

    repositories {
        mavenCentral()
        maven {
            url = uri("https://s01.oss.sonatype.org/content/groups/staging/")
        }
    }

    configurations.all {
        // We only use Jetbrains Annotations
        exclude("org.checkerframework", "checker-qual")
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    tasks.withType<Zip> {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }

    tasks.withType<Test> {
        useJUnitPlatform()

        // Viewable packets make tracking harder. Could be re-enabled later.
        jvmArgs("-Dminestom.viewable-packet=false")
        jvmArgs("-Dminestom.inside-test=true")
    }

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
    }

    publishing {
        publications {
            create<MavenPublication>("mavenJava") {
                from(components["java"])

                this.groupId = project.group.toString()
                this.artifactId = project.name
                this.version = project.version.toString()
            }
        }

        repositories {
            maven {
                name = "bytemc"
                url = uri("https://nexus.bytemc.de/repository/maven-public/")
                credentials {
                    username = System.getenv("BYTEMC_REPO_USERNAME")
                    password = System.getenv("BYTEMC_REPO_PASSWORD")
                }
            }
        }
    }
}

sourceSets {
    main {
        java.srcDir(file("src/main/java"))
        java.srcDir(file("src/autogenerated/java"))
    }
}

configurations.implementation {
    exclude(group = "org.jboss.shrinkwrap.resolver", module = "shrinkwrap-resolver-depchain")
}

dependencies {
    api(libs.slf4j)
    api(libs.jetbrainsAnnotations)
    api(libs.bundles.adventure)
    api(libs.bundles.kotlin)
    api(libs.bundles.hephaistos)
    implementation(libs.minestomData)
    implementation(libs.caffeine)
    api(libs.fastutil)
    implementation(libs.bundles.flare)
    api(libs.gson)
    implementation(libs.jcTools)
    testImplementation(libs.bundles.junit)

    api(group = "com.extollit", name = "data-structures", version = "2.18")
    api(group = "com.extollit.gaming", name = "hydrazine-path-engine", version = "1.8.1")
}
