package edu.miu.cs.cs425.studentmgmt.dto;

public class ClassroomDTO {

    private Long classroomId;
    private String buildingName;
    private String roomNumber;

    public ClassroomDTO() {}

    public Long getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Long classroomId) {
        this.classroomId = classroomId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    
    // getters and setters
}