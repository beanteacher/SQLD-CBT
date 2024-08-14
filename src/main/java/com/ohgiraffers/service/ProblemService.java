package com.ohgiraffers.service;

import com.ohgiraffers.dto.ProblemDTO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProblemService {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public void orderSolveProblem(int round, Map<Integer, List<ProblemDTO>> problemListMap) {
        try {
            if(problemListMap.get(round) == null) {
                System.out.println("해당 회차는 등록되자 않은 회차입니다.");
            } else {
                List<ProblemDTO> problemList = problemListMap.get(round);
                if(problemList.isEmpty()) System.out.println("해당 회차에 문제가 존재하지 않습니다.");
                else {
                    for (int i = 0; i < problemList.size(); i++) {
                        selectSolveProblem(i + 1, problemList.get(i));
                        System.out.println("다음 문제를 푸시겠습니까 (Y를 입력하지 않으면 회차선택으로 돌아갑니다. ");
                        if (!br.readLine().equalsIgnoreCase("Y")) break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("ProblemService solve() Exception : " + e);
        }
    }

    public void selectSolveProblem(int number, ProblemDTO problemDTO) throws Exception {
        System.out.println(number + ". " + problemDTO.getProblemTitle());
        System.out.println(problemDTO.getProblemDescription());
        String answerCheck = "N";   //정답 확인 여부 (기본 값 N)

        while(answerCheck.equals("Y")) {
            System.out.print("정답을 입력하세요 : ");
            int answer = Integer.parseInt(br.readLine());
            if (problemDTO.getProblemAnswer() == answer) {
                System.out.println("정답입니다.");

                return;
            } else {
                System.out.println("틀렸습니다.");
                System.out.print("정답을 확인하시겠습니까 (Y를 입력하지 않으면 정답을 다시 입력할 수 있습니다.) ?");
                answerCheck = br.readLine().toUpperCase();

                if(answerCheck.equals("Y"))
                    // 정답은 1번 RANK() 입니다.
                    System.out.println("정답은 " +
                            problemDTO.getProblemAnswer() + "번" +
                            problemDTO.getMultipleChoice().get(problemDTO.getProblemAnswer()));
            }
        }
    }

    public void insertProblem(BufferedReader br, List<ProblemDTO> problemList) throws Exception {
        ProblemDTO problemDTO = new ProblemDTO();

        problemDTO.setProblemNo(problemList.size() + 1);

        System.out.print("문제의 제목 입력해주세요 : ");
        problemDTO.setProblemTitle(br.readLine());
        System.out.println("문제의 상세설명을 입력해주세요 : ");
        problemDTO.setProblemDescription(br.readLine());
        problemDTO.setMultipleChoice(insertMultipleChoice());
        System.out.println("문제의 정답을 입력해주세요 : ");
        problemDTO.setProblemAnswer(Integer.parseInt(br.readLine()));
    }

    public Map<Integer, String> insertMultipleChoice() throws Exception {
        Map<Integer, String> multipleChoiceMap = new HashMap<>();
        System.out.print("1번 선택지의 설명을 입력해주세요 : ");
        multipleChoiceMap.put(1, br.readLine());
        System.out.print("2번 선택지의 설명을 입력해주세요 : ");
        multipleChoiceMap.put(2, br.readLine());
        System.out.print("3번 선택지의 설명을 입력해주세요 : ");
        multipleChoiceMap.put(3, br.readLine());
        System.out.print("4번 선택지의 설명을 입력해주세요 : ");
        multipleChoiceMap.put(4, br.readLine());

        return multipleChoiceMap;
    }
}