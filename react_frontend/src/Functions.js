function getCookieValue(name) {
    let res = "";
    let cookies = document.cookie.split(";");
    let i = 0;
    let b = false;
    while(!b && i<cookies.length) {
        if(cookies[i].split("=")[0]===name) {
            b = true;
            res = cookies[i].split("=")[1];
        }
        else {
            i++;
        }
    }
    return res;
}

function isLoggedIn() {
    return (getCookieValue('token').length !== 0)
}

// async function getUserData(token) {
//     let json;
//     fetch('http://localhost:8090/api/v1/Appuser/getUser', {
//         mode: 'cors',
//         method: 'GET',
//         headers: new Headers({'Authorization': 'Bearer ' + token})
//     }).then(res=>res.json())
//     .then(jsn=>json = jsn)
//     return json
// }
async function getUserData(token) {
    let myHeader = new Headers();
    myHeader.append('authorization','Bearer '+token);
    const res = await fetch("http://localhost:8090/api/v1/Appuser/getUser", {
        mode: 'cors',
        method: "GET",
        headers: myHeader,

    })
    let json = await res.json();
    return json;
}


module.exports = {
    getCookieValue,
    getUserData,
    isLoggedIn
}