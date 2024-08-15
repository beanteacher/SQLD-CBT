package com.ohgiraffers.repository;

import com.ohgiraffers.dto.ProblemDTO;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoundRepository {
    private static Map<Integer, List<ProblemDTO>> problemListMap = new HashMap<>();
    private static final String FILE_PATH = Paths.get("C:","SQLD-CBT").toString();

    public RoundRepository() {

        try {

            File file = new File(FILE_PATH);
            if(!file.exists()) {
                file.mkdir();
            }

            readFile(file);
        } catch (Exception e) {
            System.out.println("RoundRepository Exception" + e);
        }
    }

    public Map<Integer, List<ProblemDTO>> selectAllRound() {
        return problemListMap;
    }

    public List<ProblemDTO> selectRound(int round) {
        return problemListMap.get(round);
    }

    public int insertRound(int round) throws Exception{
        int result = 0;
        if(problemListMap.get(round) == null) {
            String filePath = Paths.get(FILE_PATH ,"SQLD " + (round) + "회 기출문제.dat").toString();
            File file = new File(filePath);
            file.createNewFile();

            problemListMap.put(round, new ArrayList<>());
            result++;
        }
        return result;
    }

    public int deleteRound(BufferedReader br, int round) throws Exception {
        int result = 0;
        if(problemListMap.get(round) != null) {
            System.out.println("SQLD " + round +"회 기출문제를 정말로 삭제하시겠습니까 ? (Y 입력 시 삭제됩니다.)");
            if(br.readLine().equalsIgnoreCase("Y")) {
                Path filePath = Paths.get(FILE_PATH ,"SQLD " + (round) + "회 기출문제.dat");
                Files.delete(filePath);

                problemListMap.remove(round);
                result++;
            }
        }
        return result;
    }

    public void putProblemInProblemListMap(int round, List<ProblemDTO> problemList) {
        String filePath = Paths.get(FILE_PATH ,"SQLD " + (round) + "회 기출문제.dat").toString();
        File file = new File(filePath);

        problemListMap.put(round, problemList);
        saveProblem(file, problemList);
    }

    public void readFile(File file) {
        File[] problemFiles = file.listFiles();

        if(problemFiles == null || problemFiles.length == 0) {
            for(int i = 0; i < 2; i++) {
                List<ProblemDTO> problemList = new ArrayList<>();

                problemList = newProblemFile(i+1, problemList);
                String filePath = Paths.get(FILE_PATH ,"SQLD " + (i+1) + "회 기출문제.dat").toString();

                File problemFile = new File(filePath);

                if(!problemFile.exists()) {
                    saveProblem(problemFile, problemList);
                }
                loadProblem(problemFile);
            }
        } else {
            for(int i = 0; i < problemFiles.length; i++) {
                loadProblem(problemFiles[i]);
            }
        }
    }

    public List<ProblemDTO> newProblemFile(int key, List<ProblemDTO> problemList) {
        if(key == 1) {
            Map<Integer, String> multipleChoices1 = new HashMap<>();
            
            multipleChoices1.put(1, "수평 분할");
            multipleChoices1.put(2, "수직 분할");
            multipleChoices1.put(3, "중복 테이블 추가");
            multipleChoices1.put(4, "수직 및 수평 분할 수행");
            ProblemDTO problem1 = new ProblemDTO(1,
                    "특정 테이블에서 사원칼럼, 부서칼럼만 추출하는 경우에 DISK I/O를 경감할 수 있는 반정규화 방법은 무엇인가?",
                    "",
                     multipleChoices1,
                    2);
            problemList.add(problem1);

            Map<Integer, String> multipleChoices2 = new HashMap<>();
            multipleChoices2.put(1, "해당 릴레이션에 기본키를 식별한다.");
            multipleChoices2.put(2, "기본키가 하나 이상의 키로 되어 있는 경우에 부분함수 종속성을 제거한다.");
            multipleChoices2.put(3, "조인으로 발생하는 종속성을 제거한다.");
            multipleChoices2.put(4, "이행함수 종속성을 제거한다.");
            ProblemDTO problem2 = new ProblemDTO(2,
                    "다음 보기 중 3차 정규화에 대한 설명으로 올바른 것은?",
                    "",
                    multipleChoices2,
                    4);
            problemList.add(problem2);

            Map<Integer, String> multipleChoices3 = new HashMap<>();
            multipleChoices3.put(1, "ONE TO ONE");
            multipleChoices3.put(2, "PLUS TYPE");
            multipleChoices3.put(3, "SINGLE TYPE");
            multipleChoices3.put(4, "정답 없음");
            ProblemDTO problem3 = new ProblemDTO(3,
                    "다음은 ABC증권회사의 회원정보를 모델링 한 것이다.\n" +
                            "회원정보는 수퍼타입이고 개인회원과 법인회원 정보는 서브타입이다.\n" +
                            "애플리케이션에 회원정보를 조회하는 경우는 항상 개인회원과 법인회원을 동시에 조회하는 특성이 있을 때 수퍼타입과 서브타입을 변환하는 방법으로 가장 올바른 것은?",
                    "회원정보테이블\n" +
                            "회원번호\n" +
                            "회원구분\n" +
                            "고객등급 회원\n" +
                            "개인회원테이블\n" +
                            "회원번호(FK)\n" +
                            "이름\n" +
                            "주소\n" +
                            "법인회원테이블\n" +
                            "회원번호(FK)\n" +
                            "회사명\n" +
                            "대표자명",
                    multipleChoices3,
                    3);
            problemList.add(problem3);

        } else if(key == 2) {
            Map<Integer, String> multipleChoices4 = new HashMap<>();
            multipleChoices4.put(1, "INSERT INTO SQLD39_20 VALUES(10,20,SYSDATE);");
            multipleChoices4.put(2, "INSERT INTO SQLD39_20 VALUES(20,NULL,'A');");
            multipleChoices4.put(3, "INSERT INTO SQLD39_20(AGE, NAME) VALUES(20,'A');");
            multipleChoices4.put(4, "INSERT INTO SQLD39_20(ID, AGE, NAME) VALUES(20,10,NULL);");
            ProblemDTO problem4 = new ProblemDTO(1,
                    "다음 주어진 SQL문에서 오류가 발생하지 않는 것은?",
                    "CREATE TABLE SQLD39_20(\n" +
                            "ID NUMBER PRIMARY KET,\n" +
                            "AGE NUMBER NOT NULL,\n" +
                            "NAME VARCHAR2(1)\n" +
                            ");",
                    multipleChoices4,
                    4 );
            problemList.add(problem4);
            
            Map<Integer, String> multipleChoices5 = new HashMap<>();
            multipleChoices5.put(1, "PARENT_ID가 0이라도 3이 포함되면 전개를 멈춘다.");
            multipleChoices5.put(2, "순방향 전개다.");
            multipleChoices5.put(3, "중복이 생겼을 때 루프를 돌지 않기 위해 NO CYCLE 옵션을 사용할 수 있다.");
            multipleChoices5.put(4,  "ORDER SIBLINGS BY를 하면 전체 테이블 기준으로 정렬한다.");
            ProblemDTO problem5 = new ProblemDTO(2,
                    "다음 계층형 쿼리문에 대한 설명으로 옳지 않은 것은?",
                    "ID   PARENT_ID   NAME   PARENT_NAME   DEPTH\n" +
                            "-------------------------------------------\n" +
                            "3     0          A                    1\n" +
                            "4     0          B                    1\n" +
                            "5     3          C       A            2\n" +
                            "6     3          D       A            2\n" +
                            "7     3          E       A            2\n" +
                            "8     3          F       A            2\n" +
                            "9     6          G       F            3\n" +
                            "10    4          H       B            2\n" +
                            "11    4          I       B            2\n" +
                            "SELECT ID, PARENT_ID, NAME, PARENT_NAME\n" +
                            "FROM SQLD39_21\n" +
                            "WHERE PARENT_ID NOT IN(3)\n" +
                            "START WITH PARENT_ID = 0\n" +
                            "CONNECT BY PRIOR ID = PARENT_ID\n" +
                            "ORDER SIBLINGS BY PARENT_ID ASC, ID ASC;",
                    multipleChoices5,
                    4 );
            problemList.add(problem5);
            
            Map<Integer, String> multipleChoices6 = new HashMap<>();
            multipleChoices6.put(1, "INDEX UNIQUE SCAN");
            multipleChoices6.put(2, "INDEX RANGE SCAN");
            multipleChoices6.put(3, "INDEX RANGE SCAN DESCENDING");
            multipleChoices6.put(4, "INDEX FULL SCAN");
            ProblemDTO problem6 = new ProblemDTO(3,
                    "다음 보기에서 설명하는 인덱스 스캔 방식은 무엇인가?",
                    "[보기]\n" +
                            "인덱스를 역순으로 탐색한다.\n" +
                            "최댓값을 쉽게 찾을 수 있다.",
                    multipleChoices6,
                    3);

            problemList.add(problem6);
        }
        return problemList;
    }

    private void saveProblem(File file, List<ProblemDTO> problemList) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            for(int i = 0; i < problemList.size(); i++) {
                oos.writeObject(problemList.get(i));
            }
        } catch (Exception e) {
            System.out.println("ProblemRepository saveProblem() Exception : " + e);
        }
    }

    private void loadProblem(File file) {
        List<ProblemDTO> problemList = new ArrayList<>();

        try(ObjectInputStream ois = new ObjectInputStream((new FileInputStream(file)))) {
            while (true) {
                problemList.add((ProblemDTO) ois.readObject());
            }
        } catch (EOFException e) {
            // SQLD 1회 기출문제.dat
            problemListMap.put(
                    Integer.parseInt(file.getName().split(" ")[1].substring(0,1)),
                    problemList);
            System.out.println(file.getName() + " 문제를 모두 로딩하였습니다.\r\n");
        }  catch (Exception e) {
            System.out.println("ProblemRepository loadProblem() Exception : " + e);
        }
    }
}
