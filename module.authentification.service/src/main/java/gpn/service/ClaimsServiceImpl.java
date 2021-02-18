package gpn.service;

import gpn.contract.Claim;
import gpn.interfaces.providers.ClaimsProvider;
import gpn.interfaces.service.IClaimsService;
import gpn.util.VmaClaimTypes;
import gpn.util.VmaRoles;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Имплементация сервиса работы с правами пользователя
 */
@Service
public class ClaimsServiceImpl implements IClaimsService {

    private final ClaimsProvider claimsProvider;

    public ClaimsServiceImpl(ClaimsProvider claimsProvider) {
        this.claimsProvider = claimsProvider;
    }

    @Transactional
    @Override
    public void updateClaims(Long userId, List<Claim> claims) {
        Objects.requireNonNull(userId);
        Objects.requireNonNull(claims);
        claimsProvider.deleteClaims(userId);
        claimsProvider.updateClaims(userId, claims);
    }

    @Override
    public List<Claim> getDefaultUserClaims() {
        Claim claim = new Claim(VmaClaimTypes.ROLES, VmaRoles.DOMAIN_USER);
        Claim claimWithId = claimsProvider.getOrCreate(claim);
        return Collections.singletonList(claimWithId);
    }

}
