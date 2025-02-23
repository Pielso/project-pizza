package jek.services.system;

public class TextService {

    public static void loginScreen(){
        centerText("--------------------------------------------------------------------------< LOGIN >--------------------------------------------------------------------------");
        centerText("INPUT LOGIN CREDENTIALS TO CONTINUE PLAYING");
        centerText("IF YOU DONT HAVE AN ACCOUNT, INPUT 'REGISTER'");
        centerText("TO EXIT, INPUT 'EXIT'");
    }

    public static void createNewUserScreen(){
        centerText("--------------------------------------------------------------------< CREATE NEW USER >----------------------------------------------------------------------");
        centerText("CHOOSE A USERNAME & PASSWORD");
    }

    public static void centerText(String text){
        int consoleWidth = 200;
        int padding = (consoleWidth - text.length()) / 2;
        System.out.printf("%" + padding + "s%s%n", "", text);
    }


}




