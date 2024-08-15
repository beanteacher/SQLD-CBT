package com.ohgiraffers.service;

import com.ohgiraffers.dto.ProblemDTO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProblemService {

    public void problemExistYn(int problemNum, List<ProblemDTO> problemList) {
        try {
            problemList.get(problemNum - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("해당 문제는 존재하지 않는 문제입니다.");
        }
    }

    public void orderSolveProblem(BufferedReader br, List<ProblemDTO> selectRoundProblemList) {
        try {
            if(selectRoundProblemList.isEmpty())
                System.out.println("해당 회차에 문제가 존재하지 않습니다.");
            else {
                for (int i = 0; i < selectRoundProblemList.size(); i++) {
                    selectSolveProblem(br, i + 1, selectRoundProblemList.get(i));
                    if(i == selectRoundProblemList.size() - 1) {
                        System.out.println("다음 문제가 존재하지 않습니다.\n" +
                                "해당 회차 메뉴로 돌아갑니다.");
                        return;
                    }
                    System.out.println("다음 문제를 푸시겠습니까 ? (Y를 입력하지 않으면 회차선택으로 돌아갑니다) ");

                    if (!br.readLine().equalsIgnoreCase("Y")) break;
                }
            }
        } catch (Exception e) {
            System.out.println("ProblemService solve() Exception : " + e);
        }
    }

    public void selectSolveProblem(BufferedReader br, int number, ProblemDTO problemDTO) {
        System.out.println(number + ". " + problemDTO.getProblemTitle());
        System.out.println(problemDTO.getProblemDescription());
        for(Map.Entry<Integer, String> multipleChoiceMap : problemDTO.getMultipleChoice().entrySet()) {
            System.out.println(multipleChoiceMap.getKey() + ") " + multipleChoiceMap.getValue());
        }

        try {
            while(true) {
                System.out.print("정답을 입력하세요 : ");
                int answer = Integer.parseInt(br.readLine());
                if (problemDTO.getProblemAnswer() == answer) {
                    System.out.println("정답입니다.");
                    break;
                } else {
                    System.out.println("틀렸습니다.");
                    System.out.print("정답을 확인하시겠습니까 ? (Y를 입력하지 않으면 정답을 다시 입력할 수 있습니다.) ");

                    if(br.readLine().equalsIgnoreCase("Y")) {
                        // 정답은 1번 RANK() 입니다.
                        System.out.println("정답은 " +
                                problemDTO.getProblemAnswer() + "번" +
                                problemDTO.getMultipleChoice().get(problemDTO.getProblemAnswer()));
                        break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("ProblemService selectSolveProblem() Exception : " + e);
        }
    }

    public List<ProblemDTO> insertProblem(BufferedReader br, List<ProblemDTO> problemList) {
        try {
            ProblemDTO problemDTO = new ProblemDTO();

            problemDTO.setProblemNo(problemList.size() + 1);

            System.out.print("문제의 제목 입력해주세요 : ");
            problemDTO.setProblemTitle(br.readLine());

            System.out.print("문제의 상세설명을 입력해주세요 : ");
            problemDTO.setProblemDescription(br.readLine());

            problemDTO.setMultipleChoice(makeMultipleChoice(br));

            System.out.print("문제의 정답을 입력해주세요 : ");
            problemDTO.setProblemAnswer(Integer.parseInt(br.readLine()));

            problemList.add(problemDTO);

        } catch (Exception e) {
            System.out.println("문제 생성 시 문제가 발생하여 문제 생성에 실패하였습니다.");
            System.out.println("ProblemService insertProblem() Exception : " + e);
        }

        return problemList;
    }

    public Map<Integer, String> makeMultipleChoice(BufferedReader br) {
        Map<Integer, String> multipleChoiceMap = new HashMap<>();
        try {
            System.out.print("1번 선택지의 설명을 입력해주세요 : ");
            multipleChoiceMap.put(1, br.readLine());
            System.out.print("2번 선택지의 설명을 입력해주세요 : ");
            multipleChoiceMap.put(2, br.readLine());
            System.out.print("3번 선택지의 설명을 입력해주세요 : ");
            multipleChoiceMap.put(3, br.readLine());
            System.out.print("4번 선택지의 설명을 입력해주세요 : ");
            multipleChoiceMap.put(4, br.readLine());

        } catch (Exception e) {
            System.out.println("ProblemService makeMultipleChoice() Exception : " + e);
        }

        return multipleChoiceMap;
    }

    public  List<ProblemDTO> updateProblem(BufferedReader br, int round, int problemNo, List<ProblemDTO> problemList) {
        ProblemDTO modifiedProblemDTO = problemList.get(problemNo - 1);

        try {
            do {
                System.out.println("===== SQLD " + round + "회 " + problemNo + "번 문제 수정 메뉴=====");
                System.out.println("1. 문제 제목");
                System.out.println("2. 문제 상세설명");
                System.out.println("3. 객관식 답안");
                System.out.println("4. 문제의 답");
                System.out.println("9. SQLD " + round + "회로 돌아가기 (돌아가면 수정사항이 삭제됩니다.)");
                System.out.print("\r\n메뉴 선택 : ");
                int menuChoice = Integer.parseInt(br.readLine());
                switch (menuChoice) {
                    case 1:
                        System.out.print("수정할 문제 제목을 입력하세요 : ");
                        modifiedProblemDTO.setProblemTitle(br.readLine());
                        break;
                    case 2:
                        System.out.print("수정할 문제 상세설명을 입력하세요 : ");
                        modifiedProblemDTO.setProblemDescription(br.readLine());
                        break;
                    case 3:
                        modifiedProblemDTO.setMultipleChoice(makeMultipleChoice(br));
                        break;
                    case 4:
                        System.out.print("수정할 문제의 답을 입력하세요 : ");
                        modifiedProblemDTO.setProblemAnswer(Integer.parseInt(br.readLine()));
                        break;
                    case 9:
                        System.out.println("SQLD" + round + "회로 돌아갑니다.");

                        return problemList;
                    default:
                        System.out.println("번호를 잘못 입력했습니다.");
                }
                System.out.print("다른 수정사항을 수정하시겠습니까 ? (Y를 입력하지 않으면 지금까지의 수정사항을 저장합니다.) : ");
            } while (br.readLine().equalsIgnoreCase("Y")); {
                problemList.set(problemNo - 1, modifiedProblemDTO);
            }
        } catch (Exception e) {
            System.out.println("updateProblem() Exception : " + e);
        }
        return problemList;
    }

    public List<ProblemDTO> deleteProblem(BufferedReader br, int round, int problemNo, List<ProblemDTO> problemList) {
        System.out.print("SQLD " + round + "회 " + problemNo + "번 문제를 정말 삭제하시겠습니까 ? (Y를 입력하면 삭제합니다.)");

        try {
            if(br.readLine().equalsIgnoreCase("Y")) {
                problemList.remove(problemNo - 1);
            }
        } catch (Exception e) {
            System.out.println("ProblemService deleteProblem() Exception : " + e);
        }

        return problemList;
    }

    public void selectAllProblemInRound(List<ProblemDTO> problemList) {
        if(problemList.size() > 0) {

            for(int i = 0; i < problemList.size(); i++) {
                System.out.println(i + 1 +"번 문제. " + problemList.get(i).getProblemTitle() + " ( " +problemList.get(i).getProblemAnswer() + " )");
                System.out.println(problemList.get(i).getProblemDescription()+"\r\n");
                for(Map.Entry<Integer, String> entry : problemList.get(i).getMultipleChoice().entrySet()) {
                    System.out.println(entry.getKey() + ") " + entry.getValue());
                }
                System.out.println();
            }
        } else {
            System.out.println("등록된 문제가 없습니다.");
        }
    }
}