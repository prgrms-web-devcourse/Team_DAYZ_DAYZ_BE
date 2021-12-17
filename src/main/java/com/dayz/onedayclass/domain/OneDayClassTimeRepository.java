package com.dayz.onedayclass.domain;

import com.dayz.onedayclass.dto.query.CurrentOneDayClassTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OneDayClassTimeRepository extends JpaRepository<OneDayClassTime, Long> {

    final String SQL_findOneDayClassTimesByDate =
            "SELECT ot.onedayclass_time_id AS 'classTimeId' , o.max_people_number AS 'maxPeopleNumber', tmp.current_people_number AS 'currentPeopleNumber', ot.start_time AS 'startTime', ot.end_time AS 'endTime', ot.status AS 'status'"
            + "FROM onedayclass_time ot "
            + " INNER JOIN onedayclass o "
            + "    ON ot.onedayclass_id = o.onedayclass_id "
            + " INNER JOIN  ("
            + "     SELECT ot.onedayclass_time_id AS time_id, ot.class_date AS class_date, sum(r.people_number) AS current_people_number"
            + "     FROM onedayclass_time ot "
            + "     INNER JOIN reservation r "
            + "         ON ot.onedayclass_time_id = r.onedayclass_time_id"
            + "     GROUP BY ot.onedayclass_time_id "
            + "     HAVING class_date = :date"
            + "     ) AS tmp "
            + "         ON ot.onedayclass_time_id = tmp.time_id "
            + "WHERE ot.class_date = :date"
            + " AND ot.status = 'PROCESS'"
            + " AND ot.use_flag = TRUE"
            + " AND ot.onedayclass_id = :classId";
    @Query(value = SQL_findOneDayClassTimesByDate,
            nativeQuery = true)
    List<CurrentOneDayClassTime> findOneDayClassTimesByDate(@Param("classId") Long classId ,@Param("date") String date);

}
