package com.waracle.cakemgr.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CakeDTO {
	/**
	 * Cake identification field.
	 */
	private Integer employeeId;

	/**
	 * Cake title field.
	 */
    private String title;

    /**
     * Cake description field.
     */
    private String desc;

    /**
     * Cake image url field.
     */
    private String image;
}
