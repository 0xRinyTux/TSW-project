<%@ page import="model.GiocoDAO" %>
<%@ page import="model.GiocoBean" %>
<%@ page import="java.util.ArrayList" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
  <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <meta http-equiv="X-UA-Compatible" content="ie=edge">

      <title>Risultato</title>

      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css" integrity="sha512-NmLkDIU1C/C88wi324HBc+S2kLhi08PN5GDeUVVVC/BVt/9Izdsc9SVeVfA1UZbY3sHUlDSyRXhCzHfr6hmPPw==" crossorigin="anonymous" />

      <link rel="stylesheet" href="<%= request.getContextPath() %>/css/risultato_ricerca.css">

      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/flickity/2.2.1/flickity.min.css" integrity="sha512-ztsAq/T5Mif7onFaDEa5wsi2yyDn5ygdVwSSQ4iok5BPJQGYz1CoXWZSA7OgwGWrxrSrbF0K85PD5uLpimu4eQ==" crossorigin="anonymous" />

      <script src="https://unpkg.com/scrollreveal@4.0.0/dist/scrollreveal.min.js"></script>

      <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;700;900&display=swap" rel="stylesheet">

      <script src="<%= request.getContextPath() %>/js/cookie.js"></script>

  </head>
  <body onload="controlCookie()">


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



      <!-- Carrello -->
      <div class="car">
          <a href="carrello.jsp">
              <div>
                  <img class="car-img" src="images/carrello.png" alt="" href="Home.html">
              </div>
          </a>
      </div>

  </div>










  <!-- Risultato funzione ricerca (immagini) -->
  <div class="giochi reveal" id="giochi_db">
  <%@ page import = " java.util.* " %>
  <ul class="for zoom">

      <%

          ArrayList<GiocoBean> items = (ArrayList<GiocoBean>) request.getSession().getAttribute("items");
          if(items == null || items.size() == 0){%>
             <div class="nessun-risultato">
                 <h2>NESSUN RISULTATO TROVATO, PER FAVORE CERCA ALTRO!</h2>
             </div>
          <%} else {
              for(int i = 0; i < items.size(); i++){%>

                        <div class="giochi-immagine reveal">
                            <p class="titolo reveal" ><%= items.get(i).getNomeGioco()%></p><br><br><br>

                            <img class="immagine reveal" src="<%= request.getContextPath() + "/imagesDB/" + items.get(i).getImmagine() + ".jpeg" %>"><br><br>

                            <p class="descrizione reveal">
                            <%= items.get(i).getDescrizione()%><br><br>
                            <% if(items.get(i).getInSconto()==0){%>
                            <%= "Al prezzo di: " + items.get(i).getPrezzoGioco() + "€"%><br><br>
                            <% } else { %>
                            <p class="prezzo-cancellato" style="text-decoration: line-through"><%= items.get(i).getPrezzoGioco() + "€"%></p>
                            <p class="prezzo-scontato"><%= "Al prezzo scontato di : " + (items.get(i).getPrezzoGioco() - items.get(i).getPrezzoScontato()) + "€"%></p><br><br>
                            <%}%>
                            <form action="Carrello">
                            <input type="hidden" name="titolo" value=<%=items.get(i).getImmagine() %>>
                            <input type="submit" class="button" id="carrello" value="Aggiungi al Carrello"><br><br><br>
                            </form>
                            </p><br>
                        </div>
         <%}
          }%>
  </ul>
  </div>


  <%@ include file="./footer.jsp" %>




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
  <script>

      function controlCookie() {
          let c = getCookie();
          if (c == "admin") {
              deleteCookie(c);
          } else if (c != "") {
              document.getElementById("utente").innerHTML = "<div class=\"dropdown\">\n" +
                  "        <button class=\"dropbtn\">Bentornato "+ c +"</button>\n" +
                  "        <div class=\"dropdown-content\">\n" +
                  "\n" +
                  "          <a href=\"area-utente.jsp\">AreaUtente</a>\n" +
                  "          <a href=\"Logout\">Logout</a>\n" +
                  "          <a href=\"contatti.html\">Contatti</a>\n" +
                  "\n" +
                  "        </div>\n" +
                  "      </div>"
          }
      }
  </script>


  </body>
</html>
