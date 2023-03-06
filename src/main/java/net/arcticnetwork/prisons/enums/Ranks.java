package net.arcticnetwork.prisons.enums;

public enum Ranks {

    ONE(0, "&8[&a1&8]", "1"),
    TWO(5000, "&8[&a2&8]", "2"),
    THREE(25000, "&8[&a3&8]", "3"),
    FOUR(85000, "&8[&a4&8]", "4"),
    FIVE(250000, "&8[&a5&8]", "5"),
    SIX(500000, "&8[&b6&8]", "6"),
    SEVEN(2500000, "&8[&b7&8]", "7"),
    EIGHT(5000000, "&8[&b8&8]", "8"),
    NINE(10000000, "&8[&b9&8]", "9"),
    TEN(25000000, "&8[&b10&8]", "10"),
    ELEVEN(60000000, "&8[&d11&8]", "11"),
    TWELVE(150000000, "&8[&d12&8]", "12"),
    THIRTEEN(300000000, "&8[&d13&8]", "13"),
    FOURTEEN(600000000, "&8[&d14&8]", "14"),
    FIFTEEN(1500000000, "&8[&d15&8]", "15"),
    SIXTEEN(3000000000L, "&8[&e16&8]", "16"),
    SEVENTEEN(6000000000L, "&8[&e17&8]", "17"),
    EIGHTEEN(12000000000L, "&8[&e18&8]", "18"),
    NINETEEN(24000000000L, "&8[&e19&8]", "19"),
    TWENTY(48000000000L, "&8[&e20&8]", "20"),
    TWENTY_ONE(96000000000L, "&8[&c21&8]", "21"),
    TWENTY_TWO(192000000000L, "&8[&c22&8]","22"),
    TWENTY_THREE(480000000000L, "&8[&c23&8]","23"),
    TWENTY_FOUR(2976000000000L, "&8[&c24&8]","24"),
    TWENTY_FIVE(5952000000000L, "&8[&425&8]","25"),
    ;

    private long amount;
    private String prefix;
    private String id;


    Ranks(long amount, String prefix, String id) {
        this.amount=amount;
        this.prefix=prefix;
        this.id=id;
    }

    public long getAmount() {
        return amount;
    }

    public String getId() {
        return id;
    }

    public static Ranks getFromName(String s) {
        switch(s) {
            case "1":
                return ONE;
            case "2":
                return TWO;
            case "3":
                return THREE;
            case "4":
                return FOUR;
            case "5":
                return FIVE;
            case "6":
                return SIX;
            case "7":
                return SEVEN;
            case "8":
                return EIGHT;
            case "9":
                return NINE;
            case "10":
                return TEN;
            case "11":
                return ELEVEN;
            case "12":
                return TWELVE;
            case "13":
                return THIRTEEN;
            case "14":
                return FOURTEEN;
            case "15":
                return FIFTEEN;
            case "16":
                return SIXTEEN;
            case "17":
                return SEVENTEEN;
            case "18":
                return EIGHTEEN;
            case "19":
                return NINETEEN;
            case "20":
                return TWENTY;
            case "21":
                return TWENTY_ONE;
            case "22":
                return TWENTY_TWO;
            case "23":
                return TWENTY_THREE;
            case "24":
                return TWENTY_FOUR;
            case "25":
                return TWENTY_FIVE;
            default:
                return ONE;
        }
    }

    public String getPrefix() {
        return prefix;
    }

    public long getUpgradePrice() {
        return amount;
    }
    
}
