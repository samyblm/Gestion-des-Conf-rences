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
    return (getCookieValue('token')!=="")
}

async function getUserData(token) {
    const res = await fetch('http://localhost:8090/api/v1/registration/test', {
        method: 'GET',
        mode: 'cors',
        headers: new Headers({'Authorization': 'Bearer ' + token})
    })
    const json = await res.json()
    return json;
}


module.exports = {
    getCookieValue,
    getUserData,
    isLoggedIn
}