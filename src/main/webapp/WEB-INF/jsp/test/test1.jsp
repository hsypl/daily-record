<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Bootstrap 101 Template</title>

    <!-- Bootstrap -->
    <link href="/media/css/bootstrap.min.css" rel="stylesheet">

    <!-- 引入这些文件至 <head> 中 -->
    <link rel="stylesheet" href="/media/css/responsive-nav.css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="/media/js/bootstrap.min.js"></script>
    <script src="/media/js/responsive-nav.js"></script>
    <script>
        var navigation = responsiveNav("#nav", { // Selector: The ID of the wrapper
            animate: true, // Boolean: 是否启动CSS过渡效果（transitions）， true 或 false
            transition: 400, // Integer: 过渡效果的执行速度，以毫秒（millisecond）为单位
            label: "Menu", // String: Label for the navigation toggle
            insert: "after", // String: Insert the toggle before or after the navigation
            customToggle: "", // Selector: Specify the ID of a custom toggle
            openPos: "relative", // String: Position of the opened nav, relative or static
            jsClass: "js", // String: 'JS enabled' class which is added to <html> el
            debug: false, // Boolean: Log debug messages to console, true 或 false
            init: function(){}, // Function: Init callback
            open: function(){}, // Function: Open callback
            close: function(){} // Function: Close callback
        });
    </script>
    <![endif]-->
</head>
<body>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">日常</a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">阅读</a></li>
                <li><a href="#">任务</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        虚拟货币
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Ico join</a></li>
                        <li class="divider"></li>
                        <li><a href="#">currency</a></li>
                        <li class="divider"></li>
                        <li><a href="#">price</a></li>
                        <li class="divider"></li>
                        <li><a href="#">Ico href</a></li>
                        <li class="divider"></li>
                        <li><a href="#">另一个分离的链接</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
</body>
</html>