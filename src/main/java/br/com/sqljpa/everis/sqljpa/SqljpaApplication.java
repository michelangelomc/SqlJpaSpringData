package br.com.sqljpa.everis.sqljpa;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.sqljpa.everis.sqljpa.entities.People;
import br.com.sqljpa.everis.sqljpa.reporitories.PeopleRepository;
import br.com.sqljpa.everis.sqljpa.utilities.Utils;

@SpringBootApplication
public class SqljpaApplication implements CommandLineRunner {
	private DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	private LocalDateTime start;
	private LocalDateTime theEnd;

	@Autowired
	private PeopleRepository peopleRepository;

	public static void main(String[] args) {
		SpringApplication.run(SqljpaApplication.class, args);
	}

	Boolean hasDeleted = true;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("****************************************");

		//createPeople();
		 createPeopleOneToOne();

		if (hasDeleted) {

			try {
				setStart(LocalDateTime.now());

				List<People> peopleForDelete = peopleRepository.findAll();
				for (People people : peopleForDelete) {
					peopleRepository.delete(people);
				}

				setTheEnd(LocalDateTime.now());

				getResultOperation("DELETE");

			} catch (Exception e) {
				System.out.println("Erro no delete ==>" + e.getMessage());
			}

		}
	}

	private void createPeople() {
		Collection<People> peoples = new ArrayList<People>();
		People people = new People();

		setStart(LocalDateTime.now());

		for (int i = 0; i < 7000000; i++) {
			people = new People(null, "Dev_Java_" + i);
			peoples.add(people);
		}
		peopleRepository.saveAll(peoples);

		setTheEnd(LocalDateTime.now());

		getResultOperation("SAVEALL");
	}

	private void createPeopleOneToOne() {
		People people = new People();

		setStart(LocalDateTime.now());

		for (int i = 0; i < 7000000; i++) {
			people = new People(null, "Dev_Java_" + i);
			peopleRepository.save(people);
		}

		setTheEnd(LocalDateTime.now());

		getResultOperation("CREATE PEOPLE ONE TO ONE");
	}

	private void getResultOperation(String string) {
		Utils utils = new Utils(getStart(), getTheEnd());

		System.out.println(String.format("TRANSAÇÃO DE %s", string));
		System.out.println("****************************************");
		System.out.println(String.format("HORA INICIO DA EXECUÇÃO: %s", getStart().format(dateformatter)));
		System.out.println(String.format("HORA TERMINO DA EXECUÇÃO: %s", getTheEnd().format(dateformatter)));
		System.out.println(String.format("DURAÇÃO: %02d hrs - %02d mins - %02d seg", utils.durationInHour(),
				utils.durationInMinutes(), utils.durationInSegundos()));
	}

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public LocalDateTime getTheEnd() {
		return theEnd;
	}

	public void setTheEnd(LocalDateTime theEnd) {
		this.theEnd = theEnd;
	}
}
