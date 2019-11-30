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

import jetbrains.buildServer.agent.BuildFinishedStatus
import jetbrains.buildServer.agent.runner.BuildServiceAdapter
import jetbrains.buildServer.agent.runner.ProgramCommandLine
import jetbrains.buildServer.agent.runner.SimpleProgramCommandLine
import jetbrains.buildServer.messages.serviceMessages.BuildStatisticValue
import org.apache.commons.io.input.ReversedLinesFileReader

class LocustService: BuildServiceAdapter() {
    private val cfgBean: LocustConfigBean = LocustConfigBean()
    private var locustStats = mutableMapOf<String, Double>()

    override fun makeProgramCommandLine(): SimpleProgramCommandLine {
        val arguments = arrayListOf(
            "-f", getParam(cfgBean.locustFileKey),
            "--host", getParam(cfgBean.targetUrlKey),
            "-c", getParam(cfgBean.usersNumberKey),
            "-r", getParam(cfgBean.hatchRateKey),
            "-t", getParam(cfgBean.timeToRunKey),
            "--no-web", "--only-summary", "--csv=build_%s".format(build.buildNumber)
        )

        return SimpleProgramCommandLine(
                runnerContext, getParam(cfgBean.toolPathKey, "locust").trim(), arguments
        )
    }

    override fun afterProcessFinished() {
        super.afterProcessFinished()

        val report = workingDirectory.resolve("build_%s_requests.csv".format(build.buildNumber))

        if (report.exists()) {
            val reader = ReversedLinesFileReader(report, Charsets.UTF_8)
            val summary = reader.readLine().split(",")

            logStatistics(summary)
        }

    }

    override fun getRunResult(exitCode: Int): BuildFinishedStatus {
        val failThreshold = getParam(cfgBean.failThresholdKey).toDoubleOrNull()

        if (failThreshold != null && locustStats.getOrDefault("failures", 0.0) > failThreshold) {
            logger.buildFailureDescription("[Locust] Filed requests: %.2f".format(locustStats["failures"]))
            return BuildFinishedStatus.FINISHED_WITH_PROBLEMS
        }

        val medianThreshold = getParam(cfgBean.medianThresholdKey).toDoubleOrNull()

        if (medianThreshold != null && locustStats.getOrDefault("median_time", 0.0) > medianThreshold) {
            logger.buildFailureDescription("[Locust] Median time: %.2f".format(locustStats["median_time"]))
            return BuildFinishedStatus.FINISHED_WITH_PROBLEMS
        }

        val maxThreshold = getParam(cfgBean.maxThresholdKey).toDoubleOrNull()

        if (maxThreshold != null && locustStats.getOrDefault("max_time", 0.0) > maxThreshold) {
            logger.buildFailureDescription("[Locust] Max time: %.2f".format(locustStats["max_time"]))
            return BuildFinishedStatus.FINISHED_WITH_PROBLEMS
        }

        val minThreshold = getParam(cfgBean.minThresholdKey).toDoubleOrNull()

        if (minThreshold != null && locustStats.getOrDefault("min_time", 0.0) > minThreshold) {
            logger.buildFailureDescription("[Locust] Min time: %.2f".format(locustStats["min_time"]))
            return BuildFinishedStatus.FINISHED_WITH_PROBLEMS
        }

        return BuildFinishedStatus.FINISHED_SUCCESS
    }

    fun getParam(param: String, default: String = ""): String = runnerParameters.getOrDefault(param, default).toString()

    private fun logStatistics(summary: List<String>, prefix: String = "locustio") {
        val keys = listOf(
                "method",
                "name",
                "requests",
                "failures",
                "median_time",
                "average_time",
                "min_time",
                "max_time",
                "average_content_size",
                "rps"
        )

        for (i in 2 until keys.size) {
            locustStats[keys[i]] = summary[i].toDouble()
            logger.message(
                    "##teamcity[buildStatisticValue key='%s_%s' value='%s']".format(prefix, keys[i], summary[i])
            )
        }
    }
}