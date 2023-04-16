package com.teammiri.projecth.domain.project.entity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ProjectStatus {
    OPEN("OPEN", "모집중인 프로젝트"),
    IN_PROGRESS("IN_PROGRESS", "진행중인 프로젝트"),
    CLOSE("CLOSE", "종료된 프로젝트");

    private final String text;
    private final String displayName;
}
