package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.testng.AbstractTestNGCucumberTests;
@CucumberOptions(features="src/test/java/features/trivago.feature",
glue="steps",
snippets= SnippetType.CAMELCASE,
monochrome=true,
plugin= {"pretty", "html:target"})

public class RunTrivagoFeatures extends AbstractTestNGCucumberTests{

}
