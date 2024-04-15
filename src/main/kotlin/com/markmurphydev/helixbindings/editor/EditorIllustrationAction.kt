package com.markmurphydev.helixbindings.editor

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys

class EditorIllustrationAction : AnAction() {
    override fun update(e: AnActionEvent) {
        // Get required data keys
        val project = e.project
        val editor = e.getData(CommonDataKeys.EDITOR)

        // Set visibility only in the case of existing project editor, and selection
        e.presentation.isEnabledAndVisible = (project != null) && (editor?.selectionModel?.hasSelection() == true)
    }

    override fun actionPerformed(e: AnActionEvent) {
        val editor = e.getRequiredData(CommonDataKeys.EDITOR)
        val project = e.getRequiredData(CommonDataKeys.PROJECT)
    }
}