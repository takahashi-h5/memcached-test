package jp.co.confrage.memcachedtest;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.stereotype.Component;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.exception.MemcachedException;

@Component
public class MemcachedAccessor {
    private final MemcachedClient memcachedClient;

    public MemcachedAccessor() throws IOException {
        XMemcachedClientBuilder builder =
                new XMemcachedClientBuilder("test.yjurzv.0001.apne1.cache.amazonaws.com:11211");
        builder.setOpTimeout(3000);
        builder.setConnectTimeout(160000L);
        this.memcachedClient = builder.build();
    }

    public String get() {
        String key = "default";
        try {
            key = (String)memcachedClient.get("key1");
        } catch (TimeoutException | InterruptedException | MemcachedException e) {
            e.printStackTrace();
            return "error";
        }
        return key;
    }

    public void set(final String value)  {
        try {
            memcachedClient.set("key1",3600, value);
        } catch (TimeoutException | InterruptedException | MemcachedException e) {
            e.printStackTrace();
        }
    }
}
