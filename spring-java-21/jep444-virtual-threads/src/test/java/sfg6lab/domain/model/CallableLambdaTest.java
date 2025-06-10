//: sfg6lab.domain.model.CallableTest.java

package sfg6lab.domain.model;


import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Supplier;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


class CallableLambdaTest {

    static final String FILE_PATH = "README.md";
    
    static Callable<String> fileContentCallable;
    static Supplier<String> fileContentSupplier;
    
    @BeforeAll
    static void beforeAll() {
        
        fileContentCallable = () -> {
            /*
             * Files::readString method throws IOException
             * It's allowed to throw exceptions here without requiring
             * explicit try-catch blocks. Because:
             * When implementing Callable with a lambda expression,
             * the lambda automatically acquires the exception-throwing capability
             * of the functional interface-method that it's implementing.
             * The compiler understands that the lambda is implementing a method
             * that can throw checked exceptions, so it allows the lambda body to
             * throw those exceptions without requiring explicit try-catch blocks.
             *
             */
            return Files.readString(Paths.get(FILE_PATH));
        };
        
        fileContentSupplier = () -> {
            /*
             * Supplier::get
             */
            try {
                return Files.readString(Paths.get(FILE_PATH));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
    
    @Test
    void lambda_Able_To_Implements_Callable_Without_Try_Catch()
            throws ExecutionException, InterruptedException {
    
        // Given
        Future<String> callableFuture = Executors.newVirtualThreadPerTaskExecutor()
                .submit(fileContentCallable);
        
        int contentLengthFromSupplier = fileContentSupplier.get().length();
        
        // When
        int contentLengthFromCallable = callableFuture.get().length();
        
        // Then
        assertThat(contentLengthFromCallable).isEqualTo(contentLengthFromSupplier);
    }
    
    @Test
    void parallel_Reduce() {
    
        // Given
        List<String> names = List.of("duck", "flamingo", "pelican");
        
        // When
        var length = names.parallelStream().parallel()
                .reduce(0,
                        (l, c2) -> l + c2.length(),
                        (s1, s2) -> s1 + s2);
        
        // Then
        assertThat(length).isEqualTo(19);
    }
    
} /// :~