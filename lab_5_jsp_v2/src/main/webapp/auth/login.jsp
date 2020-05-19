<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Log in</title>
    <link rel="stylesheet" href="../css/styles.css">
</head>
<body>
<h3 align="center">Log in</h3>
<form id="login-form">
    <input type="hidden" name="type" value="login">
    <label for="login" action="/auth" method="post">Login: </label>
        <input type="text" id="login" name="login"/>
    <br/>
    <label for="password">Password: </label>
        <input type="password" id="password" name="password"/>
    <br/>
    <input type="submit" value="Login"/>
</form>

</body>
</html>