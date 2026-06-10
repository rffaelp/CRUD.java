import java.util.ArrayList;
import java.util.Scanner;

class UserService {
    private User loggedUser;
    private Scanner sc = new Scanner(System.in);

    private ArrayList<User> users = new ArrayList<>();

    private int readNumber() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Digite apenas números!");
            return -1;
        }
    }

    public void registerUser() {
        System.out.println("\nDigite o nome do usuário: ");
        String name = sc.nextLine();

        System.out.println("\nDigite o email: ");
        String email = sc.nextLine();
        /*
        if (users.stream().anyMatch(user -> user.emailExists(email))) {}
        Isso é a mesma coisa que o codigo abaixo
        */
        for (User user : users) {
            if (user.emailExists(email)) {
                System.out.println("Esse email já está cadastrado!");
                return;
            }
        }

        System.out.println("\nDigite a senha: ");
        String password = sc.nextLine();

        User newUser = new User(name, email, password);
        users.add(newUser);

        loggedUser = newUser;
        System.out.println("Usuário criado e logado com sucesso!");
    }

    private boolean confirmPassword(User user) {
        System.out.println("Digite a senha: ");
        String password = sc.nextLine();

        if (!user.checkPassword(password)) {
            System.out.println("\nSenha incorreta!\n");
            return false;
        }
        return true;
    }

    public void listUsers() {
        if (!users.isEmpty()) {
            for (User user : users) {
                System.out.println(user);
            }
        } else {
            System.out.println("\nAinda não há usuários cadastrados!\n");
        }
    }

    private void updateMenu(User user) {
        System.out.println("O que deseja editar?\n" +
                "1 - Nome\n" +
                "2 - Email\n" +
                "3 - Senha\n");

        int option = readNumber();

        if (option == 1) {
            System.out.println("\nDigite o novo nome: ");
            user.setName(sc.nextLine());
            System.out.println("\nNome alterado com sucesso!\n");
        } else if (option == 2) {
            System.out.println("\nDigite o novo email: ");
            user.setEmail(sc.nextLine());
            System.out.println("\nEmail alterado com sucesso!\n");
        } else if (option == 3) {
            System.out.println("\nDigite a nova senha: ");
            user.setPassword(sc.nextLine());
            System.out.println("\nSenha alterada com sucesso!\n");
        } else {
            System.out.println("Opção inválida!");
            return;
        }

        System.out.println("Alteração realizada com sucesso!\n");
    }

    private User chooseUser() {
        for (int i = 0; i < users.size(); i++) {
            System.out.println((i + 1) + " - " + users.get(i).getName());
        }
        System.out.println("\nDigite o número do usuário: ");
        int index = readNumber();

        if (index > 0 && index <= users.size()) {
            return users.get(index - 1);
        }

        System.out.println("\nÍndice inválido!\n");
        return null;
    }

    public void updateUser() {
        if (!logged()) {
            System.out.println("\nVocê precisa estar logado!\n");
            return;
        }
        if (users.isEmpty()) {
            System.out.println("\nAinda não há usuários cadastrados!\n");
            return;
        }

        System.out.println("1 - Editar meu usuário\n" +
                "2 - Editar outro usuário\n" +
                "3 - Cancelar\n");

        int option = readNumber();

        User target = (option == 1) ? loggedUser : (option == 2) ? chooseUser() : null;

        if (target == null) {
            System.out.println("\nOperação cancelada!\n");
            return;
        }
        if (!confirmPassword(target)) return;
        updateMenu(target);
    }

    public void deleteUser() {
        if (!logged()) {
            System.out.println("\nVocê precisa estar logado!\n");
            return;
        }

        if (users.isEmpty()) {
            System.out.println("\nNão há usuários para deletar!\n");
            return;
        }

        System.out.println("1 - Deletar meu usuário\n" +
                "2 - Deletar outro usuário\n" +
                "3 - Cancelar\n");

        int option = readNumber();

        User target = (option == 1) ? loggedUser : (option == 2) ? chooseUser() : null;

        if (target == null) {
            System.out.println("\nOperação cancelada!\n");
            return;
        }

        if (!confirmPassword(target)) return;

        users.remove(target);

        if (target == loggedUser) {
            logout();
        }

        System.out.println("\nUsuário deletado com sucesso!\n");
    }

    public void login() {
        User foundUser = null;
        System.out.println("\nLogin\n");

        if (users.isEmpty()) {
            System.out.println("Ainda não há usuários cadastrados!\n");
            return;
        }

        System.out.println("Digite o email: ");
        String emailInput = sc.nextLine();

        for (User user : users) {
            if (user.checkEmail(emailInput)) {
                foundUser = user;
                break;
            }
        }
        if (foundUser == null) {
            System.out.println("Usuário não encontrado!\n");
            return;
        }
        if (!confirmPassword(foundUser)) return;

        System.out.println("Login realizado com sucesso!\n");
        loggedUser = foundUser;
    }

    public String getLoggedUserInfo() {
        if (loggedUser == null) {
            return "";
        }

        return "Usuário logado: \n" +
                "Nome: "+loggedUser.getName()+"\n"+
                "Email: "+loggedUser.getEmail() + "\n\n";
    }

    public boolean logged() {
        return loggedUser != null;
    }

    public void logout() {
        loggedUser = null;
    }
}