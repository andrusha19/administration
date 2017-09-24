<!DOCTYPE html>
<html>
    <head>
        <title>Browse users</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <#if error??>
            ${error}<br />
        </#if>
        <#if user.getRole()="admin">
            <#assign role=true>
        <#else>
            <#assign role=false>
        </#if>
        <b>Hello, <#if role>dear administrator</#if> ${user.getName()}.</b>  &nbsp;&nbsp;&nbsp;&nbsp;<a href = "${Request.RequestObject.getContextPath()}/logout">Logout</a> <br />
        There are such users in database:<br />
        <table border="1">
            <tr>
                <td>Name</td>
                <td>Email</td>
                <td>Creation date</td>
                <#if role>
                    <td>Last Modify</td>
                    <td>Role</td>
                </#if>
            </tr>
            <#if users??>
                <#list users as next>
                    <tr>
                        <td><#if role><a href = "${Request.RequestObject.getContextPath()}/manage/${next.getName()}"></#if>${next.getName()}<#if role></a></#if></td>
                        <td>${next.getEmail()}</td>
                        <td>${next.getRegistration_time()?datetime}</td>
                        <#if role>
                            <td>${next.getLast_edit_time()?datetime}</td>
                            <td>${next.getRole()}</td>
                        </#if>
                    </tr>
                </#list>
            </#if>
        </table>
    </body>
</html>
