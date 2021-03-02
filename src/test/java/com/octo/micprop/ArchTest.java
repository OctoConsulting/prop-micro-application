package com.octo.micprop;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.octo.micprop");

        noClasses()
            .that()
            .resideInAnyPackage("com.octo.micprop.service..")
            .or()
            .resideInAnyPackage("com.octo.micprop.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.octo.micprop.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
