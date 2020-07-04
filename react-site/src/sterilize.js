export const sterilizeString = (s) => {

    if(s == undefined){ s = ""; }
    
    s = s.replace(/[\\\;\"\']+/, "");
    return s;
}