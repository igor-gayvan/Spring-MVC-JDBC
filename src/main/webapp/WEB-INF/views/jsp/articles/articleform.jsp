<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

    <jsp:include page="../fragments/header.jsp" />

    <div class="container">

        <c:choose>
            <c:when test="${articleForm['new']}">
                <h1>Add article</h1>
            </c:when>
            <c:otherwise>
                <h1>Update article</h1>
            </c:otherwise>
        </c:choose>
        <br />

        <spring:url value="/articles" var="articleActionUrl" />

        <form:form class="form-horizontal" method="post" modelAttribute="articleForm" action="${articleActionUrl}">

            <form:hidden path="articleId" />

            <spring:bind path="articleName">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="col-sm-2 control-label">Name</label>
                    <div class="col-sm-10">
                        <form:input path="articleName" type="text" class="form-control " id="articleName" placeholder="Name" />
                        <form:errors path="articleName" class="control-label" />
                    </div>
                </div>
            </spring:bind>

            <spring:bind path="articleUrl">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="col-sm-2 control-label">URL</label>
                    <div class="col-sm-10">
                        <form:input path="articleUrl" class="form-control" id="articleUrl" placeholder="URL" />
                        <form:errors path="articleUrl" class="control-label" />
                    </div>
                </div>
            </spring:bind>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">

                    <c:choose>
                        <c:when test="${articleForm['new']}">
                            <button type="submit" class="btn-lg btn-primary pull-right">Add</button>
                        </c:when>
                        <c:otherwise>
                            <button type="submit" class="btn-lg btn-primary pull-right">Update</button>
                        </c:otherwise>
                    </c:choose>

                </div>

            </div>

        </form:form>

    </div>

    <jsp:include page="../fragments/footer.jsp" />

</body>
</html>