package com.example.nbcschedule.repository;

import com.example.nbcschedule.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepositroy {

    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // create
    @Override
    public Schedule save(Schedule schedule) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        LocalDateTime now = LocalDateTime.now();
        schedule.setCreateDate(now);
        schedule.setUpdateDate(now);

        String sql = "INSERT INTO schedule (task, member_id, created_at, updated_at) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, schedule.getTask());
            ps.setString(2, schedule.getName());
            ps.setString(3, schedule.getPassword());
            ps.setTimestamp(4, java.sql.Timestamp.valueOf(schedule.getCreatedDate()));
            ps.setTimestamp(5, java.sql.Timestamp.valueOf(schedule.getUpdatedDate()));
            return ps;
        }, keyHolder);

        schedule.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        return schedule;
    }

    @Override
    public List<Schedule> findAll() {
        return List.of();
    }

    @Override
    public Optional<Schedule> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Schedule update(Schedule schedule) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
