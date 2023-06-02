package com.albert.infinitespirit.nav

sealed class Screen(val route: String) {
    companion object {
        const val AGE = "age"
        const val NAME = "name"
    }

    object Home : Screen("home")
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