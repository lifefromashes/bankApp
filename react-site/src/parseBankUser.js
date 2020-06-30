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

    //if(obj.closedAccounts != ""){
        s += "Closed Account Numbers: " + obj.closedAccounts;
        s += "<br>";
    //}

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
        s += "><div class='placTitle'>"
        s += obj.bankAccounts[i].accountName + " #" + obj.bankAccounts[i].accountNumber;
        s += "</div> <div class='placMain'>";
        s += "Current Balance: $";
        s += obj.bankAccounts[i].balance;
        s += "</div></div> ";
    }


    return s;
}

export const parseHistory = (req) => {
    var s = "";
    var objParent = JSON.parse(req.responseText);
    var obj = objParent.transactions;
    var classString = "<div class='accountBox'";
    var num = 0;

    for(var i=obj.length-1; i>=0; i--){
    //for(var i=0; i<obj.length; i++){
        
        if(obj[i].transactionSuccess){

            s += classString;
        } else {
            s += "<div class='failBox'"
        }


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

        if(obj[i].transactionSuccess){
            s += "&nbsp; &nbsp; balance after: $";
            s += obj[i].balanceAfterTransaction;
        } else {
            s += "&nbsp; &nbsp; REJECTED";
        }
        
        s += " &nbsp; &nbsp;" + obj[i].transactionMemo;
        s += "</div> ";


    }

    return s;
}




export const parseHistoryAdmin = (req) => {
    var s = "";
    var obj = JSON.parse(req.responseText);
    var num = 0;

    for(var i = obj.length - 1; i >= 0; i--){
        
        s += "<p>";
        s += obj[i].transactionDate + "&nbsp; &nbsp;";


        
        s += "&nbsp; &nbsp; amount: $"
        s += obj[i].amount;
        
        if(obj[i].transactionSuccess){
            s += "&nbsp; &nbsp; balance after: $";
        } else {
            s += "&nbsp; &nbsp; REJECTED &nbsp; &nbsp;"
        }
        s += obj[i].balanceAfterTransaction;
        s += " &nbsp; &nbsp;" + obj[i].transactionMemo;
        s += "</p> ";

    }

    return s;
}