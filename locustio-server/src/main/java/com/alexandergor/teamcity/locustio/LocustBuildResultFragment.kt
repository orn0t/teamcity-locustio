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

import jetbrains.buildServer.serverSide.SBuild
import jetbrains.buildServer.serverSide.SBuildServer
import jetbrains.buildServer.web.openapi.*
import javax.servlet.http.HttpServletRequest

class LocustBuildResultFragment(
        server: SBuildServer,
        manager: WebControllerManager,
        descriptor: PluginDescriptor
): BuildInfoFragmentTab (
        server, manager, "LocustIO", descriptor.getPluginResourcesPath("build.result.fragment.jsp")
) {
    private val sumBean = LocustSummaryBean()

    init {
        placeId = PlaceId.BUILD_RESULTS_FRAGMENT
        register()
    }

    override fun isAvailable(request: HttpServletRequest): Boolean {
        return getBuild(request)?.getStatisticValue(sumBean.requestsKey) != null
    }

    override fun fillModel(model: MutableMap<String, Any>, request: HttpServletRequest, build: SBuild?) {
        if (build != null) {
            model["currentStats"] = build.statisticValues

            model["prevStats"] = build.previousFinished!!.statisticValues

            model["locustStats"] = sumBean.getStatsDiff(build.statisticValues, build.previousFinished?.statisticValues)
        }
    }
}