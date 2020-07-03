export const sterilizeString = (s) => {





    s = s.replace(";", "");
    s = s.replace("'", "");
    s = s.replace('"', '');
    s = s.replace("\\", "");


    return s;
}