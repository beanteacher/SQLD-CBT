package com.ohgiraffers.controller;

import com.ohgiraffers.dto.ProblemDTO;
import com.ohgiraffers.service.ProblemService;
import com.ohgiraffers.service.RoundService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProblemController {
    private static final Map<Integer, List<ProblemDTO>> problemListMap = new HashMap<>();
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final ProblemService problemService = new ProblemService();

    public static void main(String[] args) {
        RoundService roundService = new RoundService();

        try {
            while (true) {
                System.out.println("===== SQLD 기출문제 프로그램 =====");
                System.out.println("1. 회차 선택");
                System.out.println("2. 회차 조회");
                System.out.println("3. 회차 등록");
                System.out.println("4. 회차 삭제");
                System.out.println("5. 프로그램 종료");

                System.out.print("\r\n메뉴 선택 : ");
                int menuChoice = Integer.parseInt(br.readLine());

                switch(menuChoice) {
                    case 1: selectSQLRound(roundService.round(br)); break;
                    case 2: roundService.selectRound(problemListMap); break;
                    case 3: roundService.insertRound(roundService.round(br), problemListMap); break;
                    case 4: roundService.deleteRound(roundService.round(br), problemListMap); break;
                    case 5:
                        System.out.println("기출 문제 프로그램을 종료합니다."); return;
                    default:
                        System.out.println("번호를 잘 못 입력했습니다.");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static void selectSQLRound(int round) {
        try {
            while(true) {
                System.out.println("===== SQLD " + round + "회 =====");
                System.out.println("1. 순서대로 문제 풀기");
                System.out.println("2. 선택하여 문제 풀기");
                System.out.println("3. 문제 생성");
                System.out.println("4. 문제 수정");
                System.out.println("5. 문제 삭제");
                System.out.println("6. 모든 문제와 답 조회");
                System.out.println("9. 회차 선택으로 돌아가기");
                System.out.print("\r\n메뉴 선택 : ");
                int menuChoice = Integer.parseInt(br.readLine());

                switch(menuChoice) {
                    case 1: problemService.orderSolveProblem(round, problemListMap); break;
                    case 2:
                        int problemNo = chooseProblemNo();
                        problemService.selectSolveProblem(problemNo, problemListMap.get(round).get(problemNo - 1)); break;
                    case 3: problemService.insertProblem(br, problemListMap.get(round)); break;
                    // case 4: break;
                    // case 5: break;
                    // case 6: break;
                    case 9:
                        System.out.println("회차 선택으로 돌아갑니다."); return;
                    default:
                        System.out.println("번호를 잘 못 입력했습니다.");
                }
            }
        } catch (Exception e) {
            System.out.println("selectRound Exception : " + e);
        }
    }

    public static int chooseProblemNo() {
        System.out.print("문제 번호를 선택헤주세요 : ");
        int rtn = 0;
        try {
            rtn = Integer.parseInt(br.readLine());
        } catch (Exception e) {
            System.out.println("chooseNo() Exception : " + e.getMessage());
        }
        return rtn;
    }
}
