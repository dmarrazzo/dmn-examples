package com.examples;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieRuntimeFactory;
import org.kie.dmn.api.core.DMNContext;
import org.kie.dmn.api.core.DMNModel;
import org.kie.dmn.api.core.DMNResult;
import org.kie.dmn.api.core.DMNRuntime;

/**
 * DMNTest
 */
public class DMNTest {

    static DMNRuntime dmnRuntime;

    @BeforeClass
    public static void beforeAll() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kcontainer = ks.getKieClasspathContainer();
        dmnRuntime = KieRuntimeFactory.of(kcontainer.getKieBase()).get(DMNRuntime.class);
    }

    @Test
    public void test1() {
        DMNModel dmnModel = dmnRuntime.getModels().get(0);
        DMNContext context = dmnRuntime.newContext();

        context.set("start date", LocalDate.of(2020, 1, 1));
        context.set("end date", LocalDate.of(2020,6,1));

        DMNResult result = dmnRuntime.evaluateAll(dmnModel, context);

        assertEquals(BigDecimal.valueOf(2), result.getDecisionResultByName("decision").getResult());
        
        System.out.println(result);
    }
}