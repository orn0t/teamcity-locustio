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

import jetbrains.buildServer.agent.BuildAgentConfiguration
import jetbrains.buildServer.agent.AgentBuildRunnerInfo
import org.jetbrains.annotations.NotNull

import jetbrains.buildServer.agent.runner.CommandLineBuildService
import jetbrains.buildServer.agent.runner.CommandLineBuildServiceFactory

class LocustServiceFactory: CommandLineBuildServiceFactory {
    override fun getBuildRunnerInfo(): AgentBuildRunnerInfo {
        return object : AgentBuildRunnerInfo {
            override fun getType(): String = "LocustIO"
            override fun canRun(@NotNull agentConfiguration: BuildAgentConfiguration): Boolean = true
        }
    }

    override fun createService(): CommandLineBuildService = LocustService()
}