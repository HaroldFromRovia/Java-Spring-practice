<#import "lib/html.ftl" as H>

<@H.html>
    <form method="post" action="/files" enctype="multipart/form-data">
        <p><input type="file" id="file" name ="file">
        <input type="submit" value="Save file to storage">
    </form>
</@H.html>