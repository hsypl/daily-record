<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<script type="application/javascript">
    jQuery(document).ready(function(){
        $("button[id^='btn-save']").bind("click",
                function() {
                    var buttonId = $(this).attr("id");
                    var formId = "#"+buttonId.substring(9,buttonId.length);
                    $(formId).submit();
                });

    });
</script>
<div class="btn-group" style="padding: 5px">
    <c:if test="${showEdit}">
    <a class="btn btn-primary"  href="${editUrl}" role="button">新增</a>
    </c:if>
    <c:if test="${showSave}">
    <button type="button" id ="btn-save-${model}"  class="btn btn-success">保存</button>
    </c:if>
    <c:if test="${showCancel}">
    <a class="btn btn-primary"  href="${indexURL}" role="button">关闭</a>
    </c:if>
    <%--<button type="button" class="btn btn-primary">原始按钮</button>--%>
    <%--<button type="button" class="btn btn-primary">原始按钮</button>--%>
</div>