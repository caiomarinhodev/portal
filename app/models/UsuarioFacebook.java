package models;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Created by caio on 09/03/15.
 */
public class UsuarioFacebook {

    private Long id;
    private String firstName;
    private Integer timezone;
    private String email;
    private Boolean verified;
    private String middleName;
    private String gender;
    private String lastName;
    private String link;
    private String locale;
    private String name;
    private String updatedTime;

    public UsuarioFacebook(JsonNode jsonUsuario){

        id = jsonUsuario.findPath("id").longValue();
        firstName = jsonUsuario.findPath("first_name").textValue();
        timezone = jsonUsuario.findPath("timezone").intValue();
        email = jsonUsuario.findPath("email").textValue();
        verified = jsonUsuario.findPath("verified").booleanValue();
        middleName = jsonUsuario.findPath("middle_name").textValue();
        gender = jsonUsuario.findPath("gender").textValue();
        lastName = jsonUsuario.findPath("last_name").textValue();
        link = jsonUsuario.findPath("link").textValue();
        locale = jsonUsuario.findPath("locale").textValue();
        name = jsonUsuario.findPath("name").textValue();
        updatedTime = jsonUsuario.findPath("updated_time").textValue();
    }

    @Override
    public String toString() {
        return "UsuarioFacebook [id=" + id + ", firstName=" + firstName
                + ", timezone=" + timezone + ", email=" + email + ", verified="
                + verified + ", middleName=" + middleName + ", gender="
                + gender + ", lastName=" + lastName + ", link=" + link
                + ", locale=" + locale + ", name=" + name + ", updatedTime="
                + updatedTime + "]";
    }

}