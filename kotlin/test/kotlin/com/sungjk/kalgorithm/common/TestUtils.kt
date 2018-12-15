package com.sungjk.kalgorithm.common

/**
 * Created by jeremy on 09/22/2018.
 */
inline fun <reified T> matrix2d(height: Int, width: Int, initialize: () -> T) =
    Array(height) {Array(width) {initialize()}}