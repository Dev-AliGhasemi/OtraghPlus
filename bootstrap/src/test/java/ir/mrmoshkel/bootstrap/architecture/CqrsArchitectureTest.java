package ir.mrmoshkel.bootstrap.architecture;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(
        packages = "ir.mrmoshkel",
        importOptions = ImportOption.DoNotIncludeTests.class
)
class CqrsArchitectureTest {

    @ArchTest
    static final ArchRule commandMustNotDependOnQuery =
            noClasses()
                    .that().resideInAPackage("..application.command..")
                    .should().dependOnClassesThat()
                    .resideInAPackage("..application.query..");

    @ArchTest
    static final ArchRule queryMustNotDependOnCommand =
            noClasses()
                    .that().resideInAPackage("..application.query..")
                    .should().dependOnClassesThat()
                    .resideInAPackage("..application.command..");
}