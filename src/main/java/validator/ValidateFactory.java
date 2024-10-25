package validator;

import util.ValidateType;

public class ValidateFactory {

    private static ValidateFactory instance;

    private ValidateFactory() {
    }

    public static ValidateFactory getInstance() {
        return instance == null ? instance = new ValidateFactory() : instance;
    }

    public <T extends SuperValidate> T getValidateType(ValidateType type) {
        switch (type) {

        }
        return null;
    }
}
