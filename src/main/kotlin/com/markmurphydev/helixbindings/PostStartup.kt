package com.markmurphydev.helixbindings

import com.intellij.openapi.editor.actionSystem.TypedAction
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity

/**
 * Registered to run on project startup. Lets us initialize stuff.
 */
class PostStartup: StartupActivity {

    /**
     * Is run by Idea on project startup
     */
    override fun runActivity(project: Project) {
        this.replaceTypedActionHandler()
    }

    /**
     * Replace the default TypedActionHandler with the Helix one, so we can intercept keystrokes.
     */
    private fun replaceTypedActionHandler() {
        val typedAction = TypedAction.getInstance()
        val originalHandler = typedAction.rawHandler

        // Sanity check -- have we already set the TypedAction handler?
        if (originalHandler !is HelixTypedActionHandler) {
            val helixTypedActionHandler = HelixTypedActionHandler(originalHandler)
            typedAction.setupRawHandler(helixTypedActionHandler)
        } else {
            error("Expected TypedActionHandler to be non-helix.")
        }
    }
}