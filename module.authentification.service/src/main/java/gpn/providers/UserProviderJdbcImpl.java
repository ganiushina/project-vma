package gpn.providers;

import gpn.contract.Claim;
import gpn.contract.SystemUser;
import gpn.interfaces.providers.DataSourceBean;
import gpn.interfaces.providers.IUserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import javax.annotation.PostConstruct;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class UserProviderJdbcImpl implements IUserProvider {

    private Sql2o sql2o;
    private ZonedDateTime utc = ZonedDateTime.now(ZoneOffset.UTC);

    public UserProviderJdbcImpl(@Autowired DataSourceBean dataSource) {
        sql2o = new Sql2o(dataSource.getDataSource());
    }

    @PostConstruct
    public void init() {
        Map<String, String> mappings = new HashMap<>();
        mappings.put("login_name", "loginName");
        mappings.put("login_user_id", "loginUserId");
        mappings.put("login_date", "lastUpdated");
        sql2o.setDefaultColumnMappings(mappings);
    }

    @Override
    public SystemUser getUser(String userName) {
        try (Connection connection = sql2o.open()) {
            return connection.createQuery(findUserTemplate, false)
                    .addParameter("login_name", userName)
                    .executeAndFetchFirst(SystemUser.class);
        }
    }

    @Override
    public boolean isUserExists(String userName) {
        try (Connection connection = sql2o.open()) {
            Integer userCount = connection
                    .createQuery("SELECT count(*)  FROM [login] l\n" +
                            "  JOIN dbo.man m ON m.man_id = l.login_user_id\n" +
                            "  WHERE l.login_active = 1 AND l.login_name = = :userName", false)
                    .addParameter("login_name", userName)
                    .executeScalar(Integer.class);
            return userCount != null && userCount > 0;
        }
    }

    @Override
    public void update(SystemUser user) {
        try (Connection connection = sql2o.open()) {
            user.setId(connection.createQuery(MergeUserTemplate)
                    .addParameter("login_user_id", user.getLoginUserId())
                    .addParameter("login_name", user.getLoginName())
                    .addParameter("login_date", Date.from(utc.toInstant()))
                    .executeUpdate()
                    .getKey(Long.class));
        }
    }

    @Override
    public List<Claim> getClaims(Long userId) {
        try (Connection connection = sql2o.open()) {
            return connection.createQuery(getClaimsTemplate, false)
                    .addParameter("login_user_id", userId)
                    .executeAndFetch(Claim.class);
        }
    }

    private String MergeUserTemplate = "INSERT INTO dbo.login\n" +
            "  (\n" +
            "      login_user_id,     \n" +
            "      login_name,      \n" +
            "      login_date\n" +
            "  )\n" +
            "  VALUES (:id, :login_name, last_updated)";

    private final String findUserTemplate = "SELECT distinct l.login_name, l.login_user_id, l.login_admin, l.login_hash_new, " +
            "l.login_date, l.login_manager, m.man_fio, me.man_email_man_email email\n" +
            "  FROM [login] l\n" +
            "  JOIN dbo.man m ON m.man_id = l.login_user_id\n" +
            "  JOIN dbo.man_email me ON me.man_email_man_id = m.man_id\n" +
            "  WHERE l.login_active = 1 AND me.man_email_man_email LIKE '%altapersonnel.ru'\n" +
            "  AND l.login_name = :user_name;";

    private String getClaimsTemplate = "SELECT distinct l.login_user_id, l.login_admin,  l.login_manager \n" +
            "  FROM [login] l\n" +
            "  JOIN dbo.man m ON m.man_id = l.login_user_id\n" +
            "  JOIN dbo.man_email me ON me.man_email_man_id = m.man_id\n" +
            "  WHERE login_user_id = :uId";
}

