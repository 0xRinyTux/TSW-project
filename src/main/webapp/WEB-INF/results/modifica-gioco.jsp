<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <title>Modifica Gioco</title>

    <link rel="stylesheet" href="css/modifica-gioco.css" type="text/css">

    <script src="https://unpkg.com/scrollreveal@4.0.0/dist/scrollreveal.min.js"></script>


</head>
<body>
<!-- Form modifica -->
<h2 class="h zoom">Modifica giochi: </h2><br><br>
<form class="form-modifica" action="ModificaGioco" method="post">

    <label for="titolo" class="lab">Titolo Gioco: </label><br>
    <input id="titolo" type="text" placeholder="inserisci titolo gioco" value="${giocoBean.nomeGioco}" name="titolo"><br>

    <label for="prezzo" class="lab">Prezzo Gioco: </label><br>
    <input id="prezzo" type="text" placeholder="inserisci prezzo del gioco" value="${giocoBean.prezzoGioco}" name="prezzo"><br>

    <label for="descrizione" class="lab">Descrizione Gioco: </label><br>
    <input id="descrizione" type="text" placeholder="inserisci descrizione del gioco" value="${giocoBean.descrizione}" name="descrizione"><br>

    <label for="anno">Anno Gioco: </label><br>
    <input id="anno" type="text" placeholder="inserisci data del gioco" value="${giocoBean.dataGioco}"  name="anno"><br>

    <label for="console">Console: </label><br>
    <input id="console" type="text" placeholder="inserisci console del gioco" value="${giocoBean.console}"  name="console"><br>

    <label for="software">Software House </label><br>
    <input id="software" type="text" placeholder="inserisci nome della software hoyse" value="${giocoBean.softwareHouse}"  name="software"><br>

    <label for="url">Url: </label><br>
    <input id="url" type="text" placeholder="inserisci url per il gioco" value="${giocoBean.url}"  name="url"><br>

    <label for="immagine">Titolo immagine: </label><br>
    <input id="immagine" type="text" placeholder="inserisci titolo immagine per il gioco" value="${giocoBean.immagine}"  name="immagine"><br>

    <label for="vendite">Numero vendite: </label><br>
    <input id="vendite" type="text" placeholder="inserisci numero vendite per il gioco" value="${giocoBean.numeroVendite}"  name="numeroVendite"><br>

    <label for="sconto">E' in sconto?</label><br>
    <c:choose>
        <c:when test="${giocoBean.inSconto == 1}">
            <input id="sconto" type="radio" name="scelta" value="SI" checked="checked">SI<br>
            <input id="sconto1" type="radio" name="scelta" value="NO">NO<br>
        </c:when>
        <c:otherwise>
            <input id="sconto" type="radio" name="scelta" value="SI">SI<br>
            <input id="sconto1" type="radio" name="scelta" value="NO" checked="checked">NO<br>
        </c:otherwise>
    </c:choose>


    <label for="prezzoS">Prezzo in sconto: </label><br>
    <input id="prezzoS" type="text" placeholder="Se SI,inserisci prezzo scontato" value="${giocoBean.prezzoScontato}"  name="sconto"><br>

    <label for="genere">Genere: </label><br>
    <input id="genere" type="text" placeholder="inserisci genere del gioco" value="${giocoBean.genereGioco}"  name="genere"><br>

    <label for="vendita">E' in vendita?</label><br>
    <c:choose>
        <c:when test="${giocoBean.inVendita == 1}">
            <input id="vendita" type="radio" name="vendita" value="SI" checked="checked">SI<br>
            <input id="vendita1" type="radio" name="vendita" value="NO">NO<br>
        </c:when>
        <c:otherwise>
            <input id="vendita" type="radio" name="vendita" value="SI" >SI<br>
            <input id="vendita1" type="radio" name="vendita" value="NO" checked="checked">NO<br>
        </c:otherwise>
    </c:choose>


    <input class="button" type="submit" value="Modifica Gioco">
</form>

</body>
</html>
