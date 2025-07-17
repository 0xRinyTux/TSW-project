<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <title>Carrello</title>

    <link rel="stylesheet" href="css/carrello.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css" integrity="sha512-NmLkDIU1C/C88wi324HBc+S2kLhi08PN5GDeUVVVC/BVt/9Izdsc9SVeVfA1UZbY3sHUlDSyRXhCzHfr6hmPPw==" crossorigin="anonymous" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/flickity/2.2.1/flickity.min.css" integrity="sha512-ztsAq/T5Mif7onFaDEa5wsi2yyDn5ygdVwSSQ4iok5BPJQGYz1CoXWZSA7OgwGWrxrSrbF0K85PD5uLpimu4eQ==" crossorigin="anonymous" />
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;700;900&display=swap" rel="stylesheet">

    <script src="https://unpkg.com/scrollreveal@4.0.0/dist/scrollreveal.min.js"></script>
    <script src="<%= request.getContextPath() %>/scripts/cookie.js"></script>

</head>
<body onload="controlCookie()" id="body">






<div class="header">
    <!-- Barra di ricerca -->
    <div class="barra-ric">
        <form id = "b&dp-search-form" class = "bandp-search-form" action="SearchItem" method="get" role="search">
            <input  class="barra-ric-text" type="text"  name = "search"  placeholder="Minecraft, Fortnite and COD...">
        </form>
    </div>

    <!-- Logo -->
    <div class="logo reveal">
        <a class="link-logo"  title="logo"  href="index.html">
            <div>
                <img class="img-logo" src="images/Logo.png" alt="">
            </div>
        </a>
    </div>


    <!-- Full Menu -->
    <ul class="menu">
        <li><a href="index.html">Home</a></li>
        <li id="car"><a href="">Carrello</a></li>
        <li><a href="ControlCookie" id="utente">Accedi</a></li>
    </ul>
    <div class="hamburger" id="hamburger">
        <span></span>
        <span></span>
        <span></span>
    </div>
</div>








<!-- Ordini nel carrello -->
<div class="grande-div reveal">
<h2 class="text reveal">Carrello</h2>
   <p class="no"> ${totale = 0} </p>
<c:forEach items="${sessionScope['carrello']}" var="items">
    <div class="carrello reveal">
    <img src="imagesDB/${items.getImmagine()}.jpeg" class="carrello-img"><br>
           <p class="nome-genere-prezzo"> ${items.getNomeGioco()}, ${items.getGenereGioco()}, ${items.getPrezzoGioco() - items.getPrezzoScontato()}€<br></p><br>
            <form action="RimuoviDaCarrello">
                <input type="hidden" name="titolo" value="${items.getImmagine()}">
                <input type="submit" value="Rimuovi" class="button-piccolo">
            </form>
        <p class="no">${totale = totale + (items.getPrezzoGioco() - items.getPrezzoScontato())}</p>
    </div>

</c:forEach>
    <div class="paga">
        <h2 class="text reveal">Il totale provvisorio è: ${totale} €
        <form action="Acquisto">
            <input class="button" type="submit" value="Acquista">
        </form>
        </h2>
    </div>





</div>



<!-- Footer -->
<%@ include file="footer.jsp" %>





<!-- Accedere prima di andare al carrello -->
<script>
    function controlCookie(){
        let c = getCookie();
        if(c == "JSESSIONID" || c == "admin" || c == ""){

            document.getElementById("body").innerHTML = "<div class=\"prima-accesso\">" +
                "<p>PER ACQUISTARE, DEVI PRIMA ACCEDERE</p><br>" +
                "<img src=\"images\\last.gif\"><br><br><br>" +
                "<a href='Log.html'>Pagina di Login</a>" +
            "</div>";
        }
        else{
            document.getElementById("utente").innerHTML = "<div class=\"dropdown\">\n" +
                "        <button class=\"dropbtn\">Bentornato "+ c +"</button>\n" +
                "        <div class=\"dropdown-content\">\n" +
                "\n" +
                "          <a href=\"area-utente.jsp\">AreaUtente</a>\n" +
                "          <a href=\"Logout\">Logout</a>\n" +
                "          <a href=\"contatti.html\">Contatti</a>\n" +
                "\n" +
                "        </div>\n" +
                "      </div>";
        }
    }
</script>








<!-- Jquery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/flickity/2.2.1/flickity.pkgd.min.js" integrity="sha512-Nx/M3T/fWprNarYOrnl+gfWZ25YlZtSNmhjHeC0+2gCtyAdDFXqaORJBj1dC427zt3z/HwkUpPX+cxzonjUgrA==" crossorigin="anonymous"></script>

<script>
    $( document ).ready(function() {

        /* Open Panel */
        $( ".hamburger" ).on('click', function() {
            $(".menu").toggleClass("menu--open");
        });

    });


    ScrollReveal().reveal('.reveal');

    ScrollReveal().reveal('.reveal',  { distance: '100px', duration: 1500, easing: 'cubic-bezier(.215, .61, .355, 1)', interval: 600, mobile: false }) ; /*animazione reveal*/

    ScrollReveal().reveal('.zoom',  { duration: 1500, easing: 'cubic-bezier(.215, .61, .355, 1)', interval: 200, scale: 0.65, mobile: false}); /*animazione zoom*/



</script>


</body>
</html>
