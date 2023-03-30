package ca.etsmtl.log240.financej;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.uispec4j.*;
import org.uispec4j.interception.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class CategoriesTest extends FinancejAbstractTest {
    private Table categoriesTable;
    private Trigger ret;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    @AfterClass
    public void tearDownClass() throws Exception {
        //closing connection to database
        exitButton.click();
    }
    private class ValidCategoriesHandler extends WindowHandler {
        private String name, description;
        private int budget;
        @Override
        public Trigger process(Window window) throws Exception {
            return null;
        }

    }
}