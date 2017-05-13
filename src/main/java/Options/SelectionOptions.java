package Options;

public enum SelectionOptions {
    ADD("add"),
    REMOVE("remove");

    String type;

    SelectionOptions(String type) { this.type = type; }
    public String getType() { return this.type; }
}
