package com.equinity.tennis.points;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Points {
    LOVE(0, "0"),
    FIFTEEN(1, "15"),
    THIRTY(2, "30"),
    FORTY(3, "40"),
    AD(4, "AD");

    private int value;
    private String representation;

    public static String getRepresentationByValue(int value) {
        for (Points point : values()) {
            if (point.value == value) {
                return point.representation;
            }
        }
        return "";
    }


}
