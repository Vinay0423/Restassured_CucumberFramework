package stepDefinations;


import org.junit.Test;
import org.testng.*;
import org.testng.annotations.Parameters;

import io.cucumber.java.en.*;

public class Testing {
	@Given("want to write a step with precondition")
	public void want_to_write_a_step_with_precondition() {
	  System.out.println("given");
	}

	@Parameters("method1")
	@When("I complete {string}")
	public void i_complete(String action, int method1) {
	  System.out.println(method1+"when"+ action);
	}

	@Then("I validate the {string}")
	public void i_validate_the(String outcome) {
	   System.out.println("then"+outcome);
	}



}
