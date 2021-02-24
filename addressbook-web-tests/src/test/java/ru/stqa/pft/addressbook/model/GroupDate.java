package ru.stqa.pft.addressbook.model;

public class GroupDate {
    private final String name;
    private final String id;
    private final String header;
    private final String footer;

    public String getId() {
        return id;
    }
    public GroupDate(String name, String header, String footer) {
        this.id = null;
        this.name = name;
        this.header = header;
        this.footer = footer;
    }


    public GroupDate(String id, String name, String header, String footer) {
        this.name = name;
        this.id = id;
        this.header = header;
        this.footer = footer;
    }


    public String getName() {
        return name;
    }

    public String getHeader() {
        return header;
    }

    public String getFooter() {
        return footer;
    }

    @Override
    public String toString() {
        return "GroupDate{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupDate groupDate = (GroupDate) o;

        if (name != null ? !name.equals(groupDate.name) : groupDate.name != null) return false;
        return id != null ? id.equals(groupDate.id) : groupDate.id == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}
