package com.waracle.cakemgr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "Employee", uniqueConstraints 
	= {@UniqueConstraint(columnNames = "ID"),
			@UniqueConstraint(columnNames = "EMAIL")})
public class CakeEntity {
	/**
	 * Cake identification field.
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer employeeId;

	/**
	 * Cake title field.
	 */
    @Column(name = "EMAIL", unique = true, nullable = false, length = 100)
    private String title;

    /**
     * Cake description field.
     */
    @Column(name = "FIRST_NAME", unique = false, nullable = false, length = 100)
    private String desc;

    /**
     * Cake image url field.
     */
    @Column(name = "LAST_NAME", unique = false, nullable = false, length = 300)
    private String image;
}
