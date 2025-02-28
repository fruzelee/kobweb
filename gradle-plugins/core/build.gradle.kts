@Suppress("DSL_SCOPE_VIOLATION") // https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    `kotlin-dsl`
    kotlin("jvm")
    id("com.varabyte.kobweb.internal.publish")
    `java-library`
    `java-gradle-plugin`
    alias(libs.plugins.kotlinx.serialization)
}

group = "com.varabyte.kobweb.gradle"
version = libs.versions.kobweb.libs.get()

dependencies {
    implementation(kotlin("stdlib"))
    // Get access to Kotlin multiplatform source sets
    implementation(kotlin("gradle-plugin"))

    implementation(libs.kotlinx.serialization.json)

    // For parsing code.
    api(kotlin("compiler-embeddable"))


    implementation(project(":common:kobweb-common"))
}

val DESCRIPTION = "A Gradle plugin that provides common support for the Library and Application plugins."
gradlePlugin {
    plugins {
        create("kobwebLibrary") {
            id = "com.varabyte.kobweb.core"
            displayName = "Kobweb Core Plugin"
            description = DESCRIPTION
            implementationClass = "com.varabyte.kobweb.gradle.core.KobwebCorePlugin"
        }
    }
}

kobwebPublication {
    // Leave artifactId blank. It will be set to the name of this module, and then the gradlePlugin step does some
    // additional tweaking that we don't want to interfere with.
    description.set(DESCRIPTION)
}
