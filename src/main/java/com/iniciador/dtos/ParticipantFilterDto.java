package com.iniciador.dtos;

import lombok.Data;

@Data
public class ParticipantFilterDto {
    public String id;
    public String name;
    public String status;
    public String firstParticipants;
    public String limit;
    public String afterCursor;
    public String beforeCursor;
}
