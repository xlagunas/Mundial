package models;

/**
 * Created by Z77-DS3H on 03/07/2014.
 */
public class Team {
    private String country;
    private String alternate_name;
    private String fifa_code;
    private String group_id;

    public Team(String country, String alternate_name, String fifa_code, String group_id) {
        this.country = country;
        this.alternate_name = alternate_name;
        this.fifa_code = fifa_code;
        this.group_id = group_id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAlternate_name() {
        return alternate_name;
    }

    public void setAlternate_name(String alternate_name) {
        this.alternate_name = alternate_name;
    }

    public String getFifa_code() {
        return fifa_code;
    }

    public void setFifa_code(String fifa_code) {
        this.fifa_code = fifa_code;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }
}
