package com.handicraft.vernissage.port.adapters.persistence.product;

import com.handicraft.vernissage.domain.user.Role;
import com.handicraft.vernissage.domain.user.User;
import com.handicraft.vernissage.domain.user.UserRepo;
import com.handicraft.vernissage.port.adapters.persistence.handlers.JdbcPostgresExecuterRepo;
import com.handicraft.vernissage.port.adapters.persistence.models.UserSQLModel;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.handicraft.vernissage.port.adapters.persistence.models.UserSQLModel.*;


@Repository
public class PostgresqlUserRepo implements UserRepo {

    private final JdbcPostgresExecuterRepo jdbcPostgresExecuterRepo;

    public PostgresqlUserRepo(JdbcPostgresExecuterRepo jdbcPostgresExecuterRepo) {
        this.jdbcPostgresExecuterRepo = jdbcPostgresExecuterRepo;
    }

    private static RowMapper<User> asUserRowMapping() {
        return (rs, _) -> new User(
                rs.getString(idCol),
                rs.getString(nameCol),
                Role.valueOf(rs.getString(roleCol)),
                rs.getString(timeZoneCol)
        );
    }

    private MapSqlParameterSource params(User user){
        var params = new MapSqlParameterSource();
        params.addValue(idCol, user.id());
        params.addValue(nameCol, user.name());
        params.addValue(roleCol, user.role().name());
        params.addValue(timeZoneCol, user.timeZone());
        return params;
    }

    @Override
    public void save(@NotNull User user) {
        jdbcPostgresExecuterRepo.save(table, UserSQLModel.columns(), params(user));
    }

    @Override
    public List<User> all() {
        return jdbcPostgresExecuterRepo.all(table, asUserRowMapping());
    }
}