<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <link rel="stylesheet" href="css/ordini.css" type="text/css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css" integrity="sha512-NmLkDIU1C/C88wi324HBc+S2kLhi08PN5GDeUVVVC/BVt/9Izdsc9SVeVfA1UZbY3sHUlDSyRXhCzHfr6hmPPw==" crossorigin="anonymous" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/flickity/2.2.1/flickity.min.css" integrity="sha512-ztsAq/T5Mif7onFaDEa5wsi2yyDn5ygdVwSSQ4iok5BPJQGYz1CoXWZSA7OgwGWrxrSrbF0K85PD5uLpimu4eQ==" crossorigin="anonymous" />
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;700;900&display=swap" rel="stylesheet">

    <script src="https://unpkg.com/scrollreveal@4.0.0/dist/scrollreveal.min.js"></script>

    <title>Ordini</title>
</head>
<body>

<!-- Logo -->
<div class="logo reveal">
    <a class="link-logo"  title="logo"  href="index.html">
        <div>
            <img class="img-logo" src="images/Logo.png" alt="">
        </div>
    </a>
</div>
<div class="tutto reveal">
<h1 class="span-ordini">I tuoi ordini: </h1>

<% ArrayList<KeyBean> keyBeans = (ArrayList<KeyBean>) request.getAttribute("keyBeans");
   int lastFattura= 0;
   if( keyBeans.size() == 0){%>
     <h1 class="span-ordini"><%="Non hai mai acquistato nulla !"%></h1>
   <%}

   for( KeyBean keyBean: keyBeans ){
       if(lastFattura == keyBean.getFattura()){%>
        <br>
        <span class="span">ID Chiave: <%= keyBean.getIdChiave() %><br></span>
        <span class="span">Nome: <%= keyBean.getNomeGioco() %><br></span>
        <span id="chiave" class="span">Key: <%= keyBean.getKey() %><br></span>

    <%}else{%>
        <br><br><br><br>
        <span class="span">Fattura: <%= keyBean.getFattura() %><br></span><br>
        <span class="span">ID Chiave: <%= keyBean.getIdChiave() %><br></span>
        <span class="span">Nome: <%= keyBean.getNomeGioco() %><br></span>
        <span id="chiave" class="span">Key: <%= keyBean.getKey() %><br></span>
    <%lastFattura=keyBean.getFattura();%>
   <%}%>
<%}%>
</div>

<%@ include file="./footer.jsp" %>


<!-- Jquery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/flickity/2.2.1/flickity.pkgd.min.js" integrity="sha512-Nx/M3T/fWprNarYOrnl+gfWZ25YlZtSNmhjHeC0+2gCtyAdDFXqaORJBj1dC427zt3z/HwkUpPX+cxzonjUgrA==" crossorigin="anonymous"></script>


<script>
    ScrollReveal().reveal('.reveal');

    ScrollReveal().reveal('.reveal',  { distance: '100px', duration: 1500, easing: 'cubic-bezier(.215, .61, .355, 1)', interval: 600, mobile: false }) ; /*animazione reveal*/

    ScrollReveal().reveal('.zoom',  { duration: 1500, easing: 'cubic-bezier(.215, .61, .355, 1)', interval: 200, scale: 0.65, mobile: false}); /*animazione zoom*/

</script>

</body>
</html>
