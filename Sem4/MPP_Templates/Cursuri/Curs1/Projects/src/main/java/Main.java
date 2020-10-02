import decorator.DelayTaskRunner;
import decorator.PrinterTaskRunner;
import decorator.StrategyTaskRunner;
import decorator.TaskRunner;
import factory.Container;
import factory.TaskContainerFactory;
import model.*;
//import tests.Tests;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class Main {


    private static org.apache.logging.log4j.Logger log = LogManager.getLogger();

    public static void main(String[] args) {

       // Tests test = new Tests();
//        test.testSort();
//        test.messageTasks();
//        test.stackAndQueue();
//        test.testStrategyTaskRunner(args);
//        test.testPrinterTaskRunner(args);
   //     test.testDelayTaskRunner(args);

        log.traceEntry();
        log.info("helloooo wooooorld !!!!!!!!!!");
        log.traceExit();

    }
}
