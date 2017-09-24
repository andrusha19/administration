<!DOCTYPE html>
<html>
    <head>
        <title>Register page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <#if error??>
            ${error}<br />
        </#if>
        <b>Please, input information:</b>
        <form action="${Request.RequestObject.getContextPath()}/register" method="post">
            <input type="text" name="username" size="20" /> Login<br />
            <input type="password" name="password" size="20" /> Password<br />
            <input type="text" name="email" size="20" /> E-mail<br />
            <input type="submit" value="Send" />
        </form>
    </body>
</html>
