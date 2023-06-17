package me.projectzero.domain.common.valueobjects;

import lombok.experimental.UtilityClass;

@UtilityClass
public class HibernateFilter {

    public class SoftDelete implements Nested {

        public static final String NAME = "softDelete";
        public static final String CONDITION = "deleted = :isDeleted";
        public static final String PARAM_0_NAME = "isDeleted";
        private static final String[] PARAM_NAMES = new String[]{PARAM_0_NAME};

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        public String[] getParamNames() {
            return PARAM_NAMES;
        }
    }

    /**
     * The base interface for the nested static classes of {@link HibernateFilter}.
     * To apply a filter, filter properties can be retrieved by using the abstract methods of this interface.
     */
    public interface Nested {
        String getName();

        String[] getParamNames();
    }

}
