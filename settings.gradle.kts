pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

rootProject.name = "kobweb"

include(":cli:kobweb")
include(":common:kobweb-common")
include(":frontend:kobweb-core")
include(":frontend:kobweb-compose")
include(":frontend:kobweb-silk")
include(":frontend:kobweb-silk-widgets")
include(":frontend:kobweb-silk-icons-fa")
include(":frontend:kobweb-silk-icons-mdi")
include(":frontend:kobwebx-markdown")
include(":frontend:web-compose-ext")
include(":backend:kobweb-api")
include(":backend:server")
include(":gradle-plugins:core")
include(":gradle-plugins:library")
include(":gradle-plugins:application")
include(":gradle-plugins:extensions:markdown")