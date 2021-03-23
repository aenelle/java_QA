package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@XStreamAlias("group")
@Entity
@Table (name = "group_list")
public class GroupDate {
    @XStreamOmitField
    @Id
    @Column(name = "group_id")
    private  int id = Integer.MAX_VALUE;
    @Expose
    @Column(name = "group_name")
    private  String name;
    @Expose
    @Column(name = "group_header")
    @Type(type = "text")
    private  String header;
    @Expose
    @Column(name = "group_footer")
    @Type(type = "text")
    private  String footer;



    public GroupDate withId (int id) {
        this.id = id;
        return this;
    }

    public GroupDate withName(String name) {
        this.name = name;
        return this;
    }

    public GroupDate withHeader(String header) {
        this.header = header;
        return this;
    }

    public GroupDate withFooter(String footer) {
        this.footer = footer;
        return this;
    }


    public int getId() {
        return id;
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
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupDate groupDate = (GroupDate) o;

        if (id != groupDate.id) return false;
        if (name != null ? !name.equals(groupDate.name) : groupDate.name != null) return false;
        if (header != null ? !header.equals(groupDate.header) : groupDate.header != null) return false;
        return footer != null ? footer.equals(groupDate.footer) : groupDate.footer == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (header != null ? header.hashCode() : 0);
        result = 31 * result + (footer != null ? footer.hashCode() : 0);
        return result;
    }
}
