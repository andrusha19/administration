<!DOCTYPE html>
<html>
    <head>
        <title>Login page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <#if error??>
            ${error}
        </#if>
        <form action="${Request.RequestObject.getContextPath()}/login" method="post">
            Login:<br />
            <input type="text" name="username" /><br />
            Password:<br />
            <input type="password" name="password" /><br />
            <input type="submit" name="Sign in" value="Sign in" />
        </form>
        <a href="${Request.RequestObject.getContextPath()}/register">Register</a>
    </body>
</html>
