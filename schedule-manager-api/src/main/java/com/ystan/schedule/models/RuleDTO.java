package com.ystan.schedule.models;

import com.ystan.schedule.models.common.EntityDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RuleDTO extends EntityDTO {

    private Integer timesPerWeek;

    private String name;

//    private String groupId;
//
//    private String subjectId;
//
//    private String preferredRoomId;
//
//    private String preferredTeacherId;

    private GroupDTO group;

    private SubjectDTO subject;

    private ClassroomDTO preferredRoom;

    private TeacherDTO preferredTeacher;
}
