package com.ohgiraffers;

import com.ohgiraffers.dto.ProblemDTO;
import com.ohgiraffers.service.ProblemService;
import com.ohgiraffers.service.RoundService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class Application {

    private static ProblemService problemService = new ProblemService();
    private static RoundService roundService = new RoundService();

    public static void main(String[] args) {

        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.println("===== SQLD 기출문제 프로그램 =====");
                System.out.println("1. 회차 선택");
                System.out.println("2. 회차 전체 조회");
                System.out.println("3. 회차 등록");
                System.out.println("4. 회차 삭제");
                System.out.println("5. 프로그램 종료");

                System.out.print("\r\n메뉴 선택 : ");
                int menuChoice = Integer.parseInt(br.readLine());

                switch(menuChoice) {
                    case 1: selectSQLRound(roundService.round(br), br); break;
                    case 2: roundService.selectAllRound(); break;
                    case 3: roundService.insertRound(roundService.round(br)); break;
                    case 4: roundService.deleteRound(br, roundService.round(br)); break;
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


    public static void selectSQLRound(int round, BufferedReader br) {
        try {

            List<ProblemDTO> selectRoundProblemList = roundService.selectRound(round); //선택한 회차의 문제 리스트

            if(selectRoundProblemList == null) {
                System.out.println("해당 회차는 등록되지 않은 회차입니다. \r\n");
            } else {
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
                    int problemNo;

                    switch(menuChoice) {
                        case 1: problemService.orderSolveProblem(br, selectRoundProblemList); break;
                        case 2:
                            problemNo = chooseProblemNo(br, selectRoundProblemList);
                            problemService.selectSolveProblem(br, problemNo, selectRoundProblemList.get(problemNo - 1)); break;
                        case 3:
                            selectRoundProblemList = problemService.insertProblem(br, selectRoundProblemList);
                            roundService.putProblemInProblemListMap(round, selectRoundProblemList, "생성");
                            break;
                        case 4:
                            problemNo = chooseProblemNo(br,selectRoundProblemList);
                            selectRoundProblemList = problemService.updateProblem(br, round, problemNo, selectRoundProblemList);
                            roundService.putProblemInProblemListMap(round, problemNo, selectRoundProblemList, "수정");
                            break;

                        case 5:
                            problemNo = chooseProblemNo(br, selectRoundProblemList);
                            selectRoundProblemList = problemService.deleteProblem(br, round, problemNo, selectRoundProblemList);
                            roundService.putProblemInProblemListMap(round, problemNo, selectRoundProblemList, "삭제");
                            break;
                        case 6:
                            problemService.selectAllProblemInRound(selectRoundProblemList); break;
                        case 9:
                            System.out.println("회차 선택으로 돌아갑니다."); return;
                        default:
                            System.out.println("번호를 잘 못 입력했습니다.");
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("selectSQLRound() Exception : " + e);
        }
    }

    public static int chooseProblemNo(BufferedReader br, List<ProblemDTO> selectRoundProblemList) {
        System.out.print("문제 번호를 선택해주세요 : ");
        int problemNum = 0;
        try {
            problemNum = Integer.parseInt(br.readLine());
            problemService.problemExistYn(problemNum, selectRoundProblemList);
        } catch (Exception e) {
            System.out.println("숫자를 입력해주세요. ");
            System.out.println("chooseProblemNo() Exception : " + e.getMessage());
        }
        return problemNum;
    }
}
