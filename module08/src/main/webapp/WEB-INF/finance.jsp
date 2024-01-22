<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<html>
<head>
	<title>Калькулятор</title>
</head>
<body>
    <h1>Калькулятор доходности вклада</h1>
    <form action="finance" method="post">
    Сумма на момент открытия: <input type="text" name="sum"/><br/>
    Процентная ставка: <input type="text" name="percentage"/><br/>
    Количество лет: <input type="text" name="years"/><br/>

    <input type="submit" value="Просчитать"/>
    </form>

</body>
</html>