package com.ohgiraffers.dto;

import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class ProblemDTO {
    private int problemNo;  // 문제 번호
    private String problemTitle;
    private String problemDescription;  // 문제 상세설명
    private Map<Integer, String> multipleChoice;    //객관식 답 (1. XXX, 2.XXX, 3.XXX, 4.XXX)
    private int problemAnswer; // 문제의 답
    // private AnswerDTO problemAnswer;
    private Date problemCreateDate;
    private Date problemModifyDate;
}
