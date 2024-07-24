package socketTrench.gui

interface GUIScreen {
    fun dispose()
}

internal var currentScreen: GUIScreen? = null

fun goTo(newScreen: GUIScreen) {
    currentScreen?.dispose()
    currentScreen = newScreen
}
