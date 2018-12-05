package com.semihunaldi.backendbootstrap.uithymeleaf.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by semihunaldi on 5.12.2018
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChartData {

	private Date x;
	private Integer y;
}