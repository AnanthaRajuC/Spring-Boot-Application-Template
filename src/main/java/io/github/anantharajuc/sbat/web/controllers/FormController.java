package io.github.anantharajuc.sbat.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.github.anantharajuc.sbat.web.domain.frontend.Form;
import lombok.extern.log4j.Log4j2;

/**
 * FormController
 *
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 *
 */
@Log4j2
@Controller
@RequestMapping({"/sbat"})
public class FormController 
{
	private static final String COMMAND = "command";  
	
	@GetMapping("/form")
	public String fooForm(Model model)
	{
		model.addAttribute(COMMAND, new Form());
		
		return "pages/form";
	}
	
	@ModelAttribute("multiCheckboxAllValues")
    public String[] getMultiCheckboxAllValues() 
	{
        return new String[] {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    }
	
	// set of values applied to a single-select radio button set, and drop-down list.
	@ModelAttribute("singleSelectAllValues")
    public String[] getSingleSelectAllValues() 
	{
        return new String[] {"YES", "NO", "MAYBE"};
    }
		
	@PostMapping("/form")
	public String foobarPost(@ModelAttribute("command") Form command, BindingResult bindingResult, Model model, RedirectAttributes ra) 
	{
		// WARN: BindingResult *must* immediately follow the Command.
		// https://stackoverflow.com/a/29883178/1626026
		
		log.info("form submission.");

		log.info(model.getAttribute(COMMAND));  
		
		log.info("Color Field "+command.getColorField()); 
		log.info("Datetime Field "+command.getDatetimeField()); 
		log.info("Dropdown Selected Value "+command.getDropdownSelectedValue()); 
		log.info("Radio Button Selected Value "+command.getRadioButtonSelectedValue()); 
		log.info("Text-area Field "+command.getTextareaField()); 
		log.info("Text Field "+command.getTextField()); 
		log.info("Multi-Checkbox Selected Values "+command.getMultiCheckboxSelectedValues()); 
		
		if (bindingResult.hasErrors()) 
		{
			return "pages/form";
		}

		ra.addFlashAttribute(COMMAND, command);
		
		return "redirect:/index";
	}
}
