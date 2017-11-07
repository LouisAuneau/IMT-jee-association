package imta.utils;

public enum Routes {

    LOGIN("/login", "login"),
    REGISTER("/register", "register"),
    COMMAND("/commande", "commande"),
    CATALOG("/catalogue", "catalogue"),
    HOME("/home", "home");

    private final String routePath;
    private final String routeName;

    Routes(String routePath, String routeName) {
        this.routePath = routePath;
        this.routeName = routeName;
    }

    public String getRoutePath() {
        return routePath;
    }

    public String getRouteName() {
        return routeName;
    }

}
