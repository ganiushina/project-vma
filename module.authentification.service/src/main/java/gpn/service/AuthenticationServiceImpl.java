package gpn.service;

import gpn.contract.Claim;
import gpn.contract.SystemUser;
import gpn.exception.ApplicationException;
import gpn.exception.UserNotFoundException;
import gpn.interfaces.service.IAuthenticationService;
import gpn.interfaces.service.IClaimsService;
import gpn.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.jar.Attributes;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;
//    @Autowired
//    private ILdapService ldapService;
    @Autowired
    private UserService userService;
    @Autowired
    private IClaimsService claimsService;

    @Override
    public String getAuthToken(String userName) throws UserNotFoundException, ApplicationException {
//        SearchResult searchResult;
//        try {
//            searchResult = ldapService.findAccountByAccountName(userName);
//        } catch (NamingException e) {
//            throw new ApplicationException(e);
//        }
//        if (searchResult == null) {
//            throw new UserNotFoundException();
//        }

        SystemUser sUser = new SystemUser();
        sUser.setLoginName(userName);


        sUser.setEmail("-");

//        try {
//            fillAttributes(sUser, searchResult);
//        } catch (NamingException e) {
//            throw new ApplicationException(e);
//        }

        SystemUser cUser = userService.getUser(sUser.getLoginName());

        boolean isNewUser = userService.update(sUser);
        if (isNewUser) {
            List<Claim> defaultClaims = claimsService.getDefaultUserClaims();
            claimsService.updateClaims(sUser.getId(), defaultClaims);
        }

        if (cUser != null) {
            sUser.setId(cUser.getId());
            sUser.setClaims(userService.getClaims(sUser.getId()));
        }

//        SystemUser sUser = new SystemUser();

//        sUser.setId(1L);
//        sUser.setDomainName("user1");
//        sUser.setUserName("user2");
//        sUser.setDisplayName("user3");
//        sUser.setEmail("mail");
//        sUser.setGuid("uuid");

        final UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        return jwtTokenUtil.generateToken(userDetails, sUser);
    }

    /**
     * запонение контракта из ldap
     * @param ldapUser контракт
     * @param searchResult результат поиска из Ldap
     */
//    private void fillAttributes(SystemUser ldapUser, SearchResult searchResult) throws NamingException {
//        Attributes attributes = searchResult.getAttributes();
//        ldapUser.setUserName(attributes.get("mailnickname").get(0).toString());
//        ldapUser.setDomainName(attributes.get("mailnickname").get(0).toString());
//        ldapUser.setDisplayName(attributes.get("displayname").get(0).toString());
//        ldapUser.setEmail(attributes.get("mail").get(0).toString());
//        ldapUser.setGuid(attributes.get("objectguid").get(0).toString());
//    }
}
