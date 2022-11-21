package eventbus.simple;

import com.phakel.EventBusFactory;
import com.phakel.config.EventBusConfig;
import com.phakel.eventbus.EventBus;
import com.phakel.eventbus.impl.SyncEventBus;
import event.TestEvent;
import listener.TestListener;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @author EvanLuo42
 */
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3)
@Measurement(iterations = 10, time = 5)
@Threads(8)
@Fork(2)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class SimpleEventBusBenchmark {
    private EventBus eventBus;
    private TestListener testListener;

    @Setup
    public void initSimpleEventBus() {
        var eventBusConfig = new EventBusConfig(EventBusConfig.EventBusType.SYNC, "eventbus.simple.SimpleEventBusBenchmark");
        eventBus = EventBusFactory.createEventBusFromConfig(eventBusConfig);
        assert eventBus != null;
        testListener = new TestListener();
        for (int i = 0; i < 10000; i++) eventBus.registerListener(testListener);
    }

    @Benchmark
    public String benchmarkSimpleEventBus() {
        eventBus.broadcast(new TestEvent());
        var name = testListener.eventName;
        testListener.eventName = "";
        return name;
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SyncEventBus.class.getSimpleName())
                .build();
        new Runner(opt).run();
    }
}
