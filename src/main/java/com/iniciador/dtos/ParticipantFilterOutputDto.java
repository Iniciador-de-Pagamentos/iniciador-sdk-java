package com.iniciador.dtos;

import lombok.Data;

import java.util.List;

@Data
public class ParticipantFilterOutputDto {
    public List<ParticipantsPayload> data;
    public Cursor cursor;

    public static class ParticipantsPayload {
        public String id;
        public String slug;
        public String name;
        public String avatar;
    }

    public static class Cursor {
        public String afterCursor;
        public String beforeCursor;
    }
}
