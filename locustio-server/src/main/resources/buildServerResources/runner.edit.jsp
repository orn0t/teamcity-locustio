<%--
  ~ Copyright 2019 Alexander Gor
  ~
  ~ Licensed under the Apache License, Version 2.0 (the "License");
  ~ you may not use this file except in compliance with the License.
  ~ You may obtain a copy of the License at
  ~
  ~ http://www.apache.org/licenses/LICENSE-2.0
  ~
  ~ Unless required by applicable law or agreed to in writing, software
  ~ distributed under the License is distributed on an "AS IS" BASIS,
  ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  ~ See the License for the specific language governing permissions and
  ~ limitations under the License.
  --%>

<%@ include file="/include.jsp"%>
<%@ taglib prefix="forms" tagdir="/WEB-INF/tags/forms" %>
<%@ taglib prefix="props" tagdir="/WEB-INF/tags/props" %>
<%@ taglib prefix="l" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="bean" class="com.alexandergor.teamcity.locustio.LocustConfigBean"/>

<tr>
    <th>Locust file:</th>
    <td>
        <props:textProperty name="${bean.locustFileKey}" className="longField"/>
        <span class="smallNote">...</span>
        <span class="error" id="error_${bean.locustFileKey}"></span>
    </td>
</tr>

<tr>
    <th>Target server URL:</th>
    <td>
        <props:textProperty name="${bean.targetUrlKey}" className="longField"/>
        <span class="smallNote">...</span>
        <span class="error" id="error_${bean.targetUrlKey}"></span>
    </td>
</tr>

<tr>
    <th>Number of users to simulate:</th>
    <td>
        <props:textProperty name="${bean.usersNumberKey}" className="longField"/>
        <span class="smallNote">...</span>
        <span class="error" id="error_${bean.usersNumberKey}"></span>
    </td>
</tr>

<tr>
    <th>Hatch rate:</th>
    <td>
        <props:textProperty name="${bean.hatchRateKey}" className="longField"/>
        <span class="smallNote">users spawned/second</span>
        <span class="error" id="error_${bean.hatchRateKey}"></span>
    </td>
</tr>

<tr>
    <th>Time to run:</th>
    <td>
        <props:textProperty name="${bean.timeToRunKey}" className="longField"/>
        <span class="smallNote">in seconds</span>
         <span class="error" id="error_${bean.timeToRunKey}"></span>
    </td>
</tr>

<l:settingsGroup title="Thresholds">
<tr>
  <th><label for="${bean.failThresholdKey}">Maximum failed requests:</label></th>
  <td>
    <props:textProperty name="${bean.failThresholdKey}" className="longField"/>
    <span class="smallNote">Amount of failed requests to consider test as failed</span>
    <span class="error" id="error_${bean.failThresholdKey}"></span>
  </td>
</tr>
<tr>
  <th><label for="${bean.medianThresholdKey}">Median request time:</label></th>
  <td>
    <props:textProperty name="${bean.medianThresholdKey}" className="longField"/>
    <span class="smallNote">The top of accepted requests median time in ms</span>
    <span class="error" id="error_${bean.medianThresholdKey}"></span>
  </td>
</tr>
<tr>
  <th><label for="${bean.minThresholdKey}">Min request time:</label></th>
  <td>
    <props:textProperty name="${bean.minThresholdKey}" className="longField"/>
    <span class="smallNote">The top of accepted requests minimal time in ms</span>
    <span class="error" id="error_${bean.minThresholdKey}"></span>
  </td>
</tr>
<tr>
  <th><label for="${bean.maxThresholdKey}">Max request time:</label></th>
  <td>
    <props:textProperty name="${bean.maxThresholdKey}" className="longField"/>
    <span class="smallNote">Maximum of requests time in ms</span>
    <span class="error" id="error_${bean.maxThresholdKey}"></span>
  </td>
</tr>
</l:settingsGroup>

<l:settingsGroup title="Execution">
<tr>
  <th><label for="${bean.toolPathKey}">Path to Locust:</label></th>
  <td>
    <props:textProperty name="${bean.toolPathKey}" className="longField"/>
    <span class="smallNote">Specify path to locust executable. Leave blank to use agent-installed one</span>
    <span class="error" id="error_${bean.toolPathKey}"></span>
  </td>
</tr>

<forms:workingDirectory/>

<tr>
  <th><label for="${bean.commandLineParameterKey}">Additional command line parameters:</label></th>
  <td>
    <props:multilineProperty name="${bean.commandLineParameterKey}"  cols="58" linkTitle="Expand" rows="5"/>
    <span class="smallNote">Enter additional command line parameters to node.js. Put each parameter on a new line</span>
    <span class="error" id="error_${bean.commandLineParameterKey}"></span>
  </td>
</tr>
</l:settingsGroup>
