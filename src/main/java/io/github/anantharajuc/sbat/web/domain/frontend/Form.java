package io.github.anantharajuc.sbat.web.domain.frontend;

import lombok.Data;

@Data
public class Form 
{
	String textField;
	
	String textareaField;
	
	String datetimeField;
	
	String colorField;
	
	boolean singleCheckboxField;
	
	String[] multiCheckboxSelectedValues;
	
	String radioButtonSelectedValue;

	String dropdownSelectedValue;
}
