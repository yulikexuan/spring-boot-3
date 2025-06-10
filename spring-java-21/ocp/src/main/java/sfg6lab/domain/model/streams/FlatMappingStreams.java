//: sfg6lab.domain.model.streams.FlatMapingStreams.java

package sfg6lab.domain.model.streams;


import static java.util.stream.Collectors.groupingByConcurrent;

import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Stream;


class FlatMappingStreams {
    
    public static void main(String[] args) {
        
        var cats = Stream.of(
                "leopard", "lynx", "ocelot", "puma")
                .parallel();
        
        var bears = Stream.of(
                "panda", "grizzly", "polar")
                .parallel();
        
        Stream<Stream<String>> source = Stream.of(cats, bears);
        
        Stream<String> data = source.flatMap(s -> s);
        
        ConcurrentMap<Boolean, List<String>> result =
                data.collect(groupingByConcurrent(s -> !s.startsWith("p")));
        
        System.out.println(result.get(true).size());
        System.out.println(result.get(false).size());
    }
    
} /// :~