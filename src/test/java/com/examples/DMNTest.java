package com.examples;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;

import com.examples.flow.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.jbpm.workflow.instance.WorkflowProcessInstance;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieRuntimeFactory;
import org.kie.api.runtime.KieSession;
import org.kie.dmn.api.core.DMNContext;
import org.kie.dmn.api.core.DMNModel;
import org.kie.dmn.api.core.DMNResult;
import org.kie.dmn.api.core.DMNRuntime;

/**
 * DMNTest
 */
public class DMNTest {

    static DMNRuntime dmnRuntime;
    private static KieContainer kcontainer;

    @BeforeClass
    public static void beforeAll() {
        KieServices ks = KieServices.Factory.get();
        kcontainer = ks.getKieClasspathContainer();
        dmnRuntime = KieRuntimeFactory.of(kcontainer.getKieBase()).get(DMNRuntime.class);
    }

    @Test
    public void test1() {
        DMNModel dmnModel = dmnRuntime.getModels().get(0);
        DMNContext context = dmnRuntime.newContext();

        context.set("start date", LocalDate.of(2020, 1, 1));
        context.set("end date", LocalDate.of(2020, 6, 1));

        DMNResult result = dmnRuntime.evaluateAll(dmnModel, context);

        assertEquals(BigDecimal.valueOf(2), result.getDecisionResultByName("decision").getResult());

        System.out.println(result);
    }

    @Test
    public void test2() {
        KieSession ksession = kcontainer.newKieSession();

        var customer = new Customer();
        customer.setCartAmount(200);
        customer.setInitialRating(5);

        var variables = new HashMap<String, Object>();
        variables.put("customer", customer);

        // ksession.addEventListener(new DebugProcessEventListener());
        WorkflowProcessInstance pi = (WorkflowProcessInstance) ksession.startProcess("dmn-examples.dmn-flow",
                variables);
        Object o = pi.getVariable("customer");

        // Jackson casting: temporary workaround before v7.57
        ObjectMapper mapper = new ObjectMapper();
        customer = mapper.convertValue(o, Customer.class);

        assertEquals(10.0, customer.getInitialRating(), 0.0);
    }
}