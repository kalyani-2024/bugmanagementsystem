interface UserInterface {
    boolean login(String username, String password);
    void viewBugs();
}

class User implements UserInterface {
    protected String username;
    protected String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean login(String username, String password) {
        if (this.username.equals(username) && this.password.equals(password)) {
            System.out.println("Logged in as " + username);
            return true;
        } else {
            System.out.println("Invalid credentials for " + username);
            return false;
        }
    }

    @Override
    public void viewBugs() {
        System.out.println("Viewing bugs assigned to " + username);
    }
}

class Admin extends User {
    public Admin(String username, String password) {
        super(username, password);
    }

    public void updateBugStatus(Bug bug, String status) {
        bug.setStatus(status);
        System.out.println("Updated bug " + bug.getId() + " status to " + status);
    }
}
