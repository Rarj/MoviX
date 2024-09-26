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
include(":uikit")
include(":home:ui")
include(":home:api")
include(":home:impl")
include(":home:di")
include(":network:shared")
include(":navigation:home:impl")
include(":navigation:home:controller")
include(":search:ui")
include(":search:api")
include(":search:di")
include(":search:impl")
include(":navigation:search:controller")
include(":navigation:search:impl")
include(":detail:ui")
include(":detail:api")
include(":detail:di")
include(":detail:impl")
include(":navigation:detail:controller")
include(":navigation:detail:impl")
include(":review:api")
include(":review:di")
include(":review:impl")
include(":review:ui")
include(":benchmark")
