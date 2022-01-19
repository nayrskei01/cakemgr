package com.waracle.cakemgr.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CakeDTO
{
	private Integer employeeId;

    private String title;

    private String desc;

    private String image;
}
