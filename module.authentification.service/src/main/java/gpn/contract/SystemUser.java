package gpn.contract;

import java.util.Date;
import java.util.List;

public class SystemUser {
    private Long id;
//    private String domainName;
    private String manFio;
    private String loginName;
    private String email;
//    private String guid;
    private int loginUserId;
    private Date lastUpdated;
    private List<Claim> claims;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

//    public String getDomainName() {
//        return domainName;
//    }
//
//    public void setDomainName(String domainName) {
//        this.domainName = domainName;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public String getDisplayName() {
//        return displayName;
//    }
//
//    public void setDisplayName(String displayName) {
//        this.displayName = displayName;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getGuid() {
//        return guid;
//    }
//
//    public void setGuid(String guid) {
//        this.guid = guid;
//    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public List<Claim> getClaims() {
        return claims;
    }

    public void setClaims(List<Claim> claims) {
        this.claims = claims;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getManFio() {
        return manFio;
    }

    public void setManFio(String manFio) {
        this.manFio = manFio;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public int getLoginUserId() {
        return loginUserId;
    }

    public void setLoginUserId(int loginUserId) {
        this.loginUserId = loginUserId;
    }
}
