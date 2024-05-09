package com.markmurphydev.helixbindings.commands

import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.ActionPlaces
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.actionSystem.IdeActions
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.editor.Editor

object Commands {
    fun moveLineDown(editor: Editor, context: DataContext) {
        val action = ActionManager.getInstance().getAction(IdeActions.ACTION_EDITOR_MOVE_CARET_DOWN)!!
        val event = AnActionEvent(
            null,
            context,
            ActionPlaces.KEYBOARD_SHORTCUT, // ???
            action.templatePresentation.clone(), // ???
            ActionManager.getInstance(),
            0
        )
        thisLogger().info("Trying to perform action.")
        action.actionPerformed(event)
    }
}
