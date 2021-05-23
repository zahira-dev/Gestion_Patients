package org.sid.GestionPatient.entities;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Data@NoArgsConstructor @ToString @AllArgsConstructor
	public class Patient {
		
		@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private Long id;
	 @NotNull 
	@Size(min=5,max=15)
	 private String  nom;
	 @DateTimeFormat(pattern="yyyy-MM-dd")
	 private Date dateNaissance;
	 private boolean malade;
	 @DecimalMin("4")
	 private int score;
	 
}
