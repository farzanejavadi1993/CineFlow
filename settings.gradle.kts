pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "CineFlow"
include(":app")
include(":core:model")
include(":core:common")
include(":core:domain")
include(":core:database")
include(":core:network")
include(":feature:trending")
include(":feature:watchlist")
include(":feature:movie")


include(":core:testutils")
