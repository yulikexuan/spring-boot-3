//: sfg6lab.domain.model.SmsTemplateProcessor.java

package sfg6lab.domain.model;


import lombok.NonNull;


enum SmsTemplateProcessor implements
        StringTemplate.Processor<SmsMessage, RuntimeException> {

    SMS, RAW;

    static final int MAX_LENGTH = 256;

    SmsTemplateProcessor() {
    }

    @Override
    public SmsMessage process(@NonNull StringTemplate stringTemplate)
            throws RuntimeException {

        var msg = stringTemplate.interpolate();
        var length = msg.length();

        if (msg.length() > MAX_LENGTH) {
            var errMsg = STR.">>> The length of sms is too long - \{length}";
            throw new RuntimeException(errMsg);
        }

        return new SmsMessage(msg);
    }

} ///:~