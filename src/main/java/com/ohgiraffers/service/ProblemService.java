package com.ohgiraffers.service;

import com.ohgiraffers.dto.ProblemDTO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ProblemService {
    private final Map<Integer, List<ProblemDTO>> problemListMap = new HashMap<>();
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public void solve(int round) {
        try {
            if(problemListMap.get(round) == null) {
                System.out.println("해당 회차는 등록되자 않은 회차입니다.");
            } else {
                List<ProblemDTO> problemList = problemListMap.get(round);

                for(int i = 0; i < problemList.size(); i++) {
                    problemSelect(i + 1, problemList.get(i));
                    System.out.println("Y를 입력하지 않으면 종료합니다.");
                    System.out.println("다음 문제를 푸시겠습니까 ? ");
                    if(!br.readLine().equalsIgnoreCase("Y")) break;
                }
            }
        } catch (Exception e) {
            System.out.println("ProblemService solve() Exception : " + e);
        }

    }

    public void problemSelect(int number, ProblemDTO problemDTO) throws Exception {
        System.out.println(number + ". " + problemDTO.getProblemTitle());
        System.out.println(problemDTO.getProblemDescription());

        System.out.print("정답을 입력하세요 : ");
        int answer = Integer.parseInt(br.readLine());
        if(problemDTO.getProblemAnswer().get(number) == answer) {
            System.out.println("정답입니다.");
        }
    }


}
