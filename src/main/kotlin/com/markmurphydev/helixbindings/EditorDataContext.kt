package com.markmurphydev.helixbindings

import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.ex.util.EditorUtil
import com.intellij.openapi.fileEditor.FileDocumentManager

/**
 * [com.intellij.openapi.actionSystem.AnAction] constructor requires a "context". This is a copy of IDEAVIM's.
 * This is a small extension of the [Editor]'s pre-existing data context.
 */
class EditorDataContext(
    private val editor: Editor,
    private val contextDelegate: DataContext?,
) : DataContext {

    private val editorContext: DataContext = EditorUtil.getEditorDataContext(editor)


    /**
     * Returns the object corresponding to the specified data identifier. Some of the supported
     * data identifiers are defined in the [com.intellij.openapi.actionSystem.PlatformDataKeys] class.
     *
     * NOTE: For implementation only, prefer [DataContext#getData(DataKey)] in client code.
     *
     * We add a few recognized keys, then punt to the [Editor]'s context.
     * If that fails, we send it to the context delegate.
     *
     * @param dataId the data identifier for which the value is requested.
     * @return the value, or null if no value is available in the current context for this identifier.
     */
    override fun getData(dataId: String): Any? = when (dataId) {
        PlatformDataKeys.EDITOR.name -> editor
        PlatformDataKeys.PROJECT.name -> editor.project
        PlatformDataKeys.VIRTUAL_FILE.name -> FileDocumentManager.getInstance().getFile(editor.document)
        else -> editorContext.getData(dataId) ?: contextDelegate?.getData(dataId)
    }
}