package com.ohgiraffers.service;

import com.ohgiraffers.dto.ProblemDTO;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RoundService {

    public int round(BufferedReader br) throws Exception {
        System.out.print("SQLD 회차 번호를 입력해주세요 : ");

        return Integer.parseInt(br.readLine());
    }

    public void selectRound(Map<Integer, List<ProblemDTO>> problemListMap) {
        if(problemListMap.isEmpty()) {
            System.out.println("\r\n등록된 회차가 존재하지 않습니다. \r\n");
        } else {
            for(Map.Entry<Integer, List<ProblemDTO>> entry : problemListMap.entrySet()) {
                System.out.println("SQLD " + entry.getKey() + "회");
            }
        }

    }

    public void insertRound(int round, Map<Integer, List<ProblemDTO>> problemListMap) {
        if(problemListMap.get(round) == null) {
            problemListMap.put(round, new ArrayList<>());
            System.out.println(round + " 회차를 생성하였습니다.");
        } else {
            System.out.println("이미 존재하는 회차입니다.");
        }
    }

    public void deleteRound(int round, Map<Integer, List<ProblemDTO>> problemListMap) {
        if(problemListMap.get(round) == null) {
            System.out.println(round + " 회차는 존재하지 않습니다.");
        } else {
            problemListMap.remove(round);
            System.out.println("해당 회차를 삭제하였습니다.");
        }
    }
}
