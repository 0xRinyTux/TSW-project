
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

