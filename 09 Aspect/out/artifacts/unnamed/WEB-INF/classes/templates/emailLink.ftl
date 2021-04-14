<#import "lib/html.ftl" as H>

<@H.html>
    <h1>Your file is available here <a href="${fileLink}"></a></h1>
    ${fileLink}
</@H.html>