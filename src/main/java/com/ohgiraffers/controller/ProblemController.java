package com.ohgiraffers.controller;

import com.ohgiraffers.service.ProblemService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ProblemController {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final ProblemService problemService = new ProblemService();

    public static void main(String[] args) {

        try {
            System.out.println("===== SQLD 기출문제 프로그램 =====");
            while (true) {
                System.out.print("1. 회차 선택 : ");
                System.out.print("2. 회차 조회 : ");
                System.out.print("3. 회차 등록 : ");
                System.out.print("4. 회차 삭제 : ");
                System.out.print("5. 프로그램 종료 : ");

                System.out.print("메뉴 선택 : ");
                int menuChoice = Integer.parseInt(br.readLine());

                switch(menuChoice) {
                    case 1:
                        System.out.print("SQLD 회차 번호를 입력해주세요.");
                        int round = Integer.parseInt(br.readLine());
                        selectRound(round); break;
                    case 2: qustionService.selectQuestionNo(chooseNo()); break;
                    case 3: userService.registUser(signUp()); break;
                    case 4:
                        System.out.println("회원 관리 프로그램을 종료합니다."); return;
                    default:
                        System.out.println("번호를 잘 못 입력했습니다.");
                }

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void selectRound(int round) {
        try {
            while(true) {
                System.out.println("===== SQLD " + round + "회 =====");
                System.out.println("1. 순서대로 문제 풀기");
                System.out.println("2. 선택하여 문제 풀기");
                System.out.println("3. 문제 생성");
                System.out.println("4. 문제 수정");
                System.out.println("5. 문제 삭제");
                System.out.println("6. 모든 문제와 답 조회");
                System.out.println("9. 프로그램 종료");
                System.out.print("메뉴 선택 : ");
                int menuChoice = Integer.parseInt(br.readLine());

                switch(menuChoice) {
                    case 1: problemService.solve(round); break;
                    case 2: qustionService.selectQuestionNo(chooseNo()); break;
                    case 3: userService.registUser(signUp()); break;
                    case 4:
                        User selected = userService.findUserForModify(chooseNo());
                        if (selected == null) continue;
                        userService.modifyUser(reform(selected));
                        break;
                    case 5: userService.removeUser(chooseNo()); break;
                    case 6: userService.removeUser(chooseNo()); break;
                    case 9:
                        System.out.println("프로그램을 종료합니다."); return;
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
