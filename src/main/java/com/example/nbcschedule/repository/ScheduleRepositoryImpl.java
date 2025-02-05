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
import java.util.ArrayList;
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

        String sql = "INSERT INTO schedule (task, member_id, created_at, updated_at) VALUES (?, ?, ?, ?, ?)";
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

    // Lv 1전체 일정 조회
    @Override
    public List<Schedule> findAll() {
        StringBuilder sql = new StringBuilder("SELECT id, task, member_id, created_at, updated_at FROM schedule WHERE 1 = 1");
        List<Object> params = new ArrayList<>();

        sql.append("ORDER BY updated_at DESC");

        return jdbcTemplate.query(
                sql.toString(),
                (rs, rowNum) -> new Schedule(
                        rs.getLong("id"),
                        rs.getString("task"),
                        rs.getString("member_name"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("update_at").toLocalDateTime()
                ),
                params.toArray(new Object[0])
        );
    }

    // Lv 1선택 일정 조회
    @Override
    public Optional<Schedule> findById(Long id) {
        String sql = "SELECT id, task, member_id, created_at, updated_at FROM schedule WHERE id = ?";
        List<Schedule> list = jdbcTemplate.query(sql, (rs, rowNum) -> new Schedule(
                rs.getLong("id"),
                rs.getString("task"),
                rs.getString("member_name"),
                rs.getTimestamp("created_at").toLocalDateTime(),
                rs.getTimestamp("update_at").toLocalDateTime()
        ), id);
        return list.stream().findAny();
    }

    @Override
    public Schedule update(Schedule schedule) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
