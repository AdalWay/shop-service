package com.acmedia.shop;

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
            .importPackages("com.acmedia.shop");

        noClasses()
            .that()
            .resideInAnyPackage("com.acmedia.shop.service..")
            .or()
            .resideInAnyPackage("com.acmedia.shop.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.acmedia.shop.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
