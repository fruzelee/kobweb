import com.varabyte.kobweb.gradle.publish.FILTER_OUT_MULTIPLATFORM_PUBLICATIONS
import com.varabyte.kobweb.gradle.publish.set

@Suppress("DSL_SCOPE_VIOLATION") // https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.jetbrains.compose)
    id("com.varabyte.kobweb.internal.publish")
}

group = "com.varabyte.kobweb"
version = libs.versions.kobweb.libs.get()

kotlin {
    js(IR) {
        browser()
        binaries.executable()
    }
    sourceSets {
        val jsMain by getting {
            dependencies {
                implementation(compose.web.core)
                implementation(compose.runtime)

                implementation(project(":frontend:kobweb-core"))
                api(project(":frontend:kobweb-silk-widgets"))
            }
        }
    }
}

kobwebPublication {
    artifactId.set("kobweb-silk")
    description.set("A set of rich UI components built on top of Kobweb and inspired by the best parts of Compose and Chakra UI")
    filter.set(FILTER_OUT_MULTIPLATFORM_PUBLICATIONS)
}