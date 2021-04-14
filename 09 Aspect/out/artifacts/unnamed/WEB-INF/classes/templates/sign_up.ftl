<#import "lib/html.ftl" as H>

<@H.html>
    Sign up <br>
    <form action="/signUp" method="post">
        <input placeholder="email" name="email" type="email" required><br>
        <input placeholder="name" name="name" type="text"><br>
        <button type="submit">Sign up</button>

    </form>
</@H.html>