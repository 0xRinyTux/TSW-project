<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.ClienteBean" %>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<html>
  <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <meta http-equiv="X-UA-Compatible" content="ie=edge">

      <link rel="stylesheet" href="css/pagamento.css" type="text/css">
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css" integrity="sha512-NmLkDIU1C/C88wi324HBc+S2kLhi08PN5GDeUVVVC/BVt/9Izdsc9SVeVfA1UZbY3sHUlDSyRXhCzHfr6hmPPw==" crossorigin="anonymous" />
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/flickity/2.2.1/flickity.min.css" integrity="sha512-ztsAq/T5Mif7onFaDEa5wsi2yyDn5ygdVwSSQ4iok5BPJQGYz1CoXWZSA7OgwGWrxrSrbF0K85PD5uLpimu4eQ==" crossorigin="anonymous" />
      <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;700;900&display=swap" rel="stylesheet">

      <script src="https://unpkg.com/scrollreveal@4.0.0/dist/scrollreveal.min.js"></script>
      <script src="<%= request.getContextPath() %>/scripts/cookie.js"></script>

      <title>Pagamento</title>
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
          <ul class="menu">
              <li><a href="index.html">Home</a></li>
              <li id="car"><a href="VerificaLogin">Carrello</a></li>
              <li><a href="ControlCookie" id="utente">Accedi</a></li>
          </ul>
      </ul>
      <div class="hamburger" id="hamburger">
          <span></span>
          <span></span>
          <span></span>
      </div>
  </div>













  <!-- Riepilogo pagamento -->
  <div class="prodotti-carrello zoom">
      <h2 class="prodotti-carrello-text">Riepilogo ordine: </h2><br><br>
      <c:choose>
      <c:when test="${sessionScope['carrello'] == null}">
          <img class="no-img" src="images/pikachu-no.gif"><br><br>
          <p class="no-giochi">Non hai giochi nel carrello</p><br><br>
          <a class="button" href="index.html">Home</a><br><br>
      </c:when>
      <c:otherwise>
        <c:forEach items="${sessionScope['carrello']}" var="item">

            <p class="titolo-carrello">${item.getNomeGioco()}</p>

            <img class="img-carrello" src="imagesDB/${item.getImmagine()}.jpeg" alt="">

            <p class="prezzo-carrello">${item.getPrezzoGioco() - item.getPrezzoScontato()}€</p>
            <p class="totale" style="display: none">${totale = totale + (item.getPrezzoGioco() - item.getPrezzoScontato())}</p><br><br>

        </c:forEach><br>
        <!-- Check if cliente is present -->
        <c:if test="${sessionScope.cliente == null}">
            <div class="error">Si è verificato un problema durante il pagamento. Per favore effettua nuovamente l'accesso.</div>
            <a class="button" href="Log.html">Vai al login</a><br><br>
        </c:if>
        <c:if test="${sessionScope.cliente != null}">
            <p class="tot"> Il totale del tuo ordine è: <br>${totale}€</p><br><br>
        </c:if>
  </div>

  <!-- Modalità pagamento -->
  <c:if test="${sessionScope.cliente != null}">
  <div class="modalita-pagamento zoom">
      <h2 class="modalita-pagamento-text">Modalità di Pagamento:</h2>
      <form action="SalvaDatiPagamento" method="post">
          <div id="form">

              <p id="indirizzo_fatturazione"></p>
              <label>Indirizzo di Fatturazione:</label><br>
              <input type="text" id="indirizzo" name="fatturazione" value="${sessionScope.cliente.indirizzoFatturazione}" required="required"><br>

              <p id="carta"></p>
              <label>Tipo di Carta: </label><br>
              <input type="text" id="tipo_carta" name="tipo_carta" value="${sessionScope.cliente.tipoCarta}" required="required"><br>

              <p id="control"></p>
              <label>Numero Carta</label><br>
              <input type="text" id="numero_carta" name="numero_carta" value="${sessionScope.cliente.numeroCarta}" required="required"><br><br>

              <p>Ci siamo quasi, clicca conferma!</p>
              <input class="button" type="button" id="fatturazione" onclick="conferma()" value="Conferma" required="required"><br><br>
          </div>

          <!-- p-finale -->
          <div class="p-finale zoom">
              <input class="button" id="submit" type="submit" name="button" value="Completa l'ordine"><br><br>
          </div>

      </form>
  </div>
  </c:if>

  <%@ include file="./footer.jsp" %>


  <script>


      function modifica(){
          var indirizzo = document.getElementById("indirizzo").value;
          var tipoCarta = document.getElementById("tipo_carta").value;
          var numeroCarta = document.getElementById("numero_carta").value;

          $("#submit").hide();


        document.getElementById("form").innerHTML =
            "<div id=\"form\">\n" +
            "<p id=\"indirizzo_fatturazione\"></p>" +
            "<label>Indirizzo di Fatturazione: </label><br>"+
            "<input type=\"text\" id=\"indirizzo\" name=\"fatturazione\" value=\"" + indirizzo + "\"><br>" +
            "<p id=\"carta\"></p>" +
            "<label>Tipo di Carta: </label><br>"+
            "<input type=\"text\" id=\"tipo_carta\" name=\"tipo_carta\" value = \"" + tipoCarta + "\"><br>"+
            "<p id=\"control\" > </p>" +
            "<label>Numero Carta: </label><br>"+
            "<input type=\"text\" id=\"numero_carta\" name=\"numero_carta\" value=\"" + numeroCarta + "\"><br>"+
            "<input class=\"button\"type=\"button\" id=\"salva\" value=\"Aggiungi\" onclick=\"conferma()\"><br><br>"+
            "</div>";



      }

      function verificaLunghezzaPAN(numeroCarta){
          if(numeroCarta.length == 16){
              return numeroCarta;
          }
          else{
              return null
          }

      }
      function conferma() {
          var indirizzo = document.getElementById("indirizzo").value;
          var tipoCarta = document.getElementById("tipo_carta").value;
          var numeroCarta = verificaLunghezzaPAN(document.getElementById("numero_carta").value);



          if (numeroCarta != null && indirizzo != "" && tipoCarta != "") {
              document.getElementById("form").innerHTML = indirizzo +  "<input type=\"hidden\" id=\"indirizzo\" name=\"fatturazione\" value=\"" + indirizzo + "\"> <br>" + tipoCarta + "<input type=\"hidden\" id=\"tipo_carta\" name=\"tipo_carta\" value=\"" + tipoCarta + "\"> <br>" + numeroCarta + "<input type=\"hidden\" id=\"numero_carta\" name=\"numero_carta\" value=\"" + numeroCarta + "\"> <br>" +
                  " <input class=\"button\" type=\"button\" id=\"fatturazione\" onclick=\"modifica()\" value=\"modifica\"><br><br>";

              $("#submit").show();
          }
          if(numeroCarta == null){
              document.getElementById("control").innerHTML = "PAN non valido";
          }
          if(indirizzo == ""){
              document.getElementById("indirizzo_fatturazione").innerHTML = "Campo vuoto";
          }
          if(tipoCarta == ""){
              document.getElementById("carta").innerHTML = "Campo Vuoto"
          }

      }

      function controlCookie(){
          let c = getCookie();
          if(c == "JSESSIONID" || c == "admin" || c == ""){

              document.getElementById("body").innerHTML = "PER ACQUISTARE DEVI ACCEDERE<br>" +
                  "                                        <a href='Log.html'>Pagina di Login</a>";
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

              $("#submit").hide();
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
      </c:otherwise>
  </c:choose>


  </body>
</html>
