package cn.noahcode.blog.model.enums;

/**
 * @author NoahCode
 * @date 1/27/2021
 * @description
 */
public enum JournalType implements ValueEnum<Integer> {

    /**
     * Public type.
     */
    PUBLIC(1),

    /**
     * Intimate type.
     */
    INTIMATE(0);

    private final int value;

    JournalType(int value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
