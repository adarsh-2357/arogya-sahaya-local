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
import com.arogyasahaya.local.data.local.entity.HealthRecord;
import java.lang.Class;
import java.lang.Exception;
import java.lang.IllegalStateException;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class HealthRecordDao_Impl implements HealthRecordDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<HealthRecord> __insertionAdapterOfHealthRecord;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<HealthRecord> __deletionAdapterOfHealthRecord;

  private final EntityDeletionOrUpdateAdapter<HealthRecord> __updateAdapterOfHealthRecord;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllRecords;

  public HealthRecordDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfHealthRecord = new EntityInsertionAdapter<HealthRecord>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `health_records` (`id`,`systolic_bp`,`diastolic_bp`,`heart_rate`,`blood_sugar`,`temperature`,`notes`,`recorded_at`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final HealthRecord entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getSystolicBp());
        statement.bindLong(3, entity.getDiastolicBp());
        statement.bindLong(4, entity.getHeartRate());
        statement.bindDouble(5, entity.getBloodSugar());
        statement.bindDouble(6, entity.getTemperature());
        statement.bindString(7, entity.getNotes());
        final Long _tmp = __converters.dateToTimestamp(entity.getRecordedAt());
        if (_tmp == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, _tmp);
        }
      }
    };
    this.__deletionAdapterOfHealthRecord = new EntityDeletionOrUpdateAdapter<HealthRecord>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `health_records` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final HealthRecord entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfHealthRecord = new EntityDeletionOrUpdateAdapter<HealthRecord>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `health_records` SET `id` = ?,`systolic_bp` = ?,`diastolic_bp` = ?,`heart_rate` = ?,`blood_sugar` = ?,`temperature` = ?,`notes` = ?,`recorded_at` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final HealthRecord entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getSystolicBp());
        statement.bindLong(3, entity.getDiastolicBp());
        statement.bindLong(4, entity.getHeartRate());
        statement.bindDouble(5, entity.getBloodSugar());
        statement.bindDouble(6, entity.getTemperature());
        statement.bindString(7, entity.getNotes());
        final Long _tmp = __converters.dateToTimestamp(entity.getRecordedAt());
        if (_tmp == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, _tmp);
        }
        statement.bindLong(9, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteAllRecords = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM health_records";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final HealthRecord record, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfHealthRecord.insertAndReturnId(record);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final HealthRecord record, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfHealthRecord.handle(record);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final HealthRecord record, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfHealthRecord.handle(record);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAllRecords(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllRecords.acquire();
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
          __preparedStmtOfDeleteAllRecords.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<HealthRecord>> getAllRecords() {
    final String _sql = "SELECT * FROM health_records ORDER BY recorded_at DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"health_records"}, new Callable<List<HealthRecord>>() {
      @Override
      @NonNull
      public List<HealthRecord> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSystolicBp = CursorUtil.getColumnIndexOrThrow(_cursor, "systolic_bp");
          final int _cursorIndexOfDiastolicBp = CursorUtil.getColumnIndexOrThrow(_cursor, "diastolic_bp");
          final int _cursorIndexOfHeartRate = CursorUtil.getColumnIndexOrThrow(_cursor, "heart_rate");
          final int _cursorIndexOfBloodSugar = CursorUtil.getColumnIndexOrThrow(_cursor, "blood_sugar");
          final int _cursorIndexOfTemperature = CursorUtil.getColumnIndexOrThrow(_cursor, "temperature");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfRecordedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "recorded_at");
          final List<HealthRecord> _result = new ArrayList<HealthRecord>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final HealthRecord _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final int _tmpSystolicBp;
            _tmpSystolicBp = _cursor.getInt(_cursorIndexOfSystolicBp);
            final int _tmpDiastolicBp;
            _tmpDiastolicBp = _cursor.getInt(_cursorIndexOfDiastolicBp);
            final int _tmpHeartRate;
            _tmpHeartRate = _cursor.getInt(_cursorIndexOfHeartRate);
            final float _tmpBloodSugar;
            _tmpBloodSugar = _cursor.getFloat(_cursorIndexOfBloodSugar);
            final float _tmpTemperature;
            _tmpTemperature = _cursor.getFloat(_cursorIndexOfTemperature);
            final String _tmpNotes;
            _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            final Date _tmpRecordedAt;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfRecordedAt)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfRecordedAt);
            }
            final Date _tmp_1 = __converters.fromTimestamp(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.util.Date', but it was NULL.");
            } else {
              _tmpRecordedAt = _tmp_1;
            }
            _item = new HealthRecord(_tmpId,_tmpSystolicBp,_tmpDiastolicBp,_tmpHeartRate,_tmpBloodSugar,_tmpTemperature,_tmpNotes,_tmpRecordedAt);
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
  public Object getRecordById(final long id, final Continuation<? super HealthRecord> $completion) {
    final String _sql = "SELECT * FROM health_records WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<HealthRecord>() {
      @Override
      @Nullable
      public HealthRecord call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSystolicBp = CursorUtil.getColumnIndexOrThrow(_cursor, "systolic_bp");
          final int _cursorIndexOfDiastolicBp = CursorUtil.getColumnIndexOrThrow(_cursor, "diastolic_bp");
          final int _cursorIndexOfHeartRate = CursorUtil.getColumnIndexOrThrow(_cursor, "heart_rate");
          final int _cursorIndexOfBloodSugar = CursorUtil.getColumnIndexOrThrow(_cursor, "blood_sugar");
          final int _cursorIndexOfTemperature = CursorUtil.getColumnIndexOrThrow(_cursor, "temperature");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfRecordedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "recorded_at");
          final HealthRecord _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final int _tmpSystolicBp;
            _tmpSystolicBp = _cursor.getInt(_cursorIndexOfSystolicBp);
            final int _tmpDiastolicBp;
            _tmpDiastolicBp = _cursor.getInt(_cursorIndexOfDiastolicBp);
            final int _tmpHeartRate;
            _tmpHeartRate = _cursor.getInt(_cursorIndexOfHeartRate);
            final float _tmpBloodSugar;
            _tmpBloodSugar = _cursor.getFloat(_cursorIndexOfBloodSugar);
            final float _tmpTemperature;
            _tmpTemperature = _cursor.getFloat(_cursorIndexOfTemperature);
            final String _tmpNotes;
            _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            final Date _tmpRecordedAt;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfRecordedAt)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfRecordedAt);
            }
            final Date _tmp_1 = __converters.fromTimestamp(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.util.Date', but it was NULL.");
            } else {
              _tmpRecordedAt = _tmp_1;
            }
            _result = new HealthRecord(_tmpId,_tmpSystolicBp,_tmpDiastolicBp,_tmpHeartRate,_tmpBloodSugar,_tmpTemperature,_tmpNotes,_tmpRecordedAt);
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
  public Flow<List<HealthRecord>> getRecordsBetween(final long startDate, final long endDate) {
    final String _sql = "SELECT * FROM health_records WHERE recorded_at BETWEEN ? AND ? ORDER BY recorded_at ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startDate);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endDate);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"health_records"}, new Callable<List<HealthRecord>>() {
      @Override
      @NonNull
      public List<HealthRecord> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSystolicBp = CursorUtil.getColumnIndexOrThrow(_cursor, "systolic_bp");
          final int _cursorIndexOfDiastolicBp = CursorUtil.getColumnIndexOrThrow(_cursor, "diastolic_bp");
          final int _cursorIndexOfHeartRate = CursorUtil.getColumnIndexOrThrow(_cursor, "heart_rate");
          final int _cursorIndexOfBloodSugar = CursorUtil.getColumnIndexOrThrow(_cursor, "blood_sugar");
          final int _cursorIndexOfTemperature = CursorUtil.getColumnIndexOrThrow(_cursor, "temperature");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfRecordedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "recorded_at");
          final List<HealthRecord> _result = new ArrayList<HealthRecord>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final HealthRecord _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final int _tmpSystolicBp;
            _tmpSystolicBp = _cursor.getInt(_cursorIndexOfSystolicBp);
            final int _tmpDiastolicBp;
            _tmpDiastolicBp = _cursor.getInt(_cursorIndexOfDiastolicBp);
            final int _tmpHeartRate;
            _tmpHeartRate = _cursor.getInt(_cursorIndexOfHeartRate);
            final float _tmpBloodSugar;
            _tmpBloodSugar = _cursor.getFloat(_cursorIndexOfBloodSugar);
            final float _tmpTemperature;
            _tmpTemperature = _cursor.getFloat(_cursorIndexOfTemperature);
            final String _tmpNotes;
            _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            final Date _tmpRecordedAt;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfRecordedAt)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfRecordedAt);
            }
            final Date _tmp_1 = __converters.fromTimestamp(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.util.Date', but it was NULL.");
            } else {
              _tmpRecordedAt = _tmp_1;
            }
            _item = new HealthRecord(_tmpId,_tmpSystolicBp,_tmpDiastolicBp,_tmpHeartRate,_tmpBloodSugar,_tmpTemperature,_tmpNotes,_tmpRecordedAt);
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
  public Object getRecordCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM health_records";
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
