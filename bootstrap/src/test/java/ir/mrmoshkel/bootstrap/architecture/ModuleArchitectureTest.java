package ir.mrmoshkel.bootstrap.architecture;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;

import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

@AnalyzeClasses(
        packages = "ir.mrmoshkel",
        importOptions = ImportOption.DoNotIncludeTests.class
)
class ModuleArchitectureTest {

    @ArchTest
    static final com.tngtech.archunit.lang.ArchRule modulesMustNotHaveCycles =
            slices()
                    .matching("ir.mrmoshkel.(*)..")
                    .should()
                    .beFreeOfCycles();
}