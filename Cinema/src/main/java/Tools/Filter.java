package Tools;

import lombok.Getter;

import java.util.HashMap;

public class Filter {
    @Getter
    private HashMap<String, Object> filters;

    public Filter() {
        this.filters = new HashMap<>();
    }

    public void addField(String key, Object value) {
        filters.put(key, value);
    }

}
