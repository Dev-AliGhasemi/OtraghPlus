package ir.mrmoshkel.bootstrap;

import ir.mrmoshkel.bootstrap.architecture.*;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SelectClasses({
        CqrsArchitectureTest.class,
        JmoleculesArchitectureTest.class,
        LayerArchitectureTest.class,
        ModuleArchitectureTest.class,
        ContractArchitectureTest.class
})
@SuiteDisplayName("Application Architecture Test")
public class ArchitectureTestSuite {
}
