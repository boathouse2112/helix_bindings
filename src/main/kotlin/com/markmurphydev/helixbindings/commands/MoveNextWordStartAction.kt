package com.markmurphydev.helixbindings.commands

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.editor.Caret
import com.intellij.openapi.editor.CaretModel
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.Editor

/**
 * Offset of a caret into an Idea-Document. Wrapper for the value returned by [CaretModel.getOffset]
 */
typealias CaretOffset = Int

object MoveNextWordStartAction : AnAction() {
    /**
     * Move each [Caret] forward to the start of the next word.
     * Leave a selection between the starting [CaretOffset] and the new one.
     */
    override fun actionPerformed(e: AnActionEvent) {
        val editor = e.getRequiredData(CommonDataKeys.EDITOR)
        val document = editor.document
        val caretModel = editor.caretModel
        assert(caretModel.supportsMultipleCarets())

        caretModel.runForEachCaret { caret ->
            val start: CaretOffset = caretModel.offset
            val nextWord = findNextWordStart(document, start)
            caret.moveToOffset(nextWord)
            caret.setSelection(start, nextWord)
        }
    }

    /**
     * Find the [CaretOffset] before the start of the next word.
     *
     * Method:
     * - Walk forward until we hit whitespace once
     * - Once we've hit whitespace, walk through the whitespace until we hit a non-whitespace character
     *
     * @param document Current open document. Can get it from [Editor.getDocument]
     */
    private fun findNextWordStart(document: Document, start: CaretOffset): CaretOffset {
        var remainingChars = document.immutableCharSequence.asSequence().withIndex().drop(start)
        // Walk to whitespace
        remainingChars = remainingChars.dropWhile { (_, ch) -> !ch.isWhitespace() }
        // Walk to non-whitespace
        val (nextWordIdx, _) = remainingChars.dropWhile { (_, ch) -> ch.isWhitespace() }.first()
        return nextWordIdx
    }
}
