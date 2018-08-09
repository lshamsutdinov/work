package app;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import app.models.MainTableModelTest;

public class StartJUnit {

    public static void main(String[] args) throws Exception {
        JUnitCore runner = new JUnitCore();

        Result result = runner.run(MainTableModelTest.class);

        System.out.println("----------");
        System.out.println();
        System.out.println("DeleteTableModelTest");
        System.out.println("run tests: " + result.getRunCount());
        System.out.println("failed tests: " + result.getFailureCount());
        System.out.println("ignored tests: " + result.getIgnoreCount());
        System.out.println("success: " + result.wasSuccessful());
    }

}
