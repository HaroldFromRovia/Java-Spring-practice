<#macro html>
    <!DOCTYPE html>
    <html lang="en">
    <#nested>
    </html>
</#macro>

<#macro head title>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>${title}</title>
        <link rel="stylesheet" href="/css/bootstrap.min.css">
        <link rel="stylesheet" href="/css/style.css">
        <script src="/js/bootstrap.bundle.min.js"></script>
        <script src="https://kit.fontawesome.com/6c024fbb60.js" crossorigin="anonymous"></script>
        <#nested>
    </head>
</#macro>

<#macro body onload="">
    <body onload="${onload}">
    <#nested>
    </body>
</#macro>
