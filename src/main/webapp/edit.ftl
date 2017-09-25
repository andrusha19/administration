<!DOCTYPE html>

<html>
    <head>
        <title>Edit user</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <#if error??>
            ${error}<br />
        </#if>
        <b>You want to edit user: ${edituser.getName()}</b> &nbsp;&nbsp;&nbsp;&nbsp;<a href = "${Request.RequestObject.getContextPath()}/logout">Logout</a> <br /><br />
        <form action="${Request.RequestObject.getContextPath()}/manage/${edituser.getName()}" method="post">
            <input type="password" name="password" size="20" /> Password<br />
            <input type="text" name="email" size="20" value="${edituser.getEmail()}" /> E-mail<br />
            <#if user.getName() != edituser.getName()>
                <select name="role" size="1">
                    <#if edituser.getRole() = "user">
                        <option selected="selected" value="user">user</option>
                        <option value="admin">admin</option>
                    <#else>
                        <option value="user">user</option>
                        <option selected="selected" value="admin">admin</option>
                    </#if>
            </select><br />
            </#if>  
            <input type="submit" name="Send" value="OK" />
            <input type="submit" name="Cancel" value="Cancel" />
            <#if user.getName() != edituser.getName()>
                <input type="submit" name="Delete" value="Delete user" />
            </#if>
        </form>
    </body>
</html>
