package com.albert.infinitespirit.nav

sealed class Screen(val route: String) {
    companion object {
        const val AGE = "age"
        const val NAME = "name"
    }

    object NavFeatureHome : Screen("nav_feature_home")

    object Detail : Screen("detail/{$AGE}/{$NAME}")

    fun withArgs(args: Map<String, Any>): String {
        var routeResult = route
        args.forEach { (key, value) ->
            val placeholder = "{$key}"
            routeResult = routeResult.replace(placeholder, value.toString())
        }
        return routeResult
    }
}