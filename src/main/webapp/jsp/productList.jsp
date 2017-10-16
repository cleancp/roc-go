<%@ taglib prefix="S" uri="/struts-tags" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
    <base href="<%=basePath%>" >
    <title>采美365网-中国美业全方位线上交易服务互动平台，做美业，上采美</title>
    <meta charset="UTF-8">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta http-equiv="keywords" content="采美、易耗品商城、光电美容仪器项目、光电中心、皮肤管理中心、光电转型、美容院转型、光电美容">
    <meta http-equiv="description" content="采美365网——中国美业互联网共享经济平台，中国美业较大的光电美容干货信息平台，提供美容专业线客装产品、美容院消耗品、专业线院装产品、光电美容仪器、光电美容项目交易，同时提供采美公益大讲堂、光电美容干货、光电美容资讯、美业动态等行业信息。采集梦想，美启未来。">
    <link rel="shortcut icon" href="public/3.0/img/favicon.ico"
          type="image/x-icon">
    <link rel="stylesheet" href="public/3.0/css/common/common.css" >
    <link rel="stylesheet" href="public/3.0/css/bying/bying.css" type="text/css"></link>
    <script src="public/3.0/js/lib/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="/html/js/global.js"></script>
    <script src="public/3.0/js/common/common.js"></script>

    <!--[if IE 6]>
    <script type="text/javascript" src="public/3.0/js/lib/DD_belatedPNG-min.js" ></script>
    <script type="text/javascript">
        DD_belatedPNG.fix('*');
    </script>
    <![endif]-->

    <!--[if lt IE 9]>
    <script type="text/javascript" src="public/3.0/js/lib/html5shiv.js"></script>
    <![endif]-->
    <script>
        $('#CM____search_input').val('${keyWord}');
    </script>
</head>

<body class="home">
<!-- .header -->
<div class="header clearfix">
    <%--<%@ include file="../common/cm_header_top.jsp" %>--%>
    <%--<%@ include file="../common/cm_header_main_1.jsp" %>--%>
</div>
<!-- / .header -->
<!-- .nav -->
<%--<%@ include file="../common/cm_nav.jsp" %>--%>
<!-- /.nav -->
<div class="content">
    <div class="sort">
        <ul>
            <li >排序 :</li>
            <li onclick="javascript:sortType(1);">综合</li>
            <s:if test="sortType==5">
                <li class="li-bac up" onclick="javascript:sortType(6);"><span>销量</span></li>
            </s:if>
            <s:elseif test="sortType==6">
                <li class="li-bac down" onclick="javascript:sortType(5);"><span>销量</span></li>
            </s:elseif>
            <s:else>
                <li onclick="javascript:sortType(5);"><span>销量</span></li>
            </s:else>
            <s:if test="sortType==7">
                <li class="li-bac up" onclick="javascript:sortType(8);"><span>人气</span></li>
            </s:if>
            <s:elseif test="sortType==8">
                <li class="li-bac down" onclick="javascript:sortType(7);"><span>人气</span></li>
            </s:elseif>
            <s:else>
                <li onclick="javascript:sortType(7);"><span>人气</span></li>
            </s:else>
            <s:if test="(#session.agencyuser.registerUserTypeID == 3 && #session.agencyuser.clubStatus == 90) || #session.agencyuser.registerUserTypeID == 33">
                <s:if test="sortType==3">
                    <li class="li-bac up" onclick="javascript:sortType(4);"><span>价格</span></li>
                </s:if>
                <s:elseif test="sortType==4">
                    <li class="li-bac down" onclick="javascript:sortType(3);"><span>价格</span></li>
                </s:elseif>
                <s:else>
                    <li onclick="javascript:sortType(3);"><span>价格</span></li>
                </s:else>
            </s:if>
            <li class="li-page">
                <span class="orange"><s:property value="page.index"/></span>/<s:property value="page.totalPage"/>
                <s:if test="page.hasNextPage">
                    <a class="after-page" href="javascript:jumpToPage(<s:property value='page.index+1'/>)">&gt;</a>
                </s:if>
                <s:if test="page.hasPreviousPage">
                    <a class="before-page" href="javascript:jumpToPage(<s:property value='page.index-1'/>)">&lt;</a>
                </s:if>
            </li>
        </ul>
    </div>
    <div class="search-pro" >
        <s:iterator var="prod" value="page.results" status="status">
            <div class="item">
                <s:if test="actFlag==1">
                    <div class="biaoshi" >
                        <s:set var="imgURL" value="%{getActImageUrl(productType)}"></s:set>
                        <img src="<s:property value="imgURL"/>" />
                    </div>
                </s:if>
                <a href="/product-<s:property value="productID"/>.html" class="product-pic" target="_blank">
                    <img src='<s:property value="mainImage"/>' alt="<s:property value="name"/>" >
                </a>
                <div class="product-txt">
                    <input type="hidden" value="<s:property value="shopName"/>">
                    <!-- 包含文字 -->
                    <s:if test="priceInfo != null && priceInfo != ''">
                        <div class="price-row clearfix price-word">
                            <span class="word-link"><s:property value="%{priceInfo}" /></span>
                        </div>
                    </s:if>
                    <s:else>
                        <!-- 显示价格 如果参加活动-->
                        <div class="price-row clearfix">
                            <s:if test="actFlag == 1">
                                <!-- 原价（有删除线） -->
                                <del class="fr">
                                    ￥<s:property value="%{formatDouble(originalPrice)}"/>
                                </del>
                            </s:if>
                            <!-- 现价 -->
                            <span class="price fl">
							<i class="rmb">￥</i>
							<s:property value="%{formatDouble(price)}" />
					    </span>
                        </div>
                    </s:else>
                    <p class="product-name">
                        <a href="/product-<s:property value="productID"/>.html" title=""  target="_blank"><s:property value="name" escape="false"/></a>
                    </p>
                    <p class="company-name">
                        <a target="_blank"  href="/supplier/prolist-<s:property value="shopID"/>.html"><s:property value="shopName" /></a>
                    </p>
                    <p class="praise-row">
                        <span class="trans-records fr">已成交<s:property value="sellNumber" />笔</span>
                        <span class="icon-praise praised"></span> <s:property value="favorites"/>
                    </p>
                </div>
            </div>
        </s:iterator>
    </div>
</div>
<%--<%@ include file="../common/cm_pagination.jsp" %>--%>
<%--<%@ include file="../common/cm_footer.jsp" %>--%>
<script src="public/3.0/js/jelon/main.js"></script>
<script >
    var jumpBaseURL = encodeURI('/product/search/list/<s:property value="bigTypeID"/>-<s:property value="smallTypeID"/>-<s:property value="tinyTypeID"/>-<s:property value="sortType"/>-<s:property value="shopID"/>.html?wd=<s:property value="wd"/>&index=');
    function jumpToPage(index){
        location.href = jumpBaseURL + index;
    }
    function sortType(type){
        var url = '/product/search/list/<s:property value="bigTypeID"/>-<s:property value="smallTypeID"/>-<s:property value="tinyTypeID"/>-'+type+'-<s:property value="shopID"/>.html?wd=<s:property value="wd"/>&index=1';
        location.href = encodeURI(url);
    }
    var $tt = $('.content').height()+$('.nav').height()+$('.header').height()+$('.bar').height();
    if($tt<=289){
        $(".footer").css('margin-top',$(document).height()-$tt-21-$(".footer").height());
    }
</script>
</body>
</html>
