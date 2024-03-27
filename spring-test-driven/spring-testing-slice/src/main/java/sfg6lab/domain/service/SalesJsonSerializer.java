//: sfg6lab.domain.service.SalesJsonSerializer.java


package sfg6lab.domain.service;


import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.lang.NonNull;
import sfg6lab.domain.model.Sales;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static java.lang.String.format;
import static sfg6lab.domain.model.Sales.SalesData;


@JsonComponent
public class SalesJsonSerializer {

    public static class Serializer extends JsonSerializer<Sales> {

        @Override
        public void serialize(
                @NonNull final Sales sales,
                @NonNull final JsonGenerator out,
                @NonNull final SerializerProvider serializerProvider)
                throws IOException {

            out.writeStartObject();
            out.writeArrayFieldStart("salesData");
            for (SalesData dataElement : sales.salesData()) {
                out.writeStartObject();
                out.writeStringField("x",
                        format("%d-%02d", dataElement.year, dataElement.month));
                out.writeNumberField("y", dataElement.count);
                out.writeEndObject();
            }
            out.writeEndArray();
            out.writeEndObject();
        }

    }

    public static class Deserializer extends JsonDeserializer<Sales> {

        @Override
        public Sales deserialize(JsonParser jsonParser, DeserializationContext ctx)
                throws IOException {

            throw new UnsupportedEncodingException();
        }
    }

}///:~