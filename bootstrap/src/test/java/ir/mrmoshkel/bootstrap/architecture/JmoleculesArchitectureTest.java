package ir.mrmoshkel.bootstrap.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import org.jmolecules.archunit.JMoleculesDddRules;

@AnalyzeClasses(
        packages = "ir.mrmoshkel",
        importOptions = ImportOption.DoNotIncludeTests.class
)
public class JmoleculesArchitectureTest {

    @ArchTest
    void verifyDddRules(JavaClasses classes) {
        JMoleculesDddRules.all().check(classes);
    }
}