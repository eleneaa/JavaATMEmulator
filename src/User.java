public class User {
    String username;
    String password;
    int dailyLimit = 50000;

    //Конструктор
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    //Метод для проверки имени пользователя
    private static boolean checkUsername(String username){
        boolean valid = (username != null) && username.matches("[A-Za-z0-9_]+");
        return valid;
    }

    //Метод для проверки пароля пользователя
    private static boolean checkPassword(String password){
        boolean valid = (password != null) && password.matches
                ("^(?=.*[0-9])(?=.*[az])(?=.*[AZ])(?=.*[@#$%^&-+=( )])(?=\\\\S+$).{8,20}$");
        return valid;
    }

    //Метод для входа пользователя
    public static void Login(String username, String password){
        if (checkUsername(username) && checkPassword(password)){
            System.out.println("You have been successfully logged in");
        } else if (checkUsername(username)) {
            System.out.println("Incorrect password.\n" +
                    "It contains at least 8 characters and at most 20 characters.\n" +
                    "It contains at least one digit.\n" +
                    "It contains at least one upper case alphabet.\n" +
                    "It contains at least one lower case alphabet.\n" +
                    "It contains at least one special character which includes !@#$%&*()-+=^.\n" +
                    "It doesn’t contain any white space.");
        } else if (checkPassword(password)) {
            System.out.println("Incorrect username");
        } else{
            System.out.println("Incorrect username and password");
        }
    }

    //Метод для выхода пользователя
    public void Logout(){
        this.username = null;
        this.password = null;
        System.out.println("You have been successfully logged out");
    }
}
