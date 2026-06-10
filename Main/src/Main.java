import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean connected = true;
        int option;
        Scanner sc = new Scanner(System.in);
        UserService userService = new UserService();

        // Menu Principal
        do {
            // Cogigo baseado no Menu
            if (!userService.logged()) {
                System.out.println("\n\nDigite apenas o número de qual serviço você quer utilizar\n\n" +
                        "1 - Criar usuário\n" +
                        "2 - Logon\n" +
                        "Digite aqui: \n");
                try {
                    option = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Digite apenas números!");
                    continue;
                }
                try {
                    if (option == 1) {
                        userService.registerUser();
                    } else if (option == 2) {
                        userService.login();
                    } else {
                        System.out.println("Digite apenas números correspondentes as opções");
                        continue;
                    }
                } catch (Exception e) {
                    System.out.println("Digite apenas números");
                    continue;
                }
            } else {

                System.out.println("\n\nDigite apenas o número de qual serviço você quer utilizar\n\n" +
                        userService.getLoggedUserInfo() +
                        "1 - Listar usuários\n" +
                        "2 - Atualizar usuário\n" +
                        "3 - Deletar usuário\n" +
                        "4 - Logot\n" +
                        "Digite aqui: \n");
                try {
                    option = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Digite apenas números!");
                    continue;
                }
                    try {
                        if (option == 1) {
                            userService.listUsers();
                        } else if (option == 2) {
                            userService.updateUser();
                        } else if (option == 3) {
                            userService.deleteUser();
                        } else if (option == 4) {
                            userService.logout();
                        } else {
                            System.out.println("Digite apenas números correspondentes as opções");
                            continue;
                        }
                    } catch (Exception e) {
                        System.out.println("Digite apenas números");
                        continue;
                    }
            }


            // Perguntando se quer continuar
            System.out.println("Quer continuar? (S/N):");
            String answer = sc.nextLine();
            if (answer.equalsIgnoreCase("n")) {
                System.out.println("Saindo...");
                connected = false;
            }
        } while (connected);
    }
}



