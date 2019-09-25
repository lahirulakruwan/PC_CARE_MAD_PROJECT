package Database_company;

import android.provider.BaseColumns;

public final class EmployeeMaster {

    private EmployeeMaster() {
    }

    protected static class Employee implements BaseColumns {

        public static final String TABLE_NAME = "employees";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_AGE= "age";
        public static final String COLUMN_NAME_PHONE= "phone";
        public static final String COLUMN_NAME_EMAIL= "email";
        public static final String COLUMN_NAME_SALARY= "salary";



    }
}