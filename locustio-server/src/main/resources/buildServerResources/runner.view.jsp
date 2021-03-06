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
<jsp:useBean id="bean" class="com.alexandergor.teamcity.locustio.LocustBean"/>

<div class="parameter">
  Locust file: <props:displayValue name="${bean.locustFile}" emptyValue="<empty>"/>
</div>

<div class="parameter">
  Target server URL: <props:displayValue name="${bean.targetUrl}" emptyValue="<empty>"/>
</div>

<div class="parameter">
  Number of users to simulate: <props:displayValue name="${bean.usersNumber}" emptyValue="<empty>"/>
</div>

<div class="parameter">
  Hatch rate: <props:displayValue name="${bean.hatchRate}" emptyValue="<empty>"/>
</div>

<div class="parameter">
  Time to run: <props:displayValue name="${bean.timeToRun}" emptyValue="<empty>"/>
</div>

<div class="parameter">
  Locust executable: <props:displayValue name="${bean.toolPath}" emptyValue="<default>"/>
</div>

<props:viewWorkingDirectory/>

<div class="parameter">
  Additional command line arguments: <props:displayValue name="${bean.commandLineParameter}" showInPopup="${true}" emptyValue="<empty>"/>
</div>