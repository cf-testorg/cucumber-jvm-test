package io.cucumber.core.runner;

import gherkin.pickles.Argument;
import gherkin.pickles.PickleLocation;
import gherkin.pickles.PickleStep;
import io.cucumber.core.api.Scenario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class AmbiguousStepDefinitionMatchsTest {

    private final PickleStep pickleStep = new PickleStep("", Collections.<Argument>emptyList(), Collections.<PickleLocation>emptyList());
    private final AmbiguousStepDefinitionsException e = new AmbiguousStepDefinitionsException(pickleStep, Collections.<PickleStepDefinitionMatch>emptyList());
    public final AmbiguousPickleStepDefinitionsMatch match = new AmbiguousPickleStepDefinitionsMatch("uri", mock(PickleStep.class), e);

    @Test
    public void throws_ambiguous_step_definitions_exception_when_run() {
        final Executable testMethod = () -> match.runStep(mock(Scenario.class));
        final AmbiguousStepDefinitionsException actualThrown = assertThrows(AmbiguousStepDefinitionsException.class, testMethod);
        assertThat("Unexpected exception message", actualThrown.getMessage(), is(equalTo(
            "\"\" matches more than one step definition:\n"
        )));
    }

    @Test
    public void throws_ambiguous_step_definitions_exception_when_dry_run() {
        final Executable testMethod = () -> match.dryRunStep(mock(Scenario.class));
        final AmbiguousStepDefinitionsException actualThrown = assertThrows(AmbiguousStepDefinitionsException.class, testMethod);
        assertThat("Unexpected exception message", actualThrown.getMessage(), is(equalTo(
            "\"\" matches more than one step definition:\n"
        )));
    }

}