package br.com.sqljpa.everis.sqljpa.reporitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sqljpa.everis.sqljpa.entities.People;

@Repository
public interface PeopleRepository extends JpaRepository<People, Integer> {

}