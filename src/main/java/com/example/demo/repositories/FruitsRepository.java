package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.data.Fruit;

@Repository
public interface FruitsRepository extends JpaRepository<Fruit, Integer> {
	public List<Fruit> findByName(String key);
}
