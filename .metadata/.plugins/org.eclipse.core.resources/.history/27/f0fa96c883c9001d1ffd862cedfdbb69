package com.blog.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class CategoriDto {

	private Integer categoriId;
	@NotEmpty
	@Size(min= 3, message="Cagetori name must be at least 3 charecter")
	private String categoriTitle;
	@NotEmpty
	@Size(min=6, message="Categori Description minumum 3 charecter")
	private String categoriDescription;

}
