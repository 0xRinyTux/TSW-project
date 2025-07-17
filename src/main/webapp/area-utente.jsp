<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<html>
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <title>Area Utente</title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css" integrity="sha512-NmLkDIU1C/C88wi324HBc+S2kLhi08PN5GDeUVVVC/BVt/9Izdsc9SVeVfA1UZbY3sHUlDSyRXhCzHfr6hmPPw==" crossorigin="anonymous" />

    <link rel="stylesheet" href="css/area-utente.css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/flickity/2.2.1/flickity.min.css" integrity="sha512-ztsAq/T5Mif7onFaDEa5wsi2yyDn5ygdVwSSQ4iok5BPJQGYz1CoXWZSA7OgwGWrxrSrbF0K85PD5uLpimu4eQ==" crossorigin="anonymous" />

    <script src="https://unpkg.com/scrollreveal@4.0.0/dist/scrollreveal.min.js"></script>

    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;700;900&display=swap" rel="stylesheet">
  </head>
  <body>

  <!-- Logo e Barra nera -->
  <div class="barra-nera">
    <div class="logo reveal">
      <a class="link-logo"  title="logo"  href="index.html">
        <div>
          <img class="img-logo" src="images/Logo.png" alt="">
        </div>
      </a>
    </div>

    <div class="bottone">
    <a class="button-home" href="index.html">Home</a>
    </div>
  </div>

  <!-- Contenitore -->
  <div class="div-contenitore">
    <!-- Immagine -->
    <div class="img-goku reveal">
      <img src="images/lego.gif">
    </div>
    <!-- Bentornato -->
    <div class="div-bentornato reveal">
      <h1>Bentornato ${sessionScope['username']}<br> Ti diamo il benvenuto nella tua area personale</h1><br><br><br><br><br><br><br><br><br>
      <label for="modifica">Clicca sul pulsante modifica per modificare i tuoi dati</label><br><br>
      <a class="button" id="modifica" href="aggiornamento-cliente.jsp">Modifica</a><br><br><br><br><br><br><br><br><br>

      <label for="ordini">Clicca sul pulsante i miei ordini per visionare gli oridini effettuati</label><br><br>
      <a class="button" id="ordini" href="Ordini">I Miei Ordini</a><br><br><br><br><br><br><br><br><br>

      <label for="logout">Clicca sul pulsante logout per uscire</label><br><br>
      <a class="button" id="logout" href="Logout">Logout</a>

    </div>
  </div>

  <%@ include file="footer.jsp" %>


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

    ScrollReveal().reveal('.reveal',  { distance: '100px', duration: 800, easing: 'cubic-bezier(.215, .61, .355, 1)', interval: 600, mobile: false }) ; /*animazione reveal*/

    ScrollReveal().reveal('.zoom',  {  duration: 1500, easing: 'cubic-bezier(.215, .61, .355, 1)', interval: 200, scale: 0.65, mobile: false}); /*animazione zoom*/





  </script>


  </body>
</html>
