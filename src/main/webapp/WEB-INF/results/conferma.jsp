<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Conferma Ordine - BuyandPlay</title>
    <link rel="stylesheet" href="css/pagamento.css" type="text/css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css" integrity="sha512-NmLkDIU1C/C88wi324HBc+S2kLhi08PN5GDeUVVVC/BVt/9Izdsc9SVeVfA1UZbY3sHUlDSyRXhCzHfr6hmPPw==" crossorigin="anonymous" />
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;700;900&display=swap" rel="stylesheet">
</head>
<body>

<!-- Logo -->
<div class="logo reveal">
    <a class="link-logo" title="logo" href="index.html">
        <div>
            <img class="img-logo" src="images/Logo.png" alt="BuyandPlay Logo">
        </div>
    </a>
</div>

<!-- Main content -->
<div class="container">
    <div class="result">
        <h2>🎉 Ordine Confermato!</h2>
        <p>Il tuo ordine con ID <strong>${idFattura}</strong> è stato elaborato con successo.</p>
        
        <div class="keys-section">
            <h3>🔑 Chiavi generate:</h3>
            <ul class="keys-list">
                <c:forEach items="${generatedKeys}" var="kb">
                    <li class="key-item">
                        <div class="key-info">
                            <span class="game-name">🎮 ${kb.nomeGioco}</span>
                            <span class="key-value">🔐 ${kb.key}</span>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
        
        <div class="actions">
            <a href="Ordini" class="button primary">📋 Visualizza i miei ordini</a>
            <a href="index.html" class="button secondary">🏠 Torna alla home</a>
        </div>
    </div>
</div>

<%@ include file="./footer.jsp" %>

</body>
</html>
