<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <style>
        .post {
            width: 1000px;
            height: 100px;
            display: block;
            border-radius: 10px;
            border: 2px solid black;
            margin: 20px;
            text-align: center;
        }
        .hidden{
            display: none;
        }

        .float{
            float: right;
        }
    </style>
</head>
<body>
<button id="load">Load</button>
<div id="main">
    <div class="post"><h6>Hello</h6></div>
    <div class="post"><h6>Hello</h6></div>
    <div class="post"><h6>Hello</h6></div>
    <div class="post"><h6>Hello</h6></div>
    <div class="post"><h6>Hello</h6></div>
    <div class="post"><h6>Hello</h6></div>
    <div class="post"><h6>Hello</h6></div>
    <div class="post"><h6>Hello</h6></div>
</div>
<div id="loading" class="hidden float">
    <img src="loading/FmYeL3j.gif" width="120px" height="90px">
</div>
<script src="js/jquery-3.5.0.js"></script>
<script src="js/ajax2.js"></script>
</body>
</html>