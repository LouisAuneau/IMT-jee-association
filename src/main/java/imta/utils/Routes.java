package imta.utils;

public enum Routes {

    LOGIN("/login"),
    REGISTER("/register"),
    COMMAND("/commande"),
    CATALOG("/catalogue"),
    HOME("/home"),
    LOGOUT("/logout");

    private final String routePath;

    Routes(String routePath) {
        this.routePath = routePath;
    }

    public String getRoutePath() {
        return routePath;
    }

}
