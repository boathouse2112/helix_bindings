package com.markmurphydev.helixbindings.commands

import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.editor.Editor

/**
 * Kotlin uses objects for namespaces.
 *
 * Helix-commands are all implementations of Intellij [AnAction]
 * [AnAction] has... static lifetime? I don't think we're supposed to make multiple instances of them.
 */
object Commands {

    /**
     * Use Intellij's [ActionManager] to get the given native action
     */
    private fun getNativeAction(actionId: String) = ActionManager.getInstance().getAction(actionId)!!

    val moveLeft = getNativeAction(IdeActions.ACTION_EDITOR_MOVE_CARET_LEFT)
    val moveDown = getNativeAction(IdeActions.ACTION_EDITOR_MOVE_CARET_DOWN)
    val moveUp = getNativeAction(IdeActions.ACTION_EDITOR_MOVE_CARET_UP)
    val moveRight = getNativeAction(IdeActions.ACTION_EDITOR_MOVE_CARET_RIGHT)
    fun moveNextWordStart(editor: Editor, context: DataContext) {
    }
}

/**
 * Execute this action with a default [AnActionEvent].
 * I think IdeaVIM _only_ uses this instantiation of [AnActionEvent]
 */
fun AnAction.execute(context: DataContext) {
    val event = AnActionEvent(
        null,
        context,
        ActionPlaces.KEYBOARD_SHORTCUT, // Because helix-commands are "keyboard shortcuts" ?
        this.templatePresentation.clone(), // ???
        ActionManager.getInstance(), // ???
        0 // ???
    )
    this.actionPerformed(event)
}
