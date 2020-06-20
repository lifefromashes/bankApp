export const parseBankUser = (req) => {
    //JSON.parse(req.responseText).firstName
    var obj = JSON.parse(req.responseText);
    var s = "";

    for(var i=0; i<obj.length; i++){
        

        s += "UserID: ";
        s += obj[i].id;
        s += " username: ";
        s += obj[i].username;
        s += " total assets: ";
        s += obj[i].totalValue;
        
        
        s += " <br> ";
    }


    return s;
}

export const parseUserByID = (req) => {
    var obj = JSON.parse(req.responseText);
    var s = "";

    s += "UserID: ";
    s += obj.id;
    s += " username: ";
    s += obj.username;
    s += " name: ";
    s += obj.firstName + " " + obj.lastName;
    s += " <br> ";

    for(var i=0; i<obj.bankAccounts; i++){
        s += obj.bankAccounts[i].accountNumber + " ";
        s += obj.bankAccounts[i].balance;
    }


    return s;
}