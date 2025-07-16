<%@ page import="model.ClienteBean" %>
<%@ page import="model.ClienteDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <title>Aggiornamento Cliente</title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css" integrity="sha512-NmLkDIU1C/C88wi324HBc+S2kLhi08PN5GDeUVVVC/BVt/9Izdsc9SVeVfA1UZbY3sHUlDSyRXhCzHfr6hmPPw==" crossorigin="anonymous" />

    <link rel="stylesheet" href="css/aggiornamento-cliente.css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/flickity/2.2.1/flickity.min.css" integrity="sha512-ztsAq/T5Mif7onFaDEa5wsi2yyDn5ygdVwSSQ4iok5BPJQGYz1CoXWZSA7OgwGWrxrSrbF0K85PD5uLpimu4eQ==" crossorigin="anonymous" />

    <script src="https://unpkg.com/scrollreveal@4.0.0/dist/scrollreveal.min.js"></script>

    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;700;900&display=swap" rel="stylesheet">

    <script src="js/cookie.js"></script>

</head>
<body>
<%Cookie[] cookies = request.getCookies();
String nickname = null;
    for(Cookie cookie : cookies){
        if(cookie.getName().equalsIgnoreCase("username")){
            nickname = cookie.getValue();
        }

    }
ClienteDAO clienteDAO = new ClienteDAO();%>
<% ClienteBean clienteBean = clienteDAO.retrieveClientByNickname(nickname);%>
<!-- dati-imp(testo iniziale) -->
<br><br><p class="dati-imp">I campi che non desideri modificare possono essere lasciati vuoti!</p><br><br>
<!-- Tutto il form -->
<form action="ControllaPassword" id="form" method="post">
    <div class="dati-personali reveal">
        <br><p class="text">Dati Cliente:</p><br>
        <label for="nome">Nome:</label><br>
        <input id="nome" type="text" name="nome" value="<%=clienteBean.getNome()%>"><br><br>

        <label for="cognome">Cognome:</label><br>
        <input id="cognome" type="text" name="cognome" value="<%=clienteBean.getCognome()%>"><br><br>

        <label for="email">Email:</label><br>
        <input id="email" type="email" name="email" value="<%=clienteBean.getEmail()%>"><br><br>
    </div>

    <div class="cambia-password reveal">
        <br><p class="text">Vuoi cambiare password? </p><br>
        <p id="controllo_password"></p>
        <p id="password_corrente_vuota"></p>

        <label for="p-corrente">Password corrente:</label><br>
        <input id="p-corrente" type="password" id="password_corrente" name="password"><br><br>

        <p id="password_nuova_vuota"></p>
        <p id="corrispondenza_password"></p>

        <label for="nuova-password">Nuova Password:</label><br>
        <input id="nuova-password" type="password" name="password_nuova" id="password_nuova"><br>

        <label for="ripeti-password">Ripeti Password:</label><br>
        <input id="ripeti-password" type="password" id="password_ripetuta" name="password_ripetuta"><br><br>
    </div>

    <div class="pagamento reveal">
        <br><p class="text">Dati per il pagamento:</p><br>

        <label>Tipo di carta: </label><br>
        <input type="text" name="tipo_carta" value="<%=clienteBean.getTipoCarta()%>"><br><br>

        <label>Numero della carta: </label><br>
        <input type="text" name="numero_carta" value="<%=clienteBean.getNumeroCarta()%>"><br><br>

        <label>Indirizzo di Fatturazione: </label><br>
        <input type="text" name="indirizzo_fatturazione" value="<%=clienteBean.getIndirizzoFatturazione()%>"><br><br>
    <input class="button" type="button" onclick="controllaCampiPassword()" value="Modifica"><br><br>
    </div>
</form>



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

    ScrollReveal().reveal('.reveal',  { distance: '100px', duration: 1500, easing: 'cubic-bezier(.215, .61, .355, 1)', interval: 600, mobile: false }) ; /*animazione reveal*/

    ScrollReveal().reveal('.zoom',  { duration: 1500, easing: 'cubic-bezier(.215, .61, .355, 1)', interval: 200, scale: 0.65, mobile: false}); /*animazione zoom*/

</script>






<script>
    let ajax;

    function verificaPassword(passwordNuova, passwordCorrente) {
        let xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (this.status == 200 && this.readyState == 4) {
               if(this.responseText == "scorretto"){
                   ajax = false;
               }else
               {
                   ajax = true;
               }
            }
        };
        xhttp.open("POST", "ControllaPassword", false);
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send("password=" + passwordCorrente);
    }





    function controllaCorrispondenzaPassword(passwordNuova, passwordRipetuta){
        if(passwordNuova != passwordRipetuta)
        {
           return false;
        }
        return true
    }

    function controllaCampiPassword(){
        let passwordNuova = document.getElementById("nuova-password").value;
        let passwordRipetuta = document.getElementById("ripeti-password").value;
        let passwordCorrente = document.getElementById("p-corrente").value;


        if(controllaCorrispondenzaPassword(passwordNuova, passwordRipetuta)) {
            if (passwordNuova == "" && passwordCorrente != "") {
                document.getElementById("controllo_password").innerHTML = "<p id=\"controlloPassword\"> Non hai completato tutti i campi per cambiare la Password</p>";

            }
            else if(passwordNuova != "" && passwordCorrente == ""){
                    document.getElementById("controllo_password").innerHTML = "<p id=\"controlloPassword\"> Non hai completato tutti i campi per cambiare la Password</p>";

            }else if(passwordNuova != "" && passwordCorrente != "") {
                    verificaPassword(passwordNuova, passwordCorrente)
                    if(ajax) {
                        document.getElementById("form").action = "ModificaUtente";
                        document.getElementById("form").method = "POST";
                        document.getElementById("form").submit();
                    }
                    else if(!ajax){
                        document.getElementById("controllo_password").innerHTML = "<p id=\"controlloPassword\"> La password corrente è errata</p>";
                    }
            }else{
                document.getElementById("form").action = "ModificaUtente";
                document.getElementById("form").method = "POST";  /*Entrambe le password vuote*/
                document.getElementById("form").submit();

            }
        }else{
            document.getElementById("corrispondenza_password").innerHTML = "<p id = \"corrispondenza_password\"> Le password non corrispondono</p>";
        }

    }



</script>





</body>
</html>
