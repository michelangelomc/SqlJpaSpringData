package br.com.sqljpa.everis.sqljpa.utilities;

import java.time.Duration;
import java.time.LocalDateTime;

public class Utils {

	private LocalDateTime hourStart;
	private LocalDateTime hourFinal;

	public Utils(LocalDateTime hourStart, LocalDateTime hourFinal) {
		this.hourStart = hourStart;
		this.hourFinal = hourFinal;
	}

	public LocalDateTime getHourStart() {
		return hourStart;
	}

	public void setHourStart(LocalDateTime hourStart) {
		this.hourStart = hourStart;
	}

	public LocalDateTime getHourFinal() {
		return hourFinal;
	}

	public void setHourFinal(LocalDateTime hourFinal) {
		this.hourFinal = hourFinal;
	}

	public Long durationInSegundos() {
		return Duration.between(hourStart, this.hourFinal).toSeconds() - (durationInMinutes() * 60);
	}
	
	public Long durationInMinutes() {
		return Duration.between(hourStart, this.hourFinal).toMinutes();
	}

	
	public Long durationInHour() {
		return Duration.between(hourStart, this.hourFinal).toHours();
	}
}