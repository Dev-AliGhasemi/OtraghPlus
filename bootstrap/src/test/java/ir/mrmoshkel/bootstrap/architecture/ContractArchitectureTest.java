package ir.mrmoshkel.bootstrap.architecture;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import ir.mrmoshkel.common.core.framework.command.BaseCommand;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(
        packages = "ir.mrmoshkel",
        importOptions = ImportOption.DoNotIncludeTests.class
)
public class ContractArchitectureTest {

    @ArchTest
    static final ArchRule controllersMustNotAccessRepositories =
            noClasses()
                    .that().resideInAPackage("..infrastructure.endpoint..")
                    .should().dependOnClassesThat()
                    .resideInAnyPackage(
                            "..infrastructure.persistence..",
                            "..core..repository.."
                    );

    @ArchTest
    static final ArchRule eachCommandClassShouldBeExtendBaseCommand =
            classes()
                    .that()
                    .haveSimpleNameEndingWith("Command")
                    .and()
                    .resideInAnyPackage("..core..")
                    .should()
                    .beAssignableTo(BaseCommand.class);
}