<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <style>
        .hidden{
            display: none;
        }
    </style>
</head>
<body>
    <select id="brends">
        <#list brends as item>
            <option id="${item?index}">${item}</option>
        </#list>
    </select>
    <select id="models">

    </select>
    <div id="loading" class="hidden">
        <img src="loading/FmYeL3j.gif" width="120px" height="90px">
    </div>
<script src="js/jquery-3.5.0.js"></script>
<script src="js/ajax.js"></script>
</body>
</html>