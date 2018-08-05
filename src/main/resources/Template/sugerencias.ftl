<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <title>Facebook Theme Demo</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <script src="js/jquery.js"></script>
    <link href="css/bootstrap.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <link href="css/facebook.css" rel="stylesheet">
</head>

<body>

<div class="wrapper">
    <div class="box">
        <div class="row row-offcanvas row-offcanvas-left">

            <!-- sidebar -->
            <div class="column col-sm-2 col-xs-1 sidebar-offcanvas" id="sidebar">

                <ul class="nav">
                    <li><a href="#" data-toggle="offcanvas" class="visible-xs text-center"><i class="glyphicon glyphicon-chevron-right"></i></a></li>
                </ul>

                <ul class="nav hidden-xs" id="lg-menu">
                    <li class="active"><a href="/timeline"><i class="glyphicon glyphicon-list-alt"></i> Timeline</a></li>
                    <li><a href="/albums"><i class="glyphicon glyphicon-picture"></i> Albums</a></li>
                    <li><a href="/friendlist"><i class="glyphicon glyphicon-list"></i> Friends</a></li>
                </ul>
                <ul class="list-unstyled hidden-xs" id="sidebar-footer">
                    <li>
                        <a href="http://usebootstrap.com/theme/facebook"><h3>Bootstrap</h3> <i class="glyphicon glyphicon-heart-empty"></i> Bootply</a>
                    </li>
                </ul>

                <!-- tiny only nav-->
                <ul class="nav visible-xs" id="xs-menu">
                    <li><a href="#featured" class="text-center"><i class="glyphicon glyphicon-list-alt"></i></a></li>
                    <li><a href="#stories" class="text-center"><i class="glyphicon glyphicon-list"></i></a></li>
                    <li><a href="#" class="text-center"><i class="glyphicon glyphicon-paperclip"></i></a></li>
                    <li><a href="#" class="text-center"><i class="glyphicon glyphicon-refresh"></i></a></li>
                </ul>

            </div>
            <!-- /sidebar -->

            <!-- main right col -->
            <div class="column col-sm-10 col-xs-11" id="main">

                <!-- top nav -->
                <div class="navbar navbar-blue navbar-static-top">
                    <div class="navbar-header">
                        <button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".navbar-collapse">
                            <span class="sr-only">Toggle</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a href="http://usebootstrap.com/theme/facebook" class="navbar-brand logo">b</a>
                    </div>
                    <nav class="collapse navbar-collapse" role="navigation">
                        <form class="navbar-form navbar-left">
                            <div class="input-group input-group-sm" style="max-width:360px;">
                                <input class="form-control" placeholder="Search" name="srch-term" id="srch-term" type="text">
                                <div class="input-group-btn">
                                    <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
                                </div>
                            </div>
                        </form>
                        <ul class="nav navbar-nav">
                            <li>
                                <a href="/home"><i class="glyphicon glyphicon-home"></i> Home</a>
                            </li>
                            <li>
                                <a href="/sugerencias" role="button" data-toggle="modal"><i class="glyphicon glyphicon-plus"></i> Sugerencia de amigos</a>
                            </li>
                            <li>
                                <a href="/location"><span class="badge">badge</span></a>
                            </li>
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <li class="dropdown">
                                <a href="/logout"><i class="glyphicon glyphicon-user"></i> Logout</a>
                                <!-- <ul class="dropdown-menu">
                                     <li><a href="/logout">Log Out</a></li>
                                 </ul>
                             </li>
                         </ul>-->
                    </nav>
                </div>
                <!-- /top nav -->

                <div class="padding">
                    <div class="full col-sm-9">

                        <!-- content -->
                        <div class="row">

                            <!-- main col left -->

                            <!-- main col right -->
                            <div class="col-sm-7 col-sm-offset-2">
                                <h3 align="center">${usuario.nombre} ${usuario.apellido}</h3>

                                <div class="panel panel-default">
                                    <div class="panel-heading"><a href="#" class="pull-right">View all</a> <h4>Posibles amigos</h4></div>
                                        <#list sugerAmigo as sugMa>
                                                <#if sugMa.id != usuario.id>
                                            <form action="/addFriend/${sugMa.id}" method="post">
                                                <div class="panel-body">
                                                    <li>${sugMa.nombre} ${sugMa.apellido} </li><button type="submit" class="btn btn-primary btn-sm">Agregar</button>
                                            </form>
                                            </#if>

                                            </#list>


                                    </div>

                                </div>

                                <!--<div class="panel panel-default">
                                 <div class="panel-thumbnail"><img src="assets/img/bg_4.jpg" class="img-responsive"></div>
                                 <div class="panel-body">
                                   <p class="lead">Social Good</p>
                                   <p>1,200 Followers, 83 Posts</p>

                                   <p>
                                     <img src="assets/img/photo.jpg" height="28px" width="28px">
                                     <img src="assets/img/photo.png" height="28px" width="28px">
                                     <img src="assets/img/photo_002.jpg" height="28px" width="28px">
                                   </p>
                                 </div>
                               </div>-->

                            </div>
                        </div><!--/row-->

                        <div class="row">
                            <div class="col-sm-6">
                                <a href="#">Twitter</a> <small class="text-muted">|</small> <a href="#">Facebook</a> <small class="text-muted">|</small> <a href="#">Google+</a>
                            </div>
                        </div>

                        <div class="row" id="footer">
                            <div class="col-sm-6">

                            </div>
                            <div class="col-sm-6">
                                <p>
                                    <a href="#" class="pull-right">ʃopyright 2018</a>
                                </p>
                            </div>
                        </div>
                    </div><!--/row-->

                    <hr>

                    <h4 class="text-center">
                        <a href="http://usebootstrap.com/theme/facebook" target="ext">Download this Template @Bootply</a>
                    </h4>

                    <hr>


                </div><!-- /col-9 -->
            </div><!-- /padding -->
        </div>
        <!-- /main -->

    </div>
</div>
</div>


<!--post modal-->



<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $('[data-toggle=offcanvas]').click(function() {
            $(this).toggleClass('visible-xs text-center');
            $(this).find('i').toggleClass('glyphicon-chevron-right glyphicon-chevron-left');
            $('.row-offcanvas').toggleClass('active');
            $('#lg-menu').toggleClass('hidden-xs').toggleClass('visible-xs');
            $('#xs-menu').toggleClass('visible-xs').toggleClass('hidden-xs');
            $('#btnShow').toggle();
        });
    });
</script>
</body></html>