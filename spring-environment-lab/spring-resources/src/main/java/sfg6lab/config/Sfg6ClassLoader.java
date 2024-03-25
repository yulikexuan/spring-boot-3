//: sfg6lab.config.ClassBuilder


package sfg6lab.config;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ClassUtils;


public interface Sfg6ClassLoader {

    Class<?> classForName(String className);

    static Sfg6ClassLoader of(final ClassLoader classLoader) {

        ClassLoader cl = (classLoader == null) ?
                Sfg6ClassLoader.class.getClassLoader() : classLoader;

        return ClassLoaderImpl.of(cl);
    }

}


@Slf4j
@RequiredArgsConstructor(staticName = "of", access = AccessLevel.PACKAGE)
class ClassLoaderImpl implements Sfg6ClassLoader {

    private final ClassLoader classLoader;

    @Override
    public Class<?> classForName(String className) {
        try {
            return ClassUtils.forName(className, this.classLoader);
        } catch (Exception e) {
            log.error(">>> Failed to load class {} with class loader {}",
                    className, this.classLoader);
            return null;
        }
    }

}///:~