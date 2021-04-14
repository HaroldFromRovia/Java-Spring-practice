<#import "lib/html.ftl" as H>

<@H.html>
    <#import "lib/html.ftl" as H>

    <@H.html>
        Login <br>
        <form action="/signIn" method="post">
            <input placeholder="email" name="email" type="email" required><br>
            <input placeholder="password" name="password" type="password" required><br>
            <button type="submit">Login</button>

        </form>
    </@H.html>
</@H.html>