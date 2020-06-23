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

export const parseCDO = (req) => {
    //JSON.parse(req.responseText).firstName
    var obj = JSON.parse(req.responseText);
    var s = "";

    for(var i=0; i<obj.length; i++){
        

        s += "ID: ";
        s += obj[i].id;
        s += " term: ";
        s += obj[i].term;
        s += " rate: ";
        s += obj[i].interestRate;
        
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

    for(var i=0; i<obj.bankAccounts.length; i++){
        s += "act num: ";
        s += obj.bankAccounts[i].accountNumber;
        s += " " + obj.bankAccounts[i].accountName;
        s += " $"
        s += obj.bankAccounts[i].balance;
        s += " <br> ";
    }


    return s;
}


export const parseAccounts = (req) => {
    
    var s = "";
    var obj = JSON.parse(req.responseText);
    var classString = "<div class='accountBox'";
    var num = 0;

    for(var i=0; i<obj.bankAccounts.length; i++){
        //acts[i] = bankAccounts[i];
        s += classString;
        if(classString == "<div class='accountBox'"){
            classString = "<div class='accountBox2'";
        } else {
            classString = "<div class='accountBox'";
        }
        s += " id='accountID" + num + "'";
        num ++;
        s += ">"
        s += obj.bankAccounts[i].accountName;
        s += "          $";
        s += obj.bankAccounts[i].balance;
        s += "</div> <br></br>";
    }


    return s;
}

export const parseHistory = (req) => {
    var s = "";
    var obj = JSON.parse(req.responseText);
    var classString = "<div class='accountBox'";
    var num = 0;

    for(var i=0; i<obj.length; i++){
        
        s += classString;
        if(classString == "<div class='accountBox'"){
            classString = "<div class='accountBox2'";
        } else {
            classString = "<div class='accountBox'";
        }
        s += " id='accountID" + num + "'";
        num ++;
        
        s += ">" + obj[i].transactionDate;
        s += "&nbsp; &nbsp; amount: $"
        s += obj[i].amount;
        s += "&nbsp; &nbsp; balance after: $";
        s += obj[i].balanceAfterTransaction;
        s += " &nbsp; &nbsp;" + obj[i].transactionMemo;
        s += "</div> <br></br>";


    }

    return s;
}