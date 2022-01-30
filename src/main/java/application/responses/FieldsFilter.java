package application.responses;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@NoArgsConstructor
public class FieldsFilter {

    public String filter(Object dto, Set<String> fields) {
        ObjectMapper objectMapper = new ObjectMapper();
        if (fields == null || fields.isEmpty()) {
            try {
                return objectMapper.writeValueAsString(dto);
            } catch (JsonProcessingException e) {
                return e.getMessage();
            }
        }

        objectMapper.addMixIn(Object.class, MyFilterMixin.class);
        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("myfilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
        try {
            return objectMapper.writer(filterProvider).writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            return e.getMessage();
        }
    }

    @JsonFilter("myfilter")
    public static class MyFilterMixin {
    }

    private static class MyFilter extends SimpleBeanPropertyFilter {
        private final Set<String> properties;

        public MyFilter(Set<String> properties) {
            super();
            this.properties = properties;
        }

        @Override
        protected boolean include(BeanPropertyWriter writer) {
            return !this.properties.contains(writer.getName());
        }

        @Override
        protected boolean include(PropertyWriter writer) {
            return !this.properties.contains(writer.getName());
        }
    }
}
