package com.ohgiraffers.service;

import com.ohgiraffers.dto.ProblemDTO;
import com.ohgiraffers.repository.RoundRepository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

public class RoundService {
    private final RoundRepository roundRepository = new RoundRepository();

    public int round(BufferedReader br) {
        int result = 0;

        try {
            System.out.print("\r\nSQLD 회차 번호를 입력해주세요 : ");

            result = Integer.parseInt(br.readLine());

        } catch (Exception e) {
            System.out.println("RoundService round() : " + e.getMessage());
        }

        return result;
    }

    public void selectAllRound() {

        Map<Integer, List<ProblemDTO>> problemListMap = roundRepository.selectAllRound();

        if(problemListMap.isEmpty()) {
            System.out.println("\r\n등록된 회차가 존재하지 않습니다. (회차가 1개도 등록되지 않았습니다.)\r\n");
        } else {
            for(Map.Entry<Integer, List<ProblemDTO>> entry : problemListMap.entrySet()) {
                System.out.println("SQLD " + entry.getKey() + "회");
            }
        }
    }

    public List<ProblemDTO> selectRound(int round) {
        return roundRepository.selectRound(round);
    }

    public void insertRound(int round) throws Exception {
        int result = roundRepository.insertRound(round);

        if(result > 0) {
            System.out.println(round + " 회차를 생성하였습니다.");
        } else {
            System.out.println("이미 존재하는 회차입니다.");
        }
    }

    public void deleteRound(BufferedReader br, int round) throws Exception {
        int result = roundRepository.deleteRound(br, round);
        if(result > 0) {
            System.out.println(round + " 회차를 삭제하였습니다.");
        } else {
            System.out.println(round + " 회차는 존재하지 않습니다.");
        }
    }

    public void putProblemInProblemListMap(int round, int problemNo, List<ProblemDTO> problemList, String word) {
        roundRepository.putProblemInProblemListMap(round, problemList);

        if(word.equals("생성") && problemNo == 0) {
            System.out.println(round + " 회차에 문제가 생성되었습니다.");
        } else {
            System.out.println(round + " 회차의 " + problemNo + "번 문제가 " + word +"되었습니다.");
        }
    }

    public void putProblemInProblemListMap(int round, List<ProblemDTO> problemList, String word) {
        putProblemInProblemListMap(round, 0, problemList, word);
    }
}
