package cn.ananyz.cp.service.enums;

public enum GameTypeEnum {
    //1:重 2:北 3:天
    GAME_TYPE_ENUM_CQ("1","重庆"),
    GAME_TYPE_ENUM_BJ("2","北京"),
    GAME_TYPE_ENUM_TJ("3","天津"),
    ;

    private String gameType;

    private String gameName;

    GameTypeEnum(String gameType, String gameName) {
        this.gameType = gameType;
        this.gameName = gameName;
    }

    @Override
    public String toString() {
        return "GameTypeEnum{" +
                "gameType='" + gameType + '\'' +
                ", gameName='" + gameName + '\'' +
                '}';
    }

    public String getGameName() {
        return gameName;
    }

    public String getGameType() {
        return gameType;
    }

}
