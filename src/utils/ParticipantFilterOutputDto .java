package com.utils;

import java.util.Date;
import java.util.List;

public class ParticipantsPayload {
    private String id;
    private String slug;
    private String name;
    private Date avatar;
}

public class Cursor {
    private String afterCursor;
    private String beforeCursor;
}

public class ParticipantFilterOutputDto {
    private List<ParticipantsPayload> data;
    private Cursor cursor;
}
