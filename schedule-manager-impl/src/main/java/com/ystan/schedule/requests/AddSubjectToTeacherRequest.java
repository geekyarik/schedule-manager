package com.ystan.schedule.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddSubjectToTeacherRequest {

    private String teacherId;
    private String subjectId;
}
