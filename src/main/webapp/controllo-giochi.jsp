<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <title>Pagina Admin</title>

    <link rel="stylesheet" href="css/controllo-giochi.css">

    <script src="js/cookie.js"></script>
    <script>

        function controlCookie(){
            let c = getCookie();
            if(c != "admin" || c == "JSESSIONID"){

                document.getElementById("body").innerHTML = "NON SEI AMMINISTRATORE<br>" +
                    "                                        <a href='index.html'> Torna alla home</a>";

            }
        }

    </script>
</head>
<body onload="controlCookie()" id="body">

<h1>Gestione Giochi:<br></h1>
<a href="admin-home.html">
    <input class="button" type="button" name="button" value="Aggiungi Giochi">
</a><br><br>
    <c:forEach items = "${applicationScope['giochi']}" var="item">
        <!-- Contenitore -->
        <div class="contenitore">
            <h1 class="">${item.nomeGioco}</h1> <p>${item.genereGioco}; ${item.prezzoGioco}; ${item.descrizione}; ${item.dataGioco}; ${item.console}; ${item.softwareHouse}; ${item.url}; ${item.prezzoScontato};</p> <br>
                <form action="RimuoviGioco">
                    <input type="hidden" name ="titolo" value="${item.nomeGioco}">
                    <input class="button" type="submit" value="Rimuovi gioco" name="rimuovi">
                </form>
                <form action="Dati">
                    <input type="hidden" name="titolo" value="${item.nomeGioco}">
                    <input class="button" type="submit" value="Modifica Gioco" name="modifica">
                </form>
        </div><br><br>




    </c:forEach>



</body>
</html>
