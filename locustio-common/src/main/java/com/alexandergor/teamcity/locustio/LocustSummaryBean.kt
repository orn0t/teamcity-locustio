/*
 * Copyright 2019 Alexander Gor
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alexandergor.teamcity.locustio

import java.math.BigDecimal

class LocustSummaryBean {
    val PREFIX = "locustio"

    private val columns = mapOf(
        "requests" to "green",
        "failures" to "red",
        "average_time" to "red",
        "min_time" to "red",
        "max_time" to "red",
        "median_time" to "red",
        "rps" to "green"
    )

    fun getStatsDiff(current: Map<String, BigDecimal>, prev: Map<String, BigDecimal>?): List<SummaryValue> {
        var result = mutableListOf<SummaryValue>()

        println(columns.keys)

        for (k in columns.keys) {
            var diff = 0.0
            var new = 0.0

            if (prev != null) {
                new = getVal(current, k)

                val original = getVal(prev, k)

                diff = (new - original) / original * 100

                if (!diff.isFinite()) {
                    diff = 0.0
                }
            }

            result.add(SummaryValue(new, diff, getColor(k, diff)))
        }

        return result
    }

    private fun getColor(key: String, value: Double): String? {
        return if (value > 0) {
            columns[key]
        } else {
            if (columns[key] == "red") {
                "green"
            } else {
                "red"
            }
        }
    }

    private fun getVal(source: Map<String, BigDecimal>, key: String): Double {
        val value = source["%s_%s".format(PREFIX, key)]
        return value?.toDouble() ?: 0.0
    }

    val requestsKey = "locustio_requests"

}

class SummaryValue(val mean: Number, val diff: Number?, val color: String?)