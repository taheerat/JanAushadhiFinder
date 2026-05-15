package com.janaushadhi.finder.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.janaushadhi.finder.data.local.entity.MedicineEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class MedicineDao_Impl implements MedicineDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<MedicineEntity> __insertionAdapterOfMedicineEntity;

  private final SharedSQLiteStatement __preparedStmtOfClearAll;

  public MedicineDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMedicineEntity = new EntityInsertionAdapter<MedicineEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `medicines` (`id`,`brandName`,`genericName`,`saltComposition`,`brandPrice`,`genericPrice`,`category`,`manufacturer`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MedicineEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getBrandName());
        statement.bindString(3, entity.getGenericName());
        statement.bindString(4, entity.getSaltComposition());
        statement.bindDouble(5, entity.getBrandPrice());
        statement.bindDouble(6, entity.getGenericPrice());
        statement.bindString(7, entity.getCategory());
        statement.bindString(8, entity.getManufacturer());
      }
    };
    this.__preparedStmtOfClearAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM medicines";
        return _query;
      }
    };
  }

  @Override
  public Object insertAll(final List<MedicineEntity> medicines,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfMedicineEntity.insert(medicines);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object clearAll(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearAll.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfClearAll.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getAllMedicines(final Continuation<? super List<MedicineEntity>> $completion) {
    final String _sql = "SELECT * FROM medicines";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<MedicineEntity>>() {
      @Override
      @NonNull
      public List<MedicineEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfBrandName = CursorUtil.getColumnIndexOrThrow(_cursor, "brandName");
          final int _cursorIndexOfGenericName = CursorUtil.getColumnIndexOrThrow(_cursor, "genericName");
          final int _cursorIndexOfSaltComposition = CursorUtil.getColumnIndexOrThrow(_cursor, "saltComposition");
          final int _cursorIndexOfBrandPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "brandPrice");
          final int _cursorIndexOfGenericPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "genericPrice");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfManufacturer = CursorUtil.getColumnIndexOrThrow(_cursor, "manufacturer");
          final List<MedicineEntity> _result = new ArrayList<MedicineEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MedicineEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpBrandName;
            _tmpBrandName = _cursor.getString(_cursorIndexOfBrandName);
            final String _tmpGenericName;
            _tmpGenericName = _cursor.getString(_cursorIndexOfGenericName);
            final String _tmpSaltComposition;
            _tmpSaltComposition = _cursor.getString(_cursorIndexOfSaltComposition);
            final double _tmpBrandPrice;
            _tmpBrandPrice = _cursor.getDouble(_cursorIndexOfBrandPrice);
            final double _tmpGenericPrice;
            _tmpGenericPrice = _cursor.getDouble(_cursorIndexOfGenericPrice);
            final String _tmpCategory;
            _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            final String _tmpManufacturer;
            _tmpManufacturer = _cursor.getString(_cursorIndexOfManufacturer);
            _item = new MedicineEntity(_tmpId,_tmpBrandName,_tmpGenericName,_tmpSaltComposition,_tmpBrandPrice,_tmpGenericPrice,_tmpCategory,_tmpManufacturer);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM medicines";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object searchMedicines(final String query,
      final Continuation<? super List<MedicineEntity>> $completion) {
    final String _sql = "\n"
            + "        SELECT * FROM medicines \n"
            + "        WHERE \n"
            + "            brandName LIKE '%' || ? || '%' OR\n"
            + "            genericName LIKE '%' || ? || '%' OR\n"
            + "            saltComposition LIKE '%' || ? || '%'\n"
            + "        ORDER BY brandName ASC\n"
            + "        LIMIT 50\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    _statement.bindString(_argIndex, query);
    _argIndex = 2;
    _statement.bindString(_argIndex, query);
    _argIndex = 3;
    _statement.bindString(_argIndex, query);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<MedicineEntity>>() {
      @Override
      @NonNull
      public List<MedicineEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfBrandName = CursorUtil.getColumnIndexOrThrow(_cursor, "brandName");
          final int _cursorIndexOfGenericName = CursorUtil.getColumnIndexOrThrow(_cursor, "genericName");
          final int _cursorIndexOfSaltComposition = CursorUtil.getColumnIndexOrThrow(_cursor, "saltComposition");
          final int _cursorIndexOfBrandPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "brandPrice");
          final int _cursorIndexOfGenericPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "genericPrice");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfManufacturer = CursorUtil.getColumnIndexOrThrow(_cursor, "manufacturer");
          final List<MedicineEntity> _result = new ArrayList<MedicineEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MedicineEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpBrandName;
            _tmpBrandName = _cursor.getString(_cursorIndexOfBrandName);
            final String _tmpGenericName;
            _tmpGenericName = _cursor.getString(_cursorIndexOfGenericName);
            final String _tmpSaltComposition;
            _tmpSaltComposition = _cursor.getString(_cursorIndexOfSaltComposition);
            final double _tmpBrandPrice;
            _tmpBrandPrice = _cursor.getDouble(_cursorIndexOfBrandPrice);
            final double _tmpGenericPrice;
            _tmpGenericPrice = _cursor.getDouble(_cursorIndexOfGenericPrice);
            final String _tmpCategory;
            _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            final String _tmpManufacturer;
            _tmpManufacturer = _cursor.getString(_cursorIndexOfManufacturer);
            _item = new MedicineEntity(_tmpId,_tmpBrandName,_tmpGenericName,_tmpSaltComposition,_tmpBrandPrice,_tmpGenericPrice,_tmpCategory,_tmpManufacturer);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<MedicineEntity>> getTopMedicines() {
    final String _sql = "SELECT * FROM medicines ORDER BY brandName ASC LIMIT 20";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"medicines"}, new Callable<List<MedicineEntity>>() {
      @Override
      @NonNull
      public List<MedicineEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfBrandName = CursorUtil.getColumnIndexOrThrow(_cursor, "brandName");
          final int _cursorIndexOfGenericName = CursorUtil.getColumnIndexOrThrow(_cursor, "genericName");
          final int _cursorIndexOfSaltComposition = CursorUtil.getColumnIndexOrThrow(_cursor, "saltComposition");
          final int _cursorIndexOfBrandPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "brandPrice");
          final int _cursorIndexOfGenericPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "genericPrice");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfManufacturer = CursorUtil.getColumnIndexOrThrow(_cursor, "manufacturer");
          final List<MedicineEntity> _result = new ArrayList<MedicineEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MedicineEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpBrandName;
            _tmpBrandName = _cursor.getString(_cursorIndexOfBrandName);
            final String _tmpGenericName;
            _tmpGenericName = _cursor.getString(_cursorIndexOfGenericName);
            final String _tmpSaltComposition;
            _tmpSaltComposition = _cursor.getString(_cursorIndexOfSaltComposition);
            final double _tmpBrandPrice;
            _tmpBrandPrice = _cursor.getDouble(_cursorIndexOfBrandPrice);
            final double _tmpGenericPrice;
            _tmpGenericPrice = _cursor.getDouble(_cursorIndexOfGenericPrice);
            final String _tmpCategory;
            _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            final String _tmpManufacturer;
            _tmpManufacturer = _cursor.getString(_cursorIndexOfManufacturer);
            _item = new MedicineEntity(_tmpId,_tmpBrandName,_tmpGenericName,_tmpSaltComposition,_tmpBrandPrice,_tmpGenericPrice,_tmpCategory,_tmpManufacturer);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
