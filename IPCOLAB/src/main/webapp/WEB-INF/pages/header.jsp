<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<div class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container-fluid"> 
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span> 
            </button>
            <!--  <a target="_blank" href="#" class="navbar-brand">IP CO-LAB</a>
            <a target="_blank" href="userProfile.action" class="navbar-brand">IP CO-LAB</a>-->
            <a href="userProfile.action" class="navbar-brand">IP CO-LAB</a>
            
        </div>
        <div class="collapse navbar-collapse navbar-right">
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <span class="glyphicon glyphicon-user"></span> 
                        <c:if test="${user ne null}">
                        <strong>${user.firstName}</strong>
                        </c:if>
                        <c:if test="${admin ne null}">
                        <strong>${admin.firstName}</strong>
                        </c:if>
                        <span class="glyphicon glyphicon-chevron-down"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <div class="navbar-login">
                                <div class="row">
                                    <div class="col-lg-4">
                                        <p class="text-center">
                                            <span class="glyphicon glyphicon-user icon-size"></span>
                                        </p>
                                    </div>
                                    <div class="col-lg-8">
                                    	<c:if test="${user ne null}">
                                        <p class="text-left"><strong>${user.firstName} ${user.lastName}</strong></p>
                                        <p class="text-left small">${user.email}</p>
                                        </c:if>
                                        <c:if test="${admin ne null }">
                                        <p class="text-left"><strong>${admin.firstName} ${admin.lastName}</strong></p>
                                        <p class="text-left small">${admin.email}</p>
                                        </c:if>
                                        <!-- <p class="text-left">
                                            <a href="#" class="btn btn-primary btn-block btn-sm header-btn">Edit Profile</a>
                                        </p> -->
                                    </div>
                                </div>
                            </div>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <div class="navbar-login navbar-login-session">
                                <div class="row">
                                    <div class="col-lg-12">
                                        <p>
                                            <a href="logout.action" class="btn btn-danger btn-block header-btn">Logout</a>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</div>