package edu.union.adt.graph.tests.walkerl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import edu.union.adt.graph.Graph;
import edu.union.adt.graph.GraphFactory;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

public class walkerlTests {

    private Graph<String> tester1;
    private Graph<String> tester2;

    @Before
    public void setUp() {
        tester1 = GraphFactory.<String>createGraph();
        tester2 = GraphFactory.<String>createGraph();
    }

}
