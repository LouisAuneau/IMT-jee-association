package imta.controllers;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({HomeServletTest.class,
                     LoginServletTest.class,
                     RegisterServletTest.class
                    })
public class ServletTestSuite {
}
