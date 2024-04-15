package com.markmurphydev.helixbindings
import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.actionSystem.ActionPlan
import com.intellij.openapi.editor.actionSystem.TypedActionHandler
import com.intellij.openapi.editor.actionSystem.TypedActionHandlerEx
import java.awt.event.KeyEvent
import javax.swing.KeyStroke

/**
 * Intercepts keystrokes
 * @param originalHandler Idea's default TypedActionHandler. We delegate some things to it.
 */
class HelixTypedActionHandler(originalHandler: TypedActionHandler) : TypedActionHandlerEx {

    override fun beforeExecute(editor: Editor, c: Char, context: DataContext, plan: ActionPlan) {
//        TODO("Not yet implemented")
    }

    override fun execute(editor: Editor, charTyped: Char, dataContext: DataContext) {
//        TODO("Not yet implemented")
    }

    internal companion object {
        private val LOG = logger<HelixTypedActionHandler>()
    }
}