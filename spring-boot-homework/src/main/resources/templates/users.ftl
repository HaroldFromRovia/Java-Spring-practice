<#import "lib/html.ftl" as H>

<@H.html>
    <@H.head "Users">
        <@H.body>
            <ul class="list-group">
                <#list users as user>
                    <li>${user.username}</li>
                </#list>
            </ul>
            <@timeView time=date></@timeView>
        </@H.body>
    </@H.head>
</@H.html>