package jp.co.confrage.memcachedtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MemcachedTestApplication {
	private MemcachedAccessor accessor;
	MemcachedTestApplication(MemcachedAccessor accessor) {
		this.accessor = accessor;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MemcachedTestApplication.class, args);
	}

	@GetMapping("/get")
    public String get() {
		
        return accessor.get();
    }

	@PostMapping("/post/{data}")
	public String post(@PathVariable @NonNull final String data) {
		accessor.set(data);
        return "post";
	}
}
