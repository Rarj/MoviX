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
include(":home:domain")
include(":network:shared")
include(":network:state")
include(":navigation:home:impl")
include(":navigation:home:controller")
include(":search:ui")
include(":search:api")
include(":search:di")
include(":search:domain")
include(":search:impl")
include(":navigation:search:controller")
include(":navigation:search:impl")
include(":detail:ui")
include(":detail:api")
include(":detail:domain")
include(":detail:di")
include(":detail:impl")
include(":navigation:detail:controller")
include(":navigation:detail:impl")
include(":review:api")
include(":review:di")
include(":review:domain")
include(":review:impl")
include(":review:ui")
include(":benchmark")
include(":genre:api")
include(":genre:impl")
include(":genre:domain")
include(":genre:di")
include(":common")
