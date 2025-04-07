package com.workintech.fswebs17d1.controller;

import com.workintech.fswebs17d1.entity.Animal;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/workintech/animal")
public class AnimalController {

    @Value("${course.name}")
    private String courseName;

    @Value("${project.developer.fullname}")
    private String developerFullName;

    private Map<Integer, Animal> animals;

    @PostConstruct
    public void init() {
        animals = new HashMap<>();
        animals.put(1, new Animal(1, "maymun"));
        animals.put(2, new Animal(2, "köpek"));
        animals.put(3, new Animal(3, "kedi"));
    }

    @GetMapping
    public List<Animal> getAllAnimals() {
        return animals.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Animal getAnimalById(@PathVariable Integer id) {
        if (!animals.containsKey(id)) {

            throw new RuntimeException("Animal not found with id: " + id);
        }
        return animals.get(id);
    }

    @PostMapping
    public Animal addAnimal(@RequestBody Animal animal) {
        if (animal == null || animal.getId() == null || animal.getName() == null) {
            throw new RuntimeException("Animal data is invalid");
        }
        animals.put(animal.getId(), animal);
        return animal;
    }

    @PutMapping("/{id}")
    public Animal updateAnimal(@PathVariable Integer id, @RequestBody Animal animal) {
        if (!animals.containsKey(id)) {
            throw new RuntimeException("Animal not found with id: " + id);
        }
        animal.setId(id); // ID'yi path variable ile gelen değere set ediyoruz
        animals.put(id, animal);
        return animal;
    }

    @DeleteMapping("/{id}")
    public Animal deleteAnimal(@PathVariable Integer id) {
        if (!animals.containsKey(id)) {
            throw new RuntimeException("Animal not found with id: " + id);
        }
        return animals.remove(id);
    }
}
