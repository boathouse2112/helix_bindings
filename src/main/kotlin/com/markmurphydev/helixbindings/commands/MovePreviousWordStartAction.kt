package com.markmurphydev.helixbindings.commands

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.editor.Caret
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.Editor
import com.intellij.util.text.ImmutableCharSequence
import kotlin.math.max
import kotlin.math.min

object MovePreviousWordStartAction : AnAction() {
    /**
     * Move each [Caret] back to the start of the previous word.
     * Leave a selection between the starting [CaretOffset] and the new one.
     */
    override fun actionPerformed(e: AnActionEvent) {
        val editor = e.getRequiredData(CommonDataKeys.EDITOR)
        val document = editor.document
        val caretModel = editor.caretModel
        assert(caretModel.supportsMultipleCarets())

        caretModel.runForEachCaret { caret ->
            val start: CaretOffset = caretModel.offset
            val nextWord = findPreviousWordStart(document, start)
            caret.moveToOffset(nextWord)
            caret.setSelection(start, nextWord)
        }
    }

    /**
     * Find the [CaretOffset] before the start of the previous word.
     *
     * Method:
     * - Unless we're at the start of the text, move back one char
     * - Walk backwards until we hit (non-whitespace) or (document start)
     * - Then, walk back until we hit (whitespace) or (document start)
     *
     * TODO -- I think it would be possible to add method [ImmutableCharSequence.asReversed], but it's too hard.
     *  Doing so would make this implementation much nicer.
     *
     *  TODO -- Shouldn't wrap around line start
     *      I don't know how to query at-line-start
     *
     * @param document Current open document. Can get it from [Editor.getDocument]
     */
    @Suppress("KDocUnresolvedReference")
    private fun findPreviousWordStart(document: Document, start: CaretOffset): CaretOffset {
        val chars = document.immutableCharSequence

        // Unless we're at the start of the text, we want to move back a char no matter what,
        // so that we don't stay in place at the beginning of a word
        var offset = max(start - 1, 0)

        // Walk backwards until we hit (non-whitespace) or (document start)
        var hitNonWhitespace = false
        var hitStart = false
        while(!hitNonWhitespace && !hitStart) {
            if (offset == 0) {
                hitStart = true
            } else {
                val ch = chars[offset]
                if (!ch.isWhitespace()) {
                    hitNonWhitespace = true
                }
                offset -= 1 // Move back one even if we hit whitespace
            }
        }

        var hitWhitespace = false
        while(!hitWhitespace && !hitStart) {
            if (offset == 0) {
                hitStart = true
            } else {
                val ch = chars[offset]
                if (ch.isWhitespace()) {
                    hitWhitespace = true
                } else {
                    offset -= 1 // Don't move back if we hit non-whitespace.
                }
            }
        }

        // I thought putting `offset -= 1` in the `else` would mean we don't have to add 1... Whatever.
        return if (hitStart) {
            0
        } else {
            offset + 1
        }
    }
}