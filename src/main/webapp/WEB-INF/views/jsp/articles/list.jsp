<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

    <jsp:include page="../fragments/header.jsp" />

    <body>

        <div class="container">

            <c:if test="${not empty msg}">
                <div class="alert alert-${css} alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <strong>${msg}</strong>
                </div>
            </c:if>

            <h1>Articles</h1>

            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>#ID</th>
                        <th>Name</th>
                        <th>URL</th>
                    </tr>
                </thead>

                <c:forEach var="article" items="${articles}">
                    <spring:url value="/articles/${article.articleId}" var="articleUrl" />
                    <spring:url value="/articles/${article.articleId}/delete" var="deleteUrl" /> 
                    <spring:url value="/articles/${article.articleId}/update" var="updateUrl" />
                    <tr>
                        <td>
                            <c:out value="${article.articleId}" />
                        </td>
                        <td><a href=<c:out value="${articleUrl}" /> </a><c:out value="${article.articleName}" /></td>
                        <td><a href=<c:out value="${article.articleUrl}" /> </a><c:out value="${article.articleUrl}" /></td>

                        <td>
                            <button class="btn btn-info" onclick="location.href = '${articleUrl}'">View</button>
                            <button class="btn btn-primary" onclick="location.href = '${updateUrl}'">Edit</button>
                            <button class="btn btn-danger" onclick="this.disabled = true;post('${deleteUrl}')">Delete</button>
                        </td>
                    </tr>
                </c:forEach>
            </table>

        </div>

        <jsp:include page="../fragments/footer.jsp" />

    </body>
</html>