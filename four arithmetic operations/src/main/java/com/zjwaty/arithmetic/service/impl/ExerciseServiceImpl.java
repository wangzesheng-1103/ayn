package com.zjwaty.arithmetic.service.impl;

import com.zjwaty.arithmetic.entity.Exercise;
import com.zjwaty.arithmetic.entity.Question;
import com.zjwaty.arithmetic.service.ExerciseService;
import org.springframework.stereotype.Service;
import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private static final String HISTORY_DIR = "data/history/";
    private Random random = new Random();

    @Override
    public Exercise generateExercise(String username, int count) {
        Exercise exercise = new Exercise();
        exercise.setUsername(username);

        for (int i = 0; i < count; i++) {
            Question question = generateQuestion();
            exercise.getQuestions().add(question);
        }

        return exercise;
    }

    @Override
    public Exercise submitExercise(Exercise exercise) {
        int correctCount = 0;

        for (Question question : exercise.getQuestions()) {
            boolean isCorrect = question.getUserAnswer() == question.getCorrectAnswer();
            question.setCorrect(isCorrect);
            if (isCorrect) {
                correctCount++;
            }
        }

        exercise.setScore(correctCount);
        exercise.setAccuracy((double) correctCount / exercise.getQuestions().size() * 100);
        exercise.setTimestamp(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));

        saveExercise(exercise);
        return exercise;
    }

    @Override
    public List<Exercise> getHistory(String username) {
        List<Exercise> history = new ArrayList<>();

        try {
            Files.createDirectories(Paths.get(HISTORY_DIR));

            List<Path> userFiles = Files.list(Paths.get(HISTORY_DIR))
                    .filter(path -> path.getFileName().toString().startsWith(username + "_"))
                    .collect(Collectors.toList());

            for (Path file : userFiles) {
                Exercise exercise = loadExercise(file);
                if (exercise != null) {
                    history.add(exercise);
                }
            }

            // 按时间倒序排列
            history.sort((e1, e2) -> e2.getTimestamp().compareTo(e1.getTimestamp()));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return history;
    }

    private Question generateQuestion() {
        int a = random.nextInt(100) + 1;
        int b = random.nextInt(100) + 1;
        String operator;
        int result;

        int op = random.nextInt(4);
        switch (op) {
            case 0: // 加法
                operator = "+";
                result = a + b;
                break;
            case 1: // 减法
                if (a < b) { int temp = a; a = b; b = temp; }
                operator = "-";
                result = a - b;
                break;
            case 2: // 乘法
                a = random.nextInt(20) + 1;
                b = random.nextInt(20) + 1;
                operator = "×";
                result = a * b;
                break;
            case 3: // 除法
                b = random.nextInt(10) + 1;
                a = b * (random.nextInt(10) + 1);
                operator = "÷";
                result = a / b;
                break;
            default:
                operator = "+";
                result = a + b;
        }

        String expression = a + " " + operator + " " + b + " = ";
        return new Question(expression, result);
    }

    private void saveExercise(Exercise exercise) {
        try {
            Files.createDirectories(Paths.get(HISTORY_DIR));

            String filename = HISTORY_DIR + exercise.getUsername() + "_" +
                    exercise.getTimestamp() + ".csv";

            try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
                writer.println("timestamp,question,correct_answer,user_answer,is_correct");

                for (Question question : exercise.getQuestions()) {
                    writer.printf("%s,%s,%d,%d,%b%n",
                            exercise.getTimestamp(),
                            question.getExpression(),
                            question.getCorrectAnswer(),
                            question.getUserAnswer(),
                            question.isCorrect());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Exercise loadExercise(Path file) {
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            Exercise exercise = new Exercise();
            String filename = file.getFileName().toString();
            String[] parts = filename.split("_");

            if (parts.length >= 2) {
                exercise.setUsername(parts[0]);
                exercise.setTimestamp(parts[1].replace(".csv", ""));
            }

            // 跳过标题行
            reader.readLine();

            String line;
            int correctCount = 0;
            int totalCount = 0;

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 5) {
                    Question question = new Question();
                    question.setExpression(fields[1]);
                    question.setCorrectAnswer(Integer.parseInt(fields[2]));
                    question.setUserAnswer(Integer.parseInt(fields[3]));
                    question.setCorrect(Boolean.parseBoolean(fields[4]));

                    exercise.getQuestions().add(question);

                    if (question.isCorrect()) {
                        correctCount++;
                    }
                    totalCount++;
                }
            }

            exercise.setScore(correctCount);
            exercise.setAccuracy(totalCount > 0 ? (double) correctCount / totalCount * 100 : 0);

            return exercise;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}