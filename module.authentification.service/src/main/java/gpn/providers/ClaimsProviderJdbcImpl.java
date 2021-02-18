package gpn.providers;

import gpn.contract.Claim;
import gpn.interfaces.providers.ClaimsProvider;
import gpn.interfaces.providers.DataSourceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ClaimsProviderJdbcImpl implements ClaimsProvider {

    private final Sql2o sql2o;

    private static final String MERGE_GROUP_TEMPLATE = "insert into main.claim (type, code)\n" +
            "values(:type, :code)\n" +
            "on conflict (type, code)\n" +
            "do nothing";
    private static final String FIND_CLAIM_TEMPLATE = "select * from main.claim where type = :claim_type and code = :claim_value";
    private static final String DELETE_USER_CLAIMS_TEMPLATE = "delete from main.ad_user_claim where ad_user_id = :ad_user_id";
    private static final String INSERT_USER_CLAIMS_BEGIN = "insert into main.ad_user_claim (ad_user_id, claim_id) values ";


    public ClaimsProviderJdbcImpl(@Autowired DataSourceBean dataSource) {
        sql2o = new Sql2o(dataSource.getDataSource());
    }

    @Override
    public void deleteClaims(Long userId) {
        try (Connection connection = sql2o.open()) {
            connection.createQuery(DELETE_USER_CLAIMS_TEMPLATE)
                    .addParameter("ad_user_id", userId)
                    .executeUpdate();
        }
    }

    @Override
    public void updateClaims(Long userId, List<Claim> claims) {
        if (claims.isEmpty()) {
            return;
        }
        try (Connection connection = sql2o.open()) {
            // добавление новых прав юзера
            String values = claims.stream()
                    .map(claim -> String.format("(%s, %s)", userId, claim.getId()))
                    .collect(Collectors.joining(", "));
            String insertUserClaimsSql = INSERT_USER_CLAIMS_BEGIN + values;
            connection.createQuery(insertUserClaimsSql).executeUpdate();
        }
    }

    @Override
    public Claim getOrCreate(Claim claim) {
        Claim foundClaim = findClaim(claim);
        if (foundClaim != null) {
            return foundClaim;
        }
        return createClaim(claim);
    }

    private Claim createClaim(Claim claim) {
        try (Connection connection = sql2o.open()) {
            Long claimId = connection.createQuery(MERGE_GROUP_TEMPLATE)
                    .addParameter("type", claim.getType())
                    .addParameter("code", claim.getValue())
                    .executeUpdate()
                    .getKey(Long.class);
            claim.setId(claimId);
            return claim;
        }
    }

    private Claim findClaim(Claim claim) {
        try (Connection connection = sql2o.open()) {
            return connection.createQuery(FIND_CLAIM_TEMPLATE, false)
                    .addParameter("claim_type", claim.getType())
                    .addParameter("claim_value", claim.getValue())
                    .addColumnMapping("code", "value")
                    .executeAndFetchFirst(Claim.class);
        }
    }
}
