<link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!-- Include the above in your HEAD tag -->

<div class="container">
    <form class="form-horizontal" role="form">
        <h2>Registration Form</h2>
        <div class="form-group">
            <label for="Name" class="col-sm-4 control-label">First Name</label>
            <div class="col-sm-5">
                <input type="text" name="Nombre" placeholder="First Name" class="form-control" autofocus>
            </div>
        </div>
        <div class="form-group">
            <label for="Apellido" class="col-sm-4 control-label">Last Name</label>
            <div class="col-sm-5">
                <input type="text" name="Apellido" placeholder="Last Name" class="form-control" autofocus>
                <span class="help-block">Only your First Last name eg: Edward Pimentel</span>
            </div>
        </div>
        <div class="form-group">
            <label for="username" class="col-sm-4 control-label">Username</label>
            <div class="col-sm-5">
                <input type="text" name="username" placeholder="User" class="form-control">
            </div>
        </div>
        <div class="form-group">
            <label for="password" class="col-sm-4 control-label">Password</label>
            <div class="col-sm-5">
                <input type="password" name="password" placeholder="Password" class="form-control">
            </div>
        </div>
        <div class="form-group">
            <label for="birthDate" class="col-sm-4 control-label">Date of Birth</label>
            <div class="col-sm-5">
                <input type="date" name="birthDate" class="form-control">
            </div>
        </div>
        <div class="form-group">
            <label for="country" class="col-sm-4 control-label">Country</label>
            <div class="col-sm-5">
                <select name="lugarNac" class="form-control">
                    <option>Republica Dominicana</option>
                    <option>Estados Unidos</option>
                    <option>Cuba</option>
                </select>
            </div>
        </div> <!-- /.form-group -->
        <div class="form-group">
            <div class="col-sm-3 col-sm-offset-5">
                <button type="submit" class="btn btn-primary btn-block">Register</button>
            </div>
        </div>
    </form> <!-- /form -->
</div> <!-- ./container -->