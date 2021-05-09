package cn.noahcode.blog.model.enums;

/**
 * @author NoahCode
 * @date 12/27/2020
 * @description
 */
public enum OptionType implements ValueEnum<Integer> {

    /**
     * internal option
     */
    INTERNAL(0),

    /**
     * custom option
     */
    CUSTOM(1);

    private final Integer value;

    OptionType(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
