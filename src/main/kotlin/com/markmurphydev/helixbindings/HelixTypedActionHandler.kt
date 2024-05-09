package com.markmurphydev.helixbindings

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.actionSystem.ActionPlan
import com.intellij.openapi.editor.actionSystem.TypedActionHandler
import com.intellij.openapi.editor.actionSystem.TypedActionHandlerEx
import com.markmurphydev.helixbindings.commands.Commands
import com.markmurphydev.helixbindings.commands.execute

/**
 * Intercepts keystrokes
 * @param originalHandler Idea's default TypedActionHandler. We delegate some things to it.
 */
class HelixTypedActionHandler(private val originalHandler: TypedActionHandler) : TypedActionHandlerEx {

    override fun beforeExecute(editor: Editor, c: Char, context: DataContext, plan: ActionPlan) {
//        TODO("Not yet implemented")
    }

    /**
     * Get and execute an [AnAction] for this keystroke's associated helix-action.
     *
     */
    override fun execute(editor: Editor, charTyped: Char, dataContext: DataContext) {
        var caret = editor.caretModel.primaryCaret

        val command = when (charTyped) {
            'h' -> Commands.moveLeft
            'j' -> Commands.moveDown
            'k' -> Commands.moveUp
            'l' -> Commands.moveRight
            else -> null
        }
        if (command != null) {
            command.execute(dataContext)
        } else {
            originalHandler.execute(editor, charTyped, dataContext)
        }
    }

    internal companion object {
        private val LOG = logger<HelixTypedActionHandler>()
    }
}