package me.diced.serverstats.common.prometheus.metrics.world;

import me.diced.serverstats.common.prometheus.Metric;
import me.diced.serverstats.common.prometheus.MetricsManager;

import java.io.StringWriter;
import java.util.Map;
import java.util.TreeMap;

public class EntityCountPerWorld extends Metric<TreeMap<String, Integer>> {

    public EntityCountPerWorld(String name, MetricsManager manager) {
        super(name, "counter", manager, new TreeMap<>());
    }

    @Override
    public void run() {
        this.collector = this.manager.getEntityCountPerWorld();
    }

    @Override
    public String formatPrometheus() {
        StringWriter writer = new StringWriter();

        var last = this.collector.lastEntry();

        for (Map.Entry<String, Integer> entry : this.collector.entrySet()) {
            writer.write(this.name + "{world=\"" + entry.getKey() + "\"} " + entry.getValue() + (last.getKey().equals(entry.getKey()) ? "" : '\n'));
        }

        return writer.toString();
    }

    @Override
    public boolean enabled() {
        return this.manager.config.pushable.entityCount;
    }

    @Override
    public boolean isExemplar() {
        return true;
    }
}
