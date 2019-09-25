package StockDatabase;

import android.provider.BaseColumns;

public class StockM_Master {
    private StockM_Master(){

    }
    protected static class StockMainActivity implements BaseColumns{
        public static final String TABLE_NAME = "Stock_Motherboard";
        public static final String COLUMN_NAME_ITEMCODE = "itemcode";
        public static final String COLUMN_NAME_MODEL = "model";
        public static final String COLUMN_NAME_BRAND = "brand";
        public static final String COLUMN_NAME_AVAILABILITY = "availability";

    }
}
