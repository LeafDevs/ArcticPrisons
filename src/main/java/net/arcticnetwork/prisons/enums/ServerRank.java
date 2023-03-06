package net.arcticnetwork.prisons.enums;

public enum ServerRank {

    DEFAULT("&7", "default"),
    IRON("&f[IRON] ", "iron"),
    GOLD("&e[GOLD] ", "gold"),
    DIAMOND("&b[DIAMOND] ", "diamond"),
    EMERALD("&a[EMERALD] ", "emerald"),
    HELPER("&9[HELPER] ", "helper"),
    MODERATOR("&2[MOD] ", "mod"),
    ADMIN("&c[ADMIN] ", "admin"),
    DEVELOPER("&5[DEV] ", "dev"),
    OWNER("&6[OWNER] ", "owner"),
    ;

    private final String prefix;
    private final String id;

    ServerRank(String prefix, String id) {
        this.prefix = prefix;
        this.id = id;
    }

    public String getId() {
        return id;
    }


    public static ServerRank getServerRankByID(String id) {
        switch(id) {
            case "owner":
                return OWNER;
            case "admin":
                return ADMIN;
            case "dev":
                return DEVELOPER;
            case "mod":
                return MODERATOR;
            case "helper":
                return HELPER;
            case "emerald":
                return EMERALD;
            case "diamond":
                return DIAMOND;
            case "gold":
                return GOLD;
            case "iron":
                return IRON;
            default:
                return DEFAULT;
        }
    }

    public String getPrefix() {
        return prefix;
    }
    
}
