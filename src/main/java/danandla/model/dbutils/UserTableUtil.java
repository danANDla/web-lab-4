package danandla.model.dbutils;

import danandla.model.entities.MyEntity;
import danandla.model.entities.Point;

import java.util.Collections;
import java.util.List;

public class UserTableUtil extends PostgreUtil{
    public UserTableUtil(String tableName) {
        super(tableName);
    }

    @Override
    public void cleartable() {
    }
}
