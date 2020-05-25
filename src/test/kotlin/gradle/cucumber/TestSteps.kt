package gradle.cucumber

import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.junit.Assert

class TestSteps {

    private var today: String? = null
    private var actualAnswer: String? = null

    @Given("today is {string}")
    fun setDay(day: String) {
        today = day
    }

    @When("I ask whether it's {string}")
    fun checkDay(day: String) {
        actualAnswer = if (day == this.today) {
            "Yes"
        } else {
            "No"
        }
    }

    @Then("I should be told {string}")
    fun assertDay(expectedAnswer: String?) {
        Assert.assertEquals(expectedAnswer, actualAnswer)
    }
}