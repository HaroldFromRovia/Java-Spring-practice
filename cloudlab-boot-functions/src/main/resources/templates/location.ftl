<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <title>Locations</title>
</head>
<body>

<table class="table table-bordered ">
    <thead class="thead-dark">
    <tr>
        <th scope="col">Num</th>
        <th scope="col">Address</th>
    </tr>
    </thead>
    <tbody>
    <#list addresses>

        <#items as address>
            <tr>

                <th>${address?counter}</th>
                <td>${address.value}</td>

            </tr>
        </#items>
    </#list>
    </tbody>

</table>
</body>
</html>