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

import jetbrains.buildServer.serverSide.PropertiesProcessor
import jetbrains.buildServer.serverSide.RunType
import jetbrains.buildServer.web.openapi.PluginDescriptor

class LocustRunType(descriptor: PluginDescriptor) : RunType() {
    private var myDescriptor: PluginDescriptor = descriptor

    override fun getType(): String  = "LocustIO"
    override fun getDisplayName(): String = "Locust.io"
    override fun getDescription(): String = "Load test runner for locust.io"
    override fun getEditRunnerParamsJspFilePath(): String = myDescriptor.getPluginResourcesPath("runner.edit.jsp")
    override fun getViewRunnerParamsJspFilePath(): String = myDescriptor.getPluginResourcesPath("runner.view.jsp")

    override fun getRunnerPropertiesProcessor(): PropertiesProcessor {
        return PropertiesProcessor { arrayListOf() }
    }

    override fun getDefaultRunnerProperties(): MutableMap<String, String>? = mutableMapOf()
}