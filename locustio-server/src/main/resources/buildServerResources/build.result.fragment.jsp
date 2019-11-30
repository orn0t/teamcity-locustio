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

<%@ page contentType="text/html;charset=UTF-8" language="java" session="true"
  %><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"
  %><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"
  %><%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"
  %><%@ taglib prefix="bs" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="util" uri="/WEB-INF/functions/util" %>

<style>.green { color: #1c8c32; } .red {color: #c22731; } .locustStatsTable { width: 100%; text-align: left; }</style>
<bs:_collapsibleBlock title="Locust load performance" id="locustSummary" contentClass="locustSummary">
    <div class="locustStats">
        <table class="locustStatsTable">
            <thead>
                <tr>
                    <th># reqs</th>
                    <th># fails</th>
                    <th>Avg</th>
                    <th>Min</th>
                    <th>Max</th>
                    <th>Median</th>
                    <th>req/s</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                <c:forEach var="col" items="${locustStats}">
                    <td>
                        ${col.mean}
                        <span class="${col.color}">
                            (<c:if test="${col.diff>'0'}">+</c:if><fmt:formatNumber type="number" value="${col.diff}" maxFractionDigits = "2" />%)
                        </span>
                    </td>
                </c:forEach>
                </tr>
            </tbody>
        </table>
    </div>
</bs:_collapsibleBlock>
