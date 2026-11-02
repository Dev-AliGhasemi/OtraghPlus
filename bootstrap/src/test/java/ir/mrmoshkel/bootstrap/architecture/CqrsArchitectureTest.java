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
public class CqrsArchitectureTest {

    @ArchTest
    static final ArchRule commandMustNotDependOnQuery =
            noClasses()
                    .that().resideInAPackage("..core.command..")
                    .should().dependOnClassesThat()
                    .resideInAPackage("..core.query..");

    @ArchTest
    static final ArchRule queryMustNotDependOnCommand =
            noClasses()
                    .that().resideInAPackage("..core.query..")
                    .should().dependOnClassesThat()
                    .resideInAPackage("..core.command..");
}