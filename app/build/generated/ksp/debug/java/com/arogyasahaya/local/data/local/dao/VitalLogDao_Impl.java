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
import com.arogyasahaya.local.data.local.entity.VitalLogEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Float;
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
public final class VitalLogDao_Impl implements VitalLogDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<VitalLogEntity> __insertionAdapterOfVitalLogEntity;

  private final EntityDeletionOrUpdateAdapter<VitalLogEntity> __deletionAdapterOfVitalLogEntity;

  private final EntityDeletionOrUpdateAdapter<VitalLogEntity> __updateAdapterOfVitalLogEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteById;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllVitalLogs;

  public VitalLogDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfVitalLogEntity = new EntityInsertionAdapter<VitalLogEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `vital_logs` (`id`,`date`,`blood_pressure_systolic`,`blood_pressure_diastolic`,`heart_rate`,`glucose_level`,`notes`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final VitalLogEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getDate());
        if (entity.getBloodPressureSystolic() == null) {
          statement.bindNull(3);
        } else {
          statement.bindLong(3, entity.getBloodPressureSystolic());
        }
        if (entity.getBloodPressureDiastolic() == null) {
          statement.bindNull(4);
        } else {
          statement.bindLong(4, entity.getBloodPressureDiastolic());
        }
        if (entity.getHeartRate() == null) {
          statement.bindNull(5);
        } else {
          statement.bindLong(5, entity.getHeartRate());
        }
        if (entity.getGlucoseLevel() == null) {
          statement.bindNull(6);
        } else {
          statement.bindDouble(6, entity.getGlucoseLevel());
        }
        statement.bindString(7, entity.getNotes());
      }
    };
    this.__deletionAdapterOfVitalLogEntity = new EntityDeletionOrUpdateAdapter<VitalLogEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `vital_logs` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final VitalLogEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfVitalLogEntity = new EntityDeletionOrUpdateAdapter<VitalLogEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `vital_logs` SET `id` = ?,`date` = ?,`blood_pressure_systolic` = ?,`blood_pressure_diastolic` = ?,`heart_rate` = ?,`glucose_level` = ?,`notes` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final VitalLogEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getDate());
        if (entity.getBloodPressureSystolic() == null) {
          statement.bindNull(3);
        } else {
          statement.bindLong(3, entity.getBloodPressureSystolic());
        }
        if (entity.getBloodPressureDiastolic() == null) {
          statement.bindNull(4);
        } else {
          statement.bindLong(4, entity.getBloodPressureDiastolic());
        }
        if (entity.getHeartRate() == null) {
          statement.bindNull(5);
        } else {
          statement.bindLong(5, entity.getHeartRate());
        }
        if (entity.getGlucoseLevel() == null) {
          statement.bindNull(6);
        } else {
          statement.bindDouble(6, entity.getGlucoseLevel());
        }
        statement.bindString(7, entity.getNotes());
        statement.bindLong(8, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM vital_logs WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllVitalLogs = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM vital_logs";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final VitalLogEntity vitalLog,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfVitalLogEntity.insertAndReturnId(vitalLog);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertAll(final List<VitalLogEntity> vitalLogs,
      final Continuation<? super List<Long>> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<List<Long>>() {
      @Override
      @NonNull
      public List<Long> call() throws Exception {
        __db.beginTransaction();
        try {
          final List<Long> _result = __insertionAdapterOfVitalLogEntity.insertAndReturnIdsList(vitalLogs);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final VitalLogEntity vitalLog,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfVitalLogEntity.handle(vitalLog);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final VitalLogEntity vitalLog,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfVitalLogEntity.handle(vitalLog);
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
  public Object deleteAllVitalLogs(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllVitalLogs.acquire();
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
          __preparedStmtOfDeleteAllVitalLogs.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<VitalLogEntity>> getAllVitalLogs() {
    final String _sql = "SELECT * FROM vital_logs ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"vital_logs"}, new Callable<List<VitalLogEntity>>() {
      @Override
      @NonNull
      public List<VitalLogEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfBloodPressureSystolic = CursorUtil.getColumnIndexOrThrow(_cursor, "blood_pressure_systolic");
          final int _cursorIndexOfBloodPressureDiastolic = CursorUtil.getColumnIndexOrThrow(_cursor, "blood_pressure_diastolic");
          final int _cursorIndexOfHeartRate = CursorUtil.getColumnIndexOrThrow(_cursor, "heart_rate");
          final int _cursorIndexOfGlucoseLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "glucose_level");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final List<VitalLogEntity> _result = new ArrayList<VitalLogEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final VitalLogEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final Integer _tmpBloodPressureSystolic;
            if (_cursor.isNull(_cursorIndexOfBloodPressureSystolic)) {
              _tmpBloodPressureSystolic = null;
            } else {
              _tmpBloodPressureSystolic = _cursor.getInt(_cursorIndexOfBloodPressureSystolic);
            }
            final Integer _tmpBloodPressureDiastolic;
            if (_cursor.isNull(_cursorIndexOfBloodPressureDiastolic)) {
              _tmpBloodPressureDiastolic = null;
            } else {
              _tmpBloodPressureDiastolic = _cursor.getInt(_cursorIndexOfBloodPressureDiastolic);
            }
            final Integer _tmpHeartRate;
            if (_cursor.isNull(_cursorIndexOfHeartRate)) {
              _tmpHeartRate = null;
            } else {
              _tmpHeartRate = _cursor.getInt(_cursorIndexOfHeartRate);
            }
            final Float _tmpGlucoseLevel;
            if (_cursor.isNull(_cursorIndexOfGlucoseLevel)) {
              _tmpGlucoseLevel = null;
            } else {
              _tmpGlucoseLevel = _cursor.getFloat(_cursorIndexOfGlucoseLevel);
            }
            final String _tmpNotes;
            _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            _item = new VitalLogEntity(_tmpId,_tmpDate,_tmpBloodPressureSystolic,_tmpBloodPressureDiastolic,_tmpHeartRate,_tmpGlucoseLevel,_tmpNotes);
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
  public Object getVitalLogById(final long id,
      final Continuation<? super VitalLogEntity> $completion) {
    final String _sql = "SELECT * FROM vital_logs WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<VitalLogEntity>() {
      @Override
      @Nullable
      public VitalLogEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfBloodPressureSystolic = CursorUtil.getColumnIndexOrThrow(_cursor, "blood_pressure_systolic");
          final int _cursorIndexOfBloodPressureDiastolic = CursorUtil.getColumnIndexOrThrow(_cursor, "blood_pressure_diastolic");
          final int _cursorIndexOfHeartRate = CursorUtil.getColumnIndexOrThrow(_cursor, "heart_rate");
          final int _cursorIndexOfGlucoseLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "glucose_level");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final VitalLogEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final Integer _tmpBloodPressureSystolic;
            if (_cursor.isNull(_cursorIndexOfBloodPressureSystolic)) {
              _tmpBloodPressureSystolic = null;
            } else {
              _tmpBloodPressureSystolic = _cursor.getInt(_cursorIndexOfBloodPressureSystolic);
            }
            final Integer _tmpBloodPressureDiastolic;
            if (_cursor.isNull(_cursorIndexOfBloodPressureDiastolic)) {
              _tmpBloodPressureDiastolic = null;
            } else {
              _tmpBloodPressureDiastolic = _cursor.getInt(_cursorIndexOfBloodPressureDiastolic);
            }
            final Integer _tmpHeartRate;
            if (_cursor.isNull(_cursorIndexOfHeartRate)) {
              _tmpHeartRate = null;
            } else {
              _tmpHeartRate = _cursor.getInt(_cursorIndexOfHeartRate);
            }
            final Float _tmpGlucoseLevel;
            if (_cursor.isNull(_cursorIndexOfGlucoseLevel)) {
              _tmpGlucoseLevel = null;
            } else {
              _tmpGlucoseLevel = _cursor.getFloat(_cursorIndexOfGlucoseLevel);
            }
            final String _tmpNotes;
            _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            _result = new VitalLogEntity(_tmpId,_tmpDate,_tmpBloodPressureSystolic,_tmpBloodPressureDiastolic,_tmpHeartRate,_tmpGlucoseLevel,_tmpNotes);
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
  public Flow<VitalLogEntity> getVitalLogByIdFlow(final long id) {
    final String _sql = "SELECT * FROM vital_logs WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"vital_logs"}, new Callable<VitalLogEntity>() {
      @Override
      @Nullable
      public VitalLogEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfBloodPressureSystolic = CursorUtil.getColumnIndexOrThrow(_cursor, "blood_pressure_systolic");
          final int _cursorIndexOfBloodPressureDiastolic = CursorUtil.getColumnIndexOrThrow(_cursor, "blood_pressure_diastolic");
          final int _cursorIndexOfHeartRate = CursorUtil.getColumnIndexOrThrow(_cursor, "heart_rate");
          final int _cursorIndexOfGlucoseLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "glucose_level");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final VitalLogEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final Integer _tmpBloodPressureSystolic;
            if (_cursor.isNull(_cursorIndexOfBloodPressureSystolic)) {
              _tmpBloodPressureSystolic = null;
            } else {
              _tmpBloodPressureSystolic = _cursor.getInt(_cursorIndexOfBloodPressureSystolic);
            }
            final Integer _tmpBloodPressureDiastolic;
            if (_cursor.isNull(_cursorIndexOfBloodPressureDiastolic)) {
              _tmpBloodPressureDiastolic = null;
            } else {
              _tmpBloodPressureDiastolic = _cursor.getInt(_cursorIndexOfBloodPressureDiastolic);
            }
            final Integer _tmpHeartRate;
            if (_cursor.isNull(_cursorIndexOfHeartRate)) {
              _tmpHeartRate = null;
            } else {
              _tmpHeartRate = _cursor.getInt(_cursorIndexOfHeartRate);
            }
            final Float _tmpGlucoseLevel;
            if (_cursor.isNull(_cursorIndexOfGlucoseLevel)) {
              _tmpGlucoseLevel = null;
            } else {
              _tmpGlucoseLevel = _cursor.getFloat(_cursorIndexOfGlucoseLevel);
            }
            final String _tmpNotes;
            _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            _result = new VitalLogEntity(_tmpId,_tmpDate,_tmpBloodPressureSystolic,_tmpBloodPressureDiastolic,_tmpHeartRate,_tmpGlucoseLevel,_tmpNotes);
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
  public Flow<List<VitalLogEntity>> getVitalLogsForLast7Days(final long since) {
    final String _sql = "SELECT * FROM vital_logs WHERE date >= ? ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, since);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"vital_logs"}, new Callable<List<VitalLogEntity>>() {
      @Override
      @NonNull
      public List<VitalLogEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfBloodPressureSystolic = CursorUtil.getColumnIndexOrThrow(_cursor, "blood_pressure_systolic");
          final int _cursorIndexOfBloodPressureDiastolic = CursorUtil.getColumnIndexOrThrow(_cursor, "blood_pressure_diastolic");
          final int _cursorIndexOfHeartRate = CursorUtil.getColumnIndexOrThrow(_cursor, "heart_rate");
          final int _cursorIndexOfGlucoseLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "glucose_level");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final List<VitalLogEntity> _result = new ArrayList<VitalLogEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final VitalLogEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final Integer _tmpBloodPressureSystolic;
            if (_cursor.isNull(_cursorIndexOfBloodPressureSystolic)) {
              _tmpBloodPressureSystolic = null;
            } else {
              _tmpBloodPressureSystolic = _cursor.getInt(_cursorIndexOfBloodPressureSystolic);
            }
            final Integer _tmpBloodPressureDiastolic;
            if (_cursor.isNull(_cursorIndexOfBloodPressureDiastolic)) {
              _tmpBloodPressureDiastolic = null;
            } else {
              _tmpBloodPressureDiastolic = _cursor.getInt(_cursorIndexOfBloodPressureDiastolic);
            }
            final Integer _tmpHeartRate;
            if (_cursor.isNull(_cursorIndexOfHeartRate)) {
              _tmpHeartRate = null;
            } else {
              _tmpHeartRate = _cursor.getInt(_cursorIndexOfHeartRate);
            }
            final Float _tmpGlucoseLevel;
            if (_cursor.isNull(_cursorIndexOfGlucoseLevel)) {
              _tmpGlucoseLevel = null;
            } else {
              _tmpGlucoseLevel = _cursor.getFloat(_cursorIndexOfGlucoseLevel);
            }
            final String _tmpNotes;
            _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            _item = new VitalLogEntity(_tmpId,_tmpDate,_tmpBloodPressureSystolic,_tmpBloodPressureDiastolic,_tmpHeartRate,_tmpGlucoseLevel,_tmpNotes);
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
  public Flow<List<VitalLogEntity>> getVitalLogsBetweenDates(final long startDate,
      final long endDate) {
    final String _sql = "SELECT * FROM vital_logs WHERE date BETWEEN ? AND ? ORDER BY date ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startDate);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endDate);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"vital_logs"}, new Callable<List<VitalLogEntity>>() {
      @Override
      @NonNull
      public List<VitalLogEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfBloodPressureSystolic = CursorUtil.getColumnIndexOrThrow(_cursor, "blood_pressure_systolic");
          final int _cursorIndexOfBloodPressureDiastolic = CursorUtil.getColumnIndexOrThrow(_cursor, "blood_pressure_diastolic");
          final int _cursorIndexOfHeartRate = CursorUtil.getColumnIndexOrThrow(_cursor, "heart_rate");
          final int _cursorIndexOfGlucoseLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "glucose_level");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final List<VitalLogEntity> _result = new ArrayList<VitalLogEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final VitalLogEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final Integer _tmpBloodPressureSystolic;
            if (_cursor.isNull(_cursorIndexOfBloodPressureSystolic)) {
              _tmpBloodPressureSystolic = null;
            } else {
              _tmpBloodPressureSystolic = _cursor.getInt(_cursorIndexOfBloodPressureSystolic);
            }
            final Integer _tmpBloodPressureDiastolic;
            if (_cursor.isNull(_cursorIndexOfBloodPressureDiastolic)) {
              _tmpBloodPressureDiastolic = null;
            } else {
              _tmpBloodPressureDiastolic = _cursor.getInt(_cursorIndexOfBloodPressureDiastolic);
            }
            final Integer _tmpHeartRate;
            if (_cursor.isNull(_cursorIndexOfHeartRate)) {
              _tmpHeartRate = null;
            } else {
              _tmpHeartRate = _cursor.getInt(_cursorIndexOfHeartRate);
            }
            final Float _tmpGlucoseLevel;
            if (_cursor.isNull(_cursorIndexOfGlucoseLevel)) {
              _tmpGlucoseLevel = null;
            } else {
              _tmpGlucoseLevel = _cursor.getFloat(_cursorIndexOfGlucoseLevel);
            }
            final String _tmpNotes;
            _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            _item = new VitalLogEntity(_tmpId,_tmpDate,_tmpBloodPressureSystolic,_tmpBloodPressureDiastolic,_tmpHeartRate,_tmpGlucoseLevel,_tmpNotes);
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
  public Flow<List<VitalLogEntity>> getRecentVitalLogs(final int limit) {
    final String _sql = "SELECT * FROM vital_logs ORDER BY date DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"vital_logs"}, new Callable<List<VitalLogEntity>>() {
      @Override
      @NonNull
      public List<VitalLogEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfBloodPressureSystolic = CursorUtil.getColumnIndexOrThrow(_cursor, "blood_pressure_systolic");
          final int _cursorIndexOfBloodPressureDiastolic = CursorUtil.getColumnIndexOrThrow(_cursor, "blood_pressure_diastolic");
          final int _cursorIndexOfHeartRate = CursorUtil.getColumnIndexOrThrow(_cursor, "heart_rate");
          final int _cursorIndexOfGlucoseLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "glucose_level");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final List<VitalLogEntity> _result = new ArrayList<VitalLogEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final VitalLogEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final Integer _tmpBloodPressureSystolic;
            if (_cursor.isNull(_cursorIndexOfBloodPressureSystolic)) {
              _tmpBloodPressureSystolic = null;
            } else {
              _tmpBloodPressureSystolic = _cursor.getInt(_cursorIndexOfBloodPressureSystolic);
            }
            final Integer _tmpBloodPressureDiastolic;
            if (_cursor.isNull(_cursorIndexOfBloodPressureDiastolic)) {
              _tmpBloodPressureDiastolic = null;
            } else {
              _tmpBloodPressureDiastolic = _cursor.getInt(_cursorIndexOfBloodPressureDiastolic);
            }
            final Integer _tmpHeartRate;
            if (_cursor.isNull(_cursorIndexOfHeartRate)) {
              _tmpHeartRate = null;
            } else {
              _tmpHeartRate = _cursor.getInt(_cursorIndexOfHeartRate);
            }
            final Float _tmpGlucoseLevel;
            if (_cursor.isNull(_cursorIndexOfGlucoseLevel)) {
              _tmpGlucoseLevel = null;
            } else {
              _tmpGlucoseLevel = _cursor.getFloat(_cursorIndexOfGlucoseLevel);
            }
            final String _tmpNotes;
            _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            _item = new VitalLogEntity(_tmpId,_tmpDate,_tmpBloodPressureSystolic,_tmpBloodPressureDiastolic,_tmpHeartRate,_tmpGlucoseLevel,_tmpNotes);
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
  public Flow<VitalLogEntity> getLatestVitalLog() {
    final String _sql = "SELECT * FROM vital_logs ORDER BY date DESC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"vital_logs"}, new Callable<VitalLogEntity>() {
      @Override
      @Nullable
      public VitalLogEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfBloodPressureSystolic = CursorUtil.getColumnIndexOrThrow(_cursor, "blood_pressure_systolic");
          final int _cursorIndexOfBloodPressureDiastolic = CursorUtil.getColumnIndexOrThrow(_cursor, "blood_pressure_diastolic");
          final int _cursorIndexOfHeartRate = CursorUtil.getColumnIndexOrThrow(_cursor, "heart_rate");
          final int _cursorIndexOfGlucoseLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "glucose_level");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final VitalLogEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final Integer _tmpBloodPressureSystolic;
            if (_cursor.isNull(_cursorIndexOfBloodPressureSystolic)) {
              _tmpBloodPressureSystolic = null;
            } else {
              _tmpBloodPressureSystolic = _cursor.getInt(_cursorIndexOfBloodPressureSystolic);
            }
            final Integer _tmpBloodPressureDiastolic;
            if (_cursor.isNull(_cursorIndexOfBloodPressureDiastolic)) {
              _tmpBloodPressureDiastolic = null;
            } else {
              _tmpBloodPressureDiastolic = _cursor.getInt(_cursorIndexOfBloodPressureDiastolic);
            }
            final Integer _tmpHeartRate;
            if (_cursor.isNull(_cursorIndexOfHeartRate)) {
              _tmpHeartRate = null;
            } else {
              _tmpHeartRate = _cursor.getInt(_cursorIndexOfHeartRate);
            }
            final Float _tmpGlucoseLevel;
            if (_cursor.isNull(_cursorIndexOfGlucoseLevel)) {
              _tmpGlucoseLevel = null;
            } else {
              _tmpGlucoseLevel = _cursor.getFloat(_cursorIndexOfGlucoseLevel);
            }
            final String _tmpNotes;
            _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            _result = new VitalLogEntity(_tmpId,_tmpDate,_tmpBloodPressureSystolic,_tmpBloodPressureDiastolic,_tmpHeartRate,_tmpGlucoseLevel,_tmpNotes);
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
  public Flow<Float> getAverageSystolicBP(final long since) {
    final String _sql = "SELECT AVG(blood_pressure_systolic) FROM vital_logs WHERE date >= ? AND blood_pressure_systolic IS NOT NULL";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, since);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"vital_logs"}, new Callable<Float>() {
      @Override
      @Nullable
      public Float call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Float _result;
          if (_cursor.moveToFirst()) {
            final Float _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getFloat(0);
            }
            _result = _tmp;
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
  public Flow<Float> getAverageDiastolicBP(final long since) {
    final String _sql = "SELECT AVG(blood_pressure_diastolic) FROM vital_logs WHERE date >= ? AND blood_pressure_diastolic IS NOT NULL";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, since);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"vital_logs"}, new Callable<Float>() {
      @Override
      @Nullable
      public Float call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Float _result;
          if (_cursor.moveToFirst()) {
            final Float _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getFloat(0);
            }
            _result = _tmp;
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
  public Flow<Float> getAverageHeartRate(final long since) {
    final String _sql = "SELECT AVG(heart_rate) FROM vital_logs WHERE date >= ? AND heart_rate IS NOT NULL";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, since);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"vital_logs"}, new Callable<Float>() {
      @Override
      @Nullable
      public Float call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Float _result;
          if (_cursor.moveToFirst()) {
            final Float _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getFloat(0);
            }
            _result = _tmp;
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
  public Flow<Float> getAverageGlucoseLevel(final long since) {
    final String _sql = "SELECT AVG(glucose_level) FROM vital_logs WHERE date >= ? AND glucose_level IS NOT NULL";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, since);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"vital_logs"}, new Callable<Float>() {
      @Override
      @Nullable
      public Float call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Float _result;
          if (_cursor.moveToFirst()) {
            final Float _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getFloat(0);
            }
            _result = _tmp;
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
  public Flow<Integer> getVitalLogCount() {
    final String _sql = "SELECT COUNT(*) FROM vital_logs";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"vital_logs"}, new Callable<Integer>() {
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
