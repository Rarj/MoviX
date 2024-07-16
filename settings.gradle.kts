pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MoviX"
include(":app")
include(":data")
include(":uikit")
include(":home:ui")
include(":home:api")
include(":home:impl")
include(":home:di")
include(":network:shared")
include(":navigation:home:impl")
include(":navigation:home:controller")
