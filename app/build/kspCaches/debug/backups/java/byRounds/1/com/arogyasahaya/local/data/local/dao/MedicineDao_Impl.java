package com.arogyasahaya.local.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.arogyasahaya.local.data.local.Converters;
import com.arogyasahaya.local.data.local.entity.MedicineEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
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

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<MedicineEntity> __deletionAdapterOfMedicineEntity;

  private final EntityDeletionOrUpdateAdapter<MedicineEntity> __updateAdapterOfMedicineEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteById;

  private final SharedSQLiteStatement __preparedStmtOfUpdateActiveStatus;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllMedicines;

  public MedicineDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMedicineEntity = new EntityInsertionAdapter<MedicineEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `medicines` (`id`,`name`,`dosage`,`times_of_day`,`start_date`,`is_active`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MedicineEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getName());
        statement.bindString(3, entity.getDosage());
        final String _tmp = __converters.fromStringList(entity.getTimesOfDay());
        statement.bindString(4, _tmp);
        statement.bindLong(5, entity.getStartDate());
        final int _tmp_1 = entity.isActive() ? 1 : 0;
        statement.bindLong(6, _tmp_1);
      }
    };
    this.__deletionAdapterOfMedicineEntity = new EntityDeletionOrUpdateAdapter<MedicineEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `medicines` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MedicineEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfMedicineEntity = new EntityDeletionOrUpdateAdapter<MedicineEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `medicines` SET `id` = ?,`name` = ?,`dosage` = ?,`times_of_day` = ?,`start_date` = ?,`is_active` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MedicineEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getName());
        statement.bindString(3, entity.getDosage());
        final String _tmp = __converters.fromStringList(entity.getTimesOfDay());
        statement.bindString(4, _tmp);
        statement.bindLong(5, entity.getStartDate());
        final int _tmp_1 = entity.isActive() ? 1 : 0;
        statement.bindLong(6, _tmp_1);
        statement.bindLong(7, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM medicines WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateActiveStatus = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE medicines SET is_active = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllMedicines = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM medicines";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final MedicineEntity medicine,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfMedicineEntity.insertAndReturnId(medicine);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertAll(final List<MedicineEntity> medicines,
      final Continuation<? super List<Long>> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<List<Long>>() {
      @Override
      @NonNull
      public List<Long> call() throws Exception {
        __db.beginTransaction();
        try {
          final List<Long> _result = __insertionAdapterOfMedicineEntity.insertAndReturnIdsList(medicines);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final MedicineEntity medicine,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfMedicineEntity.handle(medicine);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final MedicineEntity medicine,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfMedicineEntity.handle(medicine);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteById(final long id, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteById.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, id);
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
          __preparedStmtOfDeleteById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object updateActiveStatus(final long id, final boolean isActive,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateActiveStatus.acquire();
        int _argIndex = 1;
        final int _tmp = isActive ? 1 : 0;
        _stmt.bindLong(_argIndex, _tmp);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, id);
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
          __preparedStmtOfUpdateActiveStatus.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAllMedicines(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllMedicines.acquire();
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
          __preparedStmtOfDeleteAllMedicines.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<MedicineEntity>> getAllMedicines() {
    final String _sql = "SELECT * FROM medicines ORDER BY start_date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"medicines"}, new Callable<List<MedicineEntity>>() {
      @Override
      @NonNull
      public List<MedicineEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDosage = CursorUtil.getColumnIndexOrThrow(_cursor, "dosage");
          final int _cursorIndexOfTimesOfDay = CursorUtil.getColumnIndexOrThrow(_cursor, "times_of_day");
          final int _cursorIndexOfStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "start_date");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "is_active");
          final List<MedicineEntity> _result = new ArrayList<MedicineEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MedicineEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpDosage;
            _tmpDosage = _cursor.getString(_cursorIndexOfDosage);
            final List<String> _tmpTimesOfDay;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfTimesOfDay);
            _tmpTimesOfDay = __converters.toStringList(_tmp);
            final long _tmpStartDate;
            _tmpStartDate = _cursor.getLong(_cursorIndexOfStartDate);
            final boolean _tmpIsActive;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp_1 != 0;
            _item = new MedicineEntity(_tmpId,_tmpName,_tmpDosage,_tmpTimesOfDay,_tmpStartDate,_tmpIsActive);
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

  @Override
  public Flow<List<MedicineEntity>> getActiveMedicines() {
    final String _sql = "SELECT * FROM medicines WHERE is_active = 1 ORDER BY name ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"medicines"}, new Callable<List<MedicineEntity>>() {
      @Override
      @NonNull
      public List<MedicineEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDosage = CursorUtil.getColumnIndexOrThrow(_cursor, "dosage");
          final int _cursorIndexOfTimesOfDay = CursorUtil.getColumnIndexOrThrow(_cursor, "times_of_day");
          final int _cursorIndexOfStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "start_date");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "is_active");
          final List<MedicineEntity> _result = new ArrayList<MedicineEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MedicineEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpDosage;
            _tmpDosage = _cursor.getString(_cursorIndexOfDosage);
            final List<String> _tmpTimesOfDay;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfTimesOfDay);
            _tmpTimesOfDay = __converters.toStringList(_tmp);
            final long _tmpStartDate;
            _tmpStartDate = _cursor.getLong(_cursorIndexOfStartDate);
            final boolean _tmpIsActive;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp_1 != 0;
            _item = new MedicineEntity(_tmpId,_tmpName,_tmpDosage,_tmpTimesOfDay,_tmpStartDate,_tmpIsActive);
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

  @Override
  public Flow<List<MedicineEntity>> getInactiveMedicines() {
    final String _sql = "SELECT * FROM medicines WHERE is_active = 0 ORDER BY name ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"medicines"}, new Callable<List<MedicineEntity>>() {
      @Override
      @NonNull
      public List<MedicineEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDosage = CursorUtil.getColumnIndexOrThrow(_cursor, "dosage");
          final int _cursorIndexOfTimesOfDay = CursorUtil.getColumnIndexOrThrow(_cursor, "times_of_day");
          final int _cursorIndexOfStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "start_date");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "is_active");
          final List<MedicineEntity> _result = new ArrayList<MedicineEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MedicineEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpDosage;
            _tmpDosage = _cursor.getString(_cursorIndexOfDosage);
            final List<String> _tmpTimesOfDay;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfTimesOfDay);
            _tmpTimesOfDay = __converters.toStringList(_tmp);
            final long _tmpStartDate;
            _tmpStartDate = _cursor.getLong(_cursorIndexOfStartDate);
            final boolean _tmpIsActive;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp_1 != 0;
            _item = new MedicineEntity(_tmpId,_tmpName,_tmpDosage,_tmpTimesOfDay,_tmpStartDate,_tmpIsActive);
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

  @Override
  public Object getMedicineById(final long id,
      final Continuation<? super MedicineEntity> $completion) {
    final String _sql = "SELECT * FROM medicines WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<MedicineEntity>() {
      @Override
      @Nullable
      public MedicineEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDosage = CursorUtil.getColumnIndexOrThrow(_cursor, "dosage");
          final int _cursorIndexOfTimesOfDay = CursorUtil.getColumnIndexOrThrow(_cursor, "times_of_day");
          final int _cursorIndexOfStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "start_date");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "is_active");
          final MedicineEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpDosage;
            _tmpDosage = _cursor.getString(_cursorIndexOfDosage);
            final List<String> _tmpTimesOfDay;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfTimesOfDay);
            _tmpTimesOfDay = __converters.toStringList(_tmp);
            final long _tmpStartDate;
            _tmpStartDate = _cursor.getLong(_cursorIndexOfStartDate);
            final boolean _tmpIsActive;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp_1 != 0;
            _result = new MedicineEntity(_tmpId,_tmpName,_tmpDosage,_tmpTimesOfDay,_tmpStartDate,_tmpIsActive);
          } else {
            _result = null;
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
  public Flow<MedicineEntity> getMedicineByIdFlow(final long id) {
    final String _sql = "SELECT * FROM medicines WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"medicines"}, new Callable<MedicineEntity>() {
      @Override
      @Nullable
      public MedicineEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDosage = CursorUtil.getColumnIndexOrThrow(_cursor, "dosage");
          final int _cursorIndexOfTimesOfDay = CursorUtil.getColumnIndexOrThrow(_cursor, "times_of_day");
          final int _cursorIndexOfStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "start_date");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "is_active");
          final MedicineEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpDosage;
            _tmpDosage = _cursor.getString(_cursorIndexOfDosage);
            final List<String> _tmpTimesOfDay;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfTimesOfDay);
            _tmpTimesOfDay = __converters.toStringList(_tmp);
            final long _tmpStartDate;
            _tmpStartDate = _cursor.getLong(_cursorIndexOfStartDate);
            final boolean _tmpIsActive;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp_1 != 0;
            _result = new MedicineEntity(_tmpId,_tmpName,_tmpDosage,_tmpTimesOfDay,_tmpStartDate,_tmpIsActive);
          } else {
            _result = null;
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

  @Override
  public Flow<List<MedicineEntity>> searchMedicines(final String query) {
    final String _sql = "SELECT * FROM medicines WHERE name LIKE '%' || ? || '%' ORDER BY name ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, query);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"medicines"}, new Callable<List<MedicineEntity>>() {
      @Override
      @NonNull
      public List<MedicineEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDosage = CursorUtil.getColumnIndexOrThrow(_cursor, "dosage");
          final int _cursorIndexOfTimesOfDay = CursorUtil.getColumnIndexOrThrow(_cursor, "times_of_day");
          final int _cursorIndexOfStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "start_date");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "is_active");
          final List<MedicineEntity> _result = new ArrayList<MedicineEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MedicineEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpDosage;
            _tmpDosage = _cursor.getString(_cursorIndexOfDosage);
            final List<String> _tmpTimesOfDay;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfTimesOfDay);
            _tmpTimesOfDay = __converters.toStringList(_tmp);
            final long _tmpStartDate;
            _tmpStartDate = _cursor.getLong(_cursorIndexOfStartDate);
            final boolean _tmpIsActive;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp_1 != 0;
            _item = new MedicineEntity(_tmpId,_tmpName,_tmpDosage,_tmpTimesOfDay,_tmpStartDate,_tmpIsActive);
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

  @Override
  public Flow<Integer> getActiveMedicineCount() {
    final String _sql = "SELECT COUNT(*) FROM medicines WHERE is_active = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"medicines"}, new Callable<Integer>() {
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
