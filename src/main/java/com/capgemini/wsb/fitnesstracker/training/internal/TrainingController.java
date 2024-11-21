package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import com.capgemini.wsb.fitnesstracker.training.internal.TrainingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
public class TrainingController {

    private final TrainingRepository trainingRepository;

    @GetMapping
    public List<Training> getAllTrainings() {
        return trainingRepository.findAll();
    }

    @GetMapping("/{userId}")
    public List<Training> getTrainingsByUser(@PathVariable Long userId) {
        return trainingRepository.findByUserId(userId);
    }

    @GetMapping("/finished/{afterTime}")
    public List<Training> getFinishedTrainingsAfter(@PathVariable Date afterTime) {
        return trainingRepository.findByEndTimeAfter(afterTime);
    }

    @GetMapping("/activityType")
    public List<Training> getTrainingsByActivityType(@RequestParam ActivityType activityType) {
        return trainingRepository.findByActivityType(activityType);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Training createTraining(@RequestBody Training training) {
        return trainingRepository.save(training);
    }

    @PutMapping("/{trainingId}")
    public Training updateTraining(@PathVariable Long trainingId, @RequestBody Training updatedTraining) {
        Training existingTraining = trainingRepository.findById(trainingId)
                .orElseThrow(() -> new IllegalArgumentException("Training not found"));
        existingTraining.setStartTime(updatedTraining.getStartTime());
        existingTraining.setEndTime(updatedTraining.getEndTime());
        existingTraining.setActivityType(updatedTraining.getActivityType());
        existingTraining.setDistance(updatedTraining.getDistance());
        existingTraining.setAverageSpeed(updatedTraining.getAverageSpeed());
        return trainingRepository.save(existingTraining);
    }

    @DeleteMapping("/{trainingId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTraining(@PathVariable Long trainingId) {
        trainingRepository.deleteById(trainingId);
    }
}
