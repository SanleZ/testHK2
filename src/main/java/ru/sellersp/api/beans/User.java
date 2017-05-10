package ru.sellersp.api.beans;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sanle on 21.04.2017.
 */
@XmlRootElement
public class User {

    private int userVkId;
    private String userName;
    private String email;
    private String profilePicture;
    private String vkToken;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    private Role role;

    private Map<String, List<String>> photosMap = new HashMap<>();

    public int getUserVkId() {
        return userVkId;
    }

    public void setUserVkId(int userVkId) {
        this.userVkId = userVkId;
    }

    public String getVkToken() {
        return vkToken;
    }

    public void setVkToken(String vkToken) {
        this.vkToken = vkToken;
    }

    public Map<String, List<String>> getPhorosMap() {
        return photosMap;
    }

    @XmlTransient
    public void setPhotosMap(Map<String, List<String>> phorosMap) {
        this.photosMap = phorosMap;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Map<String, List<String>> getPhotosMap() {
        return photosMap;
    }

    public User(){
    }

}
