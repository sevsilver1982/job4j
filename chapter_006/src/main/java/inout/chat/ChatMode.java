package inout.chat;

enum ChatMode {
    NORMAL("продолжить"),
    SILENT("стоп"),
    EXIT("закончить"),
    DEFAULT("");

    String value;

    ChatMode(String value) {
        this.value = value;
    }

    String getValue() {
        return value;
    }

}