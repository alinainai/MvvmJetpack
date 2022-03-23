package com.gas.ext.app.io

import com.gas.ext.app.errorLog
import java.io.Closeable

/**
 * [Closeable]的扩展
 */
// ------------------------------

fun Closeable.closeQuietly() = apply {
    try {
        close()
    } catch (ignored: Throwable) {
        errorLog(ignored)
    }
}