package com.markmurphydev.helixbindings

import com.intellij.codeInsight.editorActions.TypedHandlerDelegate
import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.diagnostic.LogLevel
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.fileTypes.FileType
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile

/**
 * This one is broken I think. Ignore.
 */
class HelixTypedHandler: TypedHandlerDelegate() {

    override fun checkAutoPopup(charTyped: Char, project: Project, editor: Editor, file: PsiFile): Result {
        return Result.STOP
    }

    override fun beforeCharTyped(c: Char, project: Project, editor: Editor, file: PsiFile, fileType: FileType): Result {
        return Result.STOP
    }

    override fun isImmediatePaintingEnabled(editor: Editor, c: Char, context: DataContext): Boolean {
        return false
    }

    override fun charTyped(c: Char, project: Project, editor: Editor, file: PsiFile): Result {
        return Result.STOP
    }

    override fun beforeSelectionRemoved(c: Char, project: Project, editor: Editor, file: PsiFile): Result {
        return Result.STOP
    }

    internal companion object {
        private val LOG = logger<HelixTypedActionHandler>()
    }
}