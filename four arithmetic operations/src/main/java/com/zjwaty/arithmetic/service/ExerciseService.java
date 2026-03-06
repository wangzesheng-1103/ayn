package com.zjwaty.arithmetic.service;

import com.zjwaty.arithmetic.entity.Exercise;
import com.zjwaty.arithmetic.entity.Question;
import java.util.List;

public interface ExerciseService {
    Exercise generateExercise(String username, int count);
    Exercise submitExercise(Exercise exercise);
    List<Exercise> getHistory(String username);
}