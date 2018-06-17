package ru.mamykin.paginatedtextview.pagination

import android.text.TextPaint

class PaginationController(text: CharSequence,
                           width: Int,
                           height: Int,
                           paint: TextPaint,
                           spacingMult: Float,
                           spacingAdd: Float,
                           includePad: Boolean
) {

    private val paginator = Paginator(text, width, height, paint, spacingMult, spacingAdd, includePad)

    fun getCurrentPage(): ReadState {
        return ReadState(paginator.currentIndex + 1,
                paginator.pagesCount,
                getReadPercent(),
                paginator.getCurrentPage()
        )
    }

    fun getNextPage(): ReadState {
        if (paginator.currentIndex < paginator.pagesCount - 1) {
            paginator.currentIndex++
        }
        return getCurrentPage()
    }

    fun getPrevPage(): ReadState {
        if (paginator.currentIndex > 0) {
            paginator.currentIndex--
        }
        return getCurrentPage()
    }

    private fun getReadPercent(): Float = when (paginator.pagesCount) {
        0 -> 0f
        else -> (paginator.currentIndex + 1) / paginator.pagesCount.toFloat() * 100
    }
}