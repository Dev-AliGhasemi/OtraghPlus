package ir.mrmoshkel.bootstrap.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.core.domain.JavaClass.Predicates.resideInAnyPackage;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static com.tngtech.archunit.library.Architectures.onionArchitecture;

@AnalyzeClasses(
        packages = "ir.mrmoshkel",
        importOptions = ImportOption.DoNotIncludeTests.class
)
class LayerArchitectureTest {

    @ArchTest
    static final ArchRule onlyBootstrapCanBeDependedOnByBootstrap =
            noClasses()
                    .that()
                    .resideOutsideOfPackage("..bootstrap..")
                    .should()
                    .dependOnClassesThat()
                    .resideInAPackage("..bootstrap..");

    @ArchTest
    static final ArchRule onionArchitecture =
            onionArchitecture()
                    .domainModels("..core.domain..", "..common.core..")
                    .applicationServices("..core.command..", "..core.query..")
                    .adapter("infrastructure", "..infrastructure..")
                    // Ignore all dependencies originating from Bootstrap.
                    .ignoreDependency(
                            resideInAnyPackage(
                                    "ir.mrmoshkel.bootstrap.."
                            ),
                            resideInAnyPackage("ir.mrmoshkel..")
                    )
                    .withOptionalLayers(true);

    @ArchTest
    static final ArchRule cleanArchitecture =
            layeredArchitecture()
                    .consideringOnlyDependenciesInAnyPackage("ir.mrmoshkel..")
                    .layer("Domain").definedBy("..core.domain..","..common.core..")
                    .layer("Application").definedBy("..core.command..", "..core.query..")
                    .layer("Infrastructure").definedBy("..infrastructure..")
                    .layer("Bootstrap").definedBy("..bootstrap..")

                    // Domain must not depend on outer layers
                    .whereLayer("Domain")
                    .mayNotAccessAnyLayer()

                    // Application can access Domain
                    .whereLayer("Application")
                    .mayOnlyAccessLayers("Domain")

                    // Infrastructure implements application/domain requirements
                    .whereLayer("Infrastructure")
                    .mayOnlyAccessLayers("Application", "Domain")

                    // Bootstrap wires everything together
                    .whereLayer("Bootstrap")
                    .mayOnlyAccessLayers(
                            "Application",
                            "Domain",
                            "Infrastructure"
                    );
}