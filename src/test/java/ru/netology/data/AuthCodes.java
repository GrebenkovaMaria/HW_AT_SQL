package ru.netology.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AuthCodes {
    private String id;
    private String user_id;
    private String code;
    private SimpleDateFormat created;

    public AuthCodes(String id, String code) {
        this.id = id;
        this.code = code;
    }

    public AuthCodes(String code) {
        this.code = code;
    }
}