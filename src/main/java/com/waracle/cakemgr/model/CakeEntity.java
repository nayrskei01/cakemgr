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
@Table(name = "Cakes", uniqueConstraints 
	= {@UniqueConstraint(columnNames = "ID"),
			@UniqueConstraint(columnNames = "EMAIL")})
public class CakeEntity
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer employeeId;

    @Column(name = "EMAIL", unique = false, nullable = false, length = 100)
    private String title;

    @Column(name = "FIRST_NAME", unique = false, nullable = false, length = 100)
    private String desc;

    @Column(name = "LAST_NAME", unique = false, nullable = false, length = 300)
    private String image;
}
