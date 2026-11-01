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
class PresentationArchitectureTest {

    @ArchTest
    static final ArchRule controllersMustNotAccessRepositories =
            noClasses()
                    .that().resideInAPackage("..presentation..")
                    .should().dependOnClassesThat()
                    .resideInAnyPackage(
                            "..infrastructure.persistence..",
                            "..domain.repository.."
                    );

    @ArchTest
    static final ArchRule controllersMustNotAccessJpaRepositories =
            noClasses()
                    .that().resideInAPackage("..presentation..")
                    .should().dependOnClassesThat()
                    .resideInAPackage("org.springframework.data.jpa.repository..");
}