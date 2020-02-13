package com.example.dalhousievotingsystem15;


public class Validator {
    private String ID;
    private String PWD;

    public Validator(String i, String p){
        ID = i;
        PWD = p;
    }

    public String getID(){
        return ID;
    }

    public String getPWD(){
        return PWD;
    }


    public boolean IDNotEmpty(){
        if (!ID.isEmpty()){
            return true;
        }
        return false;
    }

    public boolean PWDNotEmpty(){
        if (!PWD.isEmpty()){
            return true;
        }
        return false;
    }

    public boolean lengthCheck(){
        if (PWD.length() <= 5){
            return false;
        }
        return true;
    }

    public boolean ContainsCaptial(){
        String p2 = PWD;
        if (PWD.toLowerCase().equals(p2)){
            return false;
        }
        return true;
    }

    public boolean CombineNumandLetter(){
        if (PWD.matches("^[0-9]+$")){
            return false;
        }
        else if (PWD.matches("^[A-Za-z]+$")){
            return false;
        }
        return true;
    }

    public boolean StrongPWD(){
        int count = 0;
        if (lengthCheck()) count++;
        if (ContainsCaptial()) count++;
        if (CombineNumandLetter()) count++;

        if (count == 3){
            return true;
        }
        return false;
    }

}
