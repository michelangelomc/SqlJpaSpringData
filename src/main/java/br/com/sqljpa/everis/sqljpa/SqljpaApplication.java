package br.com.sqljpa.everis.sqljpa;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.sqljpa.everis.sqljpa.entities.People;
import br.com.sqljpa.everis.sqljpa.reporitories.PeopleRepository;

@SpringBootApplication
public class SqljpaApplication implements CommandLineRunner {

	@Autowired
	private PeopleRepository peopleRepository;

	public static void main(String[] args) {
		SpringApplication.run(SqljpaApplication.class, args);
	}

	Boolean hasDeleted = true;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("****************************************");
		System.out.println("*************Inicinado Transação**************");
		System.out.println("*************Hora Inicial**************");
		System.out.println(LocalDateTime.now());
		System.out.println("****************************************");

		// peopleRepository.saveAll(createPeople());
		createPeopleOneToOne();
		System.out.println("*************Hora Final**************");
		System.out.println(LocalDateTime.now());
		System.out.println("*************Comitado**************");
		System.out.println("****************************************");

		if (hasDeleted) {
			System.out.println("                                        ");
			System.out.println("****************************************");
			System.out.println("****************************************");

			System.out.println("************Delete**************");
			System.out.println("*************Hora Inicial**************");
			System.out.println(LocalDateTime.now());
			System.out.println("****************************************");

			try {
				List<People> peopleForDelete = peopleRepository.findAll();
				for (People people : peopleForDelete) {
					peopleRepository.delete(people);
				}

			} catch (Exception e) {
				System.out.println("Erro no delete ==>" + e.getMessage());
			}

			System.out.println("****************************************");
			System.out.println("*************Hora Final**************");
			System.out.println(LocalDateTime.now());
			System.out.println("*************Comitado**************");
			System.out.println("****************************************");
		}

	}

	private Collection<People> createPeople() {
		Collection<People> peoples = new ArrayList<People>();
		People people = new People();
		for (int i = 0; i < 7000000; i++) {
			people = new People(null, "Dev_Java_" + i);
			peoples.add(people);
		}
		return peoples;
	}

	private void createPeopleOneToOne() {
		People people = new People();
		for (int i = 0; i < 7000000; i++) {
			people = new People(null, "Dev_Java_" + i);
			peopleRepository.save(people);
		}
	}
}
