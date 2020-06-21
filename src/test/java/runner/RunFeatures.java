package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.testng.AbstractTestNGCucumberTests;
@CucumberOptions(features= "src/test/java/features/bigbasket.feature",
glue="steps",
snippets= SnippetType.CAMELCASE,
monochrome=true,
plugin= {"pretty", "html:target"})

public class RunFeatures extends AbstractTestNGCucumberTests{

}
