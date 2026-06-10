class User {

    private String name;
    private String email;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public String toString() {
        return "Nome: " + name +
                "\nEmail: " + email +
                "\nSenha: ********";
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public boolean checkEmail(String email) {
        return email != null && this.email.equals(email);
    }

    public boolean checkPassword(String password) {
        return password != null && this.password.equals(password);
    }
    public boolean emailExists(String email) {
        return email != null && this.email.equals(email);
    }

}
