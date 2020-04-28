package eu.simple.persistance

import org.springframework.data.jpa.repository.JpaRepository

interface TestEntityRepo: JpaRepository<TestEntity, Long>