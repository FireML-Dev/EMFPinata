import net.minecrell.pluginyml.paper.PaperPluginDescription

plugins {
    `java-library`
    `maven-publish`
    alias(libs.plugins.shadow)
    alias(libs.plugins.plugin.yml)
}

repositories {
    mavenLocal()
    mavenCentral()
    gradlePluginPortal()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.firedev.uk/repository/maven-public/")
}

dependencies {
    compileOnly(libs.paper.api)
    compileOnly(files("$projectDir/libs/even-more-fish-1.7.2.jar"))

    implementation(libs.commandapi)
}

group = "uk.firedev"
version = "1.0.2"
description = "A Piñata addon for the EvenMoreFish plugin."
java.sourceCompatibility = JavaVersion.VERSION_21

paper {
    name = project.name
    version = project.version.toString()
    main = "uk.firedev.emfpinata.EMFPinata"
    apiVersion = "1.21"
    author = "FireML"
    description = project.description.toString()
    foliaSupported = true

    serverDependencies {
        register("EvenMoreFish") {
            required = true
            load = PaperPluginDescription.RelativeLoadOrder.BEFORE
        }
    }
}

publishing {
    repositories {
        maven {
            name = "firedevRepo"
            url = uri("https://repo.firedev.uk/repository/maven-releases/")
            credentials(PasswordCredentials::class)
            authentication {
                create<BasicAuthentication>("basic")
            }
        }
    }
    publications {
        create<MavenPublication>("maven") {
            groupId = "uk.firedev"
            artifactId = "EMFPinata"
            version = version

            from(components["java"])
        }
    }
}

tasks {
    build {
        dependsOn(shadowJar)
    }
    shadowJar {
        archiveBaseName.set(project.name)
        archiveVersion.set(project.version.toString())
        archiveClassifier.set("")

        relocate("dev.jorel.commandapi", "uk.firedev.emfpinata.libs.commandapi")

        minimize()
    }
    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }
}
