function getCookie(){
    let cname = "username";
    let name = cname + "=";
    let decodedCookie = decodeURIComponent(document.cookie);
    let ca = decodedCookie.split(';');
    for(let i = 0; i <ca.length; i++) {
    let c = ca[i];
    while (c.charAt(0) == ' ') {
    c = c.substring(1);
}
    if (c.indexOf(name) == 0) {
    return c.substring(name.length, c.length);
}
}
    return "";
}


    function deleteCookie(c){
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function (){
            if(this.status == 200 && this.readyState === 4){
                console.log(document.cookie);
            }
        }
        xhttp.open("GET", "RimuoviCookieAdmin", true);
        xhttp.send();
    }

// Function to display user menu or login link
function controlCookie() {
    const user = getCookie();
    const utenteElem = document.getElementById('utente');
    if (!utenteElem) return;
    if (user && user !== 'admin') {
        utenteElem.innerHTML = `<div class="dropdown">
            <button class="dropbtn">Bentornato ${user}</button>
            <div class="dropdown-content">
                <a href="area-utente.jsp">AreaUtente</a>
                <a href="Logout">Logout</a>
                <a href="contatti.html">Contatti</a>
            </div>
        </div>`;
        utenteElem.setAttribute('href', 'ControlCookie');
    } else if (user === 'admin') {
        utenteElem.innerHTML = `<div class="dropdown">
            <button class="dropbtn">Bentornato Admin</button>
            <div class="dropdown-content">
                <a href="admin-home.html">Admin</a>
                <a href="Logout">Logout</a>
                <a href="contatti.html">Contatti</a>
            </div>
        </div>`;
        utenteElem.setAttribute('href', 'ControlCookie');
    } else {
        utenteElem.textContent = 'Accedi';
        utenteElem.setAttribute('href', 'Log.html');
    }
}

