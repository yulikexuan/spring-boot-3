//: sfg6lab.domain.model.DbConnectionFactory.java


package sfg6lab.domain.model;


public interface DbConnectionFactory {

    void init();

    static DbConnectionFactory productDbConnectionFactory(DbSource dbSource) {
        return ProductDbConnectionFactory.of(dbSource);
    }

    static DbConnectionFactory devDbConnectionFactory(DbSource dbSource) {
        return DevDbConnectionFactory.of(dbSource);
    }

    static DbConnectionFactory qaDbConnectionFactory(DbSource dbSource) {
        return QaDbConnectionFactory.of(dbSource);
    }

}///:~